package mapping.mappers;

import mapping.dtos.ClienteDTO;
import model.clientes;

import java.sql.Date;

public class clienteMapper {
    public static ClienteDTO mapFromModel(clientes cliente){
        return new ClienteDTO(cliente.getId(), cliente.getNombre(),cliente.getSexo(),cliente.getFecha_nacimiento(),cliente.getHijos(),cliente.getEstado());
    }

    public static clientes mapFromDto(ClienteDTO clienteDTO){
        return clientes.builder()
                .id(clienteDTO.id())
                .nombre(clienteDTO.nombre())
                .sexo(clienteDTO.sexo())
                .Fecha_nacimiento((Date) clienteDTO.Fecha_nacimiento())
                .hijos(clienteDTO.hijos())
                .Estado(clienteDTO.Estado())
                .build();
    }
}