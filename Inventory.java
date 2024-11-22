import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class Inventory {
    private HashMap<String, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public void addItem(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) {
            inventory.put(itemName, inventory.get(itemName) + quantity);
        } 
        else {
            inventory.put(itemName, quantity);
        }

        saveInventoryToFile("data.txt");
        System.out.println(itemName + " (q: " + quantity + ") successfully added");
    }

    public void searchItem(String itemName) {
        if (inventory.containsKey(itemName)) {
            System.out.println(itemName + " (q: " + inventory.get(itemName) + ") found");
        } 
        else {
            System.out.println(itemName + " not found");
        }
    }

    public void displayItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
        } 
        else {
            System.out.println("Items list:");

            for (String itemName : inventory.keySet()) {
                System.out.println(itemName + ": " + inventory.get(itemName));
            }
        }
    }

    public void removeItem(String itemName) {
        if (inventory.containsKey(itemName)) {
            inventory.remove(itemName);
            saveInventoryToFile("data.txt");

            System.out.println(itemName + " successfully deleted");
        } else {
            System.out.println(itemName + " not found");
        }
    }

    public void saveInventoryToFile(String fileName) {
        try { 
            File file = new File(fileName); 
            
            if (!file.exists()) { 
                file.createNewFile(); 
            } 
            try (FileWriter writer = new FileWriter(file)) { 
                for (String itemName : inventory.keySet()) { 
                    writer.write(itemName + "," + inventory.get(itemName) + "\n"); 
                } 
            } 
        } 
        catch (IOException e) { 
            System.out.println("Error: " + e.getMessage()); 
        }
    }

    public void loadInventoryFromFile(String fileName) {
        try { 
            File file = new File(fileName); 
            
            if (file.exists()) { 
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) { 
                    String line; 
                    
                    while ((line = reader.readLine()) != null) { 
                        String[] parts = line.split(","); 
                        
                        if (parts.length == 2) { String itemName = parts[0]; 
                            int quantity = Integer.parseInt(parts[1]); 
                            
                            inventory.put(itemName, quantity); 
                        } 
                    } 
                } 
            } 
            else { 
                file.createNewFile(); 
            } 
        } 
        catch (IOException e) { 
            System.out.println("Error: " + e.getMessage()); 
        } 
    }

    public void enterLine(int line) {
        for (int enter = 0; enter < line; enter++) {
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Inventory storeInventory = new Inventory();

        storeInventory.loadInventoryFromFile("data.txt");

        Scanner scanner = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.println("Enter command (add, search, display, remove, exit): ");
            System.out.print("> ");

            command = scanner.nextLine();

            storeInventory.enterLine(1);

            switch (command) {
                case "add":
                    System.out.println("Enter item name: ");
                    System.out.print("> ");

                    String addItemName = scanner.nextLine();

                    storeInventory.enterLine(1);
                    System.out.println("Enter quantity: ");
                    System.out.print("> ");

                    int addQuantity = scanner.nextInt();

                    scanner.nextLine(); // Menyerap newline character
                    storeInventory.enterLine(1);
                    storeInventory.addItem(addItemName, addQuantity);
                    storeInventory.enterLine(1);

                    break;
                case "search":
                    System.out.println("Enter item name: ");
                    System.out.print("> ");

                    String searchItemName = scanner.nextLine();

                    storeInventory.enterLine(1);
                    storeInventory.searchItem(searchItemName);
                    storeInventory.enterLine(1);

                    break;
                case "display":
                    storeInventory.displayItems();
                    storeInventory.enterLine(1);

                    break;
                case "remove":
                    System.out.println("Enter item name: ");
                    System.out.print("> ");

                    String removeItemName = scanner.nextLine();

                    storeInventory.enterLine(1);
                    storeInventory.removeItem(removeItemName);
                    storeInventory.enterLine(1);

                    break;
                case "exit":
                    System.out.println("Bye");
                    storeInventory.enterLine(1);

                    scanner.close();

                    return;
                default:
                    System.out.println("Invalid command");

                    storeInventory.enterLine(1);
            }
        }
    }
}
