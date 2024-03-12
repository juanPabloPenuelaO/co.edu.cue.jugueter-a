package mapping.dtos;

import java.io.Serializable;
import lombok.*;
@Builder

public record ToyDTO(String name, int type, double price, int quantity) implements Serializable {
}


