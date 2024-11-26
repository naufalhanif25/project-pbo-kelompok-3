<<<<<<< HEAD

import javax.swing.*;

public class ListBarang extends JFrame{
  public ListBarang(){
    // Membuat JFrame untuk Create Account
    setTitle("e-commerce");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setSize(800, 600);
    setLocationRelativeTo(null);
  
    //ICON
    setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

    // Menambahkan Panel BeliBarang
    add(new ListBarangPanel());
    setVisible(true);
    
  }
}
  
=======
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListBarang {
    private List<Barang> listBarang = new ArrayList<>();
    private Barang barang;

    public void addBarang(Scanner scanner) {
        System.out.print("\n");
        System.out.println("======= Tambah Barang =======");
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();

        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Barang barang_item = new Barang(nama, harga, stok, barang.newId());
        
        listBarang.add(barang_item);

        saveBarang();
        
        System.out.println("Barang berhasil ditambahkan");
    }

    public void deleteBarang(Scanner scanner) {
        System.out.println("\n======= Hapus Barang =======");
        
        showBarang();
        
        System.out.print("ID Barang: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 
        
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

            System.out.print("Stok Barang baru: ");
            int stok = scanner.nextInt();
            scanner.nextLine();
            
            Barang barang_item = listBarang.get(index);
            
            barang_item.setNama(nama);
            barang_item.setHarga(harga);
            barang_item.setStok(stok);
            
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
            
            System.out.println(barang_item.getId() + " - " + barang_item.getNama() + " - Rp" + barang_item.getHarga() + " - Stok: " + barang_item.getStok());
        }
    }

    public void loadBarang() {
        listBarang.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 4) {
                    String id = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    int stok = Integer.parseInt(parts[3]);
                    
                    Barang barang = new Barang(nama, harga, stok, id);
                    
                    listBarang.add(barang);
                }
            }

            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBarang() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("barang.txt"))) {
            for (Barang barang : listBarang) {
                writer.write(barang.getId() + ", " + barang.getNama() + ", " + barang.getHarga() + ", " + barang.getStok());
                writer.newLine();
            }

            writer.close();
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
>>>>>>> ab9abdc2f9fbc8c091acbfaef1019c63972bdb8f
