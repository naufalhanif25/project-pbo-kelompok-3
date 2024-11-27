import java.util.ArrayList;
import java.util.List;

public class TransaksiF {
    private CustomerF akun;
    private List<Barang> listBarang = new ArrayList<>();
    private String idTransaksi;
    private double totalHarga;
    private String metodePembayaran;

    public TransaksiF(String idTransaksi, CustomerF akun) {
        this.idTransaksi = idTransaksi;
        this.akun = akun;
    }

    public void addBarang(Barang barang) {
        listBarang.add(barang);
        totalHarga += barang.getHarga();
    }

    public CustomerF getAkun() {
        return akun;
    }

    public void setAkun(CustomerF akun) {
        this.akun = akun;
    }

    public List<Barang> getBarangList() {
        return listBarang;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
    
}
