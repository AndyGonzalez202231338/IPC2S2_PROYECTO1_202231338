<%-- 
    Document   : detalle-congreso
    Created on : 7/09/2025, 00:45:44
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Detalle Congreso</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main class="containerAdmin mt-5">
            <jsp:include page="/includes/header.jsp"/>
            <h2 class="text-center mb-4">Detalle del Congreso</h2>

            <div class="row justify-content-center">
                <div class="col-md-8">
                    <ul class="list-group">
                        <li class="list-group-item bg-dark text-light"><strong>Código:</strong> ${congreso.codigo}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Nombre:</strong> ${congreso.nombre}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Descripción:</strong> ${congreso.descripcion}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Lugar:</strong> ${congreso.lugar}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Fecha Inicio:</strong> ${congreso.fechaInicio}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Fecha Fin:</strong> ${congreso.fechaFin}</li>
                        <li class="list-group-item bg-dark text-light"><strong>Precio:</strong> $${congreso.precio}</li>
                            <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                            <li class="list-group-item bg-dark text-light"><strong>Porcentaje de Ganacias:</strong> ${congreso.porcentajeGanancia}%</li>
                            </c:if>
                    </ul>

                    <div class="mt-4 text-center">

                        <a href="${pageContext.servletContext.contextPath}/CongresoServlet"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Actvidades del Congreso
                        </a>
                        <a href="${pageContext.servletContext.contextPath}/CongresoServlet"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Volver a la lista
                        </a>
                    </div>

                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>


