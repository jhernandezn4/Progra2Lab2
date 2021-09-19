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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import modelos.MdVendedor;

/**
 *
 * @author GAMERS
 */
public class VendedoresControlador {

    Connection conexion = null;
    private static final String SQL_SELECT = "select * from vendedores";
    private static final String SQL_SELECT_USUARIO = "select * from vendedores where nombre=?";
    private static final String SQL_INSERT = "insert into vendedores (nombre,enero,febrero,marzo,promedio,total) values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update vendedores set nombre=?, enero=?, febrero=?, marzo=?, promedio=?, total=? where idvendedor=?";
    private static final String SQL_DELETE = "delete from vendedores where nombre=?";

    public List<MdVendedor> todos() {
        List<MdVendedor> lista = new ArrayList<MdVendedor>();
        MdVendedor vendedor = null;
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_SELECT);
            ResultSet resultado = cadena.executeQuery();
            while (resultado.next()) {
                vendedor = new MdVendedor();
                vendedor.setId(resultado.getInt("idvendedor"));
                vendedor.setNombre(resultado.getString("nombre"));
                vendedor.setEnero(resultado.getDouble("enero"));
                vendedor.setFebrero(resultado.getDouble("febrero"));
                vendedor.setMarzo(resultado.getDouble("marzo"));
                vendedor.setPromedio(resultado.getDouble("promedio"));
                vendedor.setTotal(resultado.getDouble("total"));
                lista.add(vendedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return lista;
    }

    public MdVendedor buscar(String nombreVendedor) {
        MdVendedor vendedor = null;
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_SELECT_USUARIO);

            cadena.setString(1, nombreVendedor);
            System.out.println(cadena.toString());
            ResultSet resultado = cadena.executeQuery();
            if (!resultado.next()) {
                System.out.println("No encontrado");
            } else {
                vendedor = new MdVendedor();
                vendedor.setId(resultado.getInt("idvendedor"));
                vendedor.setNombre(resultado.getString("nombre"));
                vendedor.setEnero(resultado.getDouble("enero"));
                vendedor.setFebrero(resultado.getDouble("febrero"));
                vendedor.setMarzo(resultado.getDouble("marzo"));
                vendedor.setPromedio(resultado.getDouble("promedio"));
                vendedor.setTotal(resultado.getDouble("total"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return vendedor;
    }

    public MdVendedor crear(String nombre, Double enero, Double febrero, Double marzo) {

        MdVendedor vendedor = null;

        vendedor = buscar(nombre);
        if (vendedor != null) {
            JOptionPane.showMessageDialog(null, "El vendedor ya existe en la base de datos");
            return null;
        }

        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_INSERT);

            cadena.setString(1, nombre);
            cadena.setDouble(2, enero);
            cadena.setDouble(3, febrero);
            cadena.setDouble(4, marzo);

            Double total = enero + febrero + marzo;
            Double promedio = total / 3;

            cadena.setDouble(5, promedio);
            cadena.setDouble(6, total);

            System.out.println(cadena.toString());
            int resultado = cadena.executeUpdate();
            if (resultado > 0) {
                vendedor = buscar(nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return vendedor;
    }

    public MdVendedor editar(Integer idvendedor, String nombre, Double enero, Double febrero, Double marzo) {

        MdVendedor vendedor = null;

        vendedor = buscar(nombre);
        if (vendedor != null) {
            if (!Objects.equals(vendedor.getId(), idvendedor)) {
                JOptionPane.showMessageDialog(null, "Ya existe otro vendedor con el mismo nombre en la base de datos");
                return null;
            }
        }
        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_UPDATE);

            cadena.setString(1, nombre);
            cadena.setDouble(2, enero);
            cadena.setDouble(3, febrero);
            cadena.setDouble(4, marzo);
            Double total = enero + febrero + marzo;
            Double promedio = total / 3;
            cadena.setDouble(5, promedio);
            cadena.setDouble(6, total);
            cadena.setInt(7, idvendedor);
            System.out.println(cadena.toString());
            int resultado = cadena.executeUpdate();
            if (resultado > 0) {
                vendedor = buscar(nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return vendedor;
    }

    public Integer eliminar(String nombre) {

        MdVendedor usuario = null;
        Integer borrado = 0;
        usuario = buscar(nombre);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "El usuario no existe en la base de datos");
            return null;
        }

        try {
            conexion = ClsConexion.getConnection();
            PreparedStatement cadena = conexion.prepareStatement(SQL_DELETE);

            cadena.setString(1, nombre);

            System.out.println(cadena.toString());
            cadena.executeUpdate();
            borrado = 1;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.cerrar(conexion);
        }
        return borrado;
    }
}
