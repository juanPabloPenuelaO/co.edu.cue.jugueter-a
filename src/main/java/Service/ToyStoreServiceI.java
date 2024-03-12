package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import mapping.dtos.*;
import mapping.mappers.ToyMapper;
import mapping.mappers.*;
import repository.impl.*;
import repository.Repository;
import model.*;
import config.DatabaseConnection;


public class ToyStoreServiceI implements ToyService {

    Connection conn = DatabaseConnection.getInstance();
    private ToyRepository<Toys> toys;
    private Repository<empleados> emple;
    private Repository<clientes> client;
    private Repository<facturas> factura;
    private Repository<ventas> venta;


    public ToyStoreServiceI() throws SQLException {
        this.toys = new toyRepositoryImpl();
        this.emple = new empleadoRepositoryImpl();
        this.client = new clienteRepositoryImpl();
        this.factura = new facturaRepositoryImpl();
        this.venta = new ventaRepositoryImpl();
    }

    @Override
public void addToy(ToyDTO toyDTO){
        toys.save(ToyMapper.mapFromDto(toyDTO));
    }

    @Override
    public void addEmpleado(EmpleadoDTO empleadoDTO){
emple.save(empleadoMapper.mapFromDto(empleadoDTO));
    }

    @Override
    public void addCliente(ClienteDTO clienteDTO){
        client.save(clienteMapper.mapFromDto(clienteDTO));
    }

@Override
    public void addFactura(FacturaDTO facturaDTO){
        factura.save(facturaMapper.mapFromDto(facturaDTO));
}

@Override
    public void addVenta(VentaDTO ventaDTO){
        venta.save(ventaMapper.mapFromDto(ventaDTO));
}

@Override
    public List<EmpleadoDTO> listEmpleados(){
        return emple.list().stream().map(empleadoMapper::mapFromModel).toList();
}

@Override
    public List<ClienteDTO> ListCliente(){

        return client.list().stream().map(clienteMapper::mapFromModel).toList();
}

@Override
    public List<FacturaDTO> ListFactura(){
        return factura.list().stream().map(facturaMapper::mapFromModel).toList();
    }

    @Override
    public List<ToyDTO> ListToy(){
        return toys.list().stream().map(ToyMapper::mapFromModel).toList();
    }

    @Override
public int showQuantityByType(){
        return toys.showQuantityByType().size();
}

@Override
public int getTotalQuantity(){
return toys.getTotalQuantity();
}

@Override
    public double getTotalValue(){
return toys.getTotalValue();
}

@Override
    public void decreaseStock(String name, int quantity){
toys.decreaseStock(name, quantity);
}

@Override
    public void increaseStock(String name, int quantity){
        toys.increaseStock(name, quantity);
}

@Override
public Map.Entry<Integer, Integer> showTypeWithMostToys(){
        return toys.showTypeWithMostToys();
}

@Override
    public Map.Entry<Integer, Integer> showTypeWithLeastToys(){
        return toys.showTypeWithLeastToys();
}

@Override
    public List<ToyDTO> getToysWithValueGreaterThan(double value){
        List<Toys> toy =toys.getToysWithValueGreaterThan(value);
        return toy.stream().map(ToyMapper::mapFromModel).toList();
}

@Override
    public List<ToyDTO> sortStockByType(){
        List<Toys> toy =toys.sortStockByType();
        return toy.stream().map(ToyMapper::mapFromModel).toList();
}

}
