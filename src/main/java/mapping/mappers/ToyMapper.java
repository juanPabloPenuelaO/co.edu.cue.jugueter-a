package mapping.mappers;

import mapping.dtos.ToyDTO;
import model.Toy;

public class ToyMapper {

    public static Toy mapFromModel(ToyDTO toyDTO) {
        return new Toy(toyDTO.name(), toyDTO.type(), toyDTO.price(), toyDTO.quantity());
    }

    public static ToyDTO mapFromDTO(Toy toy) {
        return new ToyDTO(toy.getName(), toy.getType(), toy.getPrice(), toy.getQuantity());
    }
}

