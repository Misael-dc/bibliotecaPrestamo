<%-- 
    Document   : listaAutor
    Created on : 30 jun. 2021, 14:01:08
    Author     : Desktop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Autor</title>
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
                    <a class="nav-link" href="registroUsuario.jsp">Registrar Usuario</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active" href="#">Listar Autor</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link " href="listarLibro.jsp">Listar Libros</a>
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
                <h1>Lista de Autores</h1>
            </div>
            <div class="card-body">
                <form class="d-flex justify-content-between align-items-center" >
                    <label for="formFileSm" class="form-label">Paterno: </label>
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="buscar" style="width:80%">
                    <button class="btn btn-outline-success" type="submit" name="btnBuscar">Buscar</button>
                 </form>
            </div>
            <div class="card-body">
                <jsp:useBean id="autor" scope="session" class="com.bean.autorBean"/>
                <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Cod</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Paterno</th>
                        <th scope="col">Materno</th>
                        <th scope="col">Nacionalidad</th>
                      </tr>
                    </thead>
                    <tbody>
                        <%
                            if (request.getParameter("btnBuscar") != null) {
                                String datos = autor.listaAutor(request); 
                                out.print(datos);
                            }else{
                                out.print(autor.listaAutor(null));  
                            }  
                        %>
                    </tbody>
                  </table>
            </div>
        </div>
    </main>
</body>
</html>
