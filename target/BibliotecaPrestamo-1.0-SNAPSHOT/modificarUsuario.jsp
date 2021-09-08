<%-- 
    Document   : modificarUsuario
    Created on : 8 sept. 2021, 9:48:07
    Author     : Desktop
--%>

<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Libro</title>
</head>
<style >
    .nav-link{
        color: rgb(194, 191, 191);
    }
    .nav-link:hover{
        color: aliceblue;
    }
    table{
        text-align: center;
    }
    .active {
        color: white;
        border-bottom: 3px solid rgb(255, 255, 255) ;
        border-radius: 10px;
        
    }
    .active:visited{
        color: white;
    }
</style>
</head>
<body>
    <header>
        <nav class="navbar navbar-dark bg-primary justify-content-center">
            <ul class="nav-bar nav justify-content-center navbarColor2 ">
                <li class="nav-item">
                  <a class="nav-link " href="registroUsuario.jsp">Registrar Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="listaAutor.jsp">Listar Autor</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="listarLibro.jsp">Listar Libros</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active" href="listarUsuario.jsp">Listar Usuario</a>
                </li>
              </ul>
        </nav>
    </header>
    <main class="container mt-5">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center bg-primary">
                <h1 style="color: white">Modificar Usuario</h1>
            </div>
                <jsp:useBean id="usuario" scope="session" class="com.bean.usuarioBean"/>
                <%
                    ArrayList<String> dUsuario = new ArrayList();
                    if(request.getParameter("guardar") != null){
                        usuario.modificarUsuario(request);
                        out.print("<script type='text/javascript'>"
                                    +"alert('Usuario Modificado...');"
                                    +"window.location.href = 'listarUsuario.jsp';"
                                + "</script>");
                        //response.sendRedirect("listarUsuario.jsp");
                    }else if(request.getParameter("modificar") != null){
                        dUsuario = usuario.obtenerUsuario(request);
                    }
                    
                %>
            </div>
            <div class="card-body">
                <% if(!dUsuario.isEmpty()){ %>
                <form method="POST">
                    <div class="mb-3">
                      <label class="form-label">Cedula:</label>
                      <input type="text" class="form-control" name="cedula" value="<%=dUsuario.get(0) %>"></input>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Expedido:</label>
                        <input type="text" class="form-control" name="expedido" value="<%=dUsuario.get(4) %>">
                      </div>
                    <div class="mb-3">
                      <label class="form-label">Paterno:</label>
                      <input type="text" class="form-control" name="paterno" value="<%=dUsuario.get(1) %>">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Materno:</label>
                      <input type="text" class="form-control" name="materno" value="<%=dUsuario.get(2) %>">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Nombre:</label>
                      <input type="text" class="form-control" name="nombre" value="<%=dUsuario.get(3) %>">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Telefono:</label>
                      <input type="number" class="form-control" name="telefono" value="<%=dUsuario.get(5) %>">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Dirección:</label>
                      <input type="text" class="form-control" name="direccion" value="<%=dUsuario.get(6) %>">
                    </div>
                    
                    <button type="submit" class="btn btn-primary" name = "guardar">Guardar Cambios</button>
                    <a href="listarUsuario.jsp" class="btn btn-primary" >Cancelar</a>
                </form>
                <%}%>
            </div>
        </div>

    </main>
</body>
</html>