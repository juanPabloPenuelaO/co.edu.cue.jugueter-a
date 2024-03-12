package mapping.dtos;

import java.io.Serializable;
import java.util.Date;

public record EmpleadoDTO(int id, String nombre, String sexo, Date Fecha_ingreso, int estado) implements Serializable {
}
