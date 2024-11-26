<<<<<<< HEAD
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login();
            frame.setVisible(true);
        });
    }
}
=======
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public class Main {
    private Akun akun;

    public void admin_login(String option, Scanner scanner) {
        akun = new Akun();

        System.out.print("\n");
        System.out.println("========== Login ==========");

        System.out.println("ID Akun:");
        String id_input = scanner.nextLine();

        System.out.println("Password:");
        String pass_input = scanner.nextLine();

        boolean admin_log = akun.login(id_input, pass_input, option);

        if (admin_log == true) {
            Admin admin = new Admin(id_input, pass_input);

            admin.manageBarang();
        }
    }

    public void user_login(String option, Scanner scanner) {
        akun = new Akun();

        System.out.print("\n");
        System.out.println("========== Login ==========");
        System.out.println("1. Login");
        System.out.println("2. Ubah password");
        System.out.println("3. Buat akun");
        System.out.println("4. Hapus akun");
        System.out.println("5. Keluar");
        System.out.print("Opsi: ");

        String user_option = scanner.nextLine();

        if (user_option.equals("1")) {
            System.out.print("\n");
            System.out.println("========== Login ==========");

            System.out.println("ID Akun:");
            String id_input = scanner.nextLine();

            System.out.println("Password:");
            String pass_input = scanner.nextLine();

            boolean user_log = akun.login(id_input, pass_input, option);

            if (user_log == true) {
                Customer customer = new Customer(id_input, pass_input);

                customer.customerMenu(scanner);
            }
        } 
        else if (user_option.equals("2")) {
            System.out.print("\n");
            System.out.println("======= Ubah password =======");
            System.out.println("ID Akun:");
            String id_input = scanner.nextLine();

            System.out.println("Password baru:");
            String new_pass_input = scanner.nextLine();

            try {
                akun.setIdPass(id_input, new_pass_input);
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        else if (user_option.equals("3")) {
            System.out.print("\n");
            System.out.println("======= Buat akun ========");
            System.out.println("ID Akun:");
            String id_input = scanner.nextLine();

            System.out.println("Password:");
            String pass_input = scanner.nextLine();

            try {
                akun.createAkun(id_input, pass_input);
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        else if (user_option.equals("4")) {
            System.out.println("\n");
            System.out.println("======= Hapus akun ========");
            System.out.println("ID Akun:");
            String id_input = scanner.nextLine();

            System.out.println("Password:");
            String pass_input = scanner.nextLine();

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\n");
                System.out.println("========== Login ==========");
                System.out.println("1. Admin");
                System.out.println("2. User");
                System.out.println("3. Keluar");
                System.out.print("Opsi: ");

                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.print("\n");
                        main.admin_login("Admin", scanner);

                        break;
                    case "2":
                        System.out.print("\n");
                        main.user_login("User", scanner);

                        break;
                    case "3":
                        System.out.print("\n");
                        System.out.println("Bye :)");

                        return;
                    default:
                        System.out.print("\n");
                        System.out.println("Error: Opsi tidak tersedia");

                        break;
                }
            }
            catch (NoSuchElementException e) {
                System.out.println("Error: Terjadi kesalahan");

                break;
            }
        }

        scanner.close();
    }
}
>>>>>>> ab9abdc2f9fbc8c091acbfaef1019c63972bdb8f
