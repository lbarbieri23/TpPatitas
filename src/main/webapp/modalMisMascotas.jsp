<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
    function showModalMisMascotas() {
        $('#modal-mis-mascotas').modal('show');
    }
</script>


<div class="modal fade" id="modal-mis-mascotas">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Seleccione su mascota</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="form-group col-md-12">
                        <label>Seleccione la mascota a dar en adopcion</label>
                        <form:form  method="post" >
                            <form:select path="mascota.id" cssClass="form-control select2bs4 ">
                                <form:options items="${command.misMascotas}" itemLabel="apodo" itemValue="id" />
                            </form:select>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="modal-footer justify-content-between">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <a href="javascript:executeModalAction();" class="btn btn-primary">Seleccionar mascota</a>
            </div>
        </div>
    </div>
</div>

