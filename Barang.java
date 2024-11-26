public class Barang {
    private String idBarang;
    private String namaBarang;
    private String tipeBarang;
    private double harga;
    private int stok;
  
    // Konstruktor
    public Barang(String idBarang, String namaBarang, String tipeBarang, double harga, int stok) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.tipeBarang = tipeBarang;
        this.harga = harga;
        this.stok = stok;
    }
  
    // Getter dan Setter
    public String getIdBarang() {
        return idBarang;
    }
  
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }
  
    public String getNamaBarang() {
        return namaBarang;
    }
  
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
  
    public String getTipeBarang() {
        return tipeBarang;
    }
  
    public void setTipeBarang(String tipeBarang) {
        this.tipeBarang = tipeBarang;
    }
  
    public double getHarga() {
        return harga;
    }
  
    public void setHarga(double harga) {
        this.harga = harga;
    }
  
    public int getStok() {
        return stok;
    }
  
    public void setStok(int stok) {
        this.stok = stok;
    }
  
    // Override toString() 
    @Override
    public String toString() {
        return "Barang [ID: " + idBarang + ", Nama: " + namaBarang + ", Tipe: " + tipeBarang + ", Harga: " + harga + ", Stok: " + stok + "]";
    }
  }