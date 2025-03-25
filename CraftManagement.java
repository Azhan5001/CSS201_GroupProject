import java.util.Scanner;

class Craft {
    private String name;
    private int quantity;
    private double price;
    private String category;
    private String description;

    public Craft(String name, int quantity, double price, String category, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

public class CraftManagement {
    public static void main(String[] args) {
        final int maxCrafts = 100;
        Craft[] crafts = new Craft[maxCrafts];
        int ans = -1;
        Scanner scanner = new Scanner(System.in);

        crafts[0] = new Craft("Wooden Carving", 5, 25.50, "Woodwork", "Handcrafted wooden decoration.");
        crafts[1] = new Craft("Clay Pot", 10, 15.75, "Pottery", "Handmade clay pot for plants.");
        crafts[2] = new Craft("Beaded Necklace", 20, 8.99, "Jewelry", "Colorful handmade beaded necklace.");
        crafts[3] = new Craft("Glass Painting", 2, 45.00, "Painting", "Beautiful painting on glass.");
        crafts[4] = new Craft("Handwoven Basket", 15, 12.50, "Weaving", "Eco-friendly handwoven basket.");
        crafts[5] = new Craft("Resin Keychain", 30, 5.99, "Resin Art", "Personalized resin keychains.");
        crafts[6] = new Craft("Embroidered Cushion", 8, 29.99, "Embroidery", "Hand-stitched decorative cushion.");
        crafts[7] = new Craft("Origami Paper Art", 50, 3.50, "Paper Craft", "Intricate origami paper sculpture.");
        crafts[8] = new Craft("Ceramic Mug", 12, 18.75, "Pottery", "Hand-painted ceramic mug.");
        crafts[9] = new Craft("Macrame Wall Hanging", 4, 55.00, "Macrame", "Elegant macrame wall decor.");
    

        while (true) {
            hr();
            System.out.println("Craft Management System: ");
            hr();
            System.out.println("1. Add a Craft.\n2. View Available Crafts.\n3. Edit/Delete Craft.\n4. Search for Crafts.\n5. Exit.");
            hr();
            System.out.println("Please Enter Your choice: ");

            do {
                ans = intValidator(scanner);
                if (ans < 1 || ans > 5) {
                    System.out.println("Invalid Choice! Enter a number between [1-5]: ");
                } else break;
            } while (true);

            switch (ans) {
                case 1:
                    addCraft(scanner, crafts, maxCrafts);
                    break;
                case 2:
                    viewCrafts(crafts, maxCrafts);
                    break;
                case 3:
                    editDeleteMenu(scanner, crafts, maxCrafts);
                    break;
                case 4:
                    searchMenu(scanner, crafts, maxCrafts);
                    break;
                case 5:
                    System.out.println("Exiting Craft Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    break;
            }
        }
    }
    static void hr(){
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    static int intValidator(Scanner scanner) {
        String input;
        int ans = -1;

        do {
            input = scanner.nextLine();
            try {
                ans = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number: ");
            }
        } while (true);

        return ans;
    }

    static double doubleValidator(Scanner scanner) {
        String input;
        double ans = -1;

        do {
            input = scanner.nextLine();
            try {
                ans = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number: ");
            }
        } while (true);

        return ans;
    }

    static void editDeleteMenu(Scanner scanner, Craft crafts[], int maxCrafts) {
        int choice = -1;
        hr();
        System.out.println("Edit/Delete Menu:");
        hr();
        System.out.println("1. Edit Craft.\n2. Delete Craft.\n3. Main Menu.");
        hr();
        System.out.println("Please Enter a number: ");

        do {
            choice = intValidator(scanner);
            if (choice < 1 || choice > 3) System.out.println("Please enter a number between 1-3: ");
            else if (choice == 3) return;
        } while (choice < 1 || choice > 3);

        if (!viewCrafts(crafts, maxCrafts)) return;

        int index;
        while (true) {
            System.out.println("\nEnter the Craft ID: ");
            index = intValidator(scanner) - 1;
            if (index < 0 || index >= maxCrafts || crafts[index] == null) {
                System.out.println("Craft not found, please choose from the list: ");
            } else break;
        }

        if (choice == 1) editCraft(scanner, crafts, index);
        else if (choice == 2) deleteCraft(scanner, crafts, index, maxCrafts);
    }

    static void addCraft(Scanner scanner, Craft crafts[], int maxCrafts) {
        String name, category, description;
        int quantity;
        double price;

        System.out.println("\nPlease enter the name of the craft:");
        while ((name = scanner.nextLine().trim()).isEmpty()) {
            System.out.println("Name cannot be empty. Try again.");
        }

        System.out.println("Please enter the quantity of the craft");
        while ((quantity = intValidator(scanner)) <= 0) {
            System.out.println("Please enter a positive number: ");
        }

        System.out.println("Please enter the price of the craft");
        while ((price = doubleValidator(scanner)) <= 0) {
            System.out.println("Price must be positive. Please enter again: ");
        }

        System.out.println("Please enter the category of the craft");
        while ((category = scanner.nextLine().trim()).isEmpty()) {
            System.out.println("Category cannot be empty. Please enter again: ");
        }

        System.out.println("Please enter the description of the craft (optional):");
        description = scanner.nextLine().trim();

        for (int i = 0; i < maxCrafts; i++) {
            if (crafts[i] == null) {
                crafts[i] = new Craft(name, quantity, price, category, description);
                hr();
                System.out.println("Craft added successfully!");
                hr();
                System.out.println("");
                return;
            }
        }
        System.out.println("Unable to add craft, Inventory Full!");
    }

    static boolean viewCrafts(Craft crafts[], int maxCrafts) {
        boolean hasCrafts = false;
        hr();
        System.out.printf("%n|%-5s|%-20s|%-5s|%-10s|%-15s|%-30s|%n", "ID", "Name", "Qty", "Price", "Category", "Description");
        hr();

        for (int i = 0; i < maxCrafts; i++) {
            if (crafts[i] != null) {
                printCraft(crafts, i);
                hasCrafts = true;
            }
        }
        if (!hasCrafts) {
            System.out.println("No crafts Available.");
        }
        hr();
        return hasCrafts;
    }

    static void editCraft(Scanner scanner, Craft crafts[], int index) {
        String input;
        Craft craft = crafts[index];

        hr();
        System.out.printf("Editing craft: %s:%n", craft.getName());
        hr();
        System.out.printf("Edit Name: %s (leave blank to keep): ", craft.getName());
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) craft.setName(input);

        System.out.printf("Edit Quantity: %d (Enter 0 to keep): ", craft.getQuantity());
        int qty = intValidator(scanner);
        if (qty != 0) craft.setQuantity(qty);

        System.out.printf("Edit Price: $%.2f (Enter 0 to keep): ", craft.getPrice());
        double price = doubleValidator(scanner);
        if (price != 0) craft.setPrice(price);

        System.out.printf("Edit Category: %s (leave blank to keep): ", craft.getCategory());
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) craft.setCategory(input);

        System.out.printf("Edit Description: %s (leave blank to keep): ", craft.getDescription());
        input = scanner.nextLine();
        craft.setDescription(input);

        System.out.println("\nCraft edited successfully!");
    }

    static void deleteCraft(Scanner scanner, Craft crafts[], int index, int maxCrafts) {
        for (int i = index; i < maxCrafts - 1; i++) {
            crafts[i] = crafts[i + 1];
        }
        crafts[maxCrafts - 1] = null;
        System.out.println("\nCraft deleted successfully!");
    }

    static void printCraft(Craft crafts[], int i){
        System.out.printf("|%-5d|%-20.20s|%-5d|$%-9.2f|%-15.15s|%-30.30s|%n",
        i + 1,
        crafts[i].getName(),
        crafts[i].getQuantity(),
        crafts[i].getPrice(),
        crafts[i].getCategory(),
        crafts[i].getDescription());
    }

    static void searchMenu(Scanner scanner, Craft crafts[], int maxCrafts){
        int choice = -1;
        hr();
        System.out.println("Search By: ");
        hr();
        System.out.println("1. Search by Name.\n2. Search by Quantity.\n3. Search by Price.\n4. Search by Description.");
        hr();
        System.out.println("Choose a number: ");
        do {
            choice = intValidator(scanner);
            if (choice < 1 || choice > 4) System.out.println("Please enter a number between 1-3: ");
            else if (choice == 5) return;
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1:
                searchByName(scanner, crafts, maxCrafts);
                break;
            case 2:
                searchByQuantity(scanner, crafts, maxCrafts);
                break;
            case 3:
                searchByPrice(scanner, crafts, maxCrafts);
                break;
            case 4:
                searchByDescription(scanner, crafts, maxCrafts);
            default:
                break;
        }
    }

    static void searchByName(Scanner scanner, Craft crafts[], int maxCrafts){
        boolean found = false;
        System.out.println("Search by name: ");
        String searchInput = scanner.nextLine().toLowerCase();
        hr();
        for(int i = 0; i < maxCrafts; i++){
            if (crafts[i] != null && crafts[i].getName().toLowerCase().contains(searchInput)){
                if (!found){
                    System.out.printf("%n|%-5s|%-20s|%-5s|%-10s|%-15s|%-30s|%n", "ID", "Name", "Qty", "Price", "Category", "Description");
                    hr();
                    printCraft(crafts, i);
                    found = true;
                }else{
                    printCraft(crafts, i);
                }
            } 
        }
        if(!found) System.out.println("No Crafts Found!");
    }

    static void searchByQuantity(Scanner scanner, Craft crafts[], int maxCrafts){
        int maxQty = -1;
        int minQty = -1;
        boolean found = false;
        hr();
        System.out.println("Range of Qty:");
        hr();
        System.out.println("Enter Min qty: ");
        minQty = intValidator(scanner);
        System.out.println("Enter Max qty: ");
        maxQty = intValidator(scanner); 
        hr();

        for(int i = 0; i < maxCrafts; i++){
            if (crafts[i] != null && crafts[i].getQuantity() >= minQty && crafts[i].getQuantity() <= maxQty) {
                if(!found){
                    System.out.printf("%n|%-5s|%-20s|%-5s|%-10s|%-15s|%-30s|%n", "ID", "Name", "Qty", "Price", "Category", "Description");
                    hr();
                    printCraft(crafts, i);
                    found = true;
                } else{
                    printCraft(crafts, i);
                }
            }
        }
        if(!found) System.out.println("No Crafts Found!");
    }

    static void searchByPrice(Scanner scanner, Craft crafts[], int maxCrafts){
        double maxQty = -1;
        double minQty = -1;
        boolean found = false;
        hr();
        System.out.println("Range of Price:");
        hr();
        System.out.println("Enter Min Price: ");
        minQty = doubleValidator(scanner);
        System.out.println("Enter Max Price: ");
        maxQty = doubleValidator(scanner); 
        hr();

        for(int i = 0; i < maxCrafts; i++){
            if (crafts[i] != null && crafts[i].getPrice() >= minQty && crafts[i].getPrice() <= maxQty) {
                if(!found){
                    System.out.printf("%n|%-5s|%-20s|%-5s|%-10s|%-15s|%-30s|%n", "ID", "Name", "Qty", "Price", "Category", "Description");
                    hr();
                    printCraft(crafts, i);
                    found = true;
                } else{
                    printCraft(crafts, i);
                }
            }
        }
        if(!found) System.out.println("No Crafts Found!");
    }

    static void searchByDescription(Scanner scanner, Craft crafts[], int maxCrafts){
        boolean found = false;
        System.out.println("Search by Description: ");
        String searchInput = scanner.nextLine().toLowerCase();
        hr();
        for(int i = 0; i < maxCrafts; i++){
            if (crafts[i] != null && crafts[i].getDescription().toLowerCase().contains(searchInput)){
                if (!found){
                    System.out.printf("%n|%-5s|%-20s|%-5s|%-10s|%-15s|%-30s|%n", "ID", "Name", "Qty", "Price", "Category", "Description");
                    hr();
                    printCraft(crafts, i);
                    found = true;
                }else{
                    printCraft(crafts, i);
                }
            } 
        }
        if(!found) System.out.println("No Crafts Found!");
    }
}


