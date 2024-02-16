package Service;

import mapping.dtos.ToyDTO;
import model.Toy;

import java.util.List;
import java.util.Map;

public interface ToyService {
    List<ToyDTO> addToy(ToyDTO toyDTO) throws Exception;

    Map<Integer, Integer> showQuantityByType();

    int getTotalQuantity();

    double getTotalValue();

    void decreaseStock(String name, int quantity);

    void increaseStock(String name, int quantity);

    Map.Entry<Integer, Integer> showTypeWithMostToys();

    Map.Entry<Integer, Integer> showTypeWithLeastToys();

    List<Toy> getToysWithValueGreaterThan(double value);

    List<ToyDTO> sortStockByType();


}
