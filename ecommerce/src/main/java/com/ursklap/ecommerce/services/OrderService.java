package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.CheckoutRequest;
import com.ursklap.ecommerce.models.*;
import com.ursklap.ecommerce.models.etc.StatusEnum;
import com.ursklap.ecommerce.repositories.OrderHistoryRepository;
import com.ursklap.ecommerce.repositories.OrderRepository;
import com.ursklap.ecommerce.repositories.StatusRepository;
import com.ursklap.ecommerce.threads.ThreadOrder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {
    private StatusRepository statusRepository;
    private CartService cartService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public void checkout(CheckoutRequest request) {
        CartDetail cartDetail = this.cartService.findCartDetailById(request.getCartDetailId());
        Product product = cartDetail.getProduct();
        Status currentStatus = this.statusRepository
                .findById(StatusEnum.PENDING.getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));
        if (!product.getIsInStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Product out of stock");
        }
        if (cartDetail.getQuantity() > product.getStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Maximum quantity product is " + product.getStock());
        }

        Order order = Order.builder()
                .email(request.getEmail())
                .cartDetail(cartDetail)
                .currentStatus(currentStatus)
                .build();
        this.orderRepository.save(order);

        OrderHistory orderHistory = OrderHistory.builder()
                .order(order)
                .status(currentStatus)
                .email(request.getEmail())
                .notes("Processing order to payment")
                .build();
        this.orderHistoryRepository.save(orderHistory);

        product.setStock(product.getStock() - cartDetail.getQuantity());
        product.setIsInStock(product.getStock() >= 1);
        this.productService.create(product);
    }

    public void checkoutBulk(List<CheckoutRequest> requests) {
        for (CheckoutRequest request: requests) {
            ThreadOrder threadOrder = new ThreadOrder(this, request);
            threadOrder.start();
            threadOrder.checkForException();
        }
    }

    public void payment() {
        // TODO: create service for payment operations
    }
}
