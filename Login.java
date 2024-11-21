import javax.swing.*;

public class Login extends JFrame {
    public Login() {
        setTitle("e-commerce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        //icon
        setIconImage(new ImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\IconRB.png").getImage());
        
        // Panggil Panellogin
        add(new LoginPanel());
       setVisible(true);
    }
}