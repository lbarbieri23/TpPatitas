<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jsp:include page="header.jsp" />
<div class="content-wrapper">

    <div class="container">
        <div class="row mb-2">
            <div class="col-sm-12 mt-2">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-primary card-outline">
                            <div class="card-header">
                                <h3 class="card-title mt-2">
                                    <i class="far fa-sticky-note"></i>
                                    Tipo publicacion : ${command.publicacion.tipoPublicacion.descripcion}
                                </h3>
                                <c:if test="${command.permiteAcciones}">
                                <div class="float-right">
                                    <c:if test="${command.publicacion.tipoPublicacion.id == 3}">
                                        <a href="../actions/adoptar/${command.publicacion.id}" class="btn btn-success">Adoptar</a>
                                    </c:if>
                                    <c:if test="${command.publicacion.tipoPublicacion.id == 2}">
                                        <a href="../actions/reclamar/${command.publicacion.id}" class="btn btn-success">Reclamar</a>
                                    </c:if>
                                    <c:if test="${command.publicacion.tipoPublicacion.id == 1}">
                                        <a href="../actions/contactar/${command.publicacion.id}" class="btn btn-success">Contactar</a>
                                    </c:if>
                                    <sec:authorize access="isAuthenticated()">
                                        <c:if test="${command.publicacion.tipoPublicacion.id == 4}">
                                            <a href="javascript:showModalMisMascotas();" class="btn btn-success">Ofrecer</a>
                                        </c:if>
                                    </sec:authorize>
                                    <sec:authorize access="!isAuthenticated()">
                                        <c:if test="${command.publicacion.tipoPublicacion.id == 4}">
                                            <a href="../actions/ofrecer/${command.publicacion.id}" class="btn btn-success">Ofrecer</a>
                                        </c:if>
                                    </sec:authorize>
                                </div>
                                </c:if>
                            </div>
                            <div class="card-body pad ">
                                <c:if test="${not empty command.publicacion.mascota}">
                                    <div class="row">
                                        <div class="col-md-6 offset-5">
                                            <img src="${pageContext.request.contextPath}/resources/images/perro.jpg" width="200" height="200">
                                        </div>
                                    </div>
                                </c:if>
                                <br>
                                <div class="row mt-4">
                                    <c:if test="${not empty command.publicacion.mascota}">
                                    <div class="col-md-6">
                                        <div class="card card-primary ">
                                            <div class="card-header">
                                                <h3 class="card-title">
                                                    <i class="fas fa-paw"></i>
                                                    Datos mascota
                                                </h3>
                                            </div>
                                            <div class="card-body pad ">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <b>Tipo :</b> <span class="">${command.publicacion.mascota.tipoMascota.descripcion}</span>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <b>Nombre :</b> <span class="">${command.publicacion.mascota.nombre}</span>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <b>Apodo :</b> <span class="">${command.publicacion.mascota.apodo}</span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <b>Dueño :</b> <span class="">${command.publicacion.mascota.duenio.nombre}</span>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <b>Edad :</b> <span class="">${command.publicacion.mascota.edad}</span>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <b>Sexo : </b> <span class="">${command.publicacion.mascota.sexo.descripcion}</span>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row col-md-12">
                                                    <b>&nbsp;Caracteristicas</b><br>
                                                    <table class="table table-striped table-bordered">
                                                        <tbody>
                                                        <c:forEach items="${command.publicacion.mascota.caracteristicas}" var="caracteristica" varStatus="row">
                                                            <tr>
                                                                <td><b>${caracteristica.caracteristica.nombre}</b></td>
                                                                <td style="text-align: center">${caracteristica.valor.descripcion}</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </c:if>
                                    <c:if test="${not empty command.publicacion.mascota}">
                                        <div class="col-md-6">
                                    </c:if>
                                     <c:if test="${empty command.publicacion.mascota}">
                                        <div class="col-md-12">
                                    </c:if>

                                            <div class="row col-md-12">
                                            <h3>Descripción</h3>
                                        </div>
                                        <div class="row col-md-12">
                                            ${command.publicacion.descripcion}
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${not empty command.publicacion.respuestasContestadas}">
                                <div class="row">
                                    <div class="col-md-8">
                                        <h3>Información adicional</h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-8">
                                        <ul class="list-unstyled">
                                            <c:forEach items="${command.publicacion.respuestasContestadas}" var="respuesta" varStatus="row">
                                                <li>${respuesta.pregunta.pregunta}</li>
                                                <ul>
                                                    <li>${respuesta.respuesta.descripcion}</li>
                                                </ul>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

    <c:if test="${command.permiteAcciones}">

        <jsp:include page="modalMisMascotas.jsp" />
        <script>
            function executeModalAction() {
                $("#command").attr("action","${pageContext.request.contextPath}/pubs/actions/ofrecer/${command.publicacion.id}");
                $("#command").submit();
            }

        </script>

    </c:if>
<jsp:include page="footer.jsp" />



