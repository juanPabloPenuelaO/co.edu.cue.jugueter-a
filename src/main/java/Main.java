import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import Service.ToyService;
import Service.ToyStoreServiceI;
import config.DatabaseConnection;
import mapping.dtos.*;

public class Main {

    private static ToyStoreServiceI toystore;

    public static void main(String[] args) throws Exception {
        try (Connection conn = DatabaseConnection.getInstance()) {
            toystore = new ToyStoreServiceI();

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Toy");
                System.out.println("2. Add Empleado");
                System.out.println("3. Add Cliente");
                System.out.println("4. Add Venta");
                System.out.println("5. Show Juguetes");
                System.out.println("6. Show Empleados");
                System.out.println("7. Show Ventas");
                System.out.println("8. Show Quantity by Type");
                System.out.println("9. Show Total Quantity");
                System.out.println("10. Show Total Value");
                System.out.println("11. Decrease Stock");
                System.out.println("12. Increase Stock");
                System.out.println("13. Show Type with Most Toys");
                System.out.println("14. Show Type with Least Toys");
                System.out.println("15. Get Toys with Value Greater Than");
                System.out.println("16. Sort Stock by Type");
                System.out.println("0. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new Toy:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Type: ");
                        int type = scanner.nextInt();
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Quantity: ");
                        int quantity = scanner.nextInt();

                        ToyDTO toyDTO = new ToyDTO(name, type, price, quantity);

                        CompletableFuture.runAsync(() -> {
                            try {
                                toystore.addToy(toyDTO);
                                System.out.println("Toy added successfully!");
                            } catch (Exception e) {
                                System.out.println("Error adding toy: " + e.getMessage());
                            }
                        });

                        break;

                    case 2:
                        System.out.println("Enter Employee:");
                        System.out.println("id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Name: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Sex: ");
                        String sexo = scanner.nextLine();
                        System.out.print("Joining Date (YYYY-MM-DD): ");
                        Date Fecha_ingreso = Date.valueOf(scanner.nextLine());
                        System.out.print("Status: ");
                        int Estado = Integer.parseInt(scanner.nextLine());

                        EmpleadoDTO empleadoDTO = new EmpleadoDTO(id, nombre, sexo, Fecha_ingreso, Estado);

                        CompletableFuture.runAsync(() -> {
                            try {
                                toystore.addEmpleado(empleadoDTO);
                                System.out.println("Employe added successfully");
                            } catch (Exception e) {
                                System.out.println("Error adding employe: " + e.getMessage());
                            }
                        });

                    case 0:
                        exit = true;
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }

            scanner.close();
        }
    }
}