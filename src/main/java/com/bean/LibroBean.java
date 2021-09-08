/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Desktop
 */
public class LibroBean {
        private Connection connection;
    private PreparedStatement insertLibro;
    private connectionResource variable;
    //constructores
    public LibroBean()throws SQLException{
        //instanciando
        variable=new connectionResource();
        variable.inicioConexion();
        //obteniendo la conexion
        connection=variable.getConnection();
        System.out.println("Iniciando la conexion");
    }
   
    //metodos
     @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    public String registrarLibro(HttpServletRequest request){
        String mensaje="";
        if(request==null){
            return"";
        }
        if(connection!=null){
            try {
                //definiendo la consulta
                StringBuilder query = new StringBuilder();
                query.append(" insert into categoria ");
                query.append(" values (nextval('sec_cat'),?,?)");
                //enviando la consulta
                if(insertLibro==null){
                    insertLibro=connection.prepareStatement(query.toString());
                }
                //rescatando los parametros del formulario jsp registrarCategoria
                String nombre = request.getParameter("nomCat");
                String descripcion = request.getParameter("desCat");
                //pasando los datos a los parametros de la consulta
                insertLibro.setString(1,nombre);
                insertLibro.setString(2, descripcion);
                //ejecutando la consulta
                int registro=insertLibro.executeUpdate();
                if(registro==1){
                    mensaje="Registro realizado con exito";
                }else{
                    mensaje="Error al insertar el registro";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mensaje;
    }
    //Realizando el listado de todas las categorias que se tienen registrados
    public String listaBusquedaLibro(HttpServletRequest request){
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        
        //para la busqueda
        String busqueda = "";
        if (request != null) {
            busqueda = request.getParameter("busqueda").toLowerCase();  
        }
        query.append(" SELECT a.nombre, a.paterno, a.materno, l.* ");
        query.append(" FROM libro l ");
        query.append(" INNER JOIN autor a ON a.cod_autor = l.cod_autor");
        query.append(" WHERE LOWER(a.paterno) LIKE '" + busqueda + "%'");
        System.out.println(query);
        
        
        
        try {
            PreparedStatement pst = connection.prepareStatement(query.toString());
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                String nom = resultado.getString(1);
                String pat = resultado.getString(2);
                if (!busqueda.equals("")) {
                   pat = "<span style='background-color: rgba(18, 193, 247, 0.76);'>"+resultado.getString(2) +"</span>";
                }
                String mat = resultado.getString(3);
                String nombreC = nom + " " + pat + " " +mat;
                
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(6));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(nombreC);
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(7));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(8));
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
}
