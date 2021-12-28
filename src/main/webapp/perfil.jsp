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
               <h1 class="m-0"> Mi Perfil</h1>
            </div><!-- /.col -->
         </div><!-- /.row -->
      </div><!-- /.container-fluid -->
   </div>

   <div class="content">
      <div class="container">
         <div class="row">
            <div class="col-md-3">

               <!-- Profile Image -->
               <div class="card card-primary card-outline">
                  <div class="card-body box-profile">
                     <div class="text-center">
                        <img class="profile-user-img img-fluid img-circle"
                             src="${pageContext.request.contextPath}/resources/images/nofoto.jpg"
                             alt="User profile picture">
                     </div>

                     <h3 class="profile-username text-center">${perfilForm.persona.nombre}&nbsp;${perfilForm.persona.apellido}</h3>

                     <p class="text-muted text-center">${perfilForm.persona.organizacion.descripcion}</p>

                     <ul class="list-group list-group-unbordered mb-3">
                        <li class="list-group-item">
                           <b>Contactos</b> <a class="float-right">2</a>
                           <br>
                           <b>Mascotas</b> <a class="float-right">${perfilForm.persona.misMascotas.size()}</a>
                        </li>
                     </ul>
                  </div>
                  <!-- /.card-body -->
               </div>
               <!-- /.card -->
            </div>
            <!-- /.col -->


                  <div class="col-md-9">
               <div class="card">
                  <div class="card-header p-2">
                     <ul class="nav nav-pills">
                        <li class="nav-item"><a class="nav-link active" href="#activity" data-toggle="tab">Mis Datos</a></li>
                        <li class="nav-item"><a class="nav-link" href="#timeline" data-toggle="tab">Mis Mascotas</a></li>
                        <li class="nav-item"><a class="nav-link" href="#settings" data-toggle="tab">Mis Contactos</a></li>
                     </ul>
                  </div><!-- /.card-header -->
                  <div class="card-body">
                     <div class="tab-content">
                        <div class="active tab-pane" id="activity">
                           <form:form method="POST" action="update.html" modelAttribute="perfilForm" >
                              <spring:bind path="*">

                              <form:errors path="*" cssClass="alert alert-danger" element="div" />
                              <form:hidden path="persona.id" />
                              <form:hidden path="persona.organizacion.id" />
                              <form:hidden path="persona.usuario.id" />
                              <div class="row">
                                 <div class="col-md-5">
                                    <div class="form-group">
                                       <label for="persona.organizacion.descripcion">Organizacion a la que pertenece</label>
                                       <form:input path="persona.organizacion.descripcion" cssClass="form-control" readonly="true"/>
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-5">
                                    <div class="form-group">
                                       <label for="persona.usuario.username">Nombre de usuario</label>
                                       <form:input path="persona.usuario.username" cssClass="form-control" readonly="true"/>
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-4">
                                    <div class="form-group">
                                       <label for="persona.nombre">Nombre</label>
                                       <form:input path="persona.nombre" cssClass="form-control"/>
                                    </div>
                                 </div>
                                 <div class="col-md-4 offset-1">
                                    <div class="form-group">
                                       <label for="persona.apellido">Apellido</label>
                                       <form:input path="persona.apellido" cssClass="form-control"/>
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-9">
                                    <div class="form-group">
                                       <label for="persona.direccion">Dirección</label>
                                       <form:input path="persona.direccion" cssClass="form-control"/>
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-4">
                                    <div class="form-group">
                                       <label for="persona.tipoDocumento">Tipo documento</label>
                                       <form:select path="persona.tipoDocumento" cssClass="form-control select2bs4">
                                          <form:option value="DNI">DNI</form:option>
                                          <form:option value="CI">CI</form:option>
                                          <form:option value="PASAPORTE">PASAPORTE</form:option>
                                       </form:select>
                                    </div>
                                 </div>
                                 <div class="col-md-4 offset-1">
                                    <div class="form-group">
                                       <label for="persona.apellido">Número</label>
                                       <form:input path="persona.documento" cssClass="form-control"/>
                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-4">
                                    <div class="form-group">
                                       <label for="persona.fechaNacimiento">Fecha Nacimiento</label>
                                       <div class="input-group">
                                          <form:input path="persona.fechaNacimiento" cssClass="date-picker form-control"/>
                                          <div class="input-group-append">
                                             <span class="input-group-text"><i class="far fa-calendar-alt"></i></span>
                                          </div>
                                       </div>

                                    </div>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-md-12">
                                    <button  type="submit" class="btn btn-primary btn-block float-right col-md-4" name="updateProfile">
                                       <i class="fa fa-save"></i>&nbsp;&nbsp;
                                       Actualizar
                                    </button>
                                 </div>
                              </div>
                              </spring:bind>
                           </form:form>
                        </div>

                        <!-- /.tab-pane -->
                        <div class="tab-pane" id="timeline">
                           <div class="card card-solid">
                              <div class="card-body pb-0">
                                 <div class="row">

                                    <!--Start Template-->
                                    <c:forEach items="${perfilForm.persona.misMascotas}" var="mascota" varStatus="row">
                                       <div class="col-12 col-sm-6 col-md-6 d-flex align-items-stretch flex-column">
                                       <div class="card bg-light d-flex flex-fill">
                                          <div class="card-header text-muted border-bottom-0">
                                             Mascota
                                          </div>
                                          <div class="card-body pt-0">
                                             <div class="row">
                                                <div class="col-7">
                                                   <h2 class="lead"><b>${mascota.nombre}</b></h2>
                                                   <p class="text-muted text-sm"><b>Tipo : </b> ${mascota.tipoMascota.descripcion} </p>
                                                   <p class="text-muted text-sm"><b>Estado : </b> ${mascota.estado.descripcion} </p>
                                                </div>
                                                <div class="col-5 text-center">
                                                   <img src="${pageContext.request.contextPath}/resources/images/perro.jpg" alt="user-avatar" class="img-circle img-fluid">
                                                </div>
                                             </div>
                                          </div>
                                          <div class="card-footer">
                                             <div class="text-right">
                                                <a href="${pageContext.request.contextPath}/auth/pets/view/${mascota.id}" class="btn btn-sm btn-primary">
                                                   <i class="fas fa-eye"></i>
                                                   Ver
                                                </a>
                                                <c:if test="${mascota.estaEnCasa()}">
                                                   <a href="${pageContext.request.contextPath}/auth/mypubs/mascota-en-adopcion/nueva-publicacion?mascota=${mascota.id}" class="btn btn-sm btn-success">
                                                      <i class="fas fa-paper-plane"></i>
                                                      Poner en adopción
                                                   </a>
                                                </c:if>
                                                <c:if test="${mascota.estaEnCasa()}">
                                                   <a href="${pageContext.request.contextPath}/auth/mypubs/mascota-perdida/nueva-publicacion?mascota=${mascota.id}" class="btn btn-sm btn-danger">
                                                      <i class="fas fa-sad-cry"></i>
                                                      Se perdió
                                                   </a>
                                                </c:if>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    </c:forEach>
                                    <!--End-->
                                 </div>
                              </div>
                           </div>
                        </div>

                        <div class="tab-pane" id="settings">
                           <!-- Default box -->
                           <div class="card card-solid">
                              <div class="card-body pb-0">
                                 <div class="row">
                                    <div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column">
                                       <div class="card bg-light d-flex flex-fill">
                                          <div class="card-header text-muted border-bottom-0">
                                             Contacto
                                          </div>
                                          <div class="card-body pt-0">
                                             <div class="row">
                                                <div class="col-7">
                                                   <h2 class="lead"><b>Barbieri Leonel</b></h2>
                                                   <p class="text-muted text-sm"><b>Parentezco: </b> Amigo </p>
                                                   <ul class="ml-4 mb-0 fa-ul text-muted">
                                                      <li class="small"><span class="fa-li"><i class="fas fa-lg fa-building"></i></span> Direccion: Av avellaneda 5232 BsAs</li>
                                                      <li class="small"><span class="fa-li"><i class="fas fa-lg fa-phone"></i></span> Telefono #: +54 011 30 85 26 42</li>
                                                   </ul>
                                                </div>
                                                <div class="col-5 text-center">
                                                   <img src="${pageContext.request.contextPath}/resources/images/user1-128x128.jpg" alt="user-avatar" class="img-circle img-fluid">
                                                </div>
                                             </div>
                                          </div>
                                          <div class="card-footer">
                                             <div class="text-right">
                                                <a href="#" class="btn btn-sm bg-teal">
                                                   <i class="fas fa-comments"></i>
                                                </a>
                                                <a href="#" class="btn btn-sm btn-primary">
                                                   <i class="fas fa-user"></i> Ver Perfil
                                                </a>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch flex-column">
                                       <div class="card bg-light d-flex flex-fill">
                                          <div class="card-header text-muted border-bottom-0">
                                             Contacto
                                          </div>
                                          <div class="card-body pt-0">
                                             <div class="row">
                                                <div class="col-7">
                                                   <h2 class="lead"><b>German koelberg</b></h2>
                                                   <p class="text-muted text-sm"><b>Parentezco: </b> Amigo </p>
                                                   <ul class="ml-4 mb-0 fa-ul text-muted">
                                                      <li class="small"><span class="fa-li"><i class="fas fa-lg fa-building"></i></span> Direccion: Av. Libertador 3684</li>
                                                      <li class="small"><span class="fa-li"><i class="fas fa-lg fa-phone"></i></span> Telefono #: +54 011 68 50 41 53</li>
                                                   </ul>
                                                </div>
                                                <div class="col-5 text-center">
                                                   <img src="${pageContext.request.contextPath}/resources/images/user8-128x128.jpg" alt="user-avatar" class="img-circle img-fluid">
                                                </div>
                                             </div>
                                          </div>
                                          <div class="card-footer">
                                             <div class="text-right">
                                                <a href="#" class="btn btn-sm bg-teal">
                                                   <i class="fas fa-comments"></i>
                                                </a>
                                                <a href="#" class="btn btn-sm btn-primary">
                                                   <i class="fas fa-user"></i> Ver Perfil
                                                </a>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
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