package com.cozycrochet.models;

public class CartItem {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String customization;
    private String image; // NEW FIELD

    // Updated Constructor
    public CartItem(int id, String name, double price, int quantity, String customization, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.customization = customization;
        this.image = image; // SET IMAGE
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCustomization() { return customization; }
    public String getImage() { return image; } // NEW GETTER

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setCustomization(String customization) { this.customization = customization; }

    public double getTotal() {
        return price * quantity;
    }
}
