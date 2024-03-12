package repository.impl;

import config.DatabaseConnection;
import repository.Repository;
import model.clientes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class clienteRepositoryImpl implements Repository<clientes> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private clientes createProduct(ResultSet resultSet) throws SQLException {
        clientes cliente = new clientes();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setSexo(resultSet.getString("sexo"));
        cliente.setFecha_nacimiento(resultSet.getDate("Fecha_nacimiento"));
        cliente.setHijos(resultSet.getInt("hijos"));
        cliente.setEstado(resultSet.getString("Estado"));

        return cliente;
    }

    @Override
    public List<clientes> list() {
        List<clientes> ClienteList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from clientes")) {
            while (resultSet.next()) {
                clientes Cliente = createProduct(resultSet);
                ClienteList.add(Cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ClienteList;
    }

    @Override
    public clientes byId(Long id) {
        clientes Cliente = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM clientes WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cliente = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Cliente;
    }

    @Override
    public void save(clientes cliente) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                        INSERT INTO clientes (nombre, sexo, Fecha_nacimiento, hijos, Estado) values (?,?,?,?,?)
                        """)
        ){
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2, cliente.getSexo());
            preparedStatement.setDate(3, cliente.getFecha_nacimiento());
            preparedStatement.setInt(4,cliente.getHijos());
            preparedStatement.setString(5,cliente.getEstado());
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