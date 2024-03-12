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

    List<ToyDTO> ListToy();

    int showQuantityByType();

    int getTotalQuantity();

    double getTotalValue();

    void decreaseStock(String name, int quantity);

    void increaseStock(String name, int quantity);

    Map.Entry<Integer, Integer> showTypeWithMostToys();

    Map.Entry<Integer, Integer> showTypeWithLeastToys();

    List<ToyDTO> getToysWithValueGreaterThan(double value);

    List<ToyDTO> sortStockByType();
}
