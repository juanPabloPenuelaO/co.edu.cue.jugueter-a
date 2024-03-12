package application;

import config.DatabaseConnection;
import model.ventas;
import repository.Repository;
import repository.impl.ventaRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainVentas2 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getInstance()) {
            Repository<ventas> repository = new ventaRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
