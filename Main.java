import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create a customer account
        Customer customer = new Customer("mahasiswa123", "password123");
        
        // Login process
        System.out.print("Enter ID: ");
        String idInput = scanner.nextLine();
        System.out.print("Enter Password: ");
        String passInput = scanner.nextLine();
        
        if (customer.login(idInput, passInput)) {
            System.out.println("Login successful!");

            // Add products to the cart
            Product javaBook = new Product("Java Programming Book", 100000, 10);
            Product stationery = new Product("Stationery", 50000, 20);
            Product laptopBag = new Product("Laptop Bag", 300000, 5);
            
            customer.addProductToCart(javaBook);
            customer.addProductToCart(stationery);
            customer.addProductToCart(laptopBag);

            // Display cart content
            customer.cart.displayCart();

            // Choose payment method
            System.out.println("Choose a payment method: ");
            System.out.println("1. QRIS");
            System.out.println("2. Bank Transfer");
            System.out.println("3. COD");
            System.out.print("Option: ");
            int option = scanner.nextInt();
            String paymentMethod = "";
            
            if (option == 1) {
                paymentMethod = "QRIS";
            } else if (option == 2) {
                paymentMethod = "Bank Transfer";
            } else if (option == 3) {
                paymentMethod = "COD";
            } else {
                System.out.println("Invalid option.");
            }

            // Checkout and display invoice
            customer.checkout(paymentMethod);
        } else {
            System.out.println("Login failed!");
        }

        scanner.close();
    }
}
