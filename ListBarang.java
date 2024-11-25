
import javax.swing.*;

public class ListBarang extends JFrame{
  public ListBarang(){
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new ListBarangPanel());
    setVisible(true);
    
  }
}
  

