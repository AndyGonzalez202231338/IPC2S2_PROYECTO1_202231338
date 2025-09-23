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
        <jsp:include page="/includes/header.jsp"/>
        <main class="containerAdmin mt-5">
            
            <h2 class="text-center mb-4">Detalle del Congreso</h2>
            
            <div class="containerAdmin">
                    <!-- ===== Sección de Features ===== -->
                    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                        <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMINCONGRESO')}">
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-people-fill"></i>
                            </div>
                            <h3 class="titulosh3">Participantes</h3>
                            <p>Participantes en el Congreso.</p>
                            <a href="${pageContext.servletContext.contextPath}/VerParticipanteServlet?idCongreso=${congreso.idCongreso}" class="icon-link">
                                Ver usuarios <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        </c:if>
                        
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-clipboard2-minus"></i>
                            </div>
                            <h3 class="titulosh3">Actividades</h3>
                            <p>Actividades para este congreso.</p>
                            <a href="${pageContext.servletContext.contextPath}/VerActividadServlet?codigo=${congreso.codigo}&idCongreso=${congreso.idCongreso}" class="icon-link">
                                Ver actividades <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        
                    </div>
                    </div>

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

                        <a href="${pageContext.servletContext.contextPath}/VerActividadServlet?codigo=${congreso.codigo}"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-clipboard2-minus"></i> Actividades del Congreso
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


