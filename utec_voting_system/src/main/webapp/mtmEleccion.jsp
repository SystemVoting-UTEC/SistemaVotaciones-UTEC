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
											 $(".datepicker").datepicker({
											        dateFormat: "yy-mm-dd"
											    });
										}
								</script>
								        <div class="table-wrapper">
								            <div class="table-title">
								                <div class="row">
								                    <div class="col-sm-6">
														<h2>Mantenimiento de <b>Elecci&oacute;n</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addEleccionModal" class="btn btn-success" data-toggle="modal"><span>Agregar Elecci&oacute;n</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Descripci&oacute;n</th>
														<th>Fecha Inicio</th>
														<th>Fecha Fin</th>
														<th>Estado</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.elecList}" var="elc">
								                    <tr>
								                        <td>${elc.elcId}</td>
								                        <td>${elc.elcDescripcion}</td>
														<td>${elc.elcFechaInicio}</td>
														<td>${elc.elcFechaFin}</td>
														<td>${elc.elcEstado eq 1 ? 'Activo':'Inactivo'}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${elc.elcId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addEleccionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveEleccion" name="saveEleccion" action="eleccion.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Elecci&oacute;n</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Descripci&oacute;n</label>
											<input type="text" class="form-control" required id="elcDescripcion" name="elcDescripcion" maxlength="500">
										</div>
										<div class="form-group">
											<label>Fecha Inicio</label>
											<input type="text" class="form-control datepicker" required  id="elcFechaInicio" name="elcFechaInicio" >
										</div>
										<div class="form-group">
											<label>Fecha Fin</label>
											<input type="text" class="form-control datepicker" required  id="elcFechaFin" name="elcFechaFin" >
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="elcEstado" name="elcEstado" class="form-control">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
										</div>		
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarEleccion" name="btnInsertarEleccion">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editEleccionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editEleccion" name="editEleccion" action="eleccion.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Elecci&oacute;n</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="elcIdEdi" name="elcIdEdi" >
										</div>
										<div class="form-group">
											<label>Descripci&oacute;n</label>
											<input type="text" class="form-control" required id="elcDescripcionEdi" name="elcDescripcionEdi" maxlength="500">
										</div>
										<div class="form-group">
											<label>Fecha Inicio</label>
											<input type="text" class="form-control datepicker" required  id="elcFechaInicioEdi" name="elcFechaInicioEdi" >
										</div>
										<div class="form-group">
											<label>Fecha Fin</label>
											<input type="text" class="form-control datepicker" required  id="elcFechaFinEdi" name="elcFechaFinEdi" >
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="elcEstadoEdi" name="elcEstadoEdi" class="form-control">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Guardar" id="btnModificarEleccion" name="btnModificarEleccion">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(elcId){
							$.ajax({
					       	    url: '/utec_voting_system/eleccion.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:elcId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("elcIdEdi").value = response.elcId;
					       	    	document.getElementById("elcDescripcionEdi").value = response.elcDescripcion;
					       	    	document.getElementById("elcFechaInicioEdi").value = response.elcFechaInicio;
					       	    	document.getElementById("elcFechaFinEdi").value = response.elcFechaFin;
					       	    	document.getElementById("elcEstadoEdi").value = response.elcEstado;
					       	    	$('#editEleccionModal').modal('show');
					       	    },
					       	    error: function () {
					       	        alert("error");
					       	    }
							});
						}
						
						$(function(){
						    $(".datepicker").datepicker({
						        dateFormat: "yy-mm-dd"
						    });
						});
 					</script>
				</c:if>
	</jsp:body>
</t:template>