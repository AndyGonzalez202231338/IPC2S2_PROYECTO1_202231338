<%-- 
    Document   : lista-congresos
    Created on : 5/09/2025, 01:21:15
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Congresos</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>
            <a href="lista-congresos.jsp"></a>
            <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
            <!-- TABLA DE CONGRESOS EXISTENTES -->
            <div class="containerAdmin mt-5">
                <h3 class="titulosh3 text-center mb-4">Congresos Registrados</h3>

                <table class="table table-dark table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${congresos}" var="congreso">
                            <tr>
                                <td>${congreso.codigo}</td>
                                <td>${congreso.nombre}</td>
                                <td>${congreso.fechaInicio}</td>
                                <td>${congreso.fechaFin}</td>
                                <td>$${congreso.precio}</td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/VerCongresoServlet?codigo=${congreso.codigo}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-card-list"></i> Ver mas
                                    </a>
                                    <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                                    <a href="${pageContext.servletContext.contextPath}/CongresoServlet?codigo=${congreso.codigo}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-pencil-square"></i> Editar
                                    </a>

                                    <a class="btn btn-sm btn-outline-danger">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                    </c:if> 
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </c:if>
                <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">
                    <h3 class="mt-5 text-center">Registrate en un Congreso</h3>
                    <h4>Saldo en la cuenta: ${usuarioLogueado.cartera}</h4>
                    <c:if test="${not empty congresos}">
                        <c:forEach items="${congresos}" var="congreso">
                            <div class="card text-white bg-dark mb-3 shadow-lg rounded">
                                <div class="card-body">
                                    <h5 class="card-title text-info">${congreso.nombre}</h5>
                                    <p class="card-text">Fecha Inicio: <span>${congreso.fechaInicio}</span></p>
                                    <p class="card-text">Fecha Fin: <span>${congreso.fechaFin}</span></p>
                                    <p class="card-text">Lugar: <span>${congreso.lugar}</span></p>
                                    <p class="card-text">Precio: <span>$${congreso.precio}</span></p>
                                    <p class="card-text">Descripción: <span>${congreso.descripcion}</span></p>
                                    <a href="${pageContext.servletContext.contextPath}/VerActividadServlet?codigo=${congreso.codigo}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-card-list"></i> Ver las Actividades
                                    </a>
                                    <a href="${pageContext.servletContext.contextPath}/ParticipacionServlet?idCongreso=${congreso.idCongreso}" 
                                       class="btn btn-sm btn-outline-info me-2">
                                        <i class="bi bi-card-list"></i> Inscribirse
                                    </a>
                                </div>
                            </div>

                        </c:forEach>
			</c:if>
                </c:if>
                
                <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">
                    <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/Home/home-admin.jsp"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar
                        </a>
                    </div>
                </c:if>
                <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                 <div class="mt-4 text-center">
                        <a href="${pageContext.servletContext.contextPath}/Congreso/congreso.jsp"
                           class="btn btn-sm btn-outline-info me-2">
                            <i class="bi bi-arrow-90deg-left"></i> Regresar al form
                        </a>
                    </div>   
                </c:if>
            </div>
        </main>
                           <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
