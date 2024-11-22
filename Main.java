import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Daftar produk
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 15000000));
        products.add(new Product(2, "Mouse", 50000));
        products.add(new Product(3, "Keyboard", 200000));
        products.add(new Product(4, "Flash Drive", 100000));
        products.add(new Product(5, "Notebook", 25000));
        products.add(new Product(6, "Pen", 5000));
        products.add(new Product(7, "Backpack", 300000));
        products.add(new Product(8, "Headphones", 400000));
        products.add(new Product(9, "Power Bank", 250000));
        products.add(new Product(10, "Water Bottle", 50000));
        products.add(new Product(11, "Calculator", 150000));
        products.add(new Product(12, "Highlighter", 30000));
        products.add(new Product(13, "Binder", 70000));
        products.add(new Product(14, "Ruler", 10000));
        products.add(new Product(15, "Sticky Notes", 15000));
        products.add(new Product(16, "Portable Charger", 300000));
        products.add(new Product(17, "Bluetooth Speaker", 600000));
        products.add(new Product(18, "Monitor", 2000000));
        products.add(new Product(19, "Printer", 1500000));
        products.add(new Product(20, "Desk Lamp", 300000));

        // Autentikasi Pelanggan
        System.out.println("=== SELAMAT DATANG DI SISTEM PERBELANJAAN MAHASISWA INFORMATIKA-23 ===");
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        // Membuat objek Customer
        Customer customer = new Customer(username, password);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Tambahkan ke Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Lihat Riwayat Pembelian");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (choice) {
                case 1:
                    System.out.println("\n=== Daftar Produk ===");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    System.out.print("Masukkan ID produk yang ingin ditambahkan ke keranjang (pisahkan dengan koma): ");
                    String[] input = scanner.nextLine().split(",");
                    for (String idStr : input) {
                        try {
                            int id = Integer.parseInt(idStr.trim());
                            Product product = products.stream()
                                    .filter(p -> p.getId() == id)
                                    .findFirst()
                                    .orElse(null);
                            if (product != null) {
                                customer.addToCart(product);
                                System.out.println(product.getName() + " telah ditambahkan ke keranjang.");
                            } else {
                                System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Input tidak valid: " + idStr);
                        }
                    }
                    break;

                case 3:
                    customer.checkout();  // Melakukan checkout

                    // Menambahkan pilihan metode pembayaran
                    System.out.println("\n=== Pilih Metode Pembayaran ===");
                    System.out.println("1. QRIS");
                    System.out.println("2. Transfer Bank");
                    System.out.println("3. COD (Cash on Delivery)");
                    System.out.print("Pilih metode pembayaran: ");
                    int paymentChoice = scanner.nextInt();
                    scanner.nextLine();  // Membersihkan newline setelah input pilihan

                    PaymentMethod paymentMethod;
                    switch (paymentChoice) {
                        case 1:
                            paymentMethod = new QRISPayment();
                            break;
                        case 2:
                            paymentMethod = new BankPayment();
                            break;
                        case 3:
                            paymentMethod = new CODPayment();
                            break;
                        default:
                            System.out.println("Metode pembayaran tidak valid, menggunakan QRIS sebagai default.");
                            paymentMethod = new QRISPayment();
                            break;
                    }

                    // Melakukan pembayaran setelah checkout
                    System.out.print("Masukkan jumlah pembayaran: Rp");
                    int paymentAmount = scanner.nextInt();
                    paymentMethod.pay(paymentAmount);
                    break;

                case 4:
                    customer.viewPurchaseHistory();  // Melihat riwayat pembelian
                    break;

                case 5:
                    System.out.println("THANKS YOU SO MUCH. HAPPY SHOPPING DAY");
                    isRunning = false;  // Keluar dari sistem
                    break;

                default:
                    System.out.println("Opsi tidak valid, coba lagi.");
            }
        }

        scanner.close();
    }
}
