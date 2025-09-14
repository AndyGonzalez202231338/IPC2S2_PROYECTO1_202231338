<%-- 
    Document   : header
    Created on : 2/09/2025, 12:08:05
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="py-3 mb-3 border-bottom bg-dark">
  <div class="container-fluid d-flex justify-content-between align-items-center">
    
    <!-- Marca / Título -->
    <div class="d-flex align-items-center">
      <h3 class="titulosh3">CONGRESOS CUNOC</h3>
    </div>

    <!-- Barra de búsqueda + Navegación -->
    <div class="d-flex align-items-center gap-3">
      

      <!-- Botones navegación -->
      <nav class="d-flex gap-2">
        <a href="${pageContext.servletContext.contextPath}/Home/home-admin.jsp" class="btn btn-sm btn-outline-info me-2">Home</a>
        <a href="${pageContext.servletContext.contextPath}/CongresoServlet" class="btn btn-sm btn-outline-info me-2">Congresos</a>
        <a href="${pageContext.servletContext.contextPath}/UsuarioServlet" class="btn btn-sm btn-outline-info me-2">Usuarios</a>
      </nav>
    </div>
      <!-- Barra de búsqueda -->
      <form class="d-flex" role="search">
        <input type="search" class="form-control form-control-sm" placeholder="Buscar..." aria-label="Buscar">
      </form>
    <!-- Usuario -->
    <div class="dropdown">
      <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" 
         data-bs-toggle="dropdown" aria-expanded="false">
        <img src="https://github.com/mdo.png" alt="usuario" width="35" height="35" 
             class="rounded-circle border border-2 border-light">
      </a>
      <ul class="dropdown-menu dropdown-menu-end text-small shadow">
        <li><a class="dropdown-item" href="#">Configuración</a></li>
        <li><a class="dropdown-item" href="#">Perfil</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/index.jsp">Cerrar sesión</a></li>
      </ul>
    </div>

  </div>
</header>


