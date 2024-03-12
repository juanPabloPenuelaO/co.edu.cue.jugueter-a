package repository.impl;

import config.DatabaseConnection;
import repository.Repository;
import model.ventas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ventaRepositoryImpl implements Repository<ventas> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private ventas createProduct(ResultSet resultSet) throws SQLException {
        ventas venta = new ventas();
        venta.setId(resultSet.getInt("id"));
        venta.setEstado_Oferta(resultSet.getString("estado_oferta"));
        venta.setCantidad(resultSet.getInt("cantidad"));
        venta.setIdToy(resultSet.getInt("idToy"));
        venta.setIdCliente(resultSet.getInt("idCliente"));
        venta.setIdEmpleado(resultSet.getInt("idEmpleado"));
        venta.setFecha_compra(resultSet.getDate("Fecha_compra"));
        venta.setIdFactura(resultSet.getInt("idFactura"));

        return venta;
    }

    @Override
    public List<ventas> list() {
        List<ventas> ventaList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from ventas")) {
            while (resultSet.next()) {
                ventas venta = createProduct(resultSet);
                ventaList.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventaList;
    }

    @Override
    public ventas byId(Long id) {
        ventas venta = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM ventas WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                venta = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venta;
    }

    @Override
    public void save(ventas venta) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                        INSERT INTO clientes (estado_Oferta, Cantidad, idToy, idCliente, idEmpleado, fecha_compra, idFactura) values (?,?,?,?,?,?,?)
                        """)
        ){
            preparedStatement.setString(1,venta.getEstado_Oferta());
            preparedStatement.setInt(2, venta.getCantidad());
            preparedStatement.setInt(3, venta.getIdToy());
            preparedStatement.setInt(4,venta.getIdCliente());
            preparedStatement.setInt(5,venta.getIdEmpleado());
            preparedStatement.setDate(6,venta.getFecha_compra());
            preparedStatement.setInt(7,venta.getIdFactura());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Upgrate() {

    }

    @Override
    public void delete(Long id) {

    }
}