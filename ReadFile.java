import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    // Method untuk membaca barang dari file barang.txt 
    public static void BacaBarang(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Validasi jumlah kolom
                    try {
                        String idBarang = data[0];
                        String namaBarang = data[1];
                        String tipeBarang = data[2];
                        int stok = Integer.parseInt(data[3]);
                        double harga = Double.parseDouble(data[4]);

                        // Tambahkan data ke tabel
                        tableModel.addRow(new Object[]{idBarang, namaBarang, tipeBarang, stok, harga, 1, false});
                    } catch (NumberFormatException e) {
                        System.err.println("Format data salah: " + line);
                    }
                } else {
                    System.err.println("Baris tidak valid: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
