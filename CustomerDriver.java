import java.util.ArrayList;
import java.util.List;

public class CustomerDriver extends Driver {
    Customer akun;
    List<Transaksi> listTransaksi = new ArrayList<>();

    public CustomerDriver(Customer akun) {
        this.akun = akun;
    }

    @Override
    public void addBarang(Barang barang) {
        System.out.println("Customer tidak dapat menambah barang ke dalam sistem.");
    }

    @Override
    public void showTransaksi() {
        // System.out.println("Daftar transaksi untuk customer " + akun.getNama() + ":");
        
        for (Transaksi transaksi : listTransaksi) {
            if (transaksi.getAkun().equals(akun)) {
                System.out.println(transaksi);
            }
        }
    }

    public void createTransaksi(String idTransaksi, Barang barang) {
        Transaksi transaksi = new Transaksi(idTransaksi, akun);

        transaksi.addBarang(barang);
        listTransaksi.add(transaksi);
        System.out.println("Transaksi berhasil dibuat.");
    }
}
