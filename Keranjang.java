import java.util.ArrayList;

public class Keranjang {
    ArrayList<Barang> barang = new ArrayList<>();

    public void showBarang() {
        System.out.println("Barang dalam keranjang:");
        for (Barang b : barang) {
            System.out.println(b);
        }
    }
}
