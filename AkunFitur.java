import java.io.*;
import java.util.*;

public class AkunFitur {
    private String id;
    private String pass;

    // Getter untuk id
    public String getId() {
        return id;
    }

    // Setter untuk id
    public void setId(String id) {
        this.id = id;
    }

    // Getter untuk password
    public String getPass() {
        return pass;
    }

    // Setter untuk password
    public void setPass(String pass) {
        this.pass = pass;
    }

    // Validasi login berdasarkan ID dan Password
    public boolean login(String id_input, String pass_input, String option) {
        String[] information = new String[2];

        try {
            FileReader Admin_txt = new FileReader("Admin.txt");
            BufferedReader admin_buffer = new BufferedReader(Admin_txt);
            
            FileReader Pelanggan_txt = new FileReader("Pelanggan.txt");
            BufferedReader users_buffer = new BufferedReader(Pelanggan_txt);

            if (option.equals("Admin")) {
                information = getIdPass(id_input, pass_input, admin_buffer);
            } else if (option.equals("Pelanggan")) {
                information = getIdPass(id_input, pass_input, users_buffer);
            } else {
                System.out.println("Error: Opsi tidak tersedia");
            }

            admin_buffer.close();
            users_buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (id_input.equals(information[0]) && pass_input.equals(information[1])) {
            System.out.println("Berhasil login sebagai " + information[0]);
            return true;
        } else {
            System.out.println("Error: Gagal login");
            return false;
        }
    }

    // Mendapatkan ID dan Password dari file
    public String[] getIdPass(String id_input, String pass_input, BufferedReader buffer) {
        String[] information = new String[2];

        try {
            String line;

            while ((line = buffer.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 2) {
                    setId(parts[0]);
                    setPass(parts[1]);

                    if (getId().equals(id_input) && getPass().equals(pass_input)) {
                        information[0] = getId();
                        information[1] = getPass();
                        break;
                    }
                }
            }

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return information;
    }
}
