<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="header.jsp" />



<div class="content-wrapper">


   <div class="content">
      <div class="container">
         <div class="row mt-3 ">
            <div class="card card-default col-md-12">
               <div class="card-header">
                  <h3 class="card-title">
                     <i class="fas fa-bullhorn"></i>
                     Aviso
                  </h3>
               </div>
               <!-- /.card-header -->
               <div class="card-body">
                  <div class="callout callout-success">
                     <h5>¡Su solicitud se proceso exitosamente!</h5>
                     <p>${command.successActionMessage}</p>
                     <p><a href="${pageContext.request.contextPath}" > Haga click aqui para volver a las publicaciones</p>
                  </div>
               </div>
               <!-- /.card-body -->
            </div>
         </div>
      </div>
   </div>
</div>



<jsp:include page="footer.jsp" />