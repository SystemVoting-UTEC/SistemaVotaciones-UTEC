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
										});
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
								                        <th>Elecci&oacute;n</th>
														<th>Candidato</th>
														<th> </th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.canElecList}" var="gen">
								                    <tr>
								                        <td>${gen.celId}</td>
								                        <td>${gen.elcId.elcDescripcion}</td>
														<td>[${gen.canId.canPerDui.perDui}]  ${gen.canId.canPerDui.perPNombre} ${gen.canId.canPerDui.perPApellido}</td>
								                    	<td>  </td>
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
										<h4 class="modal-title">Agregar Candidato Elecci&oacute;n</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Elecci&oacute;n</label>
											<select id="elcId" name="elcId" class="form-control" required="required">
									            <c:forEach var="ce" items="${requestScope.elecList}">
									            	
									                <option value="${ce.elcId}">${ce.elcDescripcion}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Candidato</label>
											<select id="canId" name="canId" class="form-control" required="required">
									            <c:forEach var="ce" items="${requestScope.canList}">
									                <option value="${ce.canId}">[${ce.canPerDui.perDui}] ${ce.canPerDui.perPNombre} ${ce.canPerDui.perPApellido}</option>
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
										<h4 class="modal-title">Eliminar Candidato Elecci&oacute;n</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<h3><b>Se eliminara el registro</b></h3><br/>
										<center><b>¿Está seguro?</b></center>
										<input type="hidden" class="form-control" required maxlength="11" id="celIdEdi" name="celIdEdi" />
										<input type="hidden" class="form-control" required maxlength="11" id="elcIdEdi" name="elcIdEdi" />	
										<input type="hidden" class="form-control" required maxlength="11" id="canIdEdi" name="canIdEdi" />
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
						function showModal(celId){
							$.ajax({
					       	    url: '/utec_voting_system/candidatoElecion.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:celId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("celIdEdi").value = response.celId;
					       	    	document.getElementById("elcIdEdi").value = response.elcId.elcId;
					       	    	document.getElementById("canIdEdi").value = response.canId.canId;
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