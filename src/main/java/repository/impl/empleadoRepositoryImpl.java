package repository.impl;

import config.DatabaseConnection;
import repository.Repository;
import model.empleados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class empleadoRepositoryImpl implements Repository<empleados> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private empleados createProduct(ResultSet resultSet) throws SQLException {
        empleados empleado = new empleados();
        empleado.setId(resultSet.getInt("id"));
        empleado.setNombre(resultSet.getString("nombre"));
        empleado.setSexo(resultSet.getString("sexo"));
        empleado.setFecha_ingreso(resultSet.getDate("Fecha_ingreso"));
        empleado.setEstado(resultSet.getString("Estado"));

        return empleado;
    }

    @Override
    public List<empleados> list() {
        List<empleados> empleadoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from empleados")) {
            while (resultSet.next()) {
                empleados empleado = createProduct(resultSet);
                empleadoList.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleadoList;
    }

    @Override
    public empleados byId(Long id) {
        empleados empleado = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM empleados WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                empleado = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public void save(empleados empleado) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                        INSERT INTO empleados (nombre, sexo, Fecha_ingreso, Estado) values (?,?,?,?)
                        """)
        ){
            preparedStatement.setString(1,empleado.getNombre());
            preparedStatement.setString(2, empleado.getSexo());
            preparedStatement.setDate(3, empleado.getFecha_ingreso());
            preparedStatement.setString(4,empleado.getEstado());
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