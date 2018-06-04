package com.codecool.web.model;

public class Product {

    private int productId;
    private String productName;
    private float UnitPrice;
    private int unitsInStock;
    private String categoryName;
    private String supplierName;

    public Product(int productId, String productName, float unitPrice, int unitsInStock, String categoryName, String supplierName) {
        this.productId = productId;
        this.productName = productName;
        UnitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
