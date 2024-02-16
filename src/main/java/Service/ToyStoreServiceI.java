package Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;

    public class ToyStoreServiceI implements ToyService {
        private List<Toy> toys;

        public ToyStoreServiceI() {
            this.toys = new ArrayList<>();
        }


        public Map<Integer, Integer> showQuantityByType() {
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toy toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, quantityByType.getOrDefault(type, 0) + toy.getQuantity());
            }

            for (Map.Entry<Integer, Integer> entry : quantityByType.entrySet()) {
                System.out.println("Type: " + entry.getKey() + ", Quantity: " + entry.getValue());
            }
            return quantityByType;
        }
        public int getTotalQuantity() {
            int totalQuantity = 0;
            for (Toy toy : toys) {
                totalQuantity += toy.getQuantity();
            }
            return totalQuantity;
        }


        public double getTotalValue() {
            int totalValue = 0;
            for (Toy toy : toys) {
                totalValue += toy.getPrice() * toy.getQuantity();
            }
            return totalValue;
        }


        public void decreaseStock(String name, int quantity) {
            for (Toy toy : toys) {
                if (toy.getName().equalsIgnoreCase(name)) {
                    int currentQuantity = toy.getQuantity();
                    if (currentQuantity >= quantity) {
                        toy.setQuantity(currentQuantity - quantity);
                        System.out.println("Stock decreased successfully for toy: " + name);
                        return;
                    } else {
                        System.out.println("Insufficient stock for toy: " + name);
                        return;
                    }
                }
            }
            System.out.println("Toy not found with name: " + name);
        }
        public void increaseStock(String name, int quantity) {
            for (Toy toy : toys) {
                if (toy.getName().equalsIgnoreCase(name)) {
                    int currentQuantity = toy.getQuantity();
                    toy.setQuantity(currentQuantity + quantity);
                    System.out.println("Stock increased successfully for toy: " + name);
                    return;
                }
            }
            System.out.println("Toy not found with name: " + name);
        }


        public Map.Entry<Integer, Integer> showTypeWithMostToys() {
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toy toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, quantityByType.getOrDefault(type, 0) + toy.getQuantity());
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
            Map<Integer, Integer> quantityByType = new HashMap<>();

            for (Toy toy : toys) {
                int type = toy.getType();
                quantityByType.put(type, quantityByType.getOrDefault(type, 0) + toy.getQuantity());
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
        }
        public List<Toy> getToysWithValueGreaterThan(double value) {
            List<Toy> selectedToys = new ArrayList<>();

            for (Toy toy : toys) {
                double toyValue = toy.getPrice() * toy.getQuantity();
                if (toyValue > value) {
                    selectedToys.add(toy);
                }
            }

            return selectedToys;
        }
        public List<ToyDTO> sortStockByType() {
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
        }

        @Override
        public List<ToyDTO> addToy(ToyDTO toyDTO) throws Exception {
            toys.add(ToyMapper.mapFromDTO(toyDTO));
            return toys.stream().map(ToyMapper::mapFromModel).toList();
        }
    }
