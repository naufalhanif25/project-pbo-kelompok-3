import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private String password;
    private List<Product> cart;
    private List<Product> purchaseHistory;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
    }

    // Menambahkan produk ke keranjang
    public void addToCart(Product product) {
        cart.add(product);
    }

    // Melakukan checkout dan menampilkan total harga
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Keranjang belanja kosong. Tambahkan produk terlebih dahulu.");
            return;
        }

        System.out.println("\n=== Checkout ===");
        int total = 0;
        // Menampilkan detail produk dalam keranjang
        for (Product product : cart) {
            System.out.println(product);
            total += product.getPrice();  // Menjumlahkan harga produk
        }
        System.out.println("Total yang harus dibayar: Rp" + total);

        // Menambahkan produk ke riwayat pembelian
        purchaseHistory.addAll(cart);
        cart.clear();  // Mengosongkan keranjang setelah checkout
        System.out.println("Checkout berhasil. Terima kasih telah berbelanja.");
    }

    // Melihat riwayat pembelian
    public void viewPurchaseHistory() {
        if (purchaseHistory.isEmpty()) {
            System.out.println("Belum ada riwayat pembelian.");
        } else {
            System.out.println("\n=== Riwayat Pembelian ===");
            for (Product product : purchaseHistory) {
                System.out.println(product);  // Menampilkan setiap produk dalam riwayat
            }
        }
    }

    // Getter untuk username dan password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
