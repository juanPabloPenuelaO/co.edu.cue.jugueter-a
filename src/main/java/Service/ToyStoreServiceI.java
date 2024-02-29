package Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;
import utils.Constants;
import utils.FileUtils;

public class ToyStoreServiceI implements ToyService {

    private List<Toy> toys;


    public ToyStoreServiceI() {
        try {
            CompletableFuture.runAsync(() -> {
                try {
                    toys = FileUtils.getToys(new File(Constants.PATH_Toys));
                } catch (Exception e) {
                    System.out.println("Error loading toys from file: " + e.getMessage());
                    toys = new ArrayList<>();
                }
            }).join(); // Espera a que el CompletableFuture se complete para cargar archivos en cada hilo
        } catch (Exception e) {
            System.out.println("Error in constructor: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public Map<Integer, Integer> showQuantityByType() {
        try {
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toy toy : toys) {
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
        for (Toy toy : toys) {
            totalQuantity += (int) toy.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (Toy toy : toys) {
            totalValue += toy.getPrice() * toy.getQuantity();
        }
        return totalValue;
    }

    public void decreaseStock(String name, int quantity) {
        try {
            for (Toy toy : toys) {
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

            for (Toy toy : toys) {
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

        for (Toy toy : toys) {
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

            for (Toy toy : toys) {
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


    public List<ToyDTO> getToysWithValueGreaterThan(double value) {
        try {
            if (toys != null && !toys.isEmpty()) {
                List<ToyDTO> toyList = toys.stream()
                        .filter(toy -> toy.getPrice() > value)
                        .map(ToyMapper::mapFromDTO)
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


    public List<ToyDTO> sortStockByType() {
        try {
            if (toys == null || toys.isEmpty()) {
                System.out.println("No toys found in the store.");
                return null;
            }
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toy toy : toys) {
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
    public boolean verifyToyExists(String name) {
        return toys != null && toys.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }



    @Override
    public List<Toy> addToy(Toy toy) {
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
    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public Toy getToy(String name) {
        for (Toy toy : toys) {
            if (toy.getName().equalsIgnoreCase(name)) {
                return toy;
            }
        }
        return null;
    }


}
