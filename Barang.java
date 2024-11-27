import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Barang {
    private String idBarang;
    private String namaBarang;
    private String tipeBarang;
    private double harga;
    private int stok;

    // Konstruktor
    public Barang(int index) {
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> parts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Barang.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                parts.add(line);
            }

            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        String[] part = parts.get(index).split(",");

        if (part.length == 5) {
            String idBarang = part[0];
            String namaBarang = part[1];
            String tipeBarang = part[2];
            String stok = part[3];
            String harga = part[4];

            data.add(idBarang);
            data.add(namaBarang);
            data.add(tipeBarang);
            data.add(stok);
            data.add(harga);
        }

        this.idBarang = data.get(0);
        this.namaBarang = data.get(1);
        this.tipeBarang = data.get(2);
        this.stok = Integer.parseInt(data.get(3));
        this.harga = Double.parseDouble(data.get(4));
    }
    
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
