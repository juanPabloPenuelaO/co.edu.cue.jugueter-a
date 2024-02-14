package mapping.mappers;

import mapping.dtos.ToyDTO;
import model.Toy;

public class ToyMapper {

    public static ToyDTO mapFromModel(Toy toy){
        return new ToyDTO(toy.getName(),toy.getType(),toy.getPrice(),toy.getQuantity());
    }

    public static Toy mapFromDTO(ToyDTO toy){
        return new Toy(toy.name(),toy.type(),toy.price(),toy.quantity());
    }
}
