<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="header.jsp" />



<div class="content-wrapper">
   <section class="content-header">
      <div class="container-fluid">
         <div class="row mb-2">
            <div class="col-sm-6">
               <h1>Agregar Mascota</h1>
            </div>
            <div class="col-sm-6">
               <ol class="breadcrumb float-sm-right">
                  <li class="breadcrumb-item"><a href="#">Inicio</a></li>
                  <li class="breadcrumb-item active">Perfil</li>
               </ol>
            </div>
         </div>
      </div><!-- /.container-fluid -->
   </section>

   <form:form method="POST" action="save.html">
   <!-- Main content -->
   <section class="content">
      <div class="row">
         <div class="col-md-6">
            <div class="card card-primary">
               <div class="card-header">
                  <h3 class="card-title">General</h3>

                  <div class="card-tools">
                     <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body">
                  <div class="form-group">
                     <label for="mascota.nombre">Nombre</label>
                     <form:input path="mascota.nombre" cssClass="form-control"/>
                  </div>
                  <div class="form-group">
                     <label for="inputDescription">Descripcion</label>
                     <textarea id="inputDescription" class="form-control" rows="4"></textarea>
                  </div>
                  <div class="form-group">
                     <label for="inputStatus">Estado</label>
                     <select id="inputStatus" class="form-control custom-select">
                        <option selected disabled>Select one</option>
                        <option>On Hold</option>
                        <option>Canceled</option>
                        <option>Success</option>
                     </select>
                  </div>
                  <div class="form-group">
                     <label for="inputClientCompany">Organizacion</label>
                     <input type="text" id="inputClientCompany" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputProjectLeader">Raza</label>
                     <input type="text" id="inputProjectLeader" class="form-control">
                  </div>
               </div>
               <!-- /.card-body -->
            </div>
            <!-- /.card -->
         </div>
         <div class="col-md-6">
            <div class="card card-secondary">
               <div class="card-header">
                  <h3 class="card-title">Caracteristicas</h3>

                  <div class="card-tools">
                     <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                        <i class="fas fa-minus"></i>
                     </button>
                  </div>
               </div>
               <div class="card-body">
                  <div class="form-group">
                     <label for="inputEstimatedBudget">Añadir</label>
                     <input type="number" id="inputEstimatedBudget" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputSpentBudget">Añadir</label>
                     <input type="number" id="inputSpentBudget" class="form-control">
                  </div>
                  <div class="form-group">
                     <label for="inputEstimatedDuration">Añadir</label>
                     <input type="number" id="inputEstimatedDuration" class="form-control">
                  </div>
               </div>
               <!-- /.card-body -->
            </div>
            <!-- /.card -->
         </div>
      </div>
      <div class="row">
         <div class="col-12">
            <a href="#" class="btn btn-secondary">Cancel</a>
               <input type="submit" value="Añadir Mascota" class="btn btn-success float-right" name="grabar">
               <input type="submit" value="Dar adopcion" class="btn btn-success float-right" name="adopcion">
         </div>
      </div>
   </section>
   </form:form>
   <!-- /.content -->
</div>




<jsp:include page="footer.jsp" />