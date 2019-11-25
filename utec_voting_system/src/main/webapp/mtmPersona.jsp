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
											<h2>Mantenimiento de <b>Persona</b>
													</h2>
										</div>
										<div class="col-sm-6">
											<a href="#addPersonaModal" class="btn btn-success" data-toggle="modal"><span>Agregar Persona</span></a>				
										</div>
					                </div>
					            </div>
					            <table class="table table-striped table-hover">
					                <thead>
					                    <tr>
					                        <th>DUI</th>
					                        <th>Nombres</th>
											<th>Apellidos</th>
											<th>Nacimiento</th>
											<th>Edad</th>
											<th>Genero</th>
											<th>Departamento</th>
											<th>Estado Familiar</th>
											<th>Madre</th>
											<th>Padre</th>
											<th>Estado</th>
											<th>Opciones</th>
					                    </tr>
					                </thead>
					                <tbody>
					                <c:forEach items="${requestScope.perList}" var="p">
					                    <tr>
					                        <th>${p.perDui}</th>
					                        <th>${p.perPNombre} ${p.perSNombre} ${p.perTNombre}</th>
											<th>${p.perPApellido} ${p.perSApellido}</th>
											<th>${p.perFechaNac}</th>
											<th>${p.perEdad}</th>
											<th>${p.perGenId.genNombre}</th>
											<th>${p.perDepId.depNombre}</th>
											<th>${p.perEstId.estEstado}</th>
											<th>${p.perMadre}</th>
											<th>${p.perPadre}</th>
											<th>${p.perEstado eq 1 ? 'Activo' : 'Inactivo'}</th>
					                        <td>
					                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal('${p.perDui}');">
					                        </td>
					                    </tr>
					                </c:forEach>
					                </tbody>
					            </table>
	    				</div>
						<!-- AGREGAR Modal HTML -->
						<div id="addPersonaModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<form id="savePersona" name="savePersona" action="persona.do" method="post">
										<div class="modal-header">						
											<h4 class="modal-title">Agregar Persona</h4>
											<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">					
											<div class="form-group">
												<label>DUI</label>
												<input type="text" name="perDui" id="perDui" maxlength="10"
													size="25" autofocus autocomplete="off" class="form-control"
													placeholder="00000000-0" pattern="[0-9]{8}-[0-9]{1}" required>
											</div>
											<div class="form-group">
												<label>Primer Nombre</label>
												<input type="text" class="form-control" required id="perPNombre" name="perPNombre" maxlength="100">
											</div>
											<div class="form-group">
												<label>Segundo Nombre</label>
												<input type="text" class="form-control" required id="perSNombre" name="perSNombre" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Tercer Nombre</label>
												<input type="text" class="form-control" required id="perTNombre" name="perTNombre" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Primer Apellido</label>
												<input type="text" class="form-control" required id="perPApellido" name="perPApellido" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Segundo Apellido</label>
												<input type="text" class="form-control" required id="perSApellido" name="perSApellido" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Fecha de Nacimiento</label>
												<input type="text" class="form-control datepicker" required  id="perFechaNac" name="perFechaNac" >
											</div>
											<div class="form-group">
												<label>Genero</label>
												<select id="perGenId" name="perGenId" class="form-control" required>
										            <c:forEach var="gen" items="${requestScope.genList}">
										                <option value="${gen.genId}">${gen.genNombre}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Departamento</label>
												<select id="perDepId" name="perDepId" class="form-control" required>
										            <c:forEach var="dep" items="${requestScope.depList}">
										                <option value="${dep.depId}">${dep.depNombre}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Estado Familiar</label>
												<select id="perEstId" name="perEstId" class="form-control" required>
										            <c:forEach var="std" items="${requestScope.stdList}">
										                <option value="${std.estId}">${std.estEstado}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Madre</label>
												<input type="text" class="form-control" id="perMadre" name="perMadre" maxlength="100">
											</div>
											<div class="form-group">
												<label>Padre</label>
												<input type="text" class="form-control" id="perPadre" name="perPadre" maxlength="100">
											</div>
											<div class="form-group">
												<label>Estado</label>
												<select id="perEstado" name="perEstado" class="form-control" required>
										                <option value="1">Activo</option>
										                <option value="0">Inactivo</option>
										        </select>
											</div>	
										</div>
										<div class="modal-footer">
											<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
											<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarPersona" name="btnInsertarPersona">
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- FIN AGREGAR -->
						<!-- MODIFICAR Modal HTML -->
						<div id="editPersonaModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<form id="editarPersona" name="editarPersona" action="persona.do" method="post">
										<div class="modal-header">						
											<h4 class="modal-title">Modificar Persona</h4>
											<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
										</div>
										<div class="modal-body">					
											<div class="form-group">
												<label>DUI</label>
												<input type="text" name="perDuiEdi" id="perDuiEdi" maxlength="10"
													size="25" autofocus autocomplete="off" class="form-control"
													placeholder="00000000-0" pattern="[0-9]{8}-[0-9]{1}" readonly="readonly">
											</div>
											<div class="form-group">
												<label>Primer Nombre</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" required id="perPNombreEdi" name="perPNombreEdi" maxlength="100">
											</div>
											<div class="form-group">
												<label>Segundo Nombre</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" required id="perSNombreEdi" name="perSNombreEdi" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Tercer Nombre</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" id="perTNombreEdi" name="perTNombreEdi" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Primer Apellido</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" required id="perPApellidoEdi" name="perPApellidoEdi" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Segundo Apellido</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" required id="perSApellidoEdi" name="perSApellidoEdi" maxlength="100">
											</div>	
											<div class="form-group">
												<label>Fecha de Nacimiento</label>
												<input type="text" class="form-control datepicker" required  id="perFechaNacEdi" name="perFechaNacEdi" >
											</div>
											<div class="form-group">
												<label>Genero</label>
												<select id="perGenIdEdi" name="perGenIdEdi" class="form-control" required>
										            <c:forEach var="gen" items="${requestScope.genList}">
										                <option value="${gen.genId}">${gen.genNombre}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Departamento</label>
												<select id="perDepIdEdi" name="perDepIdEdi" class="form-control" required>
										            <c:forEach var="dep" items="${requestScope.depList}">
										                <option value="${dep.depId}">${dep.depNombre}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Estado Familiar</label>
												<select id="perEstIdEdi" name="perEstIdEdi" class="form-control" required>
										            <c:forEach var="std" items="${requestScope.stdList}">
										                <option value="${std.estId}">${std.estEstado}</option>
										            </c:forEach>
										        </select>
											</div>
											<div class="form-group">
												<label>Madre</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" id="perMadreEdi" name="perMadreEdi" maxlength="100">
											</div>
											<div class="form-group">
												<label>Padre</label>
												<input type="text" class="form-control" pattern="[A-Za-z]" id="perPadreEdi" name="perPadreEdi" maxlength="100">
											</div>
											<div class="form-group">
												<label>Estado</label>
												<select id="perEstadoEdi" name="perEstadoEdi" class="form-control" required>
										                <option value="1">Activo</option>
										                <option value="0">Inactivo</option>
										        </select>
											</div>	
										</div>
										<div class="modal-footer">
											<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
											<input type="submit" class="btn btn-success" value="Guardar" id="btnEditarPersona" name="btnEditarPersona">
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- FIN MODIFICAR -->
	    				<script type="text/javascript">
						function showModal(dui){
							$.ajax({
					       	    url: '/utec_voting_system/persona.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:dui}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("perDuiEdi").value = response.perDui;
					       	    	document.getElementById("perPNombreEdi").value = response.perPNombre;
					       	    	document.getElementById("perSNombreEdi").value = response.perSNombre;
					       	    	document.getElementById("perTNombreEdi").value = response.perTNombre;
					       	    	document.getElementById("perPApellidoEdi").value = response.perPApellido;
					       	    	document.getElementById("perSApellidoEdi").value = response.perSApellido;
					       	    	document.getElementById("perFechaNacEdi").value = response.perFechaNac;
					       	    	document.getElementById("perGenIdEdi").value = response.perGenId.genId;
					       	    	document.getElementById("perDepIdEdi").value = response.perDepId.depId;
					       	    	document.getElementById("perEstIdEdi").value = response.perEstId.estId;
					       	    	document.getElementById("perMadreEdi").value = response.perMadre;
					       	    	document.getElementById("perPadreEdi").value = response.perPadre;
					       	    	document.getElementById("perEstadoEdi").value = response.perEstado;
					       	    	$('#editPersonaModal').modal('show');
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