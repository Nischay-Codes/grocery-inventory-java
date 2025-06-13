package oopslogic.grocerysystem;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        LoginManager auth = new LoginManager();
        InventoryManager inventory = new InventoryManager();
        CartManager cart = new CartManager();

        while (true) {
            System.out.println("\n===== GROCERY SYSTEM MENU =====");
            System.out.println("1. Add Item to Inventory");
            System.out.println("2. View Stock");
            System.out.println("3. Update Item");
            System.out.println("4. Start Billing (Cart)");
            System.out.println("5. Remove Item from Cart");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    if (!auth.login()) return;

                    do {
                        System.out.print("Enter item name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter stock quantity: ");
                        int stock = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        inventory.addItem(name, stock, price);
                        System.out.println("Item added successfully.");
                        System.out.print("Add another item? (yes/no): ");
                    } while (sc.nextLine().equalsIgnoreCase("yes"));
                    break;

                case 2:
                    if (!auth.login()) return;

                    inventory.displayAllItems();
                    System.out.print("Enter item name to check: ");
                    String checkName = sc.nextLine();
                    GroceryItem item = inventory.getItem(checkName);

                    if (item != null) {
                        System.out.println("Stock: " + item.getStock());
                        System.out.println("Price: ₹" + item.getPrice());
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    if (!auth.login()) return;

                    System.out.print("Enter item name to update: ");
                    String updateName = sc.nextLine();
                    System.out.print("New stock: ");
                    int newStock = Integer.parseInt(sc.nextLine());
                    System.out.print("New price: ");
                    double newPrice = Double.parseDouble(sc.nextLine());

                    inventory.updateItem(updateName, newStock, newPrice);
                    break;

                case 4:
                    do {
                        inventory.displayAllItems();
                        System.out.print("Enter item to add to cart: ");
                        String cartName = sc.nextLine();
                        GroceryItem cartItem = inventory.getItem(cartName);

                        if (cartItem == null) {
                            System.out.println("Item not found.");
                            continue;
                        }

                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(sc.nextLine());

                        if (quantity > cartItem.getStock()) {
                            System.out.println("Insufficient stock.");
                            continue;
                        }

                        inventory.reduceStock(cartName, quantity);
                        cart.addToCart(cartItem, quantity);

                        System.out.print("Add more items? (yes/no): ");
                    } while (sc.nextLine().equalsIgnoreCase("yes"));

                    cart.printReceipt();
                    break;

                case 5:
                    System.out.print("Enter item name to remove from cart: ");
                    String removeName = sc.nextLine();
                    cart.removeItemByName(removeName);
                    break;

                case 6:
                    System.out.println("Thank you for using the Grocery System. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
