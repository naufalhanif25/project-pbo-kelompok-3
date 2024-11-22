import java.util.ArrayList;
import java.util.List;

public class AdminDriver extends Driver {
    Admin akun = new Admin();
    ListBarang listBarang = new ListBarang();
    List<Transaksi> listTransaksi = new ArrayList<>();

    @Override
    public void addBarang(Barang barang) {
        listBarang.getBarangList().add(barang);
        listBarang.saveBarang();

        System.out.println("Barang berhasil ditambahkan");
    }

    @Override
    public void showTransaksi() {
        System.out.println("Daftar transaksi:");

        for (Transaksi transaksi : listTransaksi) {
            System.out.println(transaksi);
        }
    }
}
