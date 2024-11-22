import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Akun {
    String id;
    String pass;

    public String[] getIdPass(String id_input, String pass_input, BufferedReader buffer) {
        String[] information = new String[2];

        try {
            String line;

            while ((line = buffer.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 2) {
                    id = parts[0];
                    pass = parts[1];

                    if (id.equals(id_input) && pass.equals(pass_input)) {
                        information[0] = id;
                        information[1] = pass;

                        break;
                    }
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return information;
    }

    public void setIdPass(String id_input, String new_pass_input, BufferedReader buffer) {
        List<String> data = new ArrayList<>();
        boolean found = false;

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts[0].equals(id_input)) {
                    parts[1] = new_pass_input;
                    found = true;
                }

                data.add(parts[0] + ", " + parts[1]);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (!found) {
            System.out.println("Error: ID tidak ditemukan");

            return;
        }

        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("users.txt"))) {
            for (String user_data : data) {
                buffer_write.write(user_data);
                buffer_write.newLine();
            }

            System.out.println("Password berhasil diubah");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAkun(String id_input, String pass_input, BufferedReader buffer) {
        List<String> data = new ArrayList<>();
        boolean exists = false;

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts[0].equals(id_input)) {
                    System.out.println("Error: ID sudah tersedia");
                    exists = true;

                    break;
                }

                data.add(line);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (!exists) {
            data.add(id_input + ", " + pass_input);

            try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("users.txt", true))) {
                buffer_write.write(id_input + ", " + pass_input);
                buffer_write.newLine();
                System.out.println("Akun berhasil dibuat");
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAkun(String id_input, String pass_input) {
        List<String> data = new ArrayList<>();
        boolean found = false;

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts[0].equals(id_input) && parts[1].equals(pass_input)) {
                    found = true;
                } 
                else {
                    data.add(line);
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (!found) {
            System.out.println("Error: ID atau password tidak ditemukan");
            
            return;
        }

        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("users.txt"))) {
            for (String user_data : data) {
                buffer_write.write(user_data);
                buffer_write.newLine();
            }

            System.out.println("Akun berhasil dihapus");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
