import java.util.Scanner;

public class Admin extends Akun {
    private ListBarang listBarang = new ListBarang();
    private AdminDriver adminDriver = new AdminDriver();
    private String id;
    private String pass;
    
    public Admin(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void manageBarang() {
        listBarang.loadBarang(); 

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n");
            System.out.println("========== Admin ==========");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Lihat Transaksi");
            System.out.println("6. Keluar");
            System.out.print("Opsi: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    listBarang.addBarang(scanner);

                    break;
                case "2":
                    listBarang.deleteBarang(scanner);

                    break;
                case "3":
                    listBarang.editBarang(scanner);

                    break;
                case "4":
                    listBarang.showBarang();
                    
                    break;
                case "5":
                    adminDriver.showTransaksi();

                    break;
                case "6":
                    return;
                default:
                    System.out.print("\n");
                    System.out.println("Error: Opsi tidak valid");

                    break;
            }
        }
    }
}
