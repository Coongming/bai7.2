package vn.edu.hcmute.fit.ex9_1;

public class Product {
    private String productCode;
    private String productName;

    public Product(String productCode, String productName) {
        this.productCode = productCode;
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }
}