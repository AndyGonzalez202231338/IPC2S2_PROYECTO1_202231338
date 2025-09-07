<%-- 
    Document   : congreso-actualizar
    Created on : 6/09/2025, 15:53:59
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Congresos</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <!-- FORMULARIO DE CREACIÓN DE CONGRESO -->
            <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
                <div class="formulariogrande">
                    <h3 class="titulosh3 text-center mb-4">Formulario de Edición de un Congreso</h3>

                    <form class="crearCongreso" method="POST" action="${pageContext.servletContext.contextPath}/EditarCongresoServlet">
                        <div class="mb-3">
                            <label for="nombreCongreso" class="form-label">Codigo del Congreso</label>
                            <label class="form-control">${congreso.codigo}</label>
                            <input type="hidden" class="form-control" id="codigoCongreso" name="codigo" value="${congreso.codigo}">
                        </div>
                        <div class="mb-3">
                            <label for="nombreCongreso" class="form-label">Nombre del Congreso</label>
                            <input type="text" class="form-control" id="nombreCongreso" name="nombre" placeholder="Ingrese el nombre del congreso" value="${congreso.nombre}">
                        </div>
                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3"
                                      placeholder="Breve descripción del congreso">${congreso.descripcion}</textarea>

                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de Inicio</label>
                                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" value="${congreso.fechaInicio}">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="fechaFin" class="form-label">Fecha de Fin</label>
                                <input type="date" class="form-control" id="fechaFin" name="fechaFin" value="${congreso.fechaFin}">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="lugar" class="form-label">Lugar</label>
                            <input type="text" class="form-control" id="lugar" name="lugar" placeholder="Ciudad, auditorio, etc." value="${congreso.lugar}">
                        </div>
                        <div class="mb-3">
                            <label for="precio" class="form-label">Precio ($)</label>
                            <input type="number" step="0.01" min="35.00" class="form-control" id="precio" name="precio" placeholder="35.00" value="${congreso.precio}">
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>            

            <jsp:include page="/includes/footer.jsp"/>
        </main>
    </body>
</html>
