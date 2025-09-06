<%-- 
    Document   : lista-congresos
    Created on : 5/09/2025, 01:21:15
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Congresos</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <<main>
            <jsp:include page="/includes/header.jsp"/>
            
             <!-- TABLA DE CONGRESOS EXISTENTES -->
            <div class="containerAdmin mt-5">
                <h3 class="titulosh3 text-center mb-4">Congresos Registrados</h3>

                <table class="table table-dark table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Lugar</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${congresos}" var="congreso">
                        <tr>
                            <td>${congreso.idCongreso}</td>
                            <td>${congreso.codigo}</td>
                            <td>${congreso.nombre}</td>
                            <td>${congreso.descripcion}</td>
                            <td>${congreso.fechaInicio}</td>
                            <td>${congreso.fechaFin}</td>
                            <td>${congreso.lugar}</td>
                            <td>$${congreso.precio}</td>
                            <td>
                                <button class="btn btn-sm btn-outline-info me-2">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Eliminar
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
