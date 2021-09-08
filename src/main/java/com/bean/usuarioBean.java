
package com.bean;

import com.conexion.connectionResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Misael
 */
public class usuarioBean {
    private Connection connection;
    private PreparedStatement insertUsuario;
    private connectionResource variable;
    //constructores
    public usuarioBean()throws SQLException{
        //instanciando
        variable = new connectionResource();
        variable.inicioConexion();
        //obteniendo la conexion
        connection = variable.getConnection();
        System.out.println("Iniciando la conexion");
    }
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    
    public String registrarUsuario(HttpServletRequest request){
        String mensaje = "";
        if (request == null) {
            return "";
        }
        
        if (connection != null) {
            try{
                StringBuilder query = new StringBuilder();
                query.append(" INSERT INTO usuario ");
                query.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
                insertUsuario = connection.prepareStatement(query.toString());
                int cedula = Integer.valueOf(request.getParameter("cedula"));
                System.out.println(cedula);
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String nombre = request.getParameter("nombre");
                String expedido = request.getParameter("expedido");
                int telefono = Integer.valueOf(request.getParameter("telefono"));
                String direccion = request.getParameter("direccion");
                
                insertUsuario.setInt(1, cedula);
                insertUsuario.setString(2, paterno);
                insertUsuario.setString(3, materno);
                insertUsuario.setString(4, nombre);
                insertUsuario.setString(5, expedido);
                insertUsuario.setInt(6, telefono);
                insertUsuario.setString(7, direccion);
                
                int respuesta = insertUsuario.executeUpdate();
                
                if(respuesta == 1){
                    
                    mensaje = "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                            "  <strong>Holy guacamole!</strong> You should check in on some of those fields below.\n" +
                            "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                            "</div>";
                    mensaje = "Registro realizado con exito";
                }else{
                    mensaje="Error al insertar el registro";
                }

            }catch(SQLException e){
                e.getMessage();
            }
        }
        
        return mensaje;
    }
    
    public String modificarUsuario(HttpServletRequest request){
        String mensaje = "";
        if (request == null) {
            return "";
        }
        
        if (connection != null) {
            try{
                StringBuilder query = new StringBuilder();
                query.append(" UPDATE usuario ");
                query.append(" SET cedula_usr=?, paterno_usr=?, materno_usr=?, "
                             + "nombre_usr=?, expedido_usr=?, telefono_usr=?, direccion=?");
                query.append(" WHERE cedula_usr = '"+ request.getParameter("cedula") +"' ");
                insertUsuario = connection.prepareStatement(query.toString());
                
                int cedula = Integer.valueOf(request.getParameter("cedula"));
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String nombre = request.getParameter("nombre");
                String expedido = request.getParameter("expedido");
                int telefono = Integer.valueOf(request.getParameter("telefono"));
                String direccion = request.getParameter("direccion");
                
                insertUsuario.setInt(1, cedula);
                insertUsuario.setString(2, paterno);
                insertUsuario.setString(3, materno);
                insertUsuario.setString(4, nombre);
                insertUsuario.setString(5, expedido);
                insertUsuario.setInt(6, telefono);
                insertUsuario.setString(7, direccion);
                
                int respuesta = insertUsuario.executeUpdate();
                
                if(respuesta == 1){
                    
                    mensaje = "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                            "  <strong>Holy guacamole!</strong> You should check in on some of those fields below.\n" +
                            "  <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                            "</div>";
                    System.out.println("-------fdsfd----------");
                }else{
                    mensaje="Error al insertar el registro";
                }

            }catch(SQLException e){
                e.getMessage();
            }
        }
        
        return mensaje;
    }
    
    public String listaBusquedaUsuario(HttpServletRequest request){
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        
        //para la busqueda
        String busqueda = "";
        if (request != null) {
            busqueda = request.getParameter("buscar").toLowerCase();  
        }
        query.append(" SELECT cedula_usr, nombre_usr, paterno_usr, materno_usr, expedido_usr, telefono_usr, direccion");
        query.append(" FROM usuario ");
        query.append(" WHERE LOWER(paterno_usr) LIKE '" + busqueda + "%'");
        System.out.println(query);
        
        
        
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                String nom = resultado.getString(2);
                String pat = resultado.getString(3);
                if (!busqueda.equals("")) {
                   pat = "<span style='background-color: rgba(18, 193, 247, 0.76);'>"+resultado.getString(3) +"</span>";
                }
                String mat = resultado.getString(4);
                String nombreC = nom + " " + pat + " " +mat;
                
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(nombreC);
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(6));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(7));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(" <form action=\"modificarUsuario.jsp\" method=\"POST\">\n" +
                                    "<input name= \"cedula\" type=\"hidden\" value='"+ resultado.getInt(1) +"' >\n" +
                                    "<button type=\"submit\" class=\"btn btn-warning\" name=\"modificar\">Modificar</button>\n" +
                                    "</form>");
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    public String selectCedula(){
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT p.cedula_usr ");
        query.append(" FROM prestamo p ");
        query.append(" WHERE NOT EXISTS (SELECT NULL FROM usuario u WHERE u.cedula_usr = p.cedula_usr) ");
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<option>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    public ArrayList obtenerUsuario(HttpServletRequest request){
        ArrayList<String> usuarioDatos = new ArrayList<>();
        
        StringBuilder query = new StringBuilder();
        String cedula = request.getParameter("cedula");
        
        query.append(" SELECT * ");
        query.append(" FROM usuario u ");
        query.append(" WHERE u.cedula_usr = '" + cedula + "' ");
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                usuarioDatos.add(String.valueOf(resultado.getInt(1)));
                usuarioDatos.add(resultado.getString(2));
                usuarioDatos.add(resultado.getString(3));
                usuarioDatos.add(resultado.getString(4));
                usuarioDatos.add(resultado.getString(5));
                usuarioDatos.add(String.valueOf(resultado.getInt(6)));
                usuarioDatos.add(resultado.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return usuarioDatos;
    }
}
