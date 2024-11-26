<<<<<<< HEAD
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
=======
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Barang {
    private String nama;
    private double harga;
    private int stok;
    private String id;

    public Barang(String nama) {
        List<String> data = new ArrayList<>();
        List<String> idData = new ArrayList<>();

        this.nama = nama;

        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            String huruf;
            int angka;
            
            for (int index = 0; (line = reader.readLine()) != null; index++) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 4) {
                    String temp_idStr = parts[0];
                    String barangStr = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    int stok = Integer.parseInt(parts[3]);
                    
                    if (temp_idStr != null) {
                        huruf = temp_idStr.replaceAll("[^A-Z]", "");
                        angka = Integer.parseInt(temp_idStr.replaceAll("[^0-9]", ""));
        
                        idData.add(huruf + angka);
                    }
                    else {
                        huruf = "B";
                        angka = 1;

                        idData.add(huruf + angka);
                    }

                    if (barangStr.equals(nama)) {
                        data.add(idData.get(index));
                        data.add(barangStr);
                        data.add(String.valueOf(harga));
                        data.add(String.valueOf(stok));
                    }
                }
            }

            this.id = data.get(0);
            this.nama = data.get(1);
            this.harga = Double.parseDouble(data.get(2));
            this.stok = Integer.parseInt(data.get(3));

            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public Barang(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public Barang(String nama, double harga, int stok, String id) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getBarang() {
        return nama + ":" + 
               harga;
    }

    public String newId() {
        List<String> idData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            String huruf;
            int angka;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 4) {
                    String temp_idStr = parts[0];
                        
                    huruf = temp_idStr.replaceAll("[^A-Z]", "");
                    angka = Integer.parseInt(temp_idStr.replaceAll("[^0-9]", ""));
    
                    idData.add(huruf + (angka + 1));
                }
            }

            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return idData.get(idData.size() - 1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok + " pcs");
    }
    
    public String toString() {
        return "Nama = " + nama +
               ", Harga = Rp" + harga;
    }
}
>>>>>>> ab9abdc2f9fbc8c091acbfaef1019c63972bdb8f