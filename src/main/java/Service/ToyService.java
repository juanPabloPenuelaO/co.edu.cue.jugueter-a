package Service;

import mapping.dtos.*;

import java.util.List;
import java.util.Map;
import model.Toys;

public interface ToyService {

    void addToy(ToyDTO toyDTO);

    void addEmpleado(EmpleadoDTO empleadoDTO);

    void addCliente(ClienteDTO clienteDTO);

    void addFactura(FacturaDTO facturaDTO);

    void addVenta(VentaDTO ventaDTO);

    List<EmpleadoDTO> listEmpleados();

    List<ClienteDTO> ListCliente();

    List<FacturaDTO> ListFactura();
}
