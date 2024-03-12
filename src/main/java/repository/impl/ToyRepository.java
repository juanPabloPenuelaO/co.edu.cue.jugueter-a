package repository.impl;
import java.util.List;
import java.util.Map;

import mapping.dtos.ToyDTO;
import model.Toys;


public interface ToyRepository<T> {

    List<T> list();
    T byId(int id);

    boolean verifyToyExists(String name);

    Map<Integer, Integer> showQuantityByType();

    int getTotalQuantity();

    double getTotalValue();

    void decreaseStock(String name, int quantity);

    void increaseStock(String name, int quantity);

    Map.Entry<Integer, Integer> showTypeWithMostToys();

    Map.Entry<Integer, Integer> showTypeWithLeastToys();

    List<ToyDTO> getToysWithValueGreaterThan(double value);

    List<ToyDTO> sortStockByType();


    void setToys(List<Toys> toys);
    List<Toys> addToy(Toys toy) throws IllegalArgumentException;

    void save(Toys toy);
}

