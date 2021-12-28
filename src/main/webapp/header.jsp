<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="principal" property="principal" />

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mis Mascotas</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.ico">

    <jsp:include page="include.jsp" />

</head>
<body class="hold-transition layout-top-nav" style="background-color: #f4f6f9 !important;">
<div class="wrapper">

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">

        <div class="container">
            <a href="#" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
                <span class="brand-text font-weight-light">Mis mascotas</span>
            </a>

            <button class="navbar-toggler order-1" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse order-3" id="navbarCollapse">
                <!-- Left navbar links -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}" class="nav-link">Publicaciones</a>
                    </li>
                </ul>
            </div>

            <!-- Right navbar links -->
            <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
                <!-- Messages Dropdown Menu -->
                <sec:authorize access="!isAuthenticated()">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/login.html" class="nav-link">Iniciar sesión</a>
                        </li>
                    </ul>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item dropdown">
                        <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">${principal.username}</a>
                        <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                            <li><a href="${pageContext.request.contextPath}/auth/profile/" class="dropdown-item">Mi Perfil </a></li>
                            <li><a href="${pageContext.request.contextPath}/auth/mypubs/" class="dropdown-item">Mis publicaciones </a></li>
                            <li class="dropdown-divider"></li>
                            <li><a href="${pageContext.request.contextPath}/logout.html" class="dropdown-item">Cerrar sesión</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </nav>
        <script>
            function logout() {
                swal.fire({
                    title: 'Logout',
                    text: '¿Confirma que desea cerrar su sesion?',
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#28a745',
                    cancelButtonColor: '#d33',
                    focusCancel: true,
                    confirmButtonText: 'Si',
                    cancelButtonText: 'No'
                }).then( res => {
                    if (res.dismiss != 'cancel' && res.dismiss != 'backdrop') {
                        window.location.href = '${pageContext.request.contextPath}/logout.html';
                    }
                });
            }
        </script>


    <!-- Fin Navbar -->