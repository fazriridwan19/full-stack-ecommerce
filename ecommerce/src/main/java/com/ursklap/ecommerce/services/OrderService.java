package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.CheckoutRequest;
import com.ursklap.ecommerce.dto.requests.PaymentRequest;
import com.ursklap.ecommerce.dto.responses.OrderItemResponse;
import com.ursklap.ecommerce.dto.responses.OrderResponse;
import com.ursklap.ecommerce.models.*;
import com.ursklap.ecommerce.models.etc.StatusEnum;
import com.ursklap.ecommerce.repositories.*;
import com.ursklap.ecommerce.services.base.BaseService;
import com.ursklap.ecommerce.utils.ValidateProduct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class OrderService extends BaseService<Order, OrderRepository, Long> {
    private StatusRepository statusRepository;
    private CartService cartService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private OrderItemRepository orderItemRepository;
    private PaymentRepository paymentRepository;

    public List<Order> findAllByUserId(Long userId) {
        return this.orderRepository.findAllByUserId(userId);
    }

    public OrderResponse findByIdAsDto(Long id) {
        OrderResponse response = this.orderRepository
                .findByIdAsDto(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not found"));
        List<OrderItemResponse> orderItemResponse = this.orderItemRepository.findAllByOrderIdAsDto(id);
        response.setItems(orderItemResponse);
        return response;
    }

    @Transactional
    public void checkout(CheckoutRequest request, CustomUserDetails userDetails) {
        Integer totalAmount = 0;
        Status pending = this.statusRepository
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
                .currentStatus(pending)
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

        this.saveOrderHistory(order, pending, "Processing order to payment");
    }

    @Transactional
    public void payment(PaymentRequest request) {
        Order order = this.findById(request.getOrderId(), "Order is not found");
        Payment payment = this.paymentRepository.findByAccount(request.getAccount()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment method is not found"));
        Status shipment = this.statusRepository.findById(StatusEnum.SHIPMENT.getValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));

        if (!Objects.equals(order.getPayment(), payment)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Payment method is invalid");
        }

        if (!Objects.equals(request.getAmount(), order.getTotalAmount())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Request amount is not equals with total amount order");
        }

        order.setPaymentDate(LocalDate.now().toString());
        order.setPaymentTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        order.setCurrentStatus(shipment);
        this.orderRepository.save(order);

        this.saveOrderHistory(order, shipment, "Processing order to shipment");
    }

    @Transactional
    public void confirmation(Long orderId) {
        Order order = this.findById(orderId, "Order is not found");
        Status shipment = this.statusRepository.findById(StatusEnum.SHIPMENT.getValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));
        Status success = this.statusRepository.findById(StatusEnum.SUCCESS.getValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status is not found"));

        if (!Objects.equals(order.getCurrentStatus(), shipment)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This order is not on shipment process");
        }

        order.setCurrentStatus(success);
        this.orderRepository.save(order);

        this.saveOrderHistory(order, success, "Order complete");
    }

    private void saveOrderHistory(Order order, Status status, String message) {
        OrderHistory orderHistory = OrderHistory.builder()
                .order(order)
                .status(status)
                .userId(order.getUserId())
                .notes(message)
                .build();
        this.orderHistoryRepository.save(orderHistory);
    }
}
