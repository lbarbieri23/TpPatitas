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
        <spring:message code="register"/>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Registra un usuario nuevo</p>

            <form:form action="register" method="POST" modelAttribute="registracionForm" id="form">
                    <spring:bind path="*">

                        <form:errors path="*" cssClass="alert alert-danger" element="div" />
                        <span class="alert alert-danger" style="visibility:hidden" id="errorMsj"> Las contraseñas deben coincidir </span>
                    <div class="input-group mb-3">
                        <form:input path="usuario.username"  class="form-control" placeholder="Ingrese un nombre de usuario"/>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                    </div>
                    <div class="input-group mb-3">
                        <form:input path="usuario.password"  class="form-control" placeholder="Ingrese la contraseña"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                        <div class="input-group mb-3">
                            <input type="password" name="repeatPassword" id="repeatPassword" class="form-control" placeholder="Repita la contraseña" required>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-lock"></span>
                                </div>
                            </div>
                        </div>
                    <div class="form-group">
                        <label>Seleccione el rol de usuario</label>
                        <form:select path="usuario.rol.id" cssClass="form-control select2bs4">
                            <form:option value="1" label="Usuario" />
                            <form:option value="2" label="Voluntario" />
                            <form:option value="3" label="Administrador Organizacion" />
                        </form:select>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button name="login" type="submit" class="btn btn-primary btn-block float-right">Registrarse</button>
                        </div>
                    </div>
                    </spring:bind>
            </form:form>

            <p class="mb-0" style="width: 100%; display: flex; justify-content: center">
                <a href="login.html" class="text-center">Tengo un usuario</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->

</body>
</html>
