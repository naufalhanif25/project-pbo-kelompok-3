import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class AdminDriver extends Driver {
    ListBarang listBarang = new ListBarang();
    List<Transaksi> listTransaksi = new ArrayList<>();
    Admin akun;

    @Override
    public void addBarang(Barang barang) {
        listBarang.getBarangList().add(barang);
        listBarang.saveBarang();

        System.out.print("\n");
        System.out.println("Barang berhasil ditambahkan");
    }

    @Override
    public void showTransaksi() {
        loadTransaksi();

        System.out.print("\n");
        System.out.println("=== Daftar transaksi ===");

        for (Transaksi transaksi : listTransaksi) {
            System.out.println(transaksi);
        }
    }

    public void loadTransaksi() { 
        try (BufferedReader buffer_reader = new BufferedReader(new FileReader("transaksi.txt"))) { 
            String line; 

            listTransaksi.clear(); 
            
            while ((line = buffer_reader.readLine()) != null) { 
                String[] parts = line.split(",\\s*"); 
                
                if (parts.length == 4) { 
                    String id = parts[0]; 
                    String userId = parts[1];
                    String barangStr = parts[2]; 

                    Customer user = new Customer(userId, null);
                    
                    if (barangStr != null) {
                        Transaksi transaksi = new Transaksi(id, user);
                        String[] temp_items = barangStr.split(";");

                        for (String temp_item : temp_items) {
                            String[] items = temp_item.split(":");

                            if (items.length == 2) {
                                String barang_nama = items[0];
                                double barang_harga = Double.parseDouble(items[1]);
                                
                                Barang barang = new Barang(barang_nama, barang_harga);

                                transaksi.addBarang(barang);
                            }
                        }
                        
                        listTransaksi.add(transaksi);
                    }
                } 
            } 
            
            System.out.print("\n");
            System.out.println("Transaksi berhasil dimuat dari file"); 

            buffer_reader.close();
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
            System.out.print("\n");
            System.out.println("Error: Gagal membaca file transaksi"); 
        } 
    }
}
