import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Search extends JPanel{
  private JComboBox<String> searchComboBox;
    private DefaultTableModel tableModel;
    private HashMap<String, List<String[]>> tipeBarang;

    // Method Mencari Barang Dengan Tipe Data 
    public Search(DefaultTableModel tableModel, HashMap<String, List<String[]>> tipeBarang) {
      this.tableModel = tableModel;
      this.tipeBarang = tipeBarang;
      setLayout(new FlowLayout(FlowLayout.LEFT));

      // Buat ComboBox untuk pencarian
      searchComboBox = new JComboBox<>(new String[]{
              "Semua", "Elektronik", "Pakaian", "Makanan", "Peralatan",
              "Perabotan", "Mainan", "Kosmetik", "Buku", "Aksesoris", "Kendaraan"});
      searchComboBox.addActionListener(e -> filterBarang());
      searchComboBox.setPreferredSize(new Dimension(300, 35));
      add(searchComboBox);
  }
    private void filterBarang() {
      String selectedType = (String) searchComboBox.getSelectedItem();
      tableModel.setRowCount(0); // Reset tabel

      if (selectedType.equals("Semua")) {
        // Tampilkan semua barang
          for (List<String[]> barangList : tipeBarang.values()) {
              for (String[] data : barangList) {
                tableModel.addRow(data);
              }
          }
      } else {
          // Tampilkan barang berdasarkan tipe tertentu
          List<String[]> filteredData = tipeBarang.get(selectedType);
          if (filteredData != null) {
              for (String[] data : filteredData) {
                tableModel.addRow(data);
            }
        }
    }
}
}
