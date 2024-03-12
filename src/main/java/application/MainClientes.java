package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainClientes {
    public static void main(String[] args) {
        String url= "jdbc:mysql://localhost:3306/jugueteria";
        String user= "root";
        String password = "admin";
        try (Connection conn= DriverManager.getConnection(url, user, password);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes");
        ){
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println("|");
                System.out.println(resultSet.getString("nombre"));
                System.out.println("|");
                System.out.println(resultSet.getString("sexo"));
                System.out.println("|");
                System.out.println(resultSet.getDate("Fecha_nacimiento"));
                System.out.println("|");
                System.out.println(resultSet.getString("hijos"));
                System.out.println("|");
                System.out.println(resultSet.getString("Estado"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}