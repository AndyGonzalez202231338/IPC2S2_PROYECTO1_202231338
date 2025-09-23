<%-- 
    Document   : home-admin
    Created on : 30/08/2025, 22:02:39
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <main>
            <jsp:include page="/includes/header.jsp"/>

            <div class="containerAdmin">
                <!-- Si es USUARIO normal -->
                <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('NORMAL')}">
                    <div class="alert alert-success">Bienvenido ${usuarioLogueado.nombreCompleto}</div>
                    <!-- ===== Sección de Features ===== -->
                    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-calendar-event"></i>
                            </div>
                            <h3 class="titulosh3">Congresos</h3>
                            <p>Unirse a Congresos y Actividades.</p>
                            <a href="${pageContext.servletContext.contextPath}/CongresoServlet" class="icon-link">
                                Ver congresos <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-people-fill"></i>
                            </div>
                            <h3 class="titulosh3">Usuario</h3>
                            <p>Administrar tu cuenta.</p>
                            <a href="${pageContext.servletContext.contextPath}/VerUsuarioServlet?correo=${usuarioLogueado.correo}" class="icon-link">
                                Ver mi usuario <i class="bi bi-chevron-right"></i>
                            </a>
                            <a href="${pageContext.servletContext.contextPath}/UsuarioServlet?correo=${usuarioLogueado.correo}" class="icon-link">
                                Editar mi usuario <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-cash-stack"></i>
                            </div>
                            <h3 class="titulosh3">Cartera</h3>
                            <p>Dinero digital del usuario.</p>
                            <a href="${pageContext.servletContext.contextPath}/AgregarDineroServlet?correo=${usuarioLogueado.correo}" class="icon-link">
                                Ver cuenta <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                    </div>
                </c:if>
                
                <!-- Si es ADMIN -->
                <c:if test="${usuarioLogueado.tipoCuenta.equalsIgnoreCase('ADMIN')}">
                    <div class="alert alert-info">Bienvenido Administrador ${usuarioLogueado.nombreCompleto}</div>
                    <!-- ===== Sección de Features ===== -->
                    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-calendar-event"></i>
                            </div>
                            <h3 class="titulosh3">Congresos</h3>
                            <p>Administra los congresos disponibles.</p>
                            <a href="${pageContext.servletContext.contextPath}/Congreso/congreso.jsp" class="icon-link">
                                Ver congresos <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-bank"></i>
                            </div>
                            <h3 class="titulosh3">Instituciones</h3>
                            <p>Organizaciones o Instituciones encargadas de Congresos.</p>
                            <a href="${pageContext.servletContext.contextPath}/OrganizacionServlet" class="icon-link">
                                Ver Organizaciones <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-people-fill"></i>
                            </div>
                            <h3 class="titulosh3">Usuarios</h3>
                            <p>Gestiona los usuarios y participantes.</p>
                            <a href="${pageContext.servletContext.contextPath}/VerOrganizacionesServlet" class="icon-link">
                                Ver usuarios <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                        <div class="feature col">
                            <div class="feature-icon d-inline-flex align-items-center justify-content-center fs-2 mb-3">
                                <i class="bi bi-cash-stack"></i>
                            </div>
                            <h3 class="titulosh3">Ganancias</h3>
                            <p>Consulta los reportes de ganancias.</p>
                            <a href="${pageContext.servletContext.contextPath}/Congreso/congreso.jsp" class="icon-link">
                                Ver reportes <i class="bi bi-chevron-right"></i>
                            </a>
                        </div>
                    </div>
                </c:if>

                



                <!-- ===== Nueva sección: Congresos inscritos ===== -->
                <div class="congresos-inscritos mt-5">
                    <h2>Congresos a los que estás inscrito</h2>
                    <table class="table table-dark table-hover mt-3">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nombre del Congreso</th>
                                <th>Fecha</th>
                                <th>Lugar</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Aquí puedes iterar desde el backend con JSTL -->
                            <tr>
                                <td>1</td>
                                <td>Congreso Internacional de Tecnología</td>
                                <td>15/09/2025</td>
                                <td>Auditorio Central</td>
                                <td><a href="#" class="btn btn-info btn-sm">Ver</a></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Congreso de Innovación Educativa</td>
                                <td>20/09/2025</td>
                                <td>Salón Magna</td>
                                <td><a href="#" class="btn btn-info btn-sm">Ver</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>

