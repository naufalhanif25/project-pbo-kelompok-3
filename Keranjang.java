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

    public String getUserId() {
        return id;
    }

    public String getId(String idKeranjang_input) {
        List<String> data = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 5) {
                    String idKeranjang = parts[0];
                    String temp_barang = parts[3];

                    if (idKeranjang.equals(idKeranjang_input)) {
                        String[] barang_parts = temp_barang.split(":");

                        if (barang_parts.length == 2) {
                            String nama_barang = barang_parts[0];

                            data.add(nama_barang);
                        }
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        String namaBarang = data.get(0);

        if (!data.isEmpty()) {
            return namaBarang;
        }

        return null;
    }

    public Double getTotal(String barang) {
        List<Double> data = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 5) {
                    String idBarang = parts[1];
                    String nama = parts[2];
                    double total = Double.parseDouble(parts[4]);

                    if (idBarang.equals(barang) && nama.equals(id)) {
                        data.add(total);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        double totalHarga = data.get(0);

        if (!data.isEmpty()) {
            return totalHarga;
        }

        return null;
    }

    public void showKeranjang() {
        List<String> data = new ArrayList<>();

        System.out.println("======= Keranjang =======");

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 5) {
                    String userId = parts[2];

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

        if (!data.isEmpty()) {
            for (String listKeranjang : data) {
                System.out.println(listKeranjang);
            }
        }
    }

    public void showKeranjang(String userId) {
        List<String> data = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 5) {
                    String idStr = parts[2];

                    if (idStr.equals(userId)) {
                        data.add(line);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (!data.isEmpty()) {
            for (String listKeranjang : data) {
                System.out.println(listKeranjang);
            }
        }
    }    

    public double calculateHarga() {
        double total = 0;

        for (Barang barang : listBarang) {
            total += barang.getHarga();
        }

        return total;
    }

    public String newId() {
        List<String> idData = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {
            String line;
            String huruf;
            int angka;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 5) {
                    String temp_idStr = parts[0];
                    
                    if (temp_idStr != null) {
                        huruf = temp_idStr.replaceAll("[^A-Z]", "");
                        angka = Integer.parseInt(temp_idStr.replaceAll("[^0-9]", ""));
        
                        idData.add(huruf + (angka + 1));
                    }
                    else {
                        huruf = "K";
                        angka = 1;

                        idData.add(huruf + angka);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return idData.get(idData.size() - 1);
    }

    public void addKeranjang(Barang barang) {
        addBarang(barang);

        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("keranjang.txt", true))) {            
            buffer_write.write(newId() + ", " + barang.getId() + ", " + id + ", " + barang.getBarang() + ", " + calculateHarga());
            buffer_write.newLine();

            System.out.print("\n");
            System.out.println("Transaksi berhasil diproses");

            buffer_write.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkoutBarang(String idBarang) {
        List<String> data = new ArrayList<>();
        List<Integer> line_pos = new ArrayList<>();
        List<String> invoice_data = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("keranjang.txt"))) {            
            String line;
            
            for (int line_index = 0; (line = buffer_read.readLine()) != null; line_index++) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 5) {
                    String barangId = parts[1];
                    String nama = parts[2];
                    String barangInfo = parts[3];
                    double total = Double.parseDouble(parts[4]);

                    data.add(line);
                    
                    if (barangId.equals(idBarang) && nama.equals(id)) {
                        line_pos.add(line_index);

                        invoice_data.add(nama + ", " + barangInfo + ", " + total);
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

        String invoice = invoice_data.get(0);

        return invoice;
    }

    public void editBarang(String barang) {
        List<String> data = new ArrayList<>();
        List<Integer> line_pos = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("barang.txt"))) {            
            String line;
            
            for (int line_index = 0; (line = buffer_read.readLine()) != null; line_index++) {
                String[] parts = line.split(",\\s*");

                if (parts.length == 4) {
                    String idBarang = parts[0];
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    int stok = Integer.parseInt(parts[3]);

                    if (idBarang.equals(barang)) {
                        line_pos.add(line_index);

                        if (stok > 1) {
                            data.add(idBarang + ", " + nama + ", " + harga + ", " + (stok - 1));
                        }
                        else {
                            data.add(idBarang + ", " + nama + ", " + harga + ", " + stok);
                        }
                    }
                    else {
                        data.add(idBarang + ", " + nama + ", " + harga + ", " + stok);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        int lineToEdit = line_pos.get(0);

        if (line_pos.get(0) >= 0 && line_pos.get(0) < data.size()) {
            data.set(lineToEdit, data.get(lineToEdit));
        }
        else {
            data.remove(lineToEdit);
        }

        try (BufferedWriter buffer_Write = new BufferedWriter(new FileWriter("barang.txt", false))) {
            for (String line : data) {
                buffer_Write.write(line);
                buffer_Write.newLine();
            }

            System.out.println("Checkout barang berhasil");

            buffer_Write.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
