package Service;

import mapping.dtos.ToyDTO;
import model.Toy;

import java.util.List;

public interface ToyService {
    List<ToyDTO> addToy(ToyDTO toyDTO) throws Exception;

    void showQuantityByType();

    int getTotalQuantity();

    double getTotalValue();

    void decreaseStock(String name, int quantity);

    void increaseStock(String name, int quantity);

    void showTypeWithMostToys();

    void showTypeWithLeastToys();

    List<Toy> getToysWithValueGreaterThan(double value);

    void sortStockByType();




}
