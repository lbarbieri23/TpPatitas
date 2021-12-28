<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="header.jsp" />


<form:form method="post" action="./${command.mode}" modelAttribute="command">
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

                     <div class="card card-primary">
                        <div class="card-header">
                           <h3 class="card-title">Datos de la mascota encontrada</h3>
                        </div>
                        <div class="card-body">
                           <div class="form-group">
                              <label for="publicacion.mascota.tipoMascota.id">Tipo de mascota</label>
                              <form:select path="publicacion.mascota.tipoMascota.id" cssClass="form-control select2bs4 cmbTipoMascota">
                                 <form:options items="${command.tipoMascotas}" itemValue="id" itemLabel="descripcion"/>
                              </form:select>
                           </div>
                           <!--Input de ejemplo
                           <div class="form-group">
                              <label for="publicacion.mascota.nombre">Nombre de la mascota encontrada</label>
                              <form:input path="publicacion.mascota.nombre" class="form-control"/>
                           </div>
                                 FIN EJEMPLO-->

                           <div class="form-group">
                              <label for="publicacion.mascota.sexo.id">Sexo</label>
                              <form:select path="publicacion.mascota.sexo.id" cssClass="form-control select2bs4">
                                 <form:option value="1" label="MASCULINO"/>  <!--FIXME TRAER DE LA BASE-->
                                 <form:option value="2" label="FEMENINO"/>
                              </form:select>
                           </div>
                           <div class="form-group">
                              <label for="publicacion.mascota.descripcionFisica">Descripcion fisica</label>
                              <form:textarea path="publicacion.mascota.descripcionFisica" class="form-control"/>
                           </div>

                           <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                        <!-- /.card-body -->
                     </div>

                     <div class="card card-primary">
                        <div class="card-header">
                           <h3 class="card-title">Caracteristicas de la mascota </h3>
                        </div>
                        <div class="card-body bodyCaracteristicas">
                           <div class="form-group">
                              <label for="publicacion.mascota.descripcionFisica">Descripcion fisica</label>
                              <form:textarea path="publicacion.mascota.descripcionFisica" class="form-control"/>
                           </div>
                           <c:if test="${not empty command.publicacion.mascota.caracteristicas}">
                              <c:forEach items="${command.publicacion.mascota.caracteristicas}" var="item" varStatus="row">
                                 <div class='form-group'>
                                    <label for='publicacion.mascota.caracteristicas[${row.index}].valor.id'>${item.caracteristica.nombre}</label>
                                    <form:select path="publicacion.mascota" class='form-control select2bs4'>
                                       <form:options items="${item.caracteristica.opciones}"  itemLabel="descripcion" itemValue="id" />
                                    </form:select>
                                    <form:hidden path="publicacion.mascota"/>
                                 </div>
                              </c:forEach>
                           </c:if>
                        </div>
                     </div>

                     <div class="card card-primary">
                        <div class="card-header">
                           <h3 class="card-title">Datos de la publicacion </h3>
                        </div>
                        <div class="card-body">


                           <!--   <div class="form-group">
                              <label for="publicacion.detalle">Detalle (Maximo 100 caracteres)</label>
                              <form:textarea path="publicacion.detalle" class="form-control" maxlength="100"/>
                           </div>-->
                           <div class="form-group">
                              <label for="publicacion.descripcion">Descripcion de la publicacion</label>
                              <form:textarea path="publicacion.descripcion" class="form-control" cssStyle="height: 100px"/>
                           </div>
                           <div class="form-group">
                              <label for="ubicacion">Ubicación donde la encontro</label>
                              <form:input path="ubicacion" class="form-control"/>
                           </div>
                           <div class="form-group">
                              <label for="inputLugar">Cuenta con lugar para quedarse con la mascota</label>
                              <select id="inputLugar" class="form-control custom-select">
                                 <option selected disabled>Seleccione una opcion</option>
                                 <option value="1">Si</option>
                                 <option value="0">No</option>
                              </select>
                           </div>
                           <div class="form-group">
                              <label for="hogar.id">Si no cuenta con lugar, elija un hogar de transito</label>
                              <form:select path="hogar.id" cssClass="form-control select2bs4">
                                 <form:options items="${command.hogares}"  itemValue="id" itemLabel="nombre" />
                              </form:select>


                           </div>

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

   function fillCaracteristicas() {
      let idTipoMascota = $(".cmbTipoMascota").val();
      $.getJSON("findCaracteristicasBy?tipoMascotaId=" + idTipoMascota, function(data) {
         $('.bodyCaracteristicas').empty();
         $.each(data, function(index, item) {
            draw(item, index);
         });
      });
   }

   function draw(item, index) {
      var html = "";


      html += "<div class='form-group'>";
      html += "<label for='publicacion.mascota.caracteristicas["+index+"].valor.id'>"+ item.nombre +"</label>";
      html += "<select id='publicacion.mascota.caracteristicas["+index+"].valor.id' name='publicacion.mascota.caracteristicas["+index+"].valor.id' class='form-control select2bs4'>"
      $.each(item.opciones, function(i, option) {
         html += "<option value='"+ option.id+"'>"+option.descripcion+"</option>";
      });
      html += "</select>";

      html += "<input type='hidden' id='publicacion.mascota.caracteristicas["+index+"].id' name='publicacion.mascota.caracteristicas["+index+"].caracteristica.id' value='" + item.id + "'/>";
      html += "</div>";

      $('.bodyCaracteristicas').append(html);
   }

   $(document).ready(function() {
      $(".cmbTipoMascota").change(function () {
         fillCaracteristicas();
      });

      <c:if test="${empty command.publicacion.mascota.caracteristicas}">
      fillCaracteristicas();
      </c:if>
   });

</script>


<jsp:include page="footer.jsp" />