package mapping.mappers;

import mapping.dtos.EmpleadoDTO;
import model.empleados;

import java.sql.Date;

public class empleadoMapper {
    public static EmpleadoDTO mapFromModel(empleados empleado){
        return new EmpleadoDTO(empleado.getId(), empleado.getNombre(),empleado.getSexo(),empleado.getFecha_ingreso(),empleado.getEstado());
    }

    public static empleados mapFromDto(EmpleadoDTO empleadoDTO){
        return empleados.builder()
                .id(empleadoDTO.id())
                .nombre(empleadoDTO.nombre())
                .sexo(empleadoDTO.sexo())
                .fecha_ingreso((Date) empleadoDTO.Fecha_ingreso())
                .estado(empleadoDTO.estado())
                .build();
    }
}
