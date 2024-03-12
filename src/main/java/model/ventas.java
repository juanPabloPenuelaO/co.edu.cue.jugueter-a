package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ventas {
    private int id;
    private String estado_Oferta;
    private int Cantidad;
    private int idToy;
    private int idCliente;
    private int idEmpleado;
    private Date fecha_compra;
    private int idFactura;
}
