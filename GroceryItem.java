package oopslogic.grocerysystem;

public class GroceryItem {
    private String name;
    private int stock;
    private double price;

    public GroceryItem(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() { return name; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("%s - Stock: %d, Price: ₹%.2f", name, stock, price);
    }
}
