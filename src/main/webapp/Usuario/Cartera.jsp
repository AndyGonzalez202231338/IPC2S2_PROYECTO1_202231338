<%-- 
    Document   : Cartera.jsp
    Created on : 15/09/2025, 16:54:57
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cartera</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main class="container mt-5">
            <jsp:include page="/includes/header.jsp"/>
            <div class="formulariogrande">
                <h3 class="titulosh3 text-center mb-4"> <i class="bi bi-piggy-bank"></i> Cartera virtual</h3>
            <p>Usuario: ${usuario.nombreCompleto}</p>
            <p>Saldo: $${usuario.cartera}</p>
            <!-- Formulario para agregar dinero -->
            <form action="${pageContext.servletContext.contextPath}/AgregarDineroServlet" method="post" class="crearUsuario">
                <input type="hidden" name="correo" value="${usuario.correo}">
                <div class="mb-3">
                    <label for="monto" class="form-label">Ingrese el monto a agregar:</label>
                    <input type="number" step="10" class="form-control" id="monto" name="monto" required placeholder="Ejemplo: 100.50">
                </div>
                <button type="submit" class="btn btn-primary">Agregar Dinero</button>
            </form>
            </div>
            
    </body>
    <jsp:include page="/includes/footer.jsp"/>
</html>
