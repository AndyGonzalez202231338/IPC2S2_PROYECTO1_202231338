<%-- 
    Document   : index
    Created on : 30/08/2025, 18:02:04
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="/includes/resources.jsp"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    </head>
    <body class="login-page">
        <main class="form-signin">
            <h1 class="title">CONGRESOS CUNOC</h1>
            <form class="login" method="POST" action="${pageContext.servletContext.contextPath}/loginServlet">
                <h2 class="h4 mb-3 fw-normal text-center">Por favor inicia sesión</h2> 

                <!-- Mensajes del backend -->
                <c:if test="${not empty mensajeError}">
                    <div class="alert alert-danger text-center">
                        ${mensajeError}
                    </div>
                </c:if>

                <c:if test="${not empty mensajeExito}">
                    <div class="alert alert-success text-center">
                        ${mensajeExito}
                    </div>
                </c:if>
                <!-- Fin mensajes -->

                <div class="form-floating mb-3"> 
                    <input type="email" class="form-control" id="floatingInput" name="correo" placeholder="nombre@ejemplo.com"> 
                    <label for="floatingInput">Dirección de correo electrónico</label> 
                </div> 

                <div class="form-floating mb-3"> 
                    <input type="password" class="form-control" id="floatingPassword" name="contrasena" placeholder="Contraseña"> 
                    <label for="floatingPassword">Contraseña</label> 
                </div> 

                <div class="form-check text-start my-3"> 
                    <input class="form-check-input" type="checkbox" value="remember-me" id="checkDefault"> 
                    <label class="form-check-label" for="checkDefault">Acuérdate de mí</label> 
                </div> 

                <button class="btn btn-primary w-100 py-2" type="submit">Iniciar sesión</button> 

                <p class="mt-4 mb-0 text-center text-body-secondary">© 2025</p> 
            </form> 
        </main>
    </body>
</html>

UPDATE Usuario
SET foto = NULL
WHERE idUsuario = 2;
