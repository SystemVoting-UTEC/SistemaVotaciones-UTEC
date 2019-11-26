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
														<h2>Mantenimiento de <b>Municipio</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addMunicipioModal" class="btn btn-success" data-toggle="modal"><span>Agregar Municipio</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Nombre</th>
														<th>Nombre</th>
														<th>Codigo Municipio</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.munList}" var="mun">
								                    <tr>
								                        <td>${mun.munId}</td>
								                        <td>${mun.munNombre}</td>
														<td>${mun.munDepId.depNombre}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${mun.munId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addMunicipioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveMunicipio" name="saveMunicipio" action="municipio.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Municipio</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required id="munNombre" name="munNombre" maxlength="200">
										</div>
										<div class="form-group">
											<label>Departamento</label>
											<select id="munDepId" name="munDepId" class="form-control">
												<c:forEach var="dep" items="${requestScope.depList}">
									                <option value="${dep.depId}">${dep.depNombre}</option>
									            </c:forEach>
					        				</select>
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarMunicipio" name="btnInsertarMunicipio">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editMunicipioModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editMunicipio" name="editMunicipio" action="municipio.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Municipio</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="munIdEdi" name="munIdEdi" >
										</div>
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required maxlength="200" id="munNombreEdi" name="munNombreEdi" >
										</div>
										<div class="form-group">
											<label>Departamento</label>
											<select id="munDepIdEdi" name="munDepIdEdi" class="form-control">
												<c:forEach var="dep" items="${requestScope.depList}">
									                <option value="${dep.depId}">${dep.depNombre}</option>
									            </c:forEach>
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
						function showModal(munId){
							$.ajax({
					       	    url: '/utec_voting_system/municipio.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:munId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("munIdEdi").value = response.munId;
					       	    	document.getElementById("munNombreEdi").value = response.munNombre;
					       	    	document.getElementById("munDepIdEdi").value = response.munDepId.depId;
					       	    	$('#editMunicipioModal').modal('show');
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