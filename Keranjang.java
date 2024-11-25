

import javax.swing.*;

public class Keranjang extends JFrame{

  public Keranjang(){
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new KeranjangPanel());
    setVisible(true);
    
  }
}
  