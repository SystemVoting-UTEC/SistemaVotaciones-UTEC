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
														<h2>Mantenimiento de <b>Partido</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addPartidoModal" class="btn btn-success" data-toggle="modal"><span>Agregar Partido</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Partido</th>
														<th>Estado</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.parList}" var="par">
								                    <tr>
								                        <td>${par.parId}</td>
								                        <td>${par.parNombre}</td>
														<td>${par.estado eq 1 ? 'Activo':'Inactivo'}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${par.parId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addPartidoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="savePartido" name="savePartido" action="partido.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Partido</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Nombre Partido</label>
											<input type="text" class="form-control" required id="parNombre" name="parNombre" maxlength="200">
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="estado" name="estado" class="form-control" required="required">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarPartido" name="btnInsertarPartido">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editPartidoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editPartido" name="editPartido" action="partido.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Partido</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="parIdEdi" name="parIdEdi" >
										</div>
										<div class="form-group">
											<label>Nombre Partido</label>
											<input type="text" class="form-control" required maxlength="200" id="parNombreEdi" name="parNombreEdi" >
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="estadoEdi" name="estadoEdi" class="form-control" required="required">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
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
						function showModal(parId){
							$.ajax({
					       	    url: '/utec_voting_system/partido.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:parId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("parIdEdi").value = response.parId;
					       	    	document.getElementById("parNombreEdi").value = response.parNombre;
					       	    	document.getElementById("estadoEdi").value = response.estado;
					       	    	$('#editPartidoModal').modal('show');
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