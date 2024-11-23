import javax.swing.*;

public class Invoice extends JFrame{
  public Invoice(){
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);

    //Icon
    setIconImage(new ImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\IconRB.png").getImage());

    //PanggilPanel
    add(new InvoicePanel());
    setVisible(true);


  }

  
}

