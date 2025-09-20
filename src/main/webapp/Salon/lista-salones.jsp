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
                        <a href="${pageContext.servletContext.contextPath}/VerActividadServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar a Actividades
                        </a>
                    </div>
                </div>
                <%-- Lista de Salones Existentes--%>

            </div>

            <%-- Lista de Salones Existentes--%>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="mt-5 text-center">Lista de Salones Existentes Para este Congreso</h3>
                    <c:if test="${not empty salones}">
                        <c:forEach items="${salones}" var="salon">
                            <div class="card text-white bg-dark mb-3 shadow-lg rounded">
                                <div class="card-body">
                                    <h5 class="card-title text-info">${salon.nombreSalon}</h5>
                                    <p class="card-text">Ubicación: <span>${salon.ubicacion}</span></p>
                                    <p class="card-text">Capacidad: <span>${salon.capacidad}</span></p>
                                    <p class="card-text">Recursos del Salón: <span>${salon.recursos}</span></p>
                                    <a href="${pageContext.servletContext.contextPath}/SalonServlet?nombreSalon=${salon.nombreSalon}&idCongreso=${salon.idCongreso}&codigo=${congreso.codigo}" 
                                       class="btn btn-outline-info btn-sm">
                                        <i class="bi bi-pencil-square"></i>Editar Salon
                                    </a>
                                    <a class="btn btn-sm btn-outline-danger">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </div>
                            </div>

                        </c:forEach>

                        <table class="table table-striped table-bordered mt-3">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Ubicación</th>
                                    <th>Capacidad</th>
                                    <th>Recursos</th>
                                    <th>ID Congreso</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="salon" items="${salones}">
                                    <tr>
                                        <td>${salon.idSalon}</td>
                                        <td>${salon.nombreSalon}</td>
                                        <td>${salon.ubicacion}</td>
                                        <td>${salon.capacidad}</td>
                                        <td>${salon.recursos}</td>
                                        <td>${salon.idCongreso}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${empty salones}">
                        <div class="alert alert-warning text-center mt-3">
                            No hay salones registrados aún.
                        </div>
                    </c:if>

                </div>              
            </div>


        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
