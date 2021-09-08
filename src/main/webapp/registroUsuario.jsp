<%-- 
    Document   : registroUsuario
    Created on : 30 jun. 2021, 14:00:42
    Author     : Misael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Usuario</title>
</head>
<style >
    .nav-link{
        color: rgb(194, 191, 191);
    }
    .nav-link:hover{
        color: aliceblue;
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
<body>
    <header>
        <nav class="navbar navbar-dark bg-primary justify-content-center">
            <ul class="nav-bar nav justify-content-center navbarColor2 ">
                <li class="nav-item">
                  <a class="nav-link active" href="#">Registrar Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="listaAutor.jsp">Listar Autor</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="listarLibro.jsp">Listar Libros</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link " href="listarUsuario.jsp">Listar Usuario</a>
                </li>
              </ul>
        </nav>
    </header>
    <main class="container mt-4 mb-4">
        <div class="card">
            <div class="card-header">
                <h1>Registrar Usuario</h1>
                <jsp:useBean id="usuario" scope="session" class="com.bean.usuarioBean"/>
                <%
                    if(request.getParameter("registrar") != null){
                        System.out.println("entro----------------");
                        String mensaje = usuario.registrarUsuario(request);
                        out.print(mensaje);
                    }
                %>
            </div>
            <div class="card-body">
                <form method="POST">
                    <div class="mb-3">
                      <label class="form-label">Cedula:</label>
                      <select class="form-select " name="cedula">
                          <option>Cedulas...</option>
                          <%=usuario.selectCedula()%>
                      </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Expedido:</label>
                        <select class="form-select" name="expedido">
                          <option selected>Departamentos...</option>
                          <option value="La Paz">La Paz</option>
                          <option value="Oruro">Oruro</option>
                          <option value="Cochabamba">Cochabamba</option>
                          <option value="Extranjero">Extranjero</option>
                        </select>
                      </div>
                    <div class="mb-3">
                      <label class="form-label">Nombre:</label>
                      <input type="text" class="form-control" name="nombre">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Paterno:</label>
                      <input type="text" class="form-control" name="paterno">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Materno:</label>
                      <input type="text" class="form-control" name="materno">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Telefono:</label>
                      <input type="number" class="form-control" name="telefono">
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Dirección:</label>
                      <input type="text" class="form-control" name="direccion">
                    </div>
                    
                    <button type="submit" class="btn btn-primary" name="registrar">Registrar</button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>