
import javax.swing.*;


public class TambahBarang extends JFrame{
  public TambahBarang(){
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);

    //Icon
    setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

    //PanggilPanel
    add(new TambahBarangPanel());
    setVisible(true);


  }

  
}