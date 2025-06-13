package oopslogic.grocerysystem;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class CartManager {
    private final Map<GroceryItem, Integer> cart = new LinkedHashMap<>();

    public void addToCart(GroceryItem item, int quantity) {
        cart.put(item, cart.getOrDefault(item, 0) + quantity);
    }

    public void printReceipt() {
        double total = 0;
        StringBuilder receipt = new StringBuilder("\n===== FINAL RECEIPT =====\n");

        for (Map.Entry<GroceryItem, Integer> entry : cart.entrySet()) {
            GroceryItem item = entry.getKey();
            int qty = entry.getValue();
            double cost = item.getPrice() * qty;
            total += cost;
            receipt.append(String.format("%s x%d = ₹%.2f\n", item.getName(), qty, cost));
        }

        receipt.append("---------------------------\n");
        receipt.append(String.format("Total Bill: ₹%.2f\n", total));
        receipt.append("===========================\n");

        System.out.println(receipt);
        saveReceiptToFile(receipt.toString());
    }

    private void saveReceiptToFile(String receiptText) {
        try {
            File dir = new File("receipts");
            if (!dir.exists()) dir.mkdirs();

            int receiptNum = Objects.requireNonNull(dir.list()).length + 1;
            File file = new File(dir, "receipt" + receiptNum + ".txt");

            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(receiptText);
            }

            System.out.println("Receipt saved as: " + file.getName());
        } catch (Exception e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }

    public void removeItemByName(String itemName) {
        GroceryItem toRemove = null;

        for (GroceryItem item : cart.keySet()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                toRemove = item;
                break;
            }
        }

        if (toRemove != null) {
            cart.remove(toRemove);
            System.out.println(itemName + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }
}
