<%-- 
    Document   : listarLibros
    Created on : 8 sept. 2021, 9:46:22
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
                  <a class="nav-link" href="listarUsuario.jsp">Listar Usuario</a>
                </li>
              </ul>
        </nav>
    </header>
    <main class="container mt-5">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center bg-primary">
                <h1 style="color: white">Lista de Categoría</h1>
            </div>
            <div class="card-body">
                <form class="d-flex justify-content-between align-items-center" >
                    <label for="formFileSm" class = "form-label">Paterno: </label> 
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="busqueda" style="width:80%">
                    <button class="btn btn-outline-success" type="submit" name="buscar">Search</button>
                 </form>
            </div>
            <div class="card-body">
                <jsp:useBean id="libroBean" scope="session" class="com.bean.LibroBean"/>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Edición</th>
                            <th>Nro Ejemplar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if(request.getParameter("buscar") != null){
                            String datos = libroBean.listaBusquedaLibro(request);
                            if (datos.equals("")) {   
                                out.print("<tr><td colspan='4' align='center'>No se Encontro el Usuario!</td></tr>");
                            }else{
                                out.print(datos);
                            }
                        }else{
                            String datos = libroBean.listaBusquedaLibro(null);
                            out.print(datos);
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </main>

</body>
</html>
