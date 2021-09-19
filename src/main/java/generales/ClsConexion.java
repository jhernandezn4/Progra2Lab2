/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generales;

import java.sql.*;

/**
 *
 * @author GAMERS
 */
public class ClsConexion {

    Connection conexion;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Laboratorio2?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="";
    
    
    public static void main(String[] args) {

    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
    
    public static void cerrar(Connection cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
