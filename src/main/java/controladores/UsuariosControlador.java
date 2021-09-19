/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import generales.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import modelos.MdUsuario;

/**
 *
 * @author GAMERS
 */
public class UsuariosControlador {
    Connection conexion = null;
    private static final String SQL_SELECT = "select * from usuarios";
    private static final String SQL_SELECT_USUARIO = "select * from usuarios where usuario=?";
    private static final String SQL_INSERT = "insert into usuarios (usuario,password) values(?,?)";
    private static final String SQL_UPDATE = "update usuarios set usuario=?, password=? where idusuario=?";
    private static final String SQL_DELETE = "delete from usuarios where usuario=?";
    
    public static void main(String[] args) throws SQLException {
        
    }
    public List<MdUsuario> todos() {
        List<MdUsuario> lista= new ArrayList<MdUsuario>();
        MdUsuario usuario=null;
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_SELECT);
            ResultSet resultado = cadena.executeQuery();
            while(resultado.next()){
                usuario= new MdUsuario();
                usuario.setId(resultado.getInt("idusuario"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setPassword(resultado.getString("password"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return lista;
    }
    
    public MdUsuario buscar(String nombreUsuario) {
        MdUsuario usuario=null;
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_SELECT_USUARIO);
            
            cadena.setString(1, nombreUsuario);
            System.out.println(cadena.toString());
            ResultSet resultado = cadena.executeQuery();
            if (!resultado.next()) {
                System.out.println("No encontrado");
            } else {
                usuario= new MdUsuario();
                usuario.setId(resultado.getInt("idusuario"));
                usuario.setUsuario(resultado.getString("usuario"));
                usuario.setPassword(resultado.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return usuario;
    }
    public MdUsuario crear(String nombreUsuario, String password) {
       
        MdUsuario usuario=null;
        
        usuario=buscar(nombreUsuario);
        if(usuario!=null){
            JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos");
            return null;
        }
        
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_INSERT);
            
            cadena.setString(1, nombreUsuario);
            cadena.setString(2, password);
            System.out.println(cadena.toString());
            int resultado = cadena.executeUpdate();
            if (resultado>0) {
                usuario= buscar(nombreUsuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return usuario;
    }
    public MdUsuario editar(Integer idusuario, String nombreUsuario, String password) {
       
        MdUsuario usuario=null;
        
        usuario=buscar(nombreUsuario);
        if(usuario!=null){
            if(!Objects.equals(usuario.getId(), idusuario)){
                JOptionPane.showMessageDialog(null, "El usuario ya existe en la base de datos");
                return null;
            }
        }
        
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_UPDATE);
            
            cadena.setString(1, nombreUsuario);
            cadena.setString(2, password);
            cadena.setInt(3, idusuario);
            System.out.println(cadena.toString());
            int resultado = cadena.executeUpdate();
            if (resultado>0) {
                usuario= buscar(nombreUsuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return usuario;
    }
    public Integer eliminar(String nombreUsuario) {
       
        MdUsuario usuario=null;
        Integer eliminado=0;
        usuario=buscar(nombreUsuario);
        if(usuario==null){
            JOptionPane.showMessageDialog(null, "El usuario no existe en la base de datos");
            return null;
        }
        
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_DELETE);
            
            cadena.setString(1, nombreUsuario);
           
            System.out.println(cadena.toString());
            cadena.executeUpdate();
            eliminado=1;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return eliminado;
    }
}
