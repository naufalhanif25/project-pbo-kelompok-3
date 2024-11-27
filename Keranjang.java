import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> daftarBarang = new ArrayList<>();

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    public void kosongkanKeranjang() {
        daftarBarang.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Keranjang:\n");
        for (Barang barang : daftarBarang) {
            sb.append(barang).append("\n");
        }
        return sb.toString();
    }
}
