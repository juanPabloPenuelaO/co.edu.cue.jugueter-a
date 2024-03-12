package application;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainVentas {
    public static void main(String[] args) {
        String url= "jdbc:mysql://localhost:3306/jugueteria";
        String user= "root";
        String password = "admin";
        try (Connection conn= DriverManager.getConnection(url, user, password);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ventas");
        ){
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println("|");
                System.out.println(resultSet.getString("estado_Oferta"));
                System.out.println("|");
                System.out.println(resultSet.getInt("Cantidad"));
                System.out.println("|");
                System.out.println(resultSet.getInt("idToy"));
                System.out.println("|");
                System.out.println(resultSet.getInt("idCliente"));
                System.out.println("|");
                System.out.println(resultSet.getString("idEmpleado"));
                System.out.println("|");
                System.out.println(resultSet.getDate("fecha_compra"));
                System.out.println("|");
                System.out.println(resultSet.getInt("idFactura"));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
