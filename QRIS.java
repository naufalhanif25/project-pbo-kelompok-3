import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QRIS implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran melalui QRIS sebesar Rp" + total + " berhasil diproses");
    }

    public void showQR() {
        try {
            String pythonPath = "python";
            String scriptPath = "qrCode.py";

            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath);
            processBuilder.redirectErrorStream(true);
            
            Process process = processBuilder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            String line;

            StringBuilder output = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            System.out.println(output.toString());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}