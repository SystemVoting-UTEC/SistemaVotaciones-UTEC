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
														<h2>Mantenimiento de <b>Candidato Eleccion</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addCandidatoEleccionModal" class="btn btn-success" data-toggle="modal"><span>Agregar Candidato Eleccion</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>ID de Eleccion</th>
														<th>ID Candidato</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.canElecList}" var="gen">
								                    <tr>
								                        <td>${gen.celId}</td>
								                        <td>${gen.elcId.elcId}</td>
														<td>${gen.canId.canId}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${gen.celId},${gen.elcId.elcId},${gen.canId.canId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addCandidatoEleccionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveCandidatoEleccion" name="saveCandidatoEleccion" action="candidatoElecion.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Candidato Eleccion</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID Eleccion</label>
											<select id="elcId" name="elcId" class="form-control">
									            <c:forEach var="ce" items="${requestScope.elecList}">
									                <option value="${ce.elcId}">${ce.elcId}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>ID Candidato</label>
											<select id="canId" name="canId" class="form-control">
									            <c:forEach var="ce" items="${requestScope.canList}">
									                <option value="${ce.canId}">${ce.canId}</option>
									            </c:forEach>
									        </select>
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarCandidatoEleccion" name="btnInsertarCandidatoEleccion">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editCandidatoEleccionModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editCandidatoEleccion" name="editCandidatoEleccion" action="candidatoElecion.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Candidato Eleccion</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="celIdEdi" name="celIdEdi" >
										</div>
										<div class="form-group">
											<label>ID Eleccion</label>
											<input type="text" class="form-control" required maxlength="11" id="elcIdEdi" name="elcIdEdi" >
										</div>
										<div class="form-group">
											<label>ID Candidato</label>
											<input type="text" class="form-control" required maxlength="11" id="canIdEdi" name="canIdEdi">
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
						function showModal(celId,elcId,canId){
							$.ajax({
					       	    url: '/utec_voting_system/candidatoElecion.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:celId,id2:elcId,id3:canId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("celIdEdi").value = response.celId;
					       	    	document.getElementById("elcIdEdi").value = response.elcId;
					       	    	document.getElementById("canIdEdi").value = response.canId;
					       	    	$('#editCandidatoEleccionModal').modal('show');
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