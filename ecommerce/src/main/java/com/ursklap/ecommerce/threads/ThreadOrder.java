package com.ursklap.ecommerce.threads;

import com.ursklap.ecommerce.dto.CheckoutRequest;
import com.ursklap.ecommerce.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@NoArgsConstructor
public class ThreadOrder extends Thread {
    private OrderService orderService;
    private CheckoutRequest request;
    private ResponseStatusException exception;

    public ThreadOrder(OrderService orderService, CheckoutRequest request) {
        this.orderService = orderService;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            this.orderService.checkout(request);
        } catch (ResponseStatusException exception) {
            // TODO : Catch exception from thread
            this.exception = exception;
        }
    }

    public void checkForException() {
        if (this.exception != null) {
            throw this.exception;
        }
    }
}
