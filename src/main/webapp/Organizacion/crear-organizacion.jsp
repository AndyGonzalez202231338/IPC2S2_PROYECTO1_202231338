<%-- 
    Document   : crear-organizacion
    Created on : 23/09/2025, 03:37:06
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

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/OrganizacionServlet">
                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre de la Organización</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" 
                                   placeholder="Ingrese nombre de la Organización o Institución" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Organización</button>
                        </div>
                    </form>
                    <%-- Lista de Actividades Existentes--%>
                    <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                        <div class="formulariogrande">
                            <h3 class="mt-5 text-center">Lista de Organizaciones o Instituciones</h3>
                            <c:if test="${not empty organizaciones}">
                                <c:forEach items="${organizaciones}" var="organizacion">
                                    <div class="card text-white bg-dark mb-3 shadow-lg rounded">
                                        <div class="card-body">
                                            <h5 class="card-title text-info">${organizacion.nombre}</h5>

                                                <a href="${pageContext.servletContext.contextPath}/EditarOrganizacionServlet?nombre=${organizacion.nombre}" 
                                                   class="btn btn-outline-info btn-sm">
                                                    <i class="bi bi-pencil-square"></i>Editar Organización
                                                </a>
                                                <a href="${pageContext.servletContext.contextPath}/ParticipacionServlet?idCongreso=${actividad.idCongreso}" class="btn btn-sm btn-outline-danger">
                                                    <i class="bi bi-trash"></i> Eliminar
                                                </a>
                                        </div>
                                    </div>

                                </c:forEach>
                            </c:if>
                            <c:if test="${empty organizaciones}">
                                <div class="alert alert-warning text-center mt-3">
                                    No hay Organiaciones registrados aún.
                                </div>
                            </c:if>

                        </div>              
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
