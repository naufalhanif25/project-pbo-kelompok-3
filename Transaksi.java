
import javax.swing.*;

public class Transaksi extends JFrame{
  public  Transaksi(){
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new TransaksiPanel());
    setVisible(true);
  }

  public Transaksi(double totalHarga) {
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new TransaksiPanel(totalHarga));
    System.out.println("Transaksi: " + totalHarga);
    setVisible(true);
  }
  
}