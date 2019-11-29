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
										}
								</script>
								        <div class="table-wrapper">
								            <div class="table-title">
								                <div class="row">
								                    <div class="col-sm-6">
														<h2>Mantenimiento de <b>Tipo Usuario</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addTpcUsuarioModal" class="btn btn-success" data-toggle="modal"><span>Agregar Tipo de Usuario</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Tipo</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.tpcUsuarioList}" var="tpcU">
								                    <tr>
								                        <td>${tpcU.tusId}</td>
								                        <td>${tpcU.tusTipo}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${tpcU.tusId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addTpcUsuarioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveTpcUsuario" name="saveTpcUsuario" action="tipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Tipo</label>
											<input type="text" class="form-control" required id="tusTipo" name="tusTipo" maxlength="100">
										</div>
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarTpcUsuario" name="btnInsertarTpcUsuario">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editTpcUsuarioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editTpcUsuario" name="editTpcUsuario" action="tipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="tusIdEdi" name="tusIdEdi" >
										</div>
										<div class="form-group">
											<label>Tipo</label>
											<input type="text" class="form-control" required maxlength="100" id="tusTipoEdi" name="tusTipoEdi" >
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
						function showModal(tusId){
							$.ajax({
					       	    url: '/utec_voting_system/tipoUsuario.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:tusId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("tusIdEdi").value = response.tusId;
					       	    	document.getElementById("tusTipoEdi").value = response.tusTipo;
					       	    	$('#editTpcUsuarioModal').modal('show');
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