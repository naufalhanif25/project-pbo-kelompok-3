import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private Customer akun;
    private List<Barang> listBarang = new ArrayList<>();
    private String idTransaksi;
    private double totalHarga;

    public Transaksi(String idTransaksi, Customer akun) {
        this.idTransaksi = idTransaksi;
        this.akun = akun;
    }

    public void addBarang(Barang barang) {
        listBarang.add(barang);
        totalHarga += barang.getHarga();
    }

    public Customer getAkun() {
        return akun;
    }

    public void setAkun(Customer akun) {
        this.akun = akun;
    }

    public List<Barang> getBarangList() {
        return listBarang;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        
        sb.append("Transaksi {") 
          .append("idTransaksi = ").append(idTransaksi)
          .append(", akun = ").append(akun.getId()) 
          .append(", listBarang = ["); 
        
        for (Barang barang : listBarang) { 
            sb.append(barang.toString()).append(", "); 
        } 
        
        if (!listBarang.isEmpty()) { 
            sb.setLength(sb.length() - 2); 
        } 
        
        sb.append("], totalHarga = ").append(totalHarga) 
          .append("}"); 
        
        return sb.toString();
    }
}
