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
														<h2>Mantenimiento de <b>Genero</b>
																</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addGeneroModal" class="btn btn-success" data-toggle="modal"><span>Agregar Genero</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>ID</th>
								                        <th>Genero</th>
														<th>Nombre</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.genList}" var="gen">
								                    <tr>
								                        <td>${gen.genId}</td>
								                        <td>${gen.genGenero}</td>
														<td>${gen.genNombre}</td>
								                        <td>
								                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal(${gen.genId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addGeneroModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveGenero" name="saveGenero" action="genero.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Genero</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Código</label>
											<input type="text" class="form-control" required id="genGenero" name="genGenero" maxlength="1">
										</div>
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required id="genNombre" name="genNombre" maxlength="100">
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarGenero" name="btnInsertarGenero">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editGeneroModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="editGenero" name="editGenero" action="genero.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Editar Genero</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>ID</label>
											<input type="text" class="form-control" required readonly="readonly" id="genIdEdi" name="genIdEdi" >
										</div>
										<div class="form-group">
											<label>Genero</label>
											<input type="text" class="form-control" required maxlength="1" id="genGeneroEdi" name="genGeneroEdi" >
										</div>
										<div class="form-group">
											<label>Nombre</label>
											<input type="text" class="form-control" required maxlength="100" id="genNombreEdi" name="genNombreEdi">
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
						function showModal(genId){
							$.ajax({
					       	    url: '/utec_voting_system/genero.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:genId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("genIdEdi").value = response.genId;
					       	    	document.getElementById("genGeneroEdi").value = response.genGenero;
					       	    	document.getElementById("genNombreEdi").value = response.genNombre;
					       	    	$('#editGeneroModal').modal('show');
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