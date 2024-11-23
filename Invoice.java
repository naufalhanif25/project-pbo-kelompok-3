public class Invoice {
    private Cart cart;
    private String paymentMethod;

    public Invoice(Cart cart, String paymentMethod) {
        this.cart = cart;
        this.paymentMethod = paymentMethod;
    }

    // Display invoice details
    public void displayInvoice() {
        System.out.println("===== Purchase Invoice =====");
        cart.displayCart();
        System.out.println("Total Price: Rp" + cart.calculateTotal());
        System.out.println("Payment Method: " + paymentMethod);
    }
}
