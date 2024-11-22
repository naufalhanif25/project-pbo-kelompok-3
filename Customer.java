import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String password;
    private List<Barang> cart;
    private List<Barang> purchaseHistory;

    public Customer(String id, String password) {
        this.id = id;
        this.password = password;
        this.cart = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
    }

    public void addToCart(Barang barang) {
        cart.add(barang);
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Keranjang belanja kosong. Tambahkan produk terlebih dahulu.");
            return;
        }

        System.out.print("\n");
        System.out.println("=== Checkout ===");
        int total = 0;

        for (Barang barang : cart) {
            System.out.println(barang);
            total += barang.getHarga();
        }
        System.out.println("Total yang harus dibayar: Rp" + total);

        purchaseHistory.addAll(cart);

        cart.clear();

        System.out.println("Checkout berhasil");
    }

    public void viewPurchaseHistory() {
        if (purchaseHistory.isEmpty()) {
            System.out.println("Belum ada riwayat pembelian.");
        } else {
            System.out.print("\n");
            System.out.println("=== Riwayat Pembelian ===");

            for (Barang barang : purchaseHistory) {
                System.out.println(barang);
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return password;
    }
}