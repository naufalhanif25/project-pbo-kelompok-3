import java.util.ArrayList;
import java.util.List;

public class KeranjangF {
    private List<Barang> listBarang;
    private String userId;

    public KeranjangF(String userId) {
        this.userId = userId;
        this.listBarang = new ArrayList<>();
    }

    public void addBarang(Barang barang) {
        listBarang.add(barang);
    }

    public void kosongkanKeranjang() {
        listBarang.clear();
    }

    public List<Barang> getListBarang() {
        return listBarang;
    }

    public boolean isEmpty() {
        return listBarang.isEmpty();
    }

    public String getUserId() {
      return userId;
    }
}
