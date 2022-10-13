/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author K01-11
 */
public class instruccionesDentista {
    Conexion objConexion = new Conexion();
    
     public void InsertarDentista(String nombre, String edad, String sexo, String especialidad) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psInsertar=null;
           
           psInsertar= conex.prepareStatement("INSERT INTO dentista values (?,?,?,?,?)");
           psInsertar.setString(1,null);
           psInsertar.setString(2,nombre);
           psInsertar.setString(3,edad);
           psInsertar.setString(4,sexo);
           psInsertar.setString(5,especialidad);
          filasAfectadas= psInsertar.executeUpdate();
          JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);
            

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}
     public ResultSet BuscarDentista(String buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select iddentista, Nombre, Edad, Sexo, Especialidad from dentista where (iddentista ='" + buscar + "')" + " OR (Nombre= '" + buscar + "')");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }
     public void EditarDentista(String nombre, String edad, String sexo,String Especialidad, String buscar) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("UPDATE dentista SET iddentista=?,nombre=?,edad=?,sexo=?,especialidad=? WHERE iddentista=" + buscar);
            psInsertar.setString(1, buscar);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, edad);
            psInsertar.setString(4, sexo);
            psInsertar.setString(5, Especialidad);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
     public void EliminarDentista(String idDentista){
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psEditar = null;
            
            psEditar = conex.prepareStatement("DELETE FROM dentista WHERE iddensita="+idDentista);
        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
