import java.io.*;
import javax.swing.*;

public class TambahBarangFitur {

    // Metode untuk menambahkan barang
    public void tambahkanBarang(String idBarang, String namaBarang, String tipeBarang, int stok, double harga) {
        // Validasi input (untuk stok dan harga sudah dicek di panel)
        if (idBarang.isEmpty() || namaBarang.isEmpty() || tipeBarang.isEmpty() || stok <= 0 || harga <= 0) {
            JOptionPane.showMessageDialog(null, "Harap isi semua kolom dengan data yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Menulis data barang ke file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Barang.txt", true))) {
                writer.write(idBarang + "," + namaBarang + "," + tipeBarang + "," + stok + "," + harga);
                writer.newLine();
            }

            // Memberikan feedback
            JOptionPane.showMessageDialog(null, "Barang berhasil ditambahkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Gagal menulis ke file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
