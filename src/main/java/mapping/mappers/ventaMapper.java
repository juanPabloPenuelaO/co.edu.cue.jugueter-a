package mapping.mappers;

import mapping.dtos.VentaDTO;
import model.ventas;

import java.sql.Date;

public class ventaMapper {
    public static VentaDTO mapFromModel(ventas venta){
        return new VentaDTO(venta.getId(), venta.getEstado_Oferta(),venta.getCantidad(),venta.getIdToy(),venta.getIdCliente(),venta.getIdEmpleado(), venta.getFecha_compra(), venta.getIdFactura());
    }

    public static ventas mapFromDto(VentaDTO ventaDTO){
        return ventas.builder()
                .id(ventaDTO.id())
                .estado_Oferta(ventaDTO.estado_Oferta())
                .Cantidad(ventaDTO.cantidad())
                .idToy(ventaDTO.idToy())
                .idCliente(ventaDTO.idCliente())
                .idEmpleado(ventaDTO.idEmpleado())
                .fecha_compra((Date) ventaDTO.fecha_compra())
                .idFactura(ventaDTO.idFactura())
                .build();
    }
}