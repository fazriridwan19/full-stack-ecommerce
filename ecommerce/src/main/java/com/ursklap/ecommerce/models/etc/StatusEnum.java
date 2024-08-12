package com.ursklap.ecommerce.models.etc;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(1L),
    PENDING(2L),
    SHIPMENT(3L),
    CANCELED(4L);

    private final Long value;

    private StatusEnum(Long value) {
        this.value = value;
    }
}
