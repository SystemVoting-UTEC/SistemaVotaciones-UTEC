<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:template>
	<jsp:body>
				<c:if test="${sessionScope.usuario eq null}">
					<c:redirect url="index.jsp"></c:redirect>
				</c:if>
				<c:if test="${sessionScope.usuario ne null}">
								<script type="text/javascript">
										$(document).ready(function(){
										$('[data-toggle="tooltip"]').tooltip();
								</script>
								        <div class="table-wrapper">
								            <div class="table-title">
								                <div class="row">
								                    <div class="col-sm-6">
														<h2>Mantenimiento de <b>Opcion Tipo de Usuario</b>
														</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addOTUModal" class="btn btn-success" data-toggle="modal"><span>Agregar Opcion Tipo de Usuario</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID Menu</th>
								                        <th>ID Usuario</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.otuList}" var="otu">
								                    <tr>
								                        <td>${otu.optId}</td>
								                        <td>${otu.tusId}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${otu.optId,otu.tusId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addOTUModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveOTU" name="saveOTU" action="OptionTipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Opcion Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID Menu</label>
											<input type="text" class="form-control" required id="optId" name="optId" maxlength="11">
										</div>
										<div class="form-group">
											<label>ID Usuario</label>
											<input type="text" class="form-control" required id="tusId" name="tusId" maxlength="11">
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarOTU" name="btnInsertarOTU">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editOTUModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id=editOTU name=editOTU action="OptionTipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Opcion Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID Menu</label>
											<input type="text" class="form-control" required maxlength="11" id="optIdEdi" name="optIdEdi" >
										</div>
										<div class="form-group">
											<label>ID Usuario</label>
											<input type="text" class="form-control" required maxlength="11" id="tusIdEdi" name="tusIdEdi" >
										</div>
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Guardar" id="btnModificar" name="btnModificar">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(optId,tusId){
							$.ajax({
					       	    url: '/utec_voting_system/OptionTipoUsuario.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:optId,id:tusId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("optIdEdi").value = response.optId;
					       	    	document.getElementById("tusIdEdi").value = response.tusId;
					       	    	$('#editOTUModal').modal('show');
					       	    },
					       	    error: function () {
					       	        alert("error");
					       	    }
							});
						}
 					</script>
				</c:if>
	</jsp:body>
</t:template>