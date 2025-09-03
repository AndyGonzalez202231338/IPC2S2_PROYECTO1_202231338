<%-- 
    Document   : header
    Created on : 2/09/2025, 12:08:05
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="py-3 mb-3 border-bottom">
  <div class="container-fluid d-grid gap-3 align-items-center" style="grid-template-columns: auto 1fr auto;">
    
    <!-- Dropdown menú principal -->
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <!-- Icono reemplazado por un círculo simple -->
        <div style="width: 32px; height: 32px; background-color: #4db6ac; border-radius: 50%; display: flex; justify-content: center; align-items: center; color: #121212; font-weight: bold;">
          ☰
        </div>
      </a>
      <ul class="dropdown-menu text-small shadow">
        <li><a class="dropdown-item active" href="#" aria-current="page">Descripción general</a></li>
        <li><a class="dropdown-item" href="#">Inventario</a></li>
        <li><a class="dropdown-item" href="#">Clientes</a></li>
        <li><a class="dropdown-item" href="#">Productos</a></li>
        <li><hr class="dropdown-divider"></li>
        <li><a class="dropdown-item" href="#">Informes</a></li>
        <li><a class="dropdown-item" href="#">Analítica</a></li>
      </ul>
    </div>

    <!-- Título / Marca -->
    <div class="d-flex justify-content-center">
      <span class="fs-4 fw-bold text-light">CONGRESOS CUNOC</span>
    </div>

    <!-- Barra de búsqueda y usuario -->
    <div class="d-flex align-items-center">
      <form class="w-100 me-3" role="search">
        <input type="search" class="form-control" placeholder="Buscar..." aria-label="Buscar">
      </form>

      <div class="flex-shrink-0 dropdown">
        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
          <img src="https://github.com/mdo.png" alt="usuario" width="32" height="32" class="rounded-circle">
        </a>
        <ul class="dropdown-menu text-small shadow">
          <li><a class="dropdown-item" href="#">Nuevo proyecto...</a></li>
          <li><a class="dropdown-item" href="#">Configuración</a></li>
          <li><a class="dropdown-item" href="#">Perfil</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">Cerrar sesión</a></li>
        </ul>
      </div>
    </div>

  </div>
</header>

