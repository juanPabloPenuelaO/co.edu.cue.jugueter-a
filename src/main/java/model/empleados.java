package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class empleados {
    private int id;
    private String nombre;
    private String sexo;
    private Date fecha_ingreso;
    private String estado;
}