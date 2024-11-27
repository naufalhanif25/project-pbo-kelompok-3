import java.util.ArrayList;

public class Product {
    private String id;
    private String name;
    private double price;

    // Constructor
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Static list of products
    private static final ArrayList<Product> products = new ArrayList<>();

    // Add a product to the list
    public static void addProduct(Product product) {
        products.add(product);
    }

    // Display all products
    public static void displayProducts() {
        System.out.println("\n========== Daftar Produk ==========");
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + " | Nama: " + product.getName() + " | Harga: Rp " + product.getPrice());
        }
    }

    // Find a product by its ID
    public static Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null; // Return null if not found
    }
}
