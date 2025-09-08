<%-- 
    Document   : detalle-usuario
    Created on : 7/09/2025, 20:40:25
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Detalle Usuario</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main class="containerAdmin mt-5">
            <jsp:include page="/includes/header.jsp"/>
            <h2 class="text-center mb-4">Detalle del Usuario</h2>
            <!-- Foto -->
            <div class="text-center mb-4">
                <c:choose>
                    <c:when test="${not empty usuario.foto}">
                        <img src="${usuario.foto}" alt="Foto de ${usuario.nombreCompleto}" 
                             class="rounded-circle border border-2 border-info" 
                             style="width: 150px; height: 150px; object-fit: cover;">
                    </c:when>
                    <c:otherwise>
                        <!-- Imagen por defecto si el usuario no tiene foto -->
                        <img src="${pageContext.servletContext.contextPath}/resources/default-user.png" 
                             alt="Sin foto" 
                             class="rounded-circle border border-2 border-secondary" 
                             style="width: 150px; height: 150px; object-fit: cover;">
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Tabla con informacion -->
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <ul class="list-group">
                        <li class="list-group-item bg-dark text-light"><strong>Nombre: </strong> ${usuario.nombreCompleto}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Correo: </strong> ${usuario.correo}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Teléfono: </strong> ${usuario.telefono}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Organización: </strong> ${usuario.organizacion}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Número Identificación: </strong> ${usuario.numeroIdentificacion}</li>
                        <li class="list-group-item bg-dark text-light">
                            <strong>Fecha de Creación: </strong> ${usuario.fechaRegistroFormatted}
                        </li>


                        <li class="list-group-item bg-dark text-light"><strong>Tipo de Cuenta: </strong> ${usuario.tipoCuenta}</li>
                    </ul>

                    <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/UsuarioServlet"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Volver a la lista
                        </a>
                    </div>

                </div>
            </div>
            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
