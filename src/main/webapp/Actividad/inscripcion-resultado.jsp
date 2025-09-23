<%-- 
    Document   : inscripcion-resultado
    Created on : 22/09/2025, 08:29:11
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado de Inscripción</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
    <jsp:include page="/includes/header.jsp"/>
    <main class="container">
        <h2>Resultado de la inscripción</h2>
        
        <p>${mensaje}</p>

        <c:if test="${not empty participacion}">
            <p><strong>ID Taller:</strong> ${participacion.idActividad}</p>
            <p><strong>ID Usuario:</strong> ${participacion.idUsuario}</p>
            <p><strong>Fecha Registro:</strong> ${participacion.fechaRegistro}</p>
        </c:if>
    </main>
    <jsp:include page="/includes/footer.jsp"/>
</body>
</html>

