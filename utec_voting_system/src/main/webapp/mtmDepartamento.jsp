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
														<h2>Mantenimiento de <b>Departamento</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addDepartamentoModal" class="btn btn-success" data-toggle="modal"><span>Agregar Departamento</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Nombre de Departamento</th>
														
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.depList}" var="dep">
								                    <tr>
								                        <td>${dep.depId}</td>
								                        <td>${dep.depNombre}</td>
														
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${dep.depId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addDepartamentoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveDepartamento" name="saveDepartamento" action="departamento.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Departamento</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Nombre de Departamento</label>
											<input type="text" class="form-control" required id="depNombre" name="depNombre" maxlength="200">
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarDepartamento" name="btnInsertarDepartamento">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editDepartamentoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editDepartamento" name="editDepartamento" action="departamento.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Departamento</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="depIdEdi" name="depIdEdi" >
										</div>
										<div class="form-group">
											<label>Nombre del Departamento</label>
											<input type="text" class="form-control" required maxlength="200" id="depNombreEdi" name="depNombreEdi" >
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
						function showModal(depId){
							$.ajax({
					       	    url: '/utec_voting_system/departamento.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:depId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("depIdEdi").value = response.depId;
					       	    	document.getElementById("depNombreEdi").value = response.depNombre;
					       	    	
					       	    	$('#editDepartamentoModal').modal('show');
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