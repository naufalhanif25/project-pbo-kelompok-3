import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Main {
    Akun akun;
    String id;
    String pass;

    public boolean login(String id_input, String pass_input, String option) {
        akun = new Akun();
        String[] information = new String[2];

        try {
            FileReader admin_txt = new FileReader("admin.txt");
            BufferedReader admin_buffer = new BufferedReader(admin_txt);
            
            FileReader users_txt = new FileReader("users.txt");
            BufferedReader users_buffer = new BufferedReader(users_txt);

            if (option.equals("Admin")) {
                information = Arrays.copyOf(akun.getIdPass(id_input, pass_input, admin_buffer), 2);
            }
            else if (option.equals("User")) {
                information = Arrays.copyOf(akun.getIdPass(id_input, pass_input, users_buffer), 2);
            }
            else {
                System.out.println("Error: Opsi tidak tersedia");
            }

            admin_buffer.close();
            users_buffer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (id_input.equals(information[0]) && pass_input.equals(information[1])) {
                System.out.println("Berhasil login sebagai " + information[0]);

                return true;
            }
            else {
                System.out.println("Error: Gagal login");
            }
        }

        return false;
    }

    public void admin_login(String option) {
        Scanner admin_scanner = new Scanner(System.in);

        System.out.print("\n");
        System.out.println("ID Akun:");
        String id_input = admin_scanner.nextLine();

        System.out.println("Password:");
        String pass_input = admin_scanner.nextLine();

        boolean admin_log = login(id_input, pass_input, option);

        if (admin_log == true) {
            Admin admin = new Admin();

            admin.manageBarang();
        }
        else {
        }
    }

    public void user_login(String option) {
        Scanner user_scanner = new Scanner(System.in);
        akun = new Akun();

        System.out.print("\n");
        System.out.println("========== User ==========");
        System.out.println("1. Login");
        System.out.println("2. Ubah password");
        System.out.println("3. Buat akun");
        System.out.println("4. Hapus akun");
        System.out.println("5. Keluar");
        System.out.print("Opsi: ");

        String user_option = user_scanner.nextLine();

        if (user_option.equals("1")) {
            System.out.print("\n");
            System.out.println("========== Login ==========");
            System.out.println("ID Akun:");
            String id_input = user_scanner.nextLine();

            System.out.println("Password:");
            String pass_input = user_scanner.nextLine();

            boolean user_log = login(id_input, pass_input, option);

            if (user_log == true) {

            }
            else {
                return;
            }
        } 
        else if (user_option.equals("2")) {
            System.out.print("\n");
            System.out.println("======= Ubah password =======");
            System.out.println("ID Akun:");
            String id_input = user_scanner.nextLine();

            System.out.println("Password baru:");
            String new_pass_input = user_scanner.nextLine();

            try {
                FileReader users_txt = new FileReader("users.txt");
                BufferedReader buffer = new BufferedReader(users_txt);

                akun.setIdPass(id_input, new_pass_input, buffer);

                buffer.close();
            }
            catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        else if (user_option.equals("3")) {
            System.out.print("\n");
            System.out.println("======= Buat akun ========");
            System.out.println("ID Akun:");
            String id_input = user_scanner.nextLine();

            System.out.println("Password:");
            String pass_input = user_scanner.nextLine();

            try {
                FileReader users_txt = new FileReader("users.txt");
                BufferedReader buffer = new BufferedReader(users_txt);

                akun.createAkun(id_input, pass_input, buffer);

                buffer.close();
            }
            catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        else if (user_option.equals("4")) {
            System.out.println("\n");
            System.out.println("======= Hapus akun ========");
            System.out.println("ID Akun:");
            String id_input = user_scanner.nextLine();

            System.out.println("Password:");
            String pass_input = user_scanner.nextLine();

            akun.deleteAkun(id_input, pass_input);
        }
        else if (user_option.equals("5")) {
            return;
        }
        else {
            System.out.println("\nError: Opsi tidak tersedia");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner main_scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\n");
                System.out.println("========== Login ==========");
                System.out.println("1. Admin");
                System.out.println("2. User");
                System.out.println("3. Keluar");
                System.out.print("Opsi: ");

                String option = main_scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.print("\n");
                        main.admin_login("Admin");
                    case "2":
                        System.out.print("\n");
                        main.user_login("User");
                    case "3":
                        System.out.print("\n");
                        System.out.println("Bye :)");
    
                        return;
                    default:
                        System.out.print("\n");
                        System.out.println("Error: Opsi tidak tersedia");
                }
            }
            catch (NoSuchElementException e) {
                System.out.println("Error: Terjadi kesalahan");

                break;
            }
        }

        main_scanner.close();

        return;
    }
}
