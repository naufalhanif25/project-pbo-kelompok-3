import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    private static ArrayList<Barang> daftarBarang = new ArrayList<>();
    private static Pelanggan pelangganLogin = null;

    public static void main(String[] args) {
        inisialisasiDataBarang();

        while (true) {
            System.out.println("=== Menu Utama ===");
            System.out.println("1. Login Pelanggan");
            System.out.println("2. Buat Akun Pelanggan");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = scanner.nextInt();
            scanner.nextLine();  // Membaca sisa newline yang ada setelah nextInt()

            if (opsi == 1) {
                loginPelanggan();
            } else if (opsi == 2) {
                buatAkunPelanggan();
            } else if (opsi == 3) {
                System.out.println("Terima kasih! Keluar dari program.");
                break;
            }
        }
    }

    public static void inisialisasiDataBarang() {
        daftarBarang.add(new Barang("B001", "Pensil", 2000));
        daftarBarang.add(new Barang("B002", "Buku", 10000));
        daftarBarang.add(new Barang("B003", "Penghapus", 1500));
    }

    private static void loginPelanggan() {
        System.out.print("Masukkan username pelanggan: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        for (Pelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getUsername().equals(username) && pelanggan.getPassword().equals(password)) {
                pelangganLogin = pelanggan;
                System.out.println("Login berhasil!");
                menuPelanggan();
                return;
            }
        }

        System.out.println("Login gagal. Username atau password salah.");
    }

    private static void buatAkunPelanggan() {
        System.out.print("Masukkan username baru: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password baru: ");
        String password = scanner.nextLine();

        Pelanggan pelangganBaru = new Pelanggan(username, password);
        daftarPelanggan.add(pelangganBaru);
        System.out.println("Akun berhasil dibuat!");
    }

    private static void menuPelanggan() {
        while (true) {
            System.out.println("\n=== Menu Pelanggan ===");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Lihat Barang di Keranjang");
            System.out.println("4. Lihat Hasil Belanja");
            System.out.println("5. Checkout");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = scanner.nextInt();
            scanner.nextLine();

            if (opsi == 1) {
                lihatBarang();
            } else if (opsi == 2) {
                tambahKeKeranjang();
            } else if (opsi == 3) {
                lihatBarangDiKeranjang();
            } else if (opsi == 4) {
                lihatHasilBelanja();
            } else if (opsi == 5) {
                checkout();
                return; // Keluar dari menu pelanggan setelah checkout
            } else if (opsi == 6) {
                System.out.println("Keluar dari menu pelanggan.");
                return; // Keluar dari menu pelanggan
            }
        }
    }

    private static void lihatBarang() {
        System.out.println("\n=== Daftar Barang ===");
        for (Barang barang : daftarBarang) {
            System.out.println(barang);
        }
    }

    private static void tambahKeKeranjang() {
        System.out.print("Masukkan kode barang: ");
        String kode = scanner.nextLine();
        for (Barang barang : daftarBarang) {
            if (barang.getKode().equals(kode)) {
                pelangganLogin.getKeranjang().tambahBarang(barang);
                System.out.println("Barang berhasil ditambahkan ke keranjang.");
                return;
            }
        }
        System.out.println("Barang dengan kode tersebut tidak ditemukan.");
    }

    private static void lihatBarangDiKeranjang() {
        System.out.println("\n=== Barang di Keranjang ===");
        if (pelangganLogin.getKeranjang().getDaftarBarang().isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            for (Barang barang : pelangganLogin.getKeranjang().getDaftarBarang()) {
                System.out.println(barang);
            }
        }
    }

    private static void lihatHasilBelanja() {
        System.out.println("\n=== Hasil Belanja ===");
        if (pelangganLogin.getKeranjang().getDaftarBarang().isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("Total Belanja: Rp" + pelangganLogin.getKeranjang().getTotalHarga());
        }
    }

    private static void checkout() {
        if (pelangganLogin.getKeranjang().getDaftarBarang().isEmpty()) {
            System.out.println("Keranjang kosong. Tidak ada barang untuk checkout.");
            return;
        }

        System.out.println("\nTotal Belanja: Rp" + pelangganLogin.getKeranjang().getTotalHarga());
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Transfer Bank");
        System.out.println("2. COD");
        System.out.println("3. QRIS");
        System.out.print("Pilih opsi: ");
        int metodePembayaran = scanner.nextInt();
        scanner.nextLine();  // Membaca newline setelah pilihan

        Pembayaran pembayaran = null;
        if (metodePembayaran == 1) {
            pembayaran = new Bank();
        } else if (metodePembayaran == 2) {
            pembayaran = new COD();
        } else if (metodePembayaran == 3) {
            pembayaran = new QRIS();
        }

        if (pembayaran != null) {
            pembayaran.pay(pelangganLogin.getKeranjang().getTotalHarga());
            System.out.println("Checkout berhasil. Transaksi Anda telah diproses.");
        }
    }
}

class Pelanggan {
    private String username;
    private String password;
    private Keranjang keranjang;

    public Pelanggan(String username, String password) {
        this.username = username;
        this.password = password;
        this.keranjang = new Keranjang();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }
}

class Barang {
    private String kode;
    private String nama;
    private int harga;

    public Barang(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return kode + ". " + nama + " - Rp " + harga;
    }
}

class Keranjang {
    private ArrayList<Barang> daftarBarang;

    public Keranjang() {
        this.daftarBarang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    public int getTotalHarga() {
        int total = 0;
        for (Barang barang : daftarBarang) {
            total += barang.getHarga();
        }
        return total;
    }
}

interface Pembayaran {
    void pay(int amount);
}

class Bank implements Pembayaran {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: Transfer Bank");
        System.out.println("Silakan transfer sejumlah Rp" + amount + " ke rekening bank kami.");
    }
}

class COD implements Pembayaran {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: COD (Cash on Delivery)");
        System.out.println("Pesanan Anda akan dikirimkan, dan pembayaran sebesar Rp" + amount + " dilakukan saat barang diterima.");
    }
}

class QRIS implements Pembayaran {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: QRIS");
        System.out.println("Silakan scan QR Code berikut untuk melakukan pembayaran sejumlah Rp" + amount + " :");
        System.out.println("QR Code Pembayaran: [QRIS-" + amount + "]");
    }
}
