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
                        <c:if test="${command.estaEditando}">
                            <h1 class="m-0"> Editar publicación: n°${command.publicacion.id}</h1>
                        </c:if>
                        <c:if test="${!command.estaEditando}">
                            <h1 class="m-0"> Nueva publicación - ${command.publicacion.tipoPublicacion.descripcion}</h1>
                        </c:if>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>

        <div class="content">
            <div class="container">
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Tipo mascota buscada </h3>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="selectTipoMascota">Tipo de mascota</label>
                                        <form:select id="selectTipoMascota" path="publicacion.tipoMascota.id" cssClass="form-control select2bs4 cmbTipoMascota">
                                            <form:options items="${command.tipoMascotas}" itemValue="id" itemLabel="descripcion"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="publicacion.sexo.id">Sexo</label>
                                        <form:select path="publicacion.sexo.id" cssClass="form-control select2bs4">
                                            <form:option value="1" label="MASCULINO"/>  <!--FIXME TRAER DE LA BASE-->
                                            <form:option value="2" label="FEMENINO"/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>


                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Por favor responda a las siguientes condiciones: </h3>
                                </div>
                                <div class="card-body ">
                                    <c:forEach items="${command.preguntas}" var="pregunta" varStatus="row">
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




                            <div class="card card-primary">
                                <div class="card-header">
                                    <h3 class="card-title">Caracteristicas buscadas</h3>
                                </div>
                                <div class="card-body bodyCaracteristicas">



                                </div>
                            </div>
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



                            <!-- /.card -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <a href="#" class="btn btn-secondary">Cancelar</a>
                            <c:if test="${command.estaEditando}">
                                <input type="submit" value="Guardar edición" class="btn btn-success float-right">
                            </c:if>
                            <c:if test="${!command.estaEditando}">
                                <input type="submit" value="Publicar Mascota" class="btn btn-success float-right">
                            </c:if>
                        </dv>
                    </div>
                </section>

            </div>
        </div>
    </div>
</form:form>

<script>

    function fillCaracteristicas() {
        let idTipoMascota = $(".cmbTipoMascota").val();
        $.getJSON("findCaracteristicasBy?tipoMascotaId=" + idTipoMascota, function(data) {
            $('.bodyCaracteristicas').empty();
            $.each(data, function(index, item) {
                console.log("data: ", data)
                draw(item, index);
            });
        });
    }

    function draw(item, index) {
        var html = "";


        html += "<div class='form-group'>";
        html += "<label for='publicacion.caracteristicasDeMascotaBuscada["+index+"].opcion.id'>"+ item.nombre +"</label>";
        html += "<select id='publicacion.caracteristicasDeMascotaBuscada["+index+"].opcion.id' name='publicacion.caracteristicasDeMascotaBuscada["+index+"].opcion.id' class='form-control select2bs4'>"
        //html += "<option value='-1'>INDISTINTO</option>";
        $.each(item.opciones, function(i, option) {
            html += "<option value='"+ option.id+"'>"+option.descripcion+"</option>";
        });
        html += "</select>";

        html += "<input type='hidden' id='publicacion.caracteristicasDeMascotaBuscada["+index+"].id' name='publicacion.caracteristicasDeMascotaBuscada["+index+"].caracteristica.id' value='" + item.id + "'/>";
        html += "</div>";

        $('.bodyCaracteristicas').append(html);
    }

    $(document).ready(function() {
        $(".cmbTipoMascota").change(function () {
            fillCaracteristicas();
        });

        fillCaracteristicas();
    });

</script>



<jsp:include page="footer.jsp" />