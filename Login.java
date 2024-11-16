import javax.swing.*;

public class Login extends JFrame {
    public Login() {

        JFrame login = new JFrame("Login");
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setExtendedState(JFrame.MAXIMIZED_BOTH);
        login.setSize(800, 600);
        login.setLocationRelativeTo(null);
        
        // Panggil Panellogin
        login.add(new LoginPanel());
        login.setVisible(true);
    }
}
