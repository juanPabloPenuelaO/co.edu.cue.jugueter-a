package mapping.dtos;

import java.io.Serializable;
import java.util.Date;

public record VentaDTO(int ID, String estado_Oferta, int cantidad, int idToy, int idCliente,int idEmpleado, Date fecha_compra, int idFactura) implements Serializable {
}
