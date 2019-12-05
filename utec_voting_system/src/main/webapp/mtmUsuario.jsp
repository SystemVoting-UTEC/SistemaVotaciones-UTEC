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
								        <div class="table-wrapper my-custom-scrollbar">
								            <div class="table-title">
								                <div class="row">
								                    <div class="col-sm-6">
														<h2>Mantenimiento de <b>Usuario</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addUsuarioModal" class="btn btn-success" data-toggle="modal"><span>Agregar Usuario</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th scope="col">Usuario</th>
								                        <th scope="col">Tipo de Usuario</th>
								                        <th scope="col">Correo</th>
														<th scope="col">Estado</th>
														<th scope="col">Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.usList}" var="usua">
								                    <tr>
								                        <td scope="row">[${usua.usPerDui.perDui}] ${usua.usPerDui.perPNombre} ${usua.usPerDui.perPApellido}</td>
								                        <td>${usua.usTusId.tusTipo}</td>
								                        <td>${usua.usEmail}</td>
														<td>${usua.usEstado eq 1 ? 'Activo' : 'Inactivo'}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal('${usua.usPerDui.perDui}');" />
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addUsuarioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveUsuario" name="saveUsuario" action="usuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
                                            <label>Persona</label>
                                            <select id="usPerDui" name="usPerDui" class="form-control" required>
                                                <c:forEach var="vot" items="${requestScope.perList}">
                                                	<c:if test="${vot.perEstado == 1}">
	                                                    <option value="${vot.perDui}">${vot.perDui} (${vot.perPNombre} ${vot.perPApellido})</option>
                                                	</c:if>
                                                </c:forEach>
                                            </select>
                                        </div>	
										<div class="form-group">
                                            <label>Tipo de usuario</label>
                                            <select id="usTusId" name="usTusId" class="form-control" required>
                                                <c:forEach var="tpu" items="${requestScope.tpusList}">
                                                    <option value="${tpu.tusId}">${tpu.tusTipo}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Correo</label>
                                            <input type="email" class="form-control" id="usEmail" name="usEmail" required/>
                                        </div>	
                                        <div class="form-group">
                                            <label>Estado</label>
                                            <select id="usEstado" name="usEstado" class="form-control" required>
                                                    <option value="1">Activo</option>
                                                    <option value="0">Inactivo</option>
                                            </select>
                                        </div>		
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarUsuario" name="btnInsertarUsuario">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editUsuarioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editUsuario" name="editUsuario" action="usuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
                                        <div class="form-group">
                                            <label>Persona</label>
                                            <input type="text" class="form-control"   id="usPerDuiEdi" name="usPerDuiEdi" readonly />
                                            <input type="hidden" id="usPasswordEdi" name="usPasswordEdi"/> 
                                        </div>	
										<div class="form-group">
                                            <label>Tipo de usuario</label>
                                            <select id="usTusIdEdi" name="usTusIdEdi" class="form-control" required>
                                                <c:forEach var="tpu" items="${requestScope.tpusList}">
                                                    <option value="${tpu.tusId}">${tpu.tusTipo}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Correo</label>
                                            <input type="email" class="form-control" id="usEmailEdi" name="usEmailEdi"/>
                                        </div>	
                                        <div class="form-group">
                                            <label>Estado</label>
                                            <select id="usEstadoEdi" name="usEstadoEdi" class="form-control" required>
                                                    <option value="1">Activo</option>
                                                    <option value="0">Inactivo</option>
                                            </select>
                                        </div>			
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Guardar" id="btnModificarUsuario" name="btnModificarUsuario">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(dui){
							$.ajax({
					       	    url: '/utec_voting_system/usuario.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:dui}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("usPerDuiEdi").value = response.usPerDui.perDui;
					       	    	document.getElementById("usTusIdEdi").value = response.usTusId.tusId;
					       	    	document.getElementById("usEstadoEdi").value = response.usEstado;
					       	    	document.getElementById("usPasswordEdi").value = response.usPassword;
					       	    	document.getElementById("usEmailEdi").value = response.usEmail;
					       	    	$('#editUsuarioModal').modal('show');
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