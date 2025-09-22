<%-- 
    Document   : lista-participantes
    Created on : 21/09/2025, 18:51:34
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participantes</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <main class="container">
            <h3 class="titulosh3">Participantes del congreso: ${congreso.nombre}</h2>
            <h2>Dinero recaudado: ${congreso.recaudado}</h2>
            
            <c:if test="${empty participantes}">
                <p>No hay participantes registrados en este congreso.</p>
            </c:if>

            <c:if test="${not empty participantes}">
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Tipo de participación</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${participantes}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${p.nombreCompleto}</td>
                                <td>${p.correo}</td>
                                <td>${p.tipoParticipacion}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </c:if>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>

