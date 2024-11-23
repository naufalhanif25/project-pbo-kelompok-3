import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDriver extends Driver {
    private Customer akun;
    private List<Transaksi> listTransaksi = new ArrayList<>();

    public CustomerDriver(Customer akun) {
        this.akun = akun;
    }

    @Override
    public void addBarang(Barang barang) {
        System.out.println("Customer tidak dapat menambah barang ke dalam sistem");
    }

    @Override
    public void showTransaksi() {
        System.out.println("Daftar transaksi " + akun.getId() + ":");
        
        for (Transaksi transaksi : listTransaksi) {
            if (transaksi.getAkun().equals(akun)) {
                System.out.println(transaksi);
            }
        }
    }

    public void addTransaksi(String id_input, Barang barang) {
        List<String> data = new ArrayList<>();
        Keranjang keranjang = new Keranjang();
        String huruf;
        int angka;

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("transaksi.txt"))) {
            String line;

            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 4) { 
                    String id = parts[0]; 
                    
                    huruf = id.replaceAll("[^A-Z]", "");
                    angka = Integer.parseInt(id.replaceAll("[^0-9]", ""));

                    data.add(huruf + (angka + 1));
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("transaksi.txt", true))) {    
            keranjang.addBarang(barang);

            buffer_write.write(data.get(data.size() - 1) + ", " + id_input + ", " + barang.getBarang() + ", " + keranjang.calculateHarga());
            buffer_write.newLine();

            System.out.print("\n");
            System.out.println("Transaksi berhasil diproses");

            buffer_write.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
