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
            <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
            <div class="containerAdmin">
                    <!-- ===== Sección de Features ===== -->
                    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-clipboard2-check-fill"></i>
                            </div>
                            <h3 class="titulosh3">Comité Científico</h3>
                            <p>Crear un Comite Cientifico para este Congreso.</p>
                            <a href="${pageContext.servletContext.contextPath}/CongresoServlet" class="icon-link">
                                Ver Comité <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-people-fill"></i>
                            </div>
                            <h3 class="titulosh3">Participantes</h3>
                            <p>Participantes en el Congreso.</p>
                            <a href="${pageContext.servletContext.contextPath}/VerUsuarioServlet?correo=${usuarioLogueado.correo}" class="icon-link">
                                Ver usuarios <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-send-check"></i>
                            </div>
                            <h3 class="titulosh3">Convocatorias</h3>
                            <p>Propuestas para crear Actvidades en Congreso.</p>
                            <a href="${pageContext.servletContext.contextPath}/AgregarDineroServlet?correo=${usuarioLogueado.correo}" class="icon-link">
                                Ver Convocatorias <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                    </div>
                    </div>
                </c:if>          
                                
            <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">                
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de Actividad</h3>
                    <h3>La actividad creada sera para el congreso ${congreso.nombre}</h3>
                    <h3>con el código ${congreso.codigo}</h3>
                    <h3>con el id ${congreso.idCongreso}</h3>
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
                            <label for="nombreCongreso" class="form-label">Codigo de ls Actividad</label>
                            <input type="text" class="form-control" id="codigo" name="codigo" placeholder="ACT-000000" required>
                        </div>
                        
                        <input type="hidden" class="form-control" id="idCongreso" name="idCongreso" value="${congreso.idCongreso}" required>
                        <input type="hidden" class="form-control" id="codigoCongreso" name="codigoCongreso" value="${congreso.codigo}" required>

                        <div class="mb-3">
                            <label for="idSalon" class="form-label">Salón</label>
                            <select class="form-select" id="idSalon" name="idSalon" required>
                                <option value="">-- Selecciona un salón --</option>
                                <c:forEach var="salon" items="${salones}">
                                    <option value="${salon.idSalon}">
                                        ${salon.nombreSalon} - Capacidad: ${salon.capacidad}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <a href="${pageContext.servletContext.contextPath}/VerSalonServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-clipboard2-minus"></i> Ver todos los Salones
                        </a>

                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre de la Actividad</label>
                            <input type="text" class="form-control" id="nombreActividad" name="nombreActividad" 
                                   placeholder="Ingrese el nombre de la actividad" required>
                        </div>
                        <div class="mb-3">
                            <label for="tipoCuenta" class="form-label">Tipo de Actividad</label>
                            <select class="form-select" id="tipoActividad" name="tipoActividad">
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
            </c:if>                
            <%-- Lista de Actividades Existentes--%>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="mt-5 text-center">Lista de Actividades para este Congreso</h3>
                    <c:if test="${not empty actividades}">
                        <c:forEach items="${actividades}" var="actividad">
                            <div class="card text-white bg-dark mb-3 shadow-lg rounded">
                                <div class="card-body">
                                    <h5 class="card-title text-info">${actividad.codigo}</h5>
                                    <p class="card-text">Salón: <span>${actividad.salon.nombreSalon}</span></p>
                                    <p class="card-text">Ubicación del Salón: <span>${actividad.salon.ubicacion}</span></p>
                                    <p class="card-text">Fecha: <span>${actividad.fechaCreacion}</span></p>
                                    <p class="card-text">Horario: <span>${actividad.horaInicio}</span> a <span>${actividad.horaFin}</span></p>
                                    <p class="card-text">Ponente: <span>${actividad.ponente.nombreCompleto}</span></p>
                                    <p class="card-text">Nombre de la Actividad: <span>${actividad.nombreActividad}</span></p>
                                    <p class="card-text">Tipo de Actividad: <span>${actividad.tipoActividad}</span></p>
                                    <p class="card-text">Descripcion de Actividad: <span>${actividad.descripcion}</span></p>
                                    <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                                    <a href="${pageContext.servletContext.contextPath}/SalonServlet?" 
                                       class="btn btn-outline-info btn-sm">
                                        <i class="bi bi-pencil-square"></i>Editar Actividad
                                    </a>
                                    <a href="${pageContext.servletContext.contextPath}/ParticipacionServlet?idCongreso=${actividad.idCongreso}" class="btn btn-sm btn-outline-danger">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                    </c:if>
                                </div>
                            </div>

                        </c:forEach>
			</c:if>
                    <c:if test="${empty actividades}">
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
