<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="header.jsp" />


<div class="content-wrapper">
   <div class="content-header">
      <div class="container">
         <div class="row mb-2">
            <div class="col-sm-6">
               <h1 class="m-0"> Buscador de publicaciones</h1>
            </div><!-- /.col -->
         </div><!-- /.row -->
      </div><!-- /.container-fluid -->
   </div>
   <div class="content">
      <!-- Buscador -->
      <form:form method="POST" action="search" modelAttribute="command">
      <div class="container" style="width: 30%; padding: 30px 0px;">
         <div class="input-group input-group-sm">
            <form:input path="searchCriteria" class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search"/>
            <div class="input-group-append g-botonSearch">
               <button class="btn btn-navbar" type="submit" name="search">
                  <i class="fas fa-search"></i>
               </button>
            </div>
         </div>
         <div class="button-group text-center mr-4">
            <button type="submit" name="all" class="btn btn-xs btn-default" style="border-color: #fff">Todas</button>
            <button type="submit" name="perdidas" class="btn btn-xs btn-danger" style="border-color: #fff"> Mascotas Perdidas</button>
            <button type="submit" name="encontradas" class="btn btn-xs btn-success" style="border-color: #fff"> Mascotas Encontradas</button>
            <button type="submit" name="quieroadoptar" class="btn btn-xs btn-secondary" style="border-color: #fff"> Quiero adoptar</button>
            <button type="submit" name="enadopcion" class="btn btn-xs btn-primary" style="border-color: #fff"> Mascotas en adopcion</button>
         </div>
      </div>
      </form:form>
      <!-- Publicaciones -->
      <div class="container" >
         <!-- una mascota perdida -->
         <c:forEach items="${command.publicacionList}" var="publicacion" varStatus="row">

         <div class="d-flex flex-row justify-content-center" style="margin-bottom: 20px">

            <c:if test="${publicacion.tipoPublicacion.id == 3}">
               <div class="card card-primary card-outline   " style="width:70%; ">
            </c:if>
            <c:if test="${publicacion.tipoPublicacion.id == 2}">
               <div class="card card-success card-outline   " style="width:70%; ">
            </c:if>
            <c:if test="${publicacion.tipoPublicacion.id == 1}">
               <div class="card card-danger card-outline   " style="width:70%;">
            </c:if>
            <c:if test="${publicacion.tipoPublicacion.id == 4}">
               <div class="card card-secondary card-outline" style="width:70%; ">
            </c:if>


               <!-- Visuales publicacion recomendada -->
               <c:if test="${publicacion.recomendada}">  <!--publicacion.esRecomendada-->
                  <div style="display: block; height: 20px; width: 20px;">
                     <img src="${pageContext.request.contextPath}/resources/images/estrella.png" alt="Estrella" style=" height: 50px; border: solid 1px #f8f8f8;border-radius: 20px;position: relative; top: -23px;left: -20px; box-shadow: 1px 5px 5px #b8b8b8; z-index:3">
                  </div>
                  <div style="font-size: smaller;position: relative;top: -20px;height: 20px;width: 100%;padding-bottom: 3px;z-index: 2;background-color: #ffcd00a6;text-align: center;"> Recomendada según tus preferencias  </div>
               </c:if>
               <!-- FIN  Visuales publicacion recomendada -->


               <div class="card-header d-flex flex-row justify-content-center ">
                  <c:if test="${publicacion.tipoPublicacion.id == 1}">
                     <h2 class="card-title"> Se perdió ${publicacion.mascota.nombre}</h2>
                  </c:if>
                  <c:if test="${publicacion.tipoPublicacion.id == 2}">
                     <h2 class="card-title">${publicacion.tipoPublicacion.descripcion}</h2>
                  </c:if>
                  <c:if test="${publicacion.tipoPublicacion.id == 3}">
                     <h2 class="card-title">${publicacion.tipoPublicacion.descripcion}: ${publicacion.mascota.nombre} </h2>
                  </c:if>
                  <c:if test="${publicacion.tipoPublicacion.id == 4}">
                     <h2 class="card-title">${publicacion.tipoPublicacion.descripcion} ${publicacion.mascota.tipoMascota.descripcion}</h2>
                  </c:if>
               </div>

               <!-- /.card-header -->
                        <a href="pubs/view/${publicacion.id}" style="text-decoration: none;color: inherit">
                           <div class="card-body" style="padding:0px !important;">
                              <div class="row" style="height: 100%">
                                 <div class="col-4">
                                    <c:if test="${not empty publicacion.mascota}">
                                       <c:if test="${publicacion.mascota.tipoMascota.id == 1}">
                                          <img src="${pageContext.request.contextPath}/resources/images/manchita.jpg" alt="fotoMascotaPerdida" class="img-min-mascota" style="height: 200px;">
                                       </c:if>
                                       <c:if test="${publicacion.mascota.tipoMascota.id == 2}">
                                          <img src="${pageContext.request.contextPath}/resources/images/michifuz.png" alt="fotoMascotaPerdida" class="img-min-mascota" style="height: 200px;">
                                       </c:if>
                                    </c:if>
                                    <c:if test="${empty publicacion.mascota}">
                                       <c:if test="${publicacion.tipoMascota.id == 1}">
                                          <img src="${pageContext.request.contextPath}/resources/images/manchita.jpg" alt="fotoMascotaPerdida" class="img-min-mascota" style="height: 200px;">
                                       </c:if>
                                       <c:if test="${publicacion.tipoMascota.id == 2}">
                                          <img src="${pageContext.request.contextPath}/resources/images/michifuz.png" alt="fotoMascotaPerdida" class="img-min-mascota" style="height: 200px;">
                                       </c:if>
                                    </c:if>
                                 </div>
                                 <div class="col-8">
                                    <div style="margin: 5px 10px">
                                       <fmt:parseDate value="${publicacion.fecha}" pattern="yyyy-MM-dd" var="fecha" type="date"/>

                                       <p style="text-align:right; width: 100%; color:#c4c4c4"> <fmt:formatDate pattern="dd/MMM/yyyy" value="${fecha}"/></p>
                                       <p>${publicacion.descripcion}</p>
                                       <div class="callout callout-info" style="margin-bottom: 0px">
                                          Organización: <b>${publicacion.organizacion.descripcion}</b>
                                       </div>
                                       <c:if test="${not empty publicacion.mascota}">
                                          <p style="text-align:center; width: 100%; color:#c4c4c4;"> - ${publicacion.mascota.tipoMascota.descripcion} - </p>
                                       </c:if>
                                       <c:if test="${publicacion.tipoPublicacion.id == 4}">
                                          <p style="text-align:center; width: 100%; color:#c4c4c4;"> - ${publicacion.tipoMascota.descripcion} - </p>
                                       </c:if>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </a>
                     </div>
                  </div>

                  </c:forEach>
               </div>
            </div>
         </div>
         <div>
         </div>

<jsp:include page="footer.jsp" />