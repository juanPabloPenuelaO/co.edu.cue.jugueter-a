package repository.impl;

import config.DatabaseConnection;
import repository.Repository;
import model.facturas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class facturaRepositoryImpl implements Repository<facturas> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private facturas createProduct(ResultSet resultSet) throws SQLException {
        facturas factura = new facturas();
        factura.setId(resultSet.getInt("id"));
        factura.setFecha_actual(resultSet.getDate("Fecha_actual"));

        return factura;
    }

    @Override
    public List<facturas> list() {
        List<facturas> facturaList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from facturas")) {
            while (resultSet.next()) {
                facturas factura = createProduct(resultSet);
                facturaList.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturaList;
    }

    @Override
    public facturas byId(Long id) {
        facturas factura = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM facturas WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                factura = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factura;
    }

    @Override
    public void save(facturas factura) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                        INSERT INTO facturas (Fecha_actual) values (?)
                        """)
        ){
            preparedStatement.setDate(1, factura.getFecha_actual());
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