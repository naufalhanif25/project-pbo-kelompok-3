import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class JumlahEdit extends AbstractCellEditor implements TableCellEditor{
  private JSpinner jumlah;

  public JumlahEdit(){
    // Konfigurasi spinner dengan nilai minimal, maksimal, dan langkah
    jumlah = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Min: 1, Max: 100, Step: 1
    jumlah.setPreferredSize(new Dimension(50, 20));
}

@Override
public Object getCellEditorValue() {
    return jumlah.getValue(); // Mengembalikan nilai saat pengeditan selesai
}

@Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
  jumlah.setValue(value); // Set nilai awal spinner sesuai dengan sel tabel
    return jumlah;
  }
} 