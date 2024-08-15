package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.CheckoutRequest;
import com.ursklap.ecommerce.dto.responses.OrderItemResponse;
import com.ursklap.ecommerce.dto.responses.OrderResponse;
import com.ursklap.ecommerce.models.*;
import com.ursklap.ecommerce.models.etc.StatusEnum;
import com.ursklap.ecommerce.repositories.*;
import com.ursklap.ecommerce.utils.ValidateProduct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private StatusRepository statusRepository;
    private CartService cartService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private OrderItemRepository orderItemRepository;
    private PaymentRepository paymentRepository;

    @Transactional
    public Order checkout(CheckoutRequest request, CustomUserDetails userDetails) {
        Integer totalAmount = 0;
        Status currentStatus = this.statusRepository
                .findById(StatusEnum.PENDING.getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));
        Payment payment = this.paymentRepository
                .findById(request.getPaymentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment is not found"));
        Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .userId(userDetails.getCredential().getUser().getId())
                .orderDate(LocalDate.now().toString())
                .orderTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
                .currentStatus(currentStatus)
                .payment(payment)
                .build();
        order = this.orderRepository.save(order);

        for (Long cartDetailId: request.getCartDetailIds()) {
            CartDetail cartDetail = this.cartService.findCartDetailById(cartDetailId);
            ValidateProduct.validateAvailability(cartDetail.getProduct(), cartDetail.getQuantity());

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartDetail.getProduct())
                    .quantity(cartDetail.getQuantity())
                    .totalPrice(cartDetail.getTotalPrice())
                    .build();
            this.orderItemRepository.save(orderItem);

            Product product = cartDetail.getProduct();
            product.setStock(product.getStock() - cartDetail.getQuantity());
            product.setIsInStock(product.getStock() >= 1);
            this.productService.create(product);

            this.cartService.removeCartDetail(cartDetailId);

            totalAmount += orderItem.getTotalPrice();
        }

        order.setTotalAmount(totalAmount);
        this.orderRepository.save(order);

        OrderHistory orderHistory = OrderHistory.builder()
                .order(order)
                .status(currentStatus)
                .userId(order.getUserId())
                .notes("Processing order to payment")
                .build();
        this.orderHistoryRepository.save(orderHistory);

        return order;
    }

    public OrderResponse findByIdAsDto(Long id) {
        OrderResponse response = this.orderRepository
                .findByIdAsDto(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not found"));
        List<OrderItemResponse> orderItemResponse = this.orderItemRepository.findAllByOrderIdAsDto(id);
        response.setItems(orderItemResponse);
        return response;
    }

    public void payment() {
        // TODO: create service for payment operations
    }
}
