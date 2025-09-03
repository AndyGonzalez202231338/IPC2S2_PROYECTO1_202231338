<%-- 
    Document   : crear-usuario.jsp
    Created on : 3/09/2025, 00:14:57
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de Usuario</h3>
                    
                    <form >
                        <div class="mb-3">
                            <label for="nombreCompleto" class="form-label">Nombre Completo</label>
                            <input type="text" class="form-control" id="nombreCompleto" placeholder="Ingrese su nombre completo">
                        </div>
                        <div class="mb-3">
                            <label for="correo" class="form-label">Email</label>
                            <input type="email" class="form-control" id="correo" aria-describedby="emailHelp" placeholder="ejemplo@correo.com">
                            <div id="emailHelp" class="form-text">Nunca compartiremos tu correo con nadie.</div>
                        </div>
                        <div class="mb-3">
                            <label for="telefono" class="form-label">Teléfono</label>
                            <input type="text" class="form-control" id="telefono" placeholder="Número de contacto">
                        </div>
                        <div class="mb-3">
                            <label for="organizacion" class="form-label">Organización</label>
                            <input type="text" class="form-control" id="organizacion" placeholder="Organización o empresa">
                        </div>
                        <div class="mb-3">
                            <label for="numeroIdentificacion" class="form-label">Número de Identificación</label>
                            <input type="text" class="form-control" id="numeroIdentificacion" placeholder="DPI o identificación">
                        </div>
                        <div class="mb-3">
                            <label for="contrasena" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="contrasena">
                        </div>
                        <div class="mb-3">
                            <label for="tipoCuenta" class="form-label">Tipo de Cuenta</label>
                            <select class="form-select" id="tipoCuenta">
                                <option value="NORMAL" selected>Normal</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Usuario</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="containerAdmin mt-5">
                <h3 class="titulosh3 text-center mb-4">Usuarios Registrados</h3>

                <table class="table table-dark table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre Completo</th>
                            <th>Correo</th>
                            <th>Teléfono</th>
                            <th>Organización</th>
                            <th>Tipo de Cuenta</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Ejemplo de datos estáticos -->
                        <tr>
                            <td>1</td>
                            <td>Juan Pérez</td>
                            <td>juan@example.com</td>
                            <td>5555-1234</td>
                            <td>Universidad X</td>
                            <td>NORMAL</td>
                            <td>
                                <button class="btn btn-sm btn-outline-info me-2">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Dar de baja
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>María Gómez</td>
                            <td>maria@example.com</td>
                            <td>4444-5678</td>
                            <td>Empresa Y</td>
                            <td>ADMIN</td>
                            <td>
                                <button class="btn btn-sm btn-outline-info me-2">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Dar de baja
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
