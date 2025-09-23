<%-- 
    Document   : organizacion-actualizar
    Created on : 23/09/2025, 06:14:39
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organizacion</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <main>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de Organización</h3>
                    <!-- Mensajes de backend --> 
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center" role="alert">
                            ${error}
                        </div>
                    </c:if>

                    <c:if test="${not empty mensajeExito}">
                        <div class="alert alert-success text-center" role="alert">
                            ${mensajeExito}
                        </div>
                    </c:if>

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/EditarOrganizacionServlet">
                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre de la Organización</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" 
                                   placeholder="Ingrese nombre de la Organización o Institución" value="${organizacion.nombre}" required>
                            
                            <input type="hidden" class="form-control" id="idOrganizacion" name="idOrganizacion" value="${organizacion.idOrganizacion}" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Organización</button>
                        </div>
                    </form>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>