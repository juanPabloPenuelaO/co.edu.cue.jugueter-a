package mapping.dtos;

import java.io.Serializable;

public record ToyyDTO(int id, String name, int type, double price, int quantity) implements Serializable {
}
