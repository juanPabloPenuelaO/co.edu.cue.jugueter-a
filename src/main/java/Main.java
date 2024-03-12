import java.util.Scanner;

import Service.ToyService;
import Service.ToyStoreServiceI;

public class Main {
    public static void main(String[] args) throws Exception {
    ToyService toyStoreService = new ToyStoreServiceI();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Toy");
            System.out.println("2. Show Quantity by Type");
            System.out.println("3. Show Total Quantity");
            System.out.println("4. Show Total Value");
            System.out.println("5. Decrease Stock");
            System.out.println("6. Increase Stock");
            System.out.println("7. Show Type with Most Toys");
            System.out.println("8. Show Type with Least Toys");
            System.out.println("9. Get Toys with Value Greater Than");
            System.out.println("10. Sort Stock by Type");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

        }

        scanner.close();
    }
}
