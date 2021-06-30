
package com.bean;

import com.conexion.connectionResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Misael
 */
public class autorBean {
    private Connection connection;
    private PreparedStatement insertQuery;
    private connectionResource variable;
    
    public autorBean() throws SQLException{
        variable = new connectionResource();
        variable.inicioConexion();
        connection = variable.getConnection();
    }
    
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    
    public String registrarAutor(HttpServletRequest request){
        String mensaje = "";
        if (request == null) {
            return "";
        }
        if (connection != null) {
            try{
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO autor");
                query.append("VALUES (nextval('sec_autor'), ?, ?, ?, ?)");
                
                if (insertQuery == null) {
                    insertQuery = connection.prepareStatement(query.toString());
                }
  
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String nombre = request.getParameter("nombre");
                String nacionalidad = request.getParameter("nacionalidad");
                
                insertQuery.setString(1, paterno);
                insertQuery.setString(2, materno);
                insertQuery.setString(3, nombre);
                insertQuery.setString(4, nacionalidad);
                
                int registroRespuesta = insertQuery.executeUpdate();
                if (registroRespuesta == 1) {
                    mensaje = "Autor Registrado";
                }else{
                    mensaje = "No se Registro el Autor";
                }  
                
            }catch(SQLException e){
                e.getMessage();
            }
        }
        
        return mensaje;
    }
    
    public String listaAutor(HttpServletRequest request){
        String tabla = "";
        String buscar = "";
        if (request != null) {
            buscar = request.getParameter("buscar").toLowerCase();
            System.out.println("aquiiii: " + buscar + " ----");
        }
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT * ");
        query.append(" FROM autor ");
        query.append(" WHERE LOWER(paterno) LIKE '" +buscar+ "%' ");
        
        try{
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        tabla = salidaTabla.toString();
        if (tabla.equals("")) {
            tabla = "<tr><td colspan='5' align='center'>No se Encontraron los datos!! </td></tr>";
        }
        return tabla; 
    }
    
}
