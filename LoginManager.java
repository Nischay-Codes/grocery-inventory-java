package oopslogic.grocerysystem;

import java.util.Scanner;

public class LoginManager {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    public boolean login() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== ADMIN LOGIN =====");
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        System.out.println("Validating...");
        Thread.sleep(800);

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Incorrect credentials.");
            return false;
        }
    }
}
