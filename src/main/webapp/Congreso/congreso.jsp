<%-- 
    Document   : crear-congreso.jsp
    Created on : 03/09/2025
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congresos</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <!-- FORMULARIO DE CREACIÓN DE CONGRESO -->
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Creación de Congreso</h3>
                    
                    <form class="crearCongreso" method="POST" action="${pageContext.servletContext.contextPath}/CongresoServlet">
                        <div class="mb-3">
                            <label for="nombreCongreso" class="form-label">Codigo del Congreso</label>
                            <input type="text" class="form-control" id="codigoCongreso" name="codigo" placeholder="CON-000000">
                        </div>
                        <div class="mb-3">
                            <label for="nombreCongreso" class="form-label">Nombre del Congreso</label>
                            <input type="text" class="form-control" id="nombreCongreso" name="nombre" placeholder="Ingrese el nombre del congreso">
                        </div>
                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" placeholder="Breve descripción del congreso"></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de Inicio</label>
                                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="fechaFin" class="form-label">Fecha de Fin</label>
                                <input type="date" class="form-control" id="fechaFin" name="fechaFin">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="lugar" class="form-label">Lugar</label>
                            <input type="text" class="form-control" id="lugar" name="lugar" placeholder="Ciudad, auditorio, etc.">
                        </div>
                        <div class="mb-3">
                            <label for="precio" class="form-label">Precio ($)</label>
                            <input type="number" step="0.01" min="35.00" class="form-control" id="precio" name="precio" placeholder="35.00">
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Crear Congreso</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- TABLA DE CONGRESOS EXISTENTES -->
            <div class="containerAdmin mt-5">
                <h3 class="titulosh3 text-center mb-4">Congresos Registrados</h3>

                <table class="table table-dark table-hover align-middle text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Lugar</th>
                            <th>Precio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Ejemplo estático -->
                        <tr>
                            <td>1</td>
                            <td>Congreso de Tecnología</td>
                            <td>Innovaciones en IA y Big Data</td>
                            <td>2025-10-15</td>
                            <td>2025-10-17</td>
                            <td>Ciudad de México</td>
                            <td>$50.00</td>
                            <td>
                                <button class="btn btn-sm btn-outline-info me-2">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Eliminar
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Congreso de Educación</td>
                            <td>Tecnología en la enseñanza</td>
                            <td>2025-11-05</td>
                            <td>2025-11-07</td>
                            <td>Buenos Aires</td>
                            <td>$45.00</td>
                            <td>
                                <button class="btn btn-sm btn-outline-info me-2">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Eliminar
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
