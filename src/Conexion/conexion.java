/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class conexion {

    private Connection conexion = null;

    public Connection getConexion() {
        return conexion;
    }
  
    public conexion() {
        crearConexion();
    }

    public boolean crearConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql:thin:@localhost:1521:XE [hr on HR]");
            if (conexion != null) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("error en conexion: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return false;
    }

 
    public boolean ejecutarSQL(String sql) {
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

 
    public ResultSet ejecutarSQLSelect(String sql) {
        ResultSet resultado;
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            return resultado;
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
            return null;
        }
    }
}