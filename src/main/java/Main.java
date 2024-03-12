import java.sql.Connection;
import java.sql.Date;
import java.util.List;
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
                        System.out.print("Joining(YYYY-MM-DD): ");
                        Date Fecha_ingreso = Date.valueOf(scanner.nextLine());
                        System.out.print("Status: ");
                        String Estado = String.valueOf(Integer.parseInt(scanner.nextLine()));

                        EmpleadoDTO empleadoDTO = new EmpleadoDTO(id, nombre, sexo, Fecha_ingreso, Estado);

                        CompletableFuture.runAsync(() -> {
                            try {
                                toystore.addEmpleado(empleadoDTO);
                                System.out.println("Employe added successfully");
                            } catch (Exception e) {
                                System.out.println("Error adding employe: " + e.getMessage());
                            }
                        });

                        break;

                    case 3:
                        System.out.println("Enter Customer:");
                        System.out.println("id: ");
                        int Id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Name: ");
                        String Nombre = scanner.nextLine();
                        System.out.println("Sex: ");
                        String Sexo = scanner.nextLine();
                        System.out.println("birthDay(YYYY-MM-DD): ");
                        Date Fecha_nacimiento = Date.valueOf(scanner.nextLine());
                        System.out.println("children: ");
                        int hijos = Integer.parseInt(scanner.nextLine());
                        System.out.println("State: ");
                        String estado = scanner.nextLine();

                        ClienteDTO clienteDTO = new ClienteDTO(Id, Nombre, Sexo, Fecha_nacimiento, hijos, estado);

                        CompletableFuture.runAsync(() -> {
                            try {
                                toystore.addCliente(clienteDTO);
                                System.out.println("Customer added successfully");
                            } catch (Exception e) {
                                System.out.println("Error adding Customer: " + e.getMessage());
                            }
                        });

                        break;

                    case 4:
                        System.out.println("Enter Sale:");
                        System.out.println("id: ");
                        int ID = Integer.parseInt(scanner.nextLine());
                        System.out.println("offer Status : ");
                        String estado_Oferta = scanner.nextLine();
                        System.out.println("quantity: ");
                        int cantidad = Integer.parseInt(scanner.nextLine());
                        System.out.println("idToy: ");
                        int idToy = Integer.parseInt(scanner.nextLine());
                        System.out.println("idCustomer: ");
                        int idCliente = Integer.parseInt(scanner.nextLine());
                        System.out.println("idEmployee: ");
                        int idEmpleado = Integer.parseInt(scanner.nextLine());
                        System.out.println("Date of Sale(YYYY-MM-DD): ");
                        Date fecha_compra= Date.valueOf(scanner.nextLine());
                        System.out.println("idBill: ");
                        int idFactura = Integer.parseInt(scanner.nextLine());

                        VentaDTO ventaDTO = new VentaDTO(ID, estado_Oferta, cantidad, idToy, idCliente, idEmpleado, fecha_compra, idFactura);

                        CompletableFuture.runAsync(() -> {
                            try {
                                toystore.addVenta(ventaDTO);
                                System.out.println("Sale added successfully");
                            } catch (Exception e) {
                                System.out.println("Error adding Sale: " + e.getMessage());
                            }
                        });

                        break;

                    case 5:
                        CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                            List<ToyDTO> list = toystore.ListToy();
                            if (!list.isEmpty()) {
                                for (ToyDTO toys : list) {
                                    System.out.println(toys);
                                    System.out.println("loading");
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                System.out.println("There are no toys on the list");
                            }
                            return list;
                        });
                        future.join();
                        System.out.println("The task is completed");

                        break;

                    case 8:
                        toystore.showQuantityByType();
                    case 9:
                        toystore.getTotalQuantity();
                    case 10:
                        toystore.getTotalValue();
                    case 11:
                      //  toystore.decreaseStock();
                    case 12:
                     //   toystore.increaseStock();
                    case 13:
                        toystore.showTypeWithMostToys();
                    case 14:
                        toystore.showTypeWithLeastToys();
                    case 15:
                     //   toystore.getToysWithValueGreaterThan();
                    case 16:
                        toystore.sortStockByType();



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