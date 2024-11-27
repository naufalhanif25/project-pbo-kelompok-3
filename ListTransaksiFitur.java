import javax.swing.table.DefaultTableModel;
import java.io.*;
import javax.swing.JOptionPane;

public class ListTransaksiFitur {

    // Memuat data transaksi dari file
    public void loadTransaksi(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("transaksi.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {  
                    tableModel.addRow(data);
                } else {
                    System.out.println("Baris Data Tidak Valid: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat transaksi dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi untuk menyaring transaksi berdasarkan ID transaksi atau username
    public void filterTransaksi(String keyword, DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("transaksi.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].contains(keyword) || data[1].contains(keyword)) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat transaksi dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
