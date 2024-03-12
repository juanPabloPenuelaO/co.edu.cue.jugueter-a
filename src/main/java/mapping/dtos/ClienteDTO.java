package mapping.dtos;

import java.io.Serializable;
import java.util.Date;

public record ClienteDTO(int id, String nombre, String sexo, Date Fecha_nacimiento, int hijos, String Estado) implements Serializable {
}
