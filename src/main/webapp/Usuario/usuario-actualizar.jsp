<%-- 
    Document   : usuario-actualizar
    Created on : 7/09/2025, 22:22:56
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Edición de Usuario</h3>
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


                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/EditarUsuarioServlet" enctype="multipart/form-data">
                        <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">      
                            <!-- Foto -->
                            <div class="text-center mb-4">
                                <c:choose>
                                    <c:when test="${not empty usuario.foto}">
                                        <img src="${pageContext.servletContext.contextPath}/FotoUsuarioServlet?correo=${usuario.correo}" 
                                             alt="Foto de ${usuario.nombreCompleto}" 
                                             class="rounded-circle border border-2 border-info" 
                                             style="width: 150px; height: 150px; object-fit: cover;">
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Imagen por defecto si el usuario no tiene foto -->
                                        <img src="${pageContext.servletContext.contextPath}/resources/default-user.png" 
                                             alt="Sin foto" 
                                             class="rounded-circle border border-2 border-secondary" 
                                             style="width: 150px; height: 150px; object-fit: cover;">
                                    </c:otherwise>
                                </c:choose>

                                <!-- Botón cambiar foto -->
                                <div class="mt-3">
                                    <label for="foto" class="form-label d-block">Cambiar Foto</label>
                                    <input type="file" class="form-control" id="foto" name="foto" accept="image/png, image/jpeg">
                                </div>
                            </div>
                        </c:if>


                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre Completo</label>
                            <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" 
                                   placeholder="Ingrese su nombre completo" value="${usuario.nombreCompleto}"  required>
                        </div>
                        <div class="mb-3">
                            <label for="correo" class="form-label">Email</label>
                            <label class="form-control">${usuario.correo}</label>
                            <input type="hidden" class="form-control" id="correo" name="correo" 
                                   aria-describedby="emailHelp" placeholder="ejemplo@correo.com" value="${usuario.correo}">
                            <div id="emailHelp" class="form-label">Nunca compartiremos tu correo con nadie.</div>
                        </div>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Teléfono</label>
                            <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Número de contacto" value="${usuario.telefono}" required>
                        </div>
                        <div class="mb-3">
                            <label for="organizacion" class="form-label">Organización</label>
                            <select class="form-select" id="organizacion" name="organizacion" required>
                                <option value="" disabled selected>Seleccione una organización</option>
                                <c:forEach var="org" items="${organizaciones}">
                                    <option value="${org.idOrganizacion}">${org.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="numeroIdentificacion" class="form-label">Número de Identificación</label>
                            <input type="text" class="form-control" id="numeroIdentificacion" name="numeroIdentificacion" placeholder="DPI o identificación" value="${usuario.numeroIdentificacion}" required>
                        </div>
                        <!-- Cambio de tipo de cuenta para solo administradores -->
                        <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                            <div class="mb-3">
                            <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                            <select class="form-select" id="tipoCuenta" name="tipoCuenta">
                                <option value="NORMAL" selected>Normal</option>
                                <option value="ADMIN">Admin</option>
                                <option value="ADMINCONGRESO">Administrador Congreso</option>
                            </select>
                        </div>
                        </c:if>
                        <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">
                            <input type="hidden" class="form-control" id="tipoCuenta" name="tipoCuenta" value="${usuario.tipoCuenta}" required>
                        </c:if>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                        </div>
                    </form>
                    <!-- Enlace a listado de congresos -->
                    <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                        <div class="mt-4 text-center">
                            <a href="${pageContext.servletContext.contextPath}/UsuarioServlet"
                               class="btn btn-sm btn-outline-info me-2">
                                <i class="bi bi-arrow-90deg-left"></i> Regresar
                            </a>
                        </div>
                    </c:if>          
                    <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">
                        <div class="mt-4 text-center">
                            <a href="${pageContext.servletContext.contextPath}/Home/home-admin.jsp"
                               class="btn btn-sm btn-outline-info me-2">
                                <i class="bi bi-arrow-90deg-left"></i> Regresar
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>    
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
