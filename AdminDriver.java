import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class AdminDriver extends Driver {
    ListBarang listBarang = new ListBarang();
    List<Transaksi> listTransaksi = new ArrayList<>();
    List<Customer> listCustomer = new ArrayList<>();
    Customer akun;

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
        System.out.println("Daftar transaksi:");

        for (Transaksi transaksi : listTransaksi) {
            System.out.println(transaksi);
        }
    }

    public void loadTransaksi() { 
        try (BufferedReader reader = new BufferedReader(new FileReader("transaksi.txt"))) { 
            String line; 
            listTransaksi.clear(); 
            
            while ((line = reader.readLine()) != null) { 
                String[] parts = line.split(",\\s*"); 
                
                if (parts.length == 4) { 
                    String id = parts[0]; 
                    String akunId = parts[1]; 
                    String barangStr = parts[2]; 
                    double total = Double.parseDouble(parts[3]); 
                    Customer akun = null; 

                    for (Customer customer : listCustomer) { 
                        if (customer.getId().equals(akunId)) { 
                            akun = customer; 

                            break; 
                        } 
                    } 
                    
                    if (akun != null) { 
                        Transaksi transaksi = new Transaksi(id, akun); 

                        String[] barangArr = barangStr.split(";"); 

                        for (String barangInfo : barangArr) {
                            String[] barangParts = barangInfo.split(":");

                            if (barangParts.length == 2) {
                                String namaBarang = barangParts[0];
                                double hargaBarang = Double.parseDouble(barangParts[1]);
                                Barang barang = new Barang(namaBarang, hargaBarang);
                                
                                transaksi.addBarang(barang);
                            }
                        }
                        
                        listTransaksi.add(transaksi); 
                    } 
                } 
            } 
            
            System.out.println("Transaksi berhasil dimuat dari file"); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
            System.out.println("Error: Gagal membaca file transaksi"); 
        } 
    }
}
