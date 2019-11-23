<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
														<h2>Mantenimiento <b>Tipo de Candidato</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addTipoCandidatoModal" class="btn btn-success" data-toggle="modal"><span>Agregar Tipo de candidato</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Candidato</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.tpcList}" var="tpc">
								                    <tr>
								                        <td>${tpc.tcaId}</td>
								                        <td>${tpc.tcaTipo}</td>
														
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${tpc.tcaId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addTipoCandidatoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveTipoCandidato" name="saveTipoCandidato" action="tipoCandidato.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Tipo de Candidato</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Código</label>
											<input type="text" class="form-control" required id="tcaTipo" name="tcaTipo" maxlength="100">
										</div>
											
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarTipoCandidato" name="btnInsertarTipoCandidato">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editTipoCandidatoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editTipoCandidato" name="editTipoCandidato" action="tipoCandidato.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Tipo de Candidato</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="tcaIdEdi" name="tcaIdEdi" >
										</div>
										<div class="form-group">
											<label>tipo candidato</label>
											<input type="text" class="form-control" required maxlength="100" id="tcaTipoEdi" name="tcaTipoEdi" >
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
						function showModal(tcaId){
							$.ajax({
					       	    url: '/utec_voting_system/tipoCandidato.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:tcaId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("tcaIdEdi").value = response.tcaId;
					       	    	document.getElementById("tcaTipoEdi").value = response.tcaTipo;
					       	    	
					       	    	$('#editTipoCandidatoModal').modal('show');
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