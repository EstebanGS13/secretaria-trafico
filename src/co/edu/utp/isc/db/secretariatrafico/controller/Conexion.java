
package co.edu.utp.isc.db.secretariatrafico.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    
    Connection conexion;
     String database = "db_secretaria_trafico";
     String url = "jdbc:mysql://localhost:3306/db_secretaria_trafico";
     String user = "root";
     String pass = "";
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("se conecto");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conexion;
    }
    
    public void desconectar() {
        try {
            conexion.close();
            System.out.println("se desconecto");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
