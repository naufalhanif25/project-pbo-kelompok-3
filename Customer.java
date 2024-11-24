import java.util.Scanner;

public class Customer {
    private String id;
    private String pass;
    private Keranjang keranjang;
    private ListBarang listBarang = new ListBarang();

    public Customer(String id, String pass) {
        this.id = id;
        this.pass = pass;
        this.keranjang = new Keranjang(id);
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void addBarang(Barang barang) {
        keranjang.addBarang(barang);
    }

    public void checkout(String pembayaran) {
        Invoice invoice = new Invoice(keranjang, pembayaran);

        invoice.showInvoice();
    }

    public void customerMenu(Scanner scanner) {
        listBarang.loadBarang(); 

        while (true) {
            System.out.print("\n");
            System.out.println("========== User ==========");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Checkout");
            System.out.println("3. History");
            System.out.println("4. Keluar");
            System.out.print("Opsi: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    listBarang.showBarang();

                    System.out.print("\n");
                    System.out.println("======= Opsi =======");
                    System.out.println("1. Masukkan ke Keranjang");
                    System.out.println("2. Kembali");
                    System.out.println("Opsi: ");
                    option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            System.out.print("\n");
                            System.out.println("Nama Barang: ");
                            String nama = scanner.nextLine();

                            Barang barang = new Barang(nama);
                            
                            keranjang.addKeranjang(barang);
                            
                            break;
                        case "2":
                            break;
                        default:
                            System.out.print("\n");
                            System.out.println("Error: Opsi tidak valid");

                            break;
                    }

                    System.out.print("\n");
                    System.out.println("Barang dimasukkan ke keranjang");

                    break;
                case "2":
                    keranjang.showKeranjang();

                    System.out.print("\n");
                    System.out.println("ID Keranjang: ");
                    String idKeranjang = scanner.nextLine();

                    Barang barang = new Barang(keranjang.getId(idKeranjang));

                    double total = keranjang.getTotal(barang.getId());

                    System.out.print("\n");
                    System.out.println("======== Pembayaran ========");
                    System.out.println("1. COD");
                    System.out.println("2. Transfer Bank");
                    System.out.println("3. QRIS");
                    System.out.println("Opsi:");
                    option = scanner.nextLine();

                    String invoice_data;

                    switch (option) {
                        case "1":
                            invoice_data = keranjang.checkoutBarang(barang.getId());

                            Invoice invoice = new Invoice(keranjang, "COD");

                            invoice.addInvoice(invoice_data, total);
                            
                            break;
                        case "2":
                            System.out.print("\n");
                            System.out.println("========== Bank ==========");
                            System.out.println("1. Bank Tamvan");
                            System.out.println("2. Kembali");
                            System.out.println("Opsi:");
                            option = scanner.nextLine();

                            switch (option) {
                                case "1":
                                    System.out.print("\n");
                                    System.out.println("No. Rek: 1234567890");
                                    System.out.println("Konfirmasi Pembayaran [Y/N]:");
                                    option = scanner.nextLine();

                                    switch (option) {
                                        case "Y":
                                            invoice_data = keranjang.checkoutBarang(barang.getId());

                                            invoice = new Invoice(keranjang, "Bank");
                                            
                                            invoice.addInvoice(invoice_data, total);
                                            
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            System.out.println("Error: Opsi tidak valid");

                                            break;
                                    }

                                    break;
                                case "2":
                                    break;
                                default:
                                    System.out.println("Error: Opsi tidak valid");

                                    break;
                            }


                            break;
                        case "3":      
                            System.out.print("\n");
                            System.out.println("========== QRIS ==========");
                            
                            QRIS qris = new QRIS();
                            
                            qris.showQR();

                            System.out.println("Konfirmasi Pembayaran [Y/N]:");
                            option = scanner.nextLine();

                            switch (option) {
                                case "Y":
                                    invoice_data = keranjang.checkoutBarang(barang.getId());

                                    invoice = new Invoice(keranjang, "QRIS");
        
                                    invoice.addInvoice(invoice_data, total);
                                    
                                    break;
                                case "N":
                                    break;
                                default:
                                    break;
                            }

                            break;
                        default:
                            System.out.println("Error: Opsi tidak valid");

                            break;
                    }

                    break;
                case "3":
                    Invoice invoice = new Invoice(keranjang);

                    invoice.showInvoice();

                    break;
                case "4":
                    return;
                default:
                    System.out.print("\n");
                    System.out.println("Error: Opsi tidak valid");

                    break;
            }
        }
    }
}
