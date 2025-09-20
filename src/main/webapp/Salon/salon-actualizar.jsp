<%-- 
    Document   : salon-actualizar
    Created on : 18/09/2025, 16:40:10
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Salon</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Edición de un Salón</h3>
                    <h4>Cambios a Salon ${salon.nombreSalon}</h4>
                    <h4>id Congreos ${salon.idCongreso}</h4>
                    <h4>codigo ${congreso.codigo}</h4>
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

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/EditarSalonServlet">

                        <input type="hidden" class="form-control" id="idCongreso" name="idCongreso" value="${salon.idCongreso}"required>
                        <input type="hidden" class="form-control" id="nombreSalon" name="nombreSalon" value="${salon.nombreSalon}"required>
                        <input type="hidden" class="form-control" id="codigo" name="codigo" value="${congreso.codigo}"required>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Nombre del Salón</label>
                            <input type="text" class="form-control" id="nuevoNombreSalon" name="nuevoNombreSalon" placeholder="Nombre del salon de la actividad" value="${salon.nombreSalon}"required>
                        </div>

                        <div class="mb-3">
                            <label for="telefono" class="form-label">Ubicación del Salón</label>
                            <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Modulo o sección del salón" value="${salon.ubicacion}" required>
                        </div>

                        <div class="mb-3">
                            <label for="precio" class="form-label">Capacidad</label>
                            <input type="number" step="1" min="10" class="form-control" id="capacidad" name="capacidad" placeholder="10" value="${salon.capacidad}" required>
                        </div>

                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Recursos</label>
                            <textarea class="form-control" id="recursos" name="recursos" rows="3" placeholder="Cantidad de Proyectores o Pantallas" >${salon.recursos}</textarea>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>

                    <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/VerSalonServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>

