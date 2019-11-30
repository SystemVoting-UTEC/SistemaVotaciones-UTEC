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
														<h2>Mantenimiento de <b>Opción Menú</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addOpcionModal" class="btn btn-success" data-toggle="modal"><span>Agregar Opción Menú</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
														<th>Nombre</th>
														<th>URL</th>
														<th>Icono</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.optMenuList}" var="opt">
								                    <tr>
								                        <td>${opt.optId}</td>
								                        <td>${opt.optNombre}</td>
														<td>${opt.optURL}</td>
														<td>${opt.optIcono}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${opt.optId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addOpcionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveOpcion" name="saveOpcion" action="optMenu.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Opción</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required id="optNombre" name="optNombre" maxlength="150">
										</div>
										<div class="form-group">
											<label>URL</label>
											<input type="text" class="form-control" required id="optURL" name="optURL" maxlength="400">
										</div>
										<div class="form-group">
											<label>Icono</label>
											<input type="text" class="form-control" required id="optIcono" name="optIcono" maxlength="100">
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarOptionMenu" name="btnInsertarOptionMenu">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editOpcionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editOpcion" name="editOpcion" action="optMenu.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Opción</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="optIdEdi" name="optIdEdi" >
										</div>
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required id="optNombreEdi" name="optNombreEdi" maxlength="150">
										</div>
										<div class="form-group">
											<label>URL</label>
											<input type="text" class="form-control" required id="optURLEdi" name="optURLEdi" maxlength="400">
										</div>
										<div class="form-group">
											<label>Icono</label>
											<input type="text" class="form-control" required id="optIconoEdi" name="optIconoEdi" maxlength="100">
										</div>			
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Guardar" id="btnModificarOptionMenu" name="btnModificarOptionMenu">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(genId){
							$.ajax({
					       	    url: '/utec_voting_system/optMenu.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:genId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("optIdEdi").value = response.optId;
					       	    	document.getElementById("optNombreEdi").value = response.optNombre;
					       	    	document.getElementById("optURLEdi").value = response.optURL;
					       	    	document.getElementById("optIconoEdi").value = response.optIcono;
					       	    	$('#editOpcionModal').modal('show');
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