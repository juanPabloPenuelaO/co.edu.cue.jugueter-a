package application;

import config.DatabaseConnection;
import model.clientes;
import repository.Repository;
import repository.impl.clienteRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainClientes2 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getInstance()) {
            Repository<clientes> repository = new clienteRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
