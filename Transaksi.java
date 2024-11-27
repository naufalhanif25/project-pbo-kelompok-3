import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Barang> daftarBarang;

    public Transaksi(ArrayList<Barang> daftarBarang) {
        this.daftarBarang = new ArrayList<>(daftarBarang);
    }

    // Menambahkan metode getTotal untuk menghitung total harga barang
    public int getTotal() {
        int total = 0;
        for (Barang barang : daftarBarang) {
            total += barang.getHarga();  // Mengambil harga dari masing-masing barang
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transaksi:\n");
        for (Barang barang : daftarBarang) {
            sb.append(barang).append("\n");
        }
        return sb.toString();
    }
}
