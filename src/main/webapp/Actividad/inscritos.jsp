<%-- 
    Document   : inscritos
    Created on : 22/09/2025, 19:18:08
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscritos</title>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <main>
            <%-- Lista de Actividades Existentes--%>
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="mt-5 text-center">Lista de Participantes en la Actividad</h3>
                    <c:if test="${not empty inscritosTaller}">
                        <c:forEach items="${inscritosTaller}" var="inscrito">
                            <div class="card text-white bg-dark mb-3 shadow-lg rounded">
                                <div class="card-body">
                                    <h5 class="card-title text-info">${inscrito.usuario.nombreCompleto}</h5> 


                                    <c:if test="${inscrito.asistencia == 1}">
                                        <span class="badge bg-success">Asistencia registrada</span>
                                    </c:if>

                                    <c:if test="${inscrito.asistencia == 0}">
                                        <form method="POST" action="${pageContext.servletContext.contextPath}/InscribirseTallerServlet">
                                            <input type="hidden" name="idActividad" value="${inscrito.idActividad}">
                                            <input type="hidden" name="idUsuario" value="${inscrito.idusuario}">
                                            <button type="submit" class="btn btn-sm btn-outline-success">
                                                Registrar asistencia
                                            </button>
                                        </form>
                                    </c:if>

                                </div>
                            </div>
                        </c:forEach>

                    </c:if>
                    <c:if test="${empty inscritosTaller}">
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
