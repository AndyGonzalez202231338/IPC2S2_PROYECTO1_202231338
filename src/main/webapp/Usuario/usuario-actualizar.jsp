<%-- 
    Document   : usuario-actualizar
    Created on : 7/09/2025, 22:22:56
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                    <form class="crearUsuario" method="POST" action="${pageContext.servletContext.contextPath}/EditarUsuarioServlet">
                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre Completo</label>
                            <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" 
                                   placeholder="Ingrese su nombre completo" value="${usuario.nombreCompleto}">
                        </div>
                        <div class="mb-3">
                            <label for="correo" class="form-label">Email</label>
                            <label class="form-control">${usuario.correo}</label>
                            <input type="hidden" class="form-control" id="correo" name="correo" 
                                   aria-describedby="emailHelp" placeholder="ejemplo@correo.com" value="${usuario.correo}">
                            <div id="emailHelp" class="form-text">Nunca compartiremos tu correo con nadie.</div>
                        </div>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Teléfono</label>
                            <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Número de contacto" value="${usuario.telefono}">
                        </div>
                        <div class="mb-3">
                            <label for="organizacion" class="form-label">Organización</label>
                            <input type="text" class="form-control" id="organizacion" name="organizacion" placeholder="Organización o empresa" value="${usuario.organizacion}">
                        </div>
                        <div class="mb-3">
                            <label for="numeroIdentificacion" class="form-label">Número de Identificación</label>
                            <input type="text" class="form-control" id="numeroIdentificacion" name="numeroIdentificacion" placeholder="DPI o identificación" value="${usuario.numeroIdentificacion}">
                        </div>
                        <div class="mb-3">
                            <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                            <select class="form-select" id="tipoCuenta" name="tipoCuenta">
                                <option value="NORMAL" selected>Normal</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                        </div>
                    </form>

                </div>
            </div>

            <!-- Enlace a listado de Usuarios -->
            <div class="mt-4 text-center">
                <a href="${pageContext.servletContext.contextPath}/UsuarioServlet"
                   class="btn btn-sm btn-outline-info me-2">
                    <i class="bi bi-clipboard2-minus"></i> Ver todos los Usuarios
                </a>
                <a href="${pageContext.servletContext.contextPath}/Home/home-admin.jsp"
                   class="btn btn-sm btn-outline-info me-2">
                    <i class="bi bi-arrow-90deg-left"></i> Home
                </a>
            </div>
        </div>
    </div>    

    <jsp:include page="/includes/footer.jsp"/>
</main>
</body>
</html>
