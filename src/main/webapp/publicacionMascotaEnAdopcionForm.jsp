<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="header.jsp" />


<form:form method="post" action="./" modelAttribute="command">
    <div class="content-wrapper">
        <div class="content-header">
            <div class="container">
                <div class="row mb-2">
                    <div class="col-sm-8">
                        <h1 class="m-0"> Nueva publicación - ${command.publicacion.tipoPublicacion.descripcion}</h1>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>

        <div class="content">
            <div class="container">
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="alert alert-info">
                                Usted esta por dar en adopcion a su mascota ${command.publicacion.mascota.nombre} .
                            </div>

                            <form:hidden path="publicacion.mascota.id" />
                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Datos de la publicación </h3>
                                </div>
                                <div class="card-body">

                                    <div class="form-group">
                                        <label for="publicacion.detalle">Detalle (Maximo 100 caracteres)</label>
                                        <form:textarea path="publicacion.detalle" class="form-control" maxlength="100"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="publicacion.descripcion">Descripcion de la publicacion</label>
                                        <form:textarea path="publicacion.descripcion" class="form-control" cssStyle="height: 100px"/>
                                    </div>

                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->


                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Por favor responda la siguiente pregutas </h3>
                                </div>
                                <div class="card-body">
                                    <c:forEach items="${command.preguntasAdopcion}" var="pregunta" varStatus="row">
                                        <div class="form-group">
                                            <form:hidden path="publicacion.respuestas[${row.index}].pregunta.id" value="${pregunta.id}"/>
                                            <label for="publicacion.detalle">${pregunta.pregunta}</label>
                                            <form:select path="publicacion.respuestas[${row.index}].respuesta.id" cssClass="form-control select2bs4">
                                                <form:options items="${pregunta.opciones}"  itemValue="id" itemLabel="descripcion" />
                                            </form:select>
                                        </div>
                                    </c:forEach>

                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <a href="#" class="btn btn-secondary">Cancelar</a>
                            <input type="submit" value="Publicar Mascota" class="btn btn-success float-right">
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
</form:form>

<script>




</script>


<jsp:include page="footer.jsp" />