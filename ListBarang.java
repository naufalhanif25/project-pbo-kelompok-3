import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    List<Barang> listBarang = new ArrayList<>();

    public void addBarang(Scanner scanner) {
        System.out.print("\n");
        System.out.println("======= Tambah Barang =======");
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();

        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        Barang barang_item = new Barang(nama, harga);
        
        listBarang.add(barang_item);

        saveBarang();
        
        System.out.println("Barang berhasil ditambahkan");
    }

    public void deleteBarang(Scanner scanner) {
        System.out.println("\n======= Hapus Barang =======");
        
        showBarang();
        
        System.out.print("ID Barang: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Konsumsi karakter newline
        
        if (index >= 0 && index < listBarang.size()) {
            Barang removedBarang = listBarang.remove(index);
            
            saveBarang();
            
            System.out.println("Barang berhasil dihapus: " + removedBarang);
        } 
        else {
            System.out.println("Error: ID barang tidak valid");
        }
    }

    public void editBarang(Scanner scanner) {
        System.out.print("\n");
        System.out.println("======= Edit Barang =======");
        
        showBarang();
        
        System.out.print("ID Barang: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        
        if (index >= 0 && index < listBarang.size()) {
            System.out.print("Nama Barang baru: ");
            String nama = scanner.nextLine();
            
            System.out.print("Harga Barang baru: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();
            
            Barang barang_item = listBarang.get(index);
            
            barang_item.setNama(nama);
            barang_item.setHarga(harga);
            
            saveBarang();
            
            System.out.println("Barang berhasil diubah");
        } 
        else {
            System.out.println("Error: ID barang tidak valid");
        }
    }

    public void showBarang() {
        System.out.print("\n");
        System.out.println("======= Daftar Barang =======");
        
        for (int index = 0; index < listBarang.size(); index++) {
            Barang barang_item = listBarang.get(index);
            
            System.out.println(index + ". " + barang_item.getNama() + " - Rp" + barang_item.getHarga());
        }
    }

    public void loadBarang() {
        listBarang.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 2) {
                    String nama = parts[0];
                    double harga = Double.parseDouble(parts[1]);
                    
                    Barang barang = new Barang(nama, harga);
                    
                    listBarang.add(barang);
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Barang berhasil dimuat dari file");
    }

    public void saveBarang() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("barang.txt"))) {
            for (Barang barang : listBarang) {
                writer.write(barang.getNama() + ", " + barang.getHarga());
                writer.newLine();
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Barang berhasil disimpan ke file");
    }

    public List<Barang> getBarangList() {
        return listBarang;
    }
}
