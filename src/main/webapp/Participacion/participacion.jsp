<%-- 
    Document   : participacion
    Created on : 20/09/2025, 23:21:18
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inscripción al Congreso</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
    <main class="container mt-5">
        <h3 class="text-center">Inscripción al Congreso: ${congreso.nombre}</h3>
        <h5 class="text-center">Código: ${congreso.codigo}</h5>
        <h5 class="text-center">Precio: $${congreso.precio}</h5>
        <h5>Saldo $${usuarioLogueado.cartera}</h5>

        <!-- Mensajes del backend -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>
        <c:if test="${not empty mensajeExito}">
            <div class="alert alert-success text-center">${mensajeExito}</div>
        </c:if>

        <!-- Formulario de inscripción -->
        <form action="${pageContext.servletContext.contextPath}/ParticipacionServlet" method="POST" class="mt-4">
            <!-- Usuario viene de sesión -->
            <input type="hidden" name="idUsuario" value="${usuarioLogueado.id}" />

            <!-- Congreso viene del request -->
            <input type="hidden" name="idCongreso" value="${congreso.idCongreso}" />

            <div class="mb-3">
                <h5>Inscripcion del usuario ${usuarioLogueado.nombreCompleto}</h5>
                <label for="tipoParticipacion" class="form-label">Tipo de Participación</label>
                <select class="form-select" id="tipoParticipacion" name="tipoParticipacion" required>
                    <option value="">-- Selecciona --</option>
                    <option value="ASISTENTE">Asistente</option>
                    <option value="PONENTE">Ponente</option>
                    <option value="TALLERISTA">Tallerista</option>
                    <option value="TALLERISTA">Ponente invitado</option>
                    <option value="PARTICIPANTE">Participante</option>
                </select>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Inscribirme</button>
            </div>
        </form>
    </main>
    <jsp:include page="/includes/footer.jsp"/>
</body>
</html>

