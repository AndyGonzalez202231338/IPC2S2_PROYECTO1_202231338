<%-- 
    Document   : lista-usuarios.jsp
    Created on : 7/09/2025, 15:41:26
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Usuarios</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <!-- TABLA DE CONGRESOS EXISTENTES -->
            <div class="containerAdmin mt-5">
                <h3 class="titulosh3 text-center mb-4">Usuarios Registrados</h3>

                <table class="table table-dark table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Identificación</th>
                            <th>Tipo de Cuenta</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${usuarios}" var="usuarios">
                            <tr>
                                <td>${usuarios.nombreCompleto}</td>
                                <td>${usuarios.correo}</td>
                                <td>${usuarios.numeroIdentificacion}</td>
                                <td>${usuarios.tipoCuenta}</td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/VerUsuarioServlet?correo=${usuarios.correo}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-card-list"></i> Ver mas
                                    </a>
                                    
                                    <a href="${pageContext.servletContext.contextPath}/UsuarioServlet?correo=${usuarios.correo}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-pencil-square"></i> Editar
                                    </a>

                                    <a class="btn btn-sm btn-outline-danger">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/Usuario/crear-usuario.jsp"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar al form
                        </a>
                    </div>
            </div>

            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
