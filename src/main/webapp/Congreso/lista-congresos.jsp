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
