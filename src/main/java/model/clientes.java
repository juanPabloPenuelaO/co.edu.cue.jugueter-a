package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class clientes {
    private int id;
    private String nombre;
    private String sexo;
    private Date Fecha_nacimiento;
    private int hijos;
    private String Estado;
}

