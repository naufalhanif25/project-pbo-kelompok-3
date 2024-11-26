<<<<<<< HEAD

import javax.swing.*;

public class Invoice extends JFrame {
    private String metodePembayaran;

    public Invoice(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;  // Menyimpan metode pembayaran yang dipilih

        setTitle("e-commerce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocationRelativeTo(null);

        //Icon
        setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

        // Memanggil panel berdasarkan metode pembayaran yang dipilih
        JPanel paymentPanel = null;
        if (metodePembayaran.equals("QRIS")) {
            paymentPanel = new QrisPanel();  
        } else if (metodePembayaran.equals("BANK")) {
            paymentPanel = new BankPanel();  
        } else if (metodePembayaran.equals("COD")) {
            paymentPanel = new CodPanel();  
        }

        if (paymentPanel != null) {
            add(paymentPanel);  
        }

        setVisible(true);
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Keranjang keranjang;
    private String pembayaran;

    public Invoice(Keranjang keranjang) {
        this.keranjang = keranjang;
    }

    public Invoice(Keranjang keranjang, String pembayaran) {
        this.keranjang = keranjang;
        this.pembayaran = pembayaran;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    public String getNama() {
        return keranjang.getUserId();
    }

    public String newId() {
        List<String> idData = new ArrayList<>();

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("invoice.txt"))) {
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
                        huruf = "INV";
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

    public void addInvoice(String data, double total) {
        try (BufferedWriter buffer_write = new BufferedWriter(new FileWriter("invoice.txt", true))) {            
            buffer_write.write(newId() + ", " + data + ", " + getPembayaran());
            buffer_write.newLine();

            System.out.print("\n");
            System.out.println("Invoice berhasil diproses");

            buffer_write.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        if (getPembayaran().equals("Bank")) {
            Bank bank = new Bank();

            bank.bayar(total);
        }
        else if (getPembayaran().equals("COD")) {
            COD cod = new COD();

            cod.bayar(total);
        }
        else if (getPembayaran().equals("QRIS")) {
            QRIS qris = new QRIS();

            qris.bayar(total);
        }
        else {
            System.out.println("Error: Pembayaran tidak valid");
        }
    }

    public void showInvoice() {
        List<String> data = new ArrayList<>();
        
        System.out.println("========== Invoice ==========");

        try (BufferedReader buffer_read = new BufferedReader(new FileReader("invoice.txt"))) {
            String line;
            
            while ((line = buffer_read.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                
                if (parts.length == 5) {
                    String nama = parts[1];

                    if (nama.equals(getNama())) {
                        data.add(line);
                    }
                }
            }

            buffer_read.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String invoice : data) {
            System.out.println(invoice);
        }
>>>>>>> ab9abdc2f9fbc8c091acbfaef1019c63972bdb8f
    }
}