package repository.impl;

import config.DatabaseConnection;
import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toys;
import utils.Constants;
import utils.FileUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class toyRepositoryImpl implements ToyRepository<Toys> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private List<Toys> toys;

    public Map<Integer, Integer> showQuantityByType() {
        try {
            Map<Integer, Integer> quantityByType = (Map<Integer, Integer>) new ArrayList<>();

            for (Toys toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, (int) Math.round(quantityByType.getOrDefault(type, 0) + toy.getQuantity()));
            }

            for (Map.Entry<Integer, Integer> entry : quantityByType.entrySet()) {
                System.out.println("Type: " + entry.getKey() + ", Quantity: " + entry.getValue());
            }

            return quantityByType;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al mostrar la cantidad por tipo: " + e.getMessage());
        }
    }


    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Toys toy : toys) {
            totalQuantity += (int) toy.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (Toys toy : toys) {
            totalValue += toy.getPrice() * toy.getQuantity();
        }
        return totalValue;
    }

    public void decreaseStock(String name, int quantity) {
        try {
            for (Toys toy : toys) {
                if (toy.getName().equals(name)) {
                    int currentQuantity = toy.getQuantity();
                    if (currentQuantity >= quantity && quantity >= 0) {
                        toy.setQuantity(currentQuantity - quantity);

                        Thread.sleep(2000);

                        System.out.println("Stock decreased successfully for toy: " + name);
                        return;
                    } else {
                        System.out.println("Invalid quantity or insufficient stock for toy: " + name);
                        return;
                    }
                }
            }
            System.out.println("Toy not found with name: " + name);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public synchronized void increaseStock(String name, int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity should be greater than zero");
            }

            for (Toys toy : toys) {
                if (toy.getName().equalsIgnoreCase(name)) {
                    int currentQuantity = (int) toy.getQuantity();
                    toy.setQuantity(currentQuantity + quantity);
                    System.out.println("Stock increased successfully for toy: " + name);

                    Thread.sleep(2000);

                    return;
                }
            }
            System.out.println("Toy not found with name: " + name);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while sleeping: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.out.println("An error occurred while increasing stock: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public Map.Entry<Integer, Integer> showTypeWithMostToys() {
        if (toys == null || toys.isEmpty()) {
            System.out.println("No toys found in the store.");
            return null;
        }

        Map<Integer, Integer> quantityByType = new HashMap<>();

        for (Toys toy : toys) {
            int type = toy.getType();
            quantityByType.put(type, (int) (quantityByType.getOrDefault(type, 0) + toy.getQuantity()));
        }

        int maxType = -1;
        int maxQuantity = 0;
        for (Map.Entry<Integer, Integer> entry : quantityByType.entrySet()) {
            if (entry.getValue() > maxQuantity) {
                maxType = entry.getKey();
                maxQuantity = entry.getValue();
            }
        }

        if (maxType != -1) {
            System.out.println("Type with most toys: " + maxType);
        } else {
            System.out.println("No toys found in the store.");
        }
        return null;
    }

    public Map.Entry<Integer, Integer> showTypeWithLeastToys() {
        try {
            if (toys == null || toys.isEmpty()) {
                System.out.println("No toys found in the store.");
                return null;
            }

            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toys toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, (int) (quantityByType.getOrDefault(type, 0) + toy.getQuantity()));
            }

            int minType = -1;
            int minQuantity = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : quantityByType.entrySet()) {
                if (entry.getValue() < minQuantity) {
                    minType = entry.getKey();
                    minQuantity = entry.getValue();
                }
            }

            if (minType != -1) {
                System.out.println("Type with least toys: " + minType);
            } else {
                System.out.println("No toys found in the store.");
            }

            return null;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Toys> getToysWithValueGreaterThan(double value) {
        return null;
    }


   /* public List<Toys> getToysWithValueGreaterThan(double value) {
        try {
            if (toys != null && !toys.isEmpty()) {
                List<ToyDTO> toyList = toys.stream()
                        .filter(toy -> toy.getPrice() > value)
                        .map(ToyMapper::mapFromModel)
                        .collect(Collectors.toList());

                if (toyList.isEmpty()) {
                    System.out.println("There are no toys above that price");
                }
                return toyList;
            } else {
                System.out.println("The toy list is null or empty");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    */



    public List<Toys> sortStockByType() {
        try {
            if (toys == null || toys.isEmpty()) {
                System.out.println("No toys found in the store.");
                return null;
            }
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toys toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, quantityByType.getOrDefault(type, 0) + 1);
            }
            List<Integer> types = new ArrayList<>(quantityByType.keySet());
            Collections.sort(types);

            System.out.println("Quantity of toys by type in ascending order:");
            for (int type : types) {
                int quantity = quantityByType.get(type);
                System.out.println("Type: " + type + ", Quantity: " + quantity);
            }
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void setToys(List<Toys> toys) {
        this.toys = toys;
    }



    public Toys getToy(String name) {
        for (Toys toy : toys) {
            if (toy.getName().equalsIgnoreCase(name)) {
                return toy;
            }
        }
        return null;
    }


    @Override
    public List<Toys> list() {
        return null;
    }

    @Override
    public Toys byId(int id) {
        return null;
    }

    @Override
    public boolean verifyToyExists(String name) {
        return toys != null && toys.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }

    @Override
    public List<Toys> addToy(Toys toy) {
        try {
            if (!verifyToyExists(toy.getName())) {
                toys.add(toy);
                FileUtils.saveToys(new File(Constants.PATH_Toys), toys);
                return toys;
            } else {
                System.out.println("This toy already exists in the store.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred during toy addition.", e);
        }
    }

    @Override
    public void save(Toys toy) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                        INSERT INTO toys (name, type, price, quantity) values (?,?,?,?)
                        """)
        ){
            preparedStatement.setString(1,toy.getName());
            preparedStatement.setInt(2, toy.getType());
            preparedStatement.setDouble(3,toy.getPrice());
            preparedStatement.setInt(4,toy.getQuantity());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
