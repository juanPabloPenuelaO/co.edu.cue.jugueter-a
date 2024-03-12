package mapping.dtos;

import java.io.Serializable;
import java.util.Date;

public record ClienteDTO(int Id, String Nombre, String Sexo, Date Fecha_nacimiento, int hijos, String estado) implements Serializable {
}
