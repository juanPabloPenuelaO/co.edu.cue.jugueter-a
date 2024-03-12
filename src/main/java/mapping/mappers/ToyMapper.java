package mapping.mappers;

import mapping.dtos.ToyDTO;
import model.Toys;

public class ToyMapper {
    public static ToyDTO mapFromModel(Toys toy) {
        return new ToyDTO(toy.getName(), toy.getType(), toy.getPrice(), toy.getQuantity());
    }

    public static Toys mapFromDto(ToyDTO toyDTO) {
        return Toys.builder()
                .name(toyDTO.name())
                .type(toyDTO.type())
                .price(toyDTO.price())
                .quantity(toyDTO.quantity())
                .build();
    }
}


