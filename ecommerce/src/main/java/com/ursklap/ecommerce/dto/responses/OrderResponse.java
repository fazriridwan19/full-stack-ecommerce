package com.ursklap.ecommerce.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String code;
    private String orderDate;
    private String orderTime;
    private String paymentType;
    private String paymentAccount;
    private String paymentName;
    private String paymentDate;
    private String paymentTime;
    private Integer totalAmount;
    private String status;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id, String code, String orderDate, String orderTime, String paymentType, String paymentAccount, String paymentName, String paymentDate, String paymentTime, Integer totalAmount, String status) {
        this.id = id;
        this.code = code;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.paymentType = paymentType;
        this.paymentAccount = paymentAccount;
        this.paymentName = paymentName;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
