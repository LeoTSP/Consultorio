/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author MIGUEL ÁNGEL ROMERO
 */
public class Conexion {

    public Connection conectar() {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/consultorio"
                + "?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String pass = "12345";

        Connection conex = null;

        try {
            System.out.println("Conectando a la base de datos");
            Class.forName(driver);

            conex = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa!!");

        } catch (SQLException ex) {
            System.out.println("Error de mysql " + ex);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Se ha encontrado el siguiente error " + ex);
        }
        return conex;
    }
    

}
