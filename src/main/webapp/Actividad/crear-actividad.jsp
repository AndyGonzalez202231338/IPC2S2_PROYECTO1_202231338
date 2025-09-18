<%-- 
    Document   : crear-actividad
    Created on : 15/09/2025, 21:57:33
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actividades</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de Actividad</h3>
                    <h3>La actividad creada sera para el congreso ${congreso.nombre}</h3>
                    <h3>con el código ${congreso.codigo}</h3>
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

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/ActividadServlet">
                        <div class="mb-3">
                            <label for="nombreCongreso" class="form-label">Codigo del Congreso</label>
                            <input type="text" class="form-control" id="codigo" name="codigo" placeholder="ACT-000000" required>
                        </div>


                        <input type="hidden" class="form-control" id="idCongreso" name="idCongreso" required>
                        <input type="hidden" class="form-control" id="creado_por" name="creado_por" required>


                        <div class="mb-3">
                            <label for="telefono" class="form-label">Salon</label>
                            <input type="text" class="form-control" id="idSalon" name="idSalon" placeholder="Salon de la actividad" required>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/VerSalonServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-clipboard2-minus"></i> Ver todos los Salones
                        </a>

                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre de la Actividad</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" 
                                   placeholder="Ingrese el nombre de la actividad" required>
                        </div>
                        <div class="mb-3">
                            <label for="tipoCuenta" class="form-label">Tipo de Actividad</label>
                            <select class="form-select" id="tipoCuenta" name="tipoCuenta">
                                <option value="PONENCIA" selected>Ponencia</option>
                                <option value="TALLER">Taller</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" placeholder="Breve descripción del congreso" required></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="fechaInicio" class="form-label">Hora de Inicio</label>
                                <input type="time" class="form-control" id="horaInicio" name="horaInicio" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="fechaFin" class="form-label">Hora de Fin</label>
                                <input type="time" class="form-control" id="horaFin" name="horaFin" required>
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="fechaFin" class="form-label">Fecha de realización</label>
                            <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Actividad</button>
                        </div>
                    </form>

                    <!-- Enlace a listado de Actividades -->
                    <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/UsuarioServlet"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-clipboard2-minus"></i> Ver todos los Usuarios
                        </a>
                        <a href="${pageContext.servletContext.contextPath}/Home/home-admin.jsp"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Home
                        </a>
                    </div>

                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
