package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.CheckoutRequest;
import com.ursklap.ecommerce.models.*;
import com.ursklap.ecommerce.models.etc.StatusEnum;
import com.ursklap.ecommerce.repositories.OrderHistoryRepository;
import com.ursklap.ecommerce.repositories.OrderRepository;
import com.ursklap.ecommerce.repositories.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private StatusRepository statusRepository;
    private CartService cartService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public void checkout(CartDetail cartDetail, String email) {
        Product product = cartDetail.getProduct();
        Status currentStatus = this.statusRepository
                .findById(StatusEnum.PENDING.getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));

        Order order = Order.builder()
                .email(email)
                .cartDetail(cartDetail)
                .currentStatus(currentStatus)
                .build();
        this.orderRepository.save(order);

        OrderHistory orderHistory = OrderHistory.builder()
                .order(order)
                .status(currentStatus)
                .email(email)
                .notes("Processing order to payment")
                .build();
        this.orderHistoryRepository.save(orderHistory);

        product.setStock(product.getStock() - cartDetail.getQuantity());
        product.setIsInStock(product.getStock() >= 1);
        this.productService.create(product);
    }

    private String validateCartDetail(CartDetail cartDetail) {
        if (this.orderRepository.existsByCartDetail(cartDetail)) {
            return "Cannot process cart detail with id " + cartDetail.getId() + " this item already proceed";
        }
        if (!cartDetail.getProduct().getIsInStock()) {
            return "Cannot process cart detail with id " + cartDetail.getId() + " product out of stock";
        }
        if (cartDetail.getQuantity() > cartDetail.getProduct().getStock()) {
            return "Cannot process cart detail with id " + cartDetail.getId() + " maximum quantity product is " + cartDetail.getProduct().getStock();
        }

        return null;
    }

    public String checkoutBulk(List<CheckoutRequest> requests) {
        List<String> errorMessages = new ArrayList<>();
        int successCounter = 0;
        for (CheckoutRequest request: requests) {
            CartDetail cartDetail = null;
            try {
                cartDetail = this.cartService.findCartDetailById(request.getCartDetailId());
            } catch (ResponseStatusException exception) {
                errorMessages.add(exception.getReason());
            }
            if (cartDetail != null) {
                String errorMessage = this.validateCartDetail(cartDetail);
                if (errorMessage != null) {
                    errorMessages.add(errorMessage);
                } else {
                    successCounter++;
                    this.checkout(cartDetail, request.getEmail());
                }
            }
        }
        if (successCounter == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, StringUtils.arrayToDelimitedString(errorMessages.toArray(), ", "));
        } else if (!errorMessages.isEmpty()) {
            return "Success with some error : " + StringUtils.arrayToDelimitedString(errorMessages.toArray(), ", ");
        }
        return "Success checkout products";
    }

    public void payment() {
        // TODO: create service for payment operations
    }
}
