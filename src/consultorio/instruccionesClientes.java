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
 * @author MIGUEL ÁNGEL ROMERO
 */
public class instruccionesClientes {

    Conexion objConexion = new Conexion();

    public void InsertarCliente(String nombre, String edad, String sexo) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            //Método de inserción 1:
            /*
            Statement stmt = conex.createStatement();
            
            String SQL = "insert into clientes (Nombre, Edad, Sexo) values" + "('" + nombre + "', '" + edad + "', '" + sexo + "')";
            filasAfectadas = stmt.executeUpdate(SQL);
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);
            
             */
            //Método de inserción 2: 
            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO clientes values (?,?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, edad);
            psInsertar.setString(4, sexo);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public ResultSet BuscarCliente(String buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select idClientes, Nombre, Edad, Sexo from clientes where (idClientes ='" + buscar + "')" + " OR (Nombre= '" + buscar + "')");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }
    public void EditarCliente(String nombre, String edad, String sexo, String buscar) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("UPDATE clientes SET idClientes=?,nombre=?,edad=?,sexo=? WHERE idClientes=" + buscar);
            psInsertar.setString(1, buscar);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, edad);
            psInsertar.setString(4, sexo);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void EliminarCliente(String idClientes){
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psEditar = null;
            
            psEditar = conex.prepareStatement("DELETE FROM clientes WHERE idclientes="+idClientes);
        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ResultSet MostrarClientesT(String tabla){
        Connection conex = objConexion.conectar();
        Statement st;
        ResultSet datos=null;
        try {
            st=conex.createStatement();
            datos=st.executeQuery(tabla);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return datos;
    }

}
