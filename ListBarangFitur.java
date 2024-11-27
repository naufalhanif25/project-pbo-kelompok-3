import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class ListBarangFitur {

    // Memuat data barang dari file
    public void loadBarang(DefaultTableModel tableModel, HashMap<String, List<String[]>> TipeBarang) {
        tableModel.setRowCount(0); // Reset tabel
        TipeBarang.clear(); // Kosongkan HashMap
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);

                // Masukkan data ke HashMap berdasarkan tipe barang
                TipeBarang.computeIfAbsent(data[2], k -> new ArrayList<>()).add(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi untuk mencari barang berdasarkan tipe
    public void cariBarang(String tipeBarang, DefaultTableModel tableModel, HashMap<String, List<String[]>> TipeBarang) {
        // Clear tabel sebelum mencari
        tableModel.setRowCount(0);

        // Ambil daftar barang berdasarkan tipe yang dicari
        List<String[]> barangList = TipeBarang.get(tipeBarang);

        if (barangList != null) {
            for (String[] data : barangList) {
                tableModel.addRow(data);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tipe barang tidak ditemukan!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
