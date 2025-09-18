<%-- 
    Document   : lista-salones
    Created on : 16/09/2025, 18:02:19
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Salones</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de un Salón</h3>
                    <h4>El salón registrado sera para el congreso ${congreso.nombre}</h4>
                    <h4>El código ${congreso.codigo}</h4>
                    <h4>El salón servira para uso de actividades</h4>
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

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/SalonServlet">

                        <input type="hidden" class="form-control" id="idCongreso" name="idCongreso" value="${congreso.idCongreso}"required>
                        <input type="hidden" class="form-control" id="codigo" name="codigo" value="${congreso.codigo}"required>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Nombre del Salón</label>
                            <input type="text" class="form-control" id="nombreSalon" name="nombreSalon" placeholder="Nombre del salon de la actividad" required>
                        </div>

                        <div class="mb-3">
                            <label for="telefono" class="form-label">Ubicación del Salón</label>
                            <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Modulo o sección del salón" required>
                        </div>

                        <div class="mb-3">
                            <label for="precio" class="form-label">Capacidad</label>
                            <input type="number" step="1" min="10" class="form-control" id="capacidad" name="capacidad" placeholder="10" required>
                        </div>

                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Recursos</label>
                            <textarea class="form-control" id="recursos" name="recursos" rows="3" placeholder="Cantidad de Proyectores o Pantallas" ></textarea>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Salón</button>
                        </div>
                    </form>
                        
                    <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/SalonServlet"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-clipboard2-minus"></i> Ver todas los Salones
                        </a>
                        <a href="${pageContext.servletContext.contextPath}/VerActividadServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar a Actividades
                        </a>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
