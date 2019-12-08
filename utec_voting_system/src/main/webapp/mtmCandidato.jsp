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
														<h2>Mantenimiento de <b>Candidato</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addCandidatoModal" class="btn btn-success" data-toggle="modal"><span>Agregar Candidato</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Candidato</th>
														<th>Partido</th>
														<th>Departamento</th>
														<th>Municipio</th>
														<th>Tipo de candidato</th>
														<th>Estado</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.canList}" var="can">
								                    <tr>
								                        <td>${can.canId}</td>
								                        <td>[${can.canPerDui.perDui}] ${can.canPerDui.perPNombre} ${can.canPerDui.perPApellido}</td>
														<td>${can.canParId.parNombre}</td>
														<td>${can.canDepId.depNombre}</td>
														<td>${can.canMunId.munNombre}</td>
														<td>${can.canTcaId.tcaTipo}</td>
														<td>${can.canEstado eq 1? 'Activo':'Inactivo' }</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${can.canId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addCandidatoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveCandidato" name="saveCandidato" action="candidato.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Candidato</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Candidato</label>
											<select id="canPerDui" name="canPerDui" class="form-control">
									            <c:forEach var="per" items="${requestScope.perList}">
									                <option value="${per.perDui}">${per.perDui} (${per.perPNombre} ${per.perPApellido})</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Partido</label>
											<select id="canParId" name="canParId" class="form-control">
									            <c:forEach var="par" items="${requestScope.parList}">
									                <option value="${par.parId}">${par.parNombre}</option>
									            </c:forEach>
									        </select>
										</div>	
										<div class="form-group">
											<label>Departamento</label>
											<select id="canDepId" name="canDepId" class="form-control" onchange="selectMunicipio();">
									            <c:forEach var="depto" items="${requestScope.depList}">
									                <option value="${depto.depId}">${depto.depNombre}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Municipio</label>
											<select id="canMunId" name="canMunId" class="form-control" >
									            <c:forEach var="mun" items="${requestScope.munList}">
									                <option value="${mun.munId}">${mun.munNombre}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Tipo de candidato</label>
											<select id="canTcaId" name="canTcaId" class="form-control">
									            <c:forEach var="tca" items="${requestScope.tpcList}">
									                <option value="${tca.tcaId}">${tca.tcaTipo}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="canEstado" name="canEstado" class="form-control">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
										</div>				
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarCandidato" name="btnInsertarCandidato">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editCandidatoModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editCandidato" name="editCandidato" action="candidato.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Candidato</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Candidato</label>
											<select id="canPerDuiEdi1" name="canPerDuiEdi1" class="form-control">
									            <c:forEach var="per" items="${requestScope.perList}">
									                <option value="${per.perDui}">${per.perDui} (${per.perPNombre} ${per.perPApellido})</option>
									            </c:forEach>
									        </select>
									        <input type="hidden" id="canIdEdi" name="canIdEdi">
									        <input type="hidden" id="canPerDuiEdi" name="canPerDuiEdi">
										</div>
										<div class="form-group">
											<label>Partido</label>
											<select id="canParIdEdi" name="canParIdEdi" class="form-control">
									            <c:forEach var="par" items="${requestScope.parList}">
									                <option value="${par.parId}">${par.parNombre}</option>
									            </c:forEach>
									        </select>
										</div>	
										<div class="form-group">
											<label>Departamento</label>
											<select id="canDepIdEdi" name="canDepIdEdi" class="form-control">
									            <c:forEach var="depto" items="${requestScope.depList}">
									                <option value="${depto.depId}">${depto.depNombre}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Municipio</label>
											<select id="canMunIdEdi" name="canMunIdEdi" class="form-control">
									            <c:forEach var="mun" items="${requestScope.munList}">
									                <option value="${mun.munId}">${mun.munNombre}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Tipo de candidato</label>
											<select id="canTcaIdEdi" name="canTcaIdEdi" class="form-control">
									            <c:forEach var="tca" items="${requestScope.tpcList}">
									                <option value="${tca.tcaId}">${tca.tcaTipo}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Estado</label>
											<select id="canEstadoEdi" name="canEstadoEdi" class="form-control">
									                <option value="1">Activo</option>
									                <option value="0">Inactivo</option>
									        </select>
										</div>						
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Guardar" id="btnModificarCandidato" name="btnModificarCandidato">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(canId){
							$.ajax({
					       	    url: '/utec_voting_system/candidato.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:canId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("canIdEdi").value = response.canId;
					       	    	document.getElementById("canPerDuiEdi").value = response.canPerDui.perDui;
					       	    	document.getElementById("canPerDuiEdi1").value = response.canPerDui.perDui;
					       	    	document.getElementById("canParIdEdi").value = response.canParId.parId;
					       	    	document.getElementById("canDepIdEdi").value = response.canDepId.depId;
					       	    	document.getElementById("canTcaIdEdi").value = response.canTcaId.tcaId;
					       	    	document.getElementById("canEstadoEdi").value = response.canEstado;
					       	    	$('#editCandidatoModal').modal('show');
					       	    },
					       	    error: function () {
					       	        alert("error");
					       	    }
							});
						}
						
						function selectMunicipio(){
							
						}
 					</script>
				</c:if>
	</jsp:body>
</t:template>