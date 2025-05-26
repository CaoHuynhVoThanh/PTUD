package test;

public class Product {
    private String productName; // 🔁 Đổi từ `name` → `productName`
    private int quantity;
    private double price;

    public Product(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotal() { return quantity * price; }
}

