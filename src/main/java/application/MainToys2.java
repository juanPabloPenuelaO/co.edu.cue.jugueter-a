package application;

import config.DatabaseConnection;
import model.Toys;
import repository.Repository;
import repository.impl.toyRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainToys2 {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getInstance()) {
            Repository<Toys> repository = (Repository<Toys>) new toyRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1L).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}