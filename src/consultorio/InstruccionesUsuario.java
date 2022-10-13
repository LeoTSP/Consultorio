package consultorio;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

public class InstruccionesUsuario {

    Conexion objConexion = new Conexion();

    public void RegistrarUsuario(String Usuario, String textoEncriptadoConMD5) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO usuario values (?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, Usuario);
            psInsertar.setString(3, textoEncriptadoConMD5);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " usuario", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultSet BuscarUsuario(String Usuario) {
       
        ResultSet user = null;

        try{
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            user = stmt.executeQuery("select Usuario from Usuario where (Usuario = '"+Usuario+"')");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return user;
    }
    
    public ResultSet BuscarContraseña(String Contraseña) {

        ResultSet contra = null;
        
        try{
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            contra = stmt.executeQuery("select Contraseña from usuario where (Contraseña = '"+Contraseña+"')");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return contra;
    }
}
