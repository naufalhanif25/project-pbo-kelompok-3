import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> productList;
    
    public Cart() {
        productList = new ArrayList<>();
    }

    // Add product to cart
    public void addProduct(Product product) {
        productList.add(product);
    }

    // Display all products in the cart
    public void displayCart() {
        System.out.println("===== Cart Products =====");
        for (Product product : productList) {
            product.displayInfo();
            System.out.println("----------------------");
        }
    }

    // Calculate total price of products in the cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : productList) {
            total += product.getPrice();
        }
        return total;
    }
}
