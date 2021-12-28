<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="header.jsp" />


<!-- DUDA ger: estos botones de quiero adoptar y reportar mascota no deberian estar en el menu? -->
<div class="content-wrapper">
   <div class="content-header">
      <div class="container">
         <div class="row mb-2">
            <div class="col-sm-4">
               <h1 class="m-0"> Mis publicaciones</h1>
            </div><!-- /.col -->
            <div class="col-sm-8 ">
               <div class="float-right">
                  <a href="${pageContext.request.contextPath}/auth/mypubs/quiero-adoptar/nueva-publicacion" class="btn btn-primary"  style="margin-right: 5px">
                  <i class="fas fa-plus"></i>
                  Quiero adoptar
               </button>
                  </a>
               <a href="${pageContext.request.contextPath}/auth/mypubs/mascota-encontrada/nueva-publicacion" class="btn btn-primary"  >
                  <i class="fas fa-plus"></i>
                  Reportar mascota encontrada
                  </button>
               </a>
               </div>
            </div>
         </div><!-- /.row -->
      </div><!-- /.container-fluid -->
   </div>

   <div class="content">
      <div class="container">
         <section class="content">

            <!-- Default box -->
            <div class="card">
               <div class="card-header">
                  <h3 class="card-title">Publicaciones</h3>

               </div>
               <div class="card-body p-0">
                  <table class="table table-hover projects">
                     <thead>
                     <tr>
                        <th style="width: 1%">
                           #
                        </th>
                        <th style="width: 10%">
                           Foto
                        </th>
                        <th style="width: 20%">
                           Tipo de publicación
                        </th>
                        <th style="width: 40%">
                           Detalle
                        </th>
                        <th style="width: 30%">
                           Estado
                        </th>
                        <th style="width: 10%" class="text-center">
                           Acciones
                        </th>
                     </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${command.publicaciones}" var="publicacion" varStatus="row">
                        <a href="#">
                           <tr>
                              <td>${publicacion.id}</td>
                              <td>
                                 <ul class="list-inline">
                                    <li class="list-inline-item">
                                       <img alt="Avatar" class="table-avatar" src="${pageContext.request.contextPath}/resources/images/perro.jpg">
                                    </li>
                                 </ul>
                              </td>
                              <td>
                                    ${publicacion.tipoPublicacion.descripcion}
                              </td>
                              <td>
                                    ${publicacion.detalle}
                              </td>
                              <td>
                                    ${publicacion.estado.descripcion}
                              </td>
                              <td class="project-actions text-center">
                                 <div class="btn-group">
                                    <button type="button" class="btn btn-success">Acciones</button>
                                    <button type="button" class="btn btn-success dropdown-toggle dropdown-icon" data-toggle="dropdown">
                                       <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <!--FIXME VER SI HACER ACCIONES EN EL MODELO Y RECORRER UNA LISTA DE ACCIONES-->
                                    <div class="dropdown-menu" role="menu">
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/auth/mypubs/view/${publicacion.id}"><i class="fas fa-eye"></i>&nbsp;Ver</a>
                                       <c:if test="${publicacion.sePuedeEditar}">
                                          <a class="dropdown-item" href="${pageContext.request.contextPath}/auth/mypubs/editar/${publicacion.id}"><i class="fas fa-edit"></i>&nbsp;Editar</a>
                                       </c:if>
                                       <div class="dropdown-divider"></div>
                                       <c:if test="${publicacion.sePuedeFinalizar}">
                                          <a class="dropdown-item" href="javascript:doAction('${pageContext.request.contextPath}/auth/mypubs/finalizar/${publicacion.id}','Confirmar la Finalizacion', 'Esta acción no se podra cancelar. Confirma  la finalizacion de la publicacion')"><i class="fas fa-flag-checkered"></i>&nbsp;Finalizar</a>
                                       </c:if>
                                       <c:if test="${publicacion.sePuedeEntregar}">
                                          <a class="dropdown-item" href="javascript:doAction('${pageContext.request.contextPath}/auth/mypubs/entregar/${publicacion.id}','Confirmar la entrega', 'Esta acción no se podra cancelar. Confirma  la entrega de la mascota')"><i class="fas fa-envelope"></i>&nbsp;Entregar</a>
                                       </c:if>
                                       <c:if test="${publicacion.sePuedeRechazarSolicitud}">
                                          <a class="dropdown-item" href="javascript:doAction('${pageContext.request.contextPath}/auth/mypubs/rechazar/${publicacion.id}','Confirmar el rechazo', 'Esta acción no se podra cancelar. Confirma  que desea rechazar el reclamo')"><i class="fas fa-times"></i>&nbsp;Rechazar</a>
                                       </c:if>
                                       <c:if test="${publicacion.sePuedeCancelar}">
                                          <a class="dropdown-item" href="javascript:doAction('${pageContext.request.contextPath}/auth/mypubs/cancelar/${publicacion.id}','Confirmar cancelacion', 'Esta acción no se podra cancelar. Confirma  que desea cancelar la publicación')"><i class="fas fa-ban"></i>&nbsp;Cancelar</a>
                                       </c:if>
                                    </div>
                                 </div>

                              </td>

                           </tr>
                        </a>
                     </c:forEach>
                     </tbody>
                  </table>
               </div>
               <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <br>
            <br>
            <br>
            <div class="row mb-2">
               <div class="col-sm-12">
                  <h2 class="m-0"> Publicaciones que requieren acciones</h2>
               </div><!-- /.col -->
            </div>
            <!--PENDIENTES DE ACCION-->
            <div class="card">
               <div class="card-header">
                  <h3 class="card-title">Publicaciones pendientes de accion</h3>

               </div>
               <div class="card-body p-0">
                  <table class="table table-hover projects">
                     <thead>
                     <tr>
                        <th style="width: 1%">
                           #
                        </th>
                        <th style="width: 10%">
                           Foto
                        </th>
                        <th style="width: 20%">
                           Tipo de publicación
                        </th>
                        <th style="width: 40%">
                           Detalle
                        </th>
                        <th style="width: 30%">
                           Estado
                        </th>
                        <th style="width: 10%" class="text-center">
                           Acciones
                        </th>
                     </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${command.publicacionesPendienteAccion}" var="publicacion" varStatus="row">
                        <a href="#">
                           <tr>
                              <td>${publicacion.id}</td>
                              <td>
                                 <ul class="list-inline">
                                    <li class="list-inline-item">
                                       <img alt="Avatar" class="table-avatar" src="${pageContext.request.contextPath}/resources/images/perro.jpg">
                                    </li>
                                 </ul>
                              </td>
                              <td>
                                    ${publicacion.tipoPublicacion.descripcion}
                              </td>
                              <td>
                                    ${publicacion.detalle}
                              </td>
                              <td>
                                    ${publicacion.estado.descripcion}
                              </td>
                              <td class="project-actions text-center">
                                 <div class="btn-group">
                                    <button type="button" class="btn btn-success">Acciones</button>
                                    <button type="button" class="btn btn-success dropdown-toggle dropdown-icon" data-toggle="dropdown">
                                       <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <!--FIXME VER SI HACER ACCIONES EN EL MODELO Y RECORRER UNA LISTA DE ACCIONES-->
                                    <div class="dropdown-menu" role="menu">
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/auth/mypubs/view/${publicacion.id}"><i class="fas fa-eye"></i>&nbsp;Ver</a>
                                       <a class="dropdown-item" href="javascript:doAction('${pageContext.request.contextPath}/auth/mypubs/finalizar/${publicacion.id}','Confirmar la Finalizacion', 'Esta acción no se podra cancelar. Confirma  la finalizacion de la publicacion')"><i class="fas fa-flag-checkered"></i>&nbsp;Finalizar</a>

                                    </div>
                                 </div>

                              </td>

                           </tr>
                        </a>
                     </c:forEach>
                     </tbody>
                  </table>
               </div>
               <!-- /.card-body -->
            </div>
            <!-- /.card -->
         </section>

      </div>
   </div>
</div>

<script>
   function doAction(url, title, message) {
      Swal.fire({
         title: title,
         text: message,
         icon: 'question',
         showCancelButton: true,
         confirmButtonColor: '#3085d6',
         cancelButtonColor: '#d33',
         confirmButtonText: 'SI',
         cancelButtonText: 'NO'
      }).then((res) => {
         if (res.dismiss != 'cancel' && res.dismiss != 'backdrop') {
            $.ajax({
               url: url,
               method: "PATCH",
               success: function(data, textStatus, jqXHR){
                  window.location = '${pageContext.request.contextPath}/auth/mypubs/';
               },
            });
         }
      })

   }


</script>

<jsp:include page="footer.jsp" />