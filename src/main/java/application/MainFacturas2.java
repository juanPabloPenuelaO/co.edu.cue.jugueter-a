package application;

import config.DatabaseConnection;
import model.facturas;
import repository.Repository;
import repository.impl.facturaRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainFacturas2 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getInstance()) {
            Repository<facturas> repository = new facturaRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}