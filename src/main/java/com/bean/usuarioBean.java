
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
}
