/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Desktop
 */
public class connectionResource {
    public static String URL_BBDD="jdbc:postgresql://localhost:5432/Biblioteca_P";
    public static String DRIVER_BBDD="org.postgresql.Driver";
    public static String USUARIO_BBDD="postgres";
    public static String PASSWORD_BBDD="postgres";
    
    //objeto para realizar la conexion
    private Connection connection;
    
    
    public void inicioConexion()throws SQLException{
        try {
            Class.forName(DRIVER_BBDD);
            connection = DriverManager.getConnection(URL_BBDD, USUARIO_BBDD, PASSWORD_BBDD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void cerrarConexion(){
        if(connection!=null){
            try {
                //cerrando la conexion
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //get y set

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
