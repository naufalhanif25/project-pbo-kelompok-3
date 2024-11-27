
import javax.swing.*;
public class Belibarang extends JFrame{
  public Belibarang() {
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new BeliBarangPanel());
    setVisible(true);
  }
}