package oopslogic.grocerysystem;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class InventoryManager {
    private Map<String, GroceryItem> inventory = new HashMap<>();
    private static final String FILE_NAME = "inventory.json";

    public InventoryManager() {
        loadFromJSON();
    }

    public void addItem(String name, int stock, double price) {
        GroceryItem item = new GroceryItem(name, stock, price);
        inventory.put(name.toLowerCase(), item);
        saveToJSON();
    }

    public GroceryItem getItem(String name) {
        return inventory.get(name.toLowerCase());
    }

    public void displayAllItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is currently empty.");
        } else {
            inventory.values().forEach(System.out::println);
        }
    }

    public void updateItem(String name, int stock, double price) {
        GroceryItem item = getItem(name);
        if (item != null) {
            item.setStock(stock);
            item.setPrice(price);
            saveToJSON();
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public void reduceStock(String name, int quantity) {
        GroceryItem item = getItem(name);
        if (item != null) {
            item.setStock(item.getStock() - quantity);
            saveToJSON();
        }
    }

    private void loadFromJSON() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, GroceryItem>>() {}.getType();
            inventory = gson.fromJson(reader, type);
            if (inventory == null) inventory = new HashMap<>();
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
    }

    private void saveToJSON() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(inventory, writer);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public Set<String> getAvailableItemNames() {
        return inventory.keySet();
    }
}
