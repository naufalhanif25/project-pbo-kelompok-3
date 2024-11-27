import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLog {
    private String username;

    public ReadLog() {
        username = null;
    }

    public String readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Log.txt"))) {
            username = reader.readLine();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return username;
    }
}
