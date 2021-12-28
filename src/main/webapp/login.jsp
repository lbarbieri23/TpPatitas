<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mis Mascotas</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.ico">

    <jsp:include page="include.jsp" />

</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <img src="${pageContext.request.contextPath}/resources/images/login.png" class="mb-4"> </img>
        <spring:message code="login"/>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Complete los datos para iniciar sesión</p>

            <form:form action="login.html" method="POST" modelAttribute="usuario">
                    <spring:bind path="*">

                    <form:errors path="username" cssClass="alert alert-danger" element="div" />

                    <div class="input-group mb-3">
                        <form:input path="username"  class="form-control" placeholder="Ingrese su email"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-envelope"></span>
                            </div>
                        </div>
                    </div>
                    <div class="input-group mb-3">
                        <form:password path="password" class="form-control" placeholder="Ingrese su contraseña"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button name="login" type="submit" class="btn btn-primary btn-block float-right">Iniciar sesión</button>
                        </div>
                    </div>
                    </spring:bind>
            </form:form>

            <p class="mb-1" style="width: 100%; display: flex; justify-content: center">
                <a href="forgot-password.html">Olvidé mi contraseña</a>
            </p>
            <p class="mb-0" style="width: 100%; display: flex; justify-content: center">
                <a href="register" class="text-center">Crear un usuario</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->

</body>
</html>
