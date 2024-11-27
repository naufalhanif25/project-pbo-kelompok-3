import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListBarangF  {
    private List<Barang> daftarBarang;

    public ListBarangF() {
        daftarBarang = new ArrayList<>();
        loadBarangDariFile("barang.txt");
    }

    // Memuat barang dari file
    private void loadBarangDariFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    try {
                        String idBarang = data[0];
                        String namaBarang = data[1];
                        String tipeBarang = data[2];
                        double harga = Double.parseDouble(data[3]);
                        int stok = Integer.parseInt(data[4]);

                        Barang barang = new Barang(idBarang, namaBarang, tipeBarang, harga, stok);
                        daftarBarang.add(barang);
                    } catch (NumberFormatException e) {
                        System.err.println("Kesalahan format pada baris: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Gagal memuat file barang: " + e.getMessage());
        }
    }

    // Mendapatkan daftar barang
    public List<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    // Mencari barang berdasarkan ID
    public Barang cariBarangById(String idBarang) {
        for (Barang barang : daftarBarang) {
            if (barang.getIdBarang().equals(idBarang)) {
                return barang;
            }
        }
        return null; // Jika barang tidak ditemukan
    }

    // Memperbarui stok barang
    public boolean updateStokBarang(String idBarang, int jumlah) {
        Barang barang = cariBarangById(idBarang);
        if (barang != null && barang.getStok() >= jumlah) {
            barang.setStok(barang.getStok() - jumlah);
            simpanKeFile("barang.txt");
            return true;
        }
        return false;
    }

    // Menyimpan daftar barang ke file
    private void simpanKeFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Barang barang : daftarBarang) {
                writer.write(barang.getIdBarang() + "," + barang.getNamaBarang() + "," +
                        barang.getTipeBarang() + "," + barang.getHarga() + "," + barang.getStok());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Gagal menyimpan ke file barang: " + e.getMessage());
        }
    }
}
