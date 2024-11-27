import java.io.*;
import java.util.*;

public class CreateAkunFitur {
    // Metode untuk membuat akun baru
    public void createAkun(String id_input, String pass_input, String roles) {
        List<String> data = new ArrayList<>();
        boolean exists = false;

        // Memilih file yang sesuai berdasarkan role
        String fileName = roles.equals("Admin") ? "admin.txt" : "Pelanggan.txt";

        // Cek apakah ID sudah ada di file
        try (BufferedReader buffer_read = new BufferedReader(new FileReader(fileName))) {
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

            buffer_read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!exists) {
            data.add(id_input + ", " + pass_input + "\n");

            // Menulis akun ke file sesuai dengan role
            try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter(fileName, true))) {
                buffer_write.write(id_input + ", " + pass_input + "\n");
                buffer_write.newLine();

                System.out.println("Akun berhasil dibuat sebagai " + roles);
                buffer_write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
