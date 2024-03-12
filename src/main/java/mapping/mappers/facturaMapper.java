package mapping.mappers;

import mapping.dtos.FacturaDTO;
import model.facturas;

import java.sql.Date;

public class facturaMapper {
    public static FacturaDTO mapFromModel(facturas factura){
        return new FacturaDTO(factura.getId(), factura.getFecha_actual());
    }

    public static facturas mapFromDto(FacturaDTO facturaDTO){
        return facturas.builder()
                .id(facturaDTO.id())
                .fecha_actual((Date) facturaDTO.fecha_actual())
                .build();
    }
}
