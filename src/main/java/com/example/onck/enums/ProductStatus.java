package com.example.onck.enums;

public enum ProductStatus {
    ACTIVE(0), INACTIVE(1);

    private int value;
    ProductStatus(int i) {
        this.value=i;
    }

    public int getValue() {
        return value;
    }
}
