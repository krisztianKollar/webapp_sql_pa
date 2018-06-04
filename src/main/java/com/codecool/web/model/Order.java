package com.codecool.web.model;

public class Order {

    private int orderId;
    private int numberOfProductsOrdered;
    private float totalPrice;
    private int productId;
    private String productName;
    private float unitPrice;

    public Order(int orderId, int numberOfProductsOrdered, float totalPrice) {
        this.orderId = orderId;
        this.numberOfProductsOrdered = numberOfProductsOrdered;
        this.totalPrice = totalPrice;
    }

    public Order(int productId, String productName, float unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getNumberOfProductsOrdered() {
        return numberOfProductsOrdered;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
}
