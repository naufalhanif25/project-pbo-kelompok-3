import java.util.Scanner;

public class Customer {
    private String id;
    private String password;
    private Cart cart;

    public Customer(String id, String password) {
        this.id = id;
        this.password = password;
        this.cart = new Cart();
    }

    // Login method
    public boolean login(String idInput, String passInput) {
        if (idInput.equals(id) && passInput.equals(password)) {
            return true;
        }
        return false;
    }

    // Add product to cart
    public void addProductToCart(Product product) {
        cart.addProduct(product);
    }

    // Checkout and display invoice
    public void checkout(String paymentMethod) {
        Invoice invoice = new Invoice(cart, paymentMethod);
        invoice.displayInvoice();
    }
}

