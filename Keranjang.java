import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Keranjang {
    private ArrayList<Barang> listBarang;
    private String id;
    
    public Keranjang() {
        listBarang = new ArrayList<>();
    }

    public Keranjang(String id) {
        listBarang = new ArrayList<>();
        this.id = id;
    }

    public void addBarang(Barang barang) {
        listBarang.add(barang);
    }

    public void showKeranjang() {
        List<String> data = new ArrayList<>();

        System.out.println("======= Keranjang =======");

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 3) {
                    String userId = parts[1];

                    if (userId.equals(id)) {
                        data.add(line);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String listKeranjang : data) {
            System.out.println(listKeranjang);
        }
    }

    public double calculateHarga() {
        double total = 0;

        for (Barang barang : listBarang) {
            total += barang.getHarga();
        }

        return total;
    }

    public void addKeranjang(Barang barang) {
        addBarang(barang);

        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("keranjang.txt", true))) {            
            buffer_write.write(newId() + ", " + id + ", " + barang.getBarang() + ", " + calculateHarga());
            buffer_write.newLine();

            System.out.print("\n");
            System.out.println("Transaksi berhasil diproses");

            buffer_write.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String newId() {
        List<String> idData = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {
            String line;
            String huruf;
            int angka;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 4) {
                    String temp_idStr = parts[0];
                        
                    huruf = temp_idStr.replaceAll("[^A-Z]", "");
                    angka = Integer.parseInt(temp_idStr.replaceAll("[^0-9]", ""));
    
                    idData.add(huruf + (angka + 1));
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return idData.get(idData.size() - 1);
    }

    public void checkoutBarang(String idBarang) {
        List<String> data = new ArrayList<>();
        List<Integer> line_pos = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            for (int line_index = 0; (line = buffer_read.readLine()) != null; line_index++) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 4) {
                    String temp_id = parts[0];

                    data.add(line);
                    
                    if (temp_id.equals(idBarang)) {
                        line_pos.add(line_index + 1);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        int lineToDelete = line_pos.get(0);

        if (lineToDelete >= 0 && lineToDelete < data.size()) {
            data.remove(lineToDelete);
        }

        try (BufferedWriter buffer_Write = new BufferedWriter(new FileWriter("keranjang.txt", false))) {
            for (String line : data) {
                buffer_Write.write(line);
                buffer_Write.newLine();
            }

            buffer_Write.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        editBarang(idBarang);
    }

    public void editBarang(String idBarang) {
        List<String> data = new ArrayList<>();
        List<Integer> line_pos = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("barang.txt"))) {            
            String line;
            
            for (int line_index = 0; (line = buffer_read.readLine()) != null; line_index++) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 4) {
                    String temp_id = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    int stok = Integer.parseInt(parts[3]);

                    
                    if (temp_id.equals(idBarang)) {
                        line_pos.add(line_index + 1);
                        data.add(temp_id + ", " + nama + ", " + harga + ", " + (stok - 1));
                    }
                    else {
                        data.add(temp_id + ", " + nama + ", " + harga + ", " + stok);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (line_pos.get(0) >= 0 && line_pos.get(0) < data.size()) {
            data.set(line_pos.get(0), idBarang);
        }

        try (BufferedWriter buffer_Write = new BufferedWriter(new FileWriter("barang.txt", false))) {
            for (String line : data) {
                buffer_Write.write(line);
                buffer_Write.newLine();
            }

            buffer_Write.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
