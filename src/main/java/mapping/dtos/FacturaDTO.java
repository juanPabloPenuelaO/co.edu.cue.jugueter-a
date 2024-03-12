package mapping.dtos;

import java.io.Serializable;
import java.util.Date;

public record FacturaDTO(int id, Date fecha_actual) implements Serializable {
}
