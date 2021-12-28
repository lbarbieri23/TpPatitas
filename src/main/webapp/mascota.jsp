<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="header.jsp" />


<div class="content-wrapper">
   <div class="content-header">
      <div class="container">
         <div class="row mb-2">
            <div class="col-sm-6">
               <h1 class="m-0"> Mascota</h1>
            </div><!-- /.col -->
         </div><!-- /.row -->
      </div><!-- /.container-fluid -->
   </div>

   <div class="content">
      <div class="container">
         <div class="row">
            <div class="col-md-6">

               <!-- Profile Image -->
               <div class="card card-primary card-outline">
                  <div class="card-body box-profile">
                     <div class="text-center">
                        <img class="profile-user-img img-fluid img-circle"
                             src="${pageContext.request.contextPath}/resources/images/perro.jpg"
                             alt="User profile picture">
                     </div>

                     <h3 class="profile-username text-center">${command.mascota.nombre}&nbsp;| ${command.mascota.apodo}</h3>

                     <p class="text-muted text-center">Dueño : ${command.mascota.duenio.nombre} ${command.mascota.duenio.apellido}</p>
                     <p class="text-muted text-center">Edad : ${command.mascota.edad}</p>
                     <p class="text-muted text-center">Sexo : ${command.mascota.sexo.descripcion}</p>
                     <p class="text-muted text-center">Estado : ${command.mascota.estado.descripcion}</p>
                     <p class="text-muted ">${command.mascota.descripcionFisica}</p>
                  </div>
                  <!-- /.card-body -->
               </div>
               <!-- /.card -->
            </div>
            <!-- /.col -->

            <div class="col-md-6">
               <div class="card">
                  <div class="card-header p-2">
                     <ul class="nav nav-pills">
                        <li class="nav-item"><a class="nav-link active" href="#fotos" data-toggle="tab">Fotos</a></li>
                        <li class="nav-item"><a class="nav-link" href="#caracteristicas" data-toggle="tab">Caracteristicas</a></li>
                     </ul>
                  </div><!-- /.card-header -->
                  <div class="card-body">
                     <div class="tab-content">
                        <div class="active tab-pane" id="fotos">
                           <div class="row">
                              <c:forEach items="${command.mascota.fotos}" var="foto" varStatus="row">
                                 <div class="col-sm-2">
                                    <a href="#" data-toggle="lightbox" data-title="foto" data-gallery="gallery">
                                       <img src="data:image/png;base64, ${foto.base64}" class="img-fluid mb-2" alt="white sample"/>
                                    </a>
                                 </div>
                              </c:forEach>
                           </div>
                        </div>
                        <div class="tab-pane" id="caracteristicas">
                           <div class="row">
                              <div class="col-md-5">
                                 <div class="form-group">
                                    <c:forEach items="${command.mascota.caracteristicas}" var="c" varStatus="row">
                                       ${c.caracteristica.nombre} : ${c.valor.descripcion}<br/>
                                    </c:forEach>
                                 </div>
                              </div>
                           </div>
                        </div>

                        <!-- /.card-body -->
                        <!--<div class="card-footer">
                           <nav aria-label="Contacts Page Navigation">
                              <ul class="pagination justify-content-center m-0">
                                 <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                 <li class="page-item"><a class="page-link" href="#">2</a></li>
                                 <li class="page-item"><a class="page-link" href="#">3</a></li>
                                 <li class="page-item"><a class="page-link" href="#">4</a></li>
                                 <li class="page-item"><a class="page-link" href="#">5</a></li>
                                 <li class="page-item"><a class="page-link" href="#">6</a></li>
                                 <li class="page-item"><a class="page-link" href="#">7</a></li>
                                 <li class="page-item"><a class="page-link" href="#">8</a></li>
                              </ul>
                           </nav>
                        </div>-->
                        <!-- /.tab-pane -->
                     </div>
                     <!-- /.tab-content -->
                  </div><!-- /.card-body -->
               </div>
               <!-- /.card -->
            </div>


            <!-- /.col -->
         </div>
      </div>
   </div>
</div>

<jsp:include page="footer.jsp" />