import java.util.Scanner;

import Service.ToyService;
import mapping.dtos.ToyDTO;
import model.Toy;
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

            switch (choice) {
                case 1:
                    System.out.print("Enter Toy Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Toy Type: ");
                    int type = scanner.nextInt();
                    System.out.print("Enter Toy Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Toy Quantity: ");
                    int quantity = scanner.nextInt();

                    ToyDTO toy = new ToyDTO(name, type, price, quantity);
                    toyStoreService.addToy(toy);
                    System.out.println("Toy added successfully!");
                    break;

                case 2:
                    toyStoreService.showQuantityByType();
                    break;

                case 3:
                    int totalQuantity = toyStoreService.getTotalQuantity();
                    System.out.println("Total Quantity of Toys: " + totalQuantity);
                    break;

                case 4:
                    double totalValue = toyStoreService.getTotalValue();
                    System.out.println("Total Value of Toys: " + totalValue);
                    break;

                case 5:
                    System.out.print("Enter Toy Name to Decrease Stock: ");
                    String decreaseName = scanner.nextLine();
                    System.out.print("Enter Quantity to Decrease: ");
                    int decreaseQuantity = scanner.nextInt();
                    toyStoreService.decreaseStock(decreaseName, decreaseQuantity);
                    break;

                case 6:
                    System.out.print("Enter Toy Name to Increase Stock: ");
                    String increaseName = scanner.nextLine();
                    System.out.print("Enter Quantity to Increase: ");
                    int increaseQuantity = scanner.nextInt();
                    toyStoreService.increaseStock(increaseName, increaseQuantity);
                    break;

                case 7:
                    toyStoreService.showTypeWithMostToys();
                    break;

                case 8:
                    toyStoreService.showTypeWithLeastToys();
                    break;

                case 9:
                    System.out.print("Enter Value to Filter Toys: ");
                    double filterValue = scanner.nextDouble();
                    System.out.println("Toys with Value Greater Than " + filterValue + ":");
                    toyStoreService.getToysWithValueGreaterThan(filterValue)
                            .forEach(t -> System.out.println(t.getName()));
                    break;

                case 10:
                    toyStoreService.sortStockByType();
                    break;

                case 0:
                    exit = true;
                    System.out.println("Exiting the program. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }
}
