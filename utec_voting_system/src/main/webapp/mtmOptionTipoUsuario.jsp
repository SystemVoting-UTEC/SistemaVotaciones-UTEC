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
								        <div class="table-wrapper">
								            <div class="table-title">
								                <div class="row">
								                    <div class="col-sm-6">
														<h2>Mantenimiento de <b>Opci&oacute;n Tipo de Usuario</b>
														</h2>
													</div>
													<div class="col-sm-6">
														<a href="#addOTUModal" class="btn btn-success" data-toggle="modal"><span>Agregar Opcion Tipo de Usuario</span></a>				
													</div>
								                </div>
								            </div>
								            <table class="table table-striped table-hover">
								                <thead>
								                    <tr>
								                        <th>Menu</th>
								                        <th>Tipo Usuario</th>
														<th>Opciones</th>
								                    </tr>
								                </thead>
								                <tbody>
								                <c:forEach items="${requestScope.OpTpUsuarioList}" var="otu">
								                    <tr>
								                        <td>${otu.optId.optNombre}</td>
								                        <td>${otu.tusId.tusTipo}</td>
								                        <td>
								                            <input type="submit" class="btn btn-danger" value="Eliminar" onclick="showModal(${otu.optId.optId},${otu.tusId.tusId});">
								                        </td>
								                    </tr>
								                </c:forEach>
								                </tbody>
								            </table>
				    				</div>
					<!-- AGREGAR Modal HTML -->
					<div id="addOTUModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id="saveOTU" name="saveOTU" action="OptionTipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Agregar Opci&oacute;n Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">					
										<div class="form-group">
											<label>Menu</label>
											<select id="optId" name="optId" class="form-control">
									            <c:forEach var="opt" items="${requestScope.optMenuList}">
									                <option value="${opt.optId}">${opt.optNombre}</option>
									            </c:forEach>
									        </select>
										</div>
										<div class="form-group">
											<label>Tipo de Usuario</label>
											<select id="tusId" name="tusId" class="form-control">
									            <c:forEach var="tpu" items="${requestScope.tpcUsuarioList}">
									                <option value="${tpu.tusId}">${tpu.tusTipo}</option>
									            </c:forEach>
									        </select>
										</div>	
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
										<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarOTU" name="btnInsertarOTU">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN AGREGAR -->
					<!-- Edit Modal HTML -->
					<div id="editOTUModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<form id=editOTU name=editOTU action="OptionTipoUsuario.do" method="post">
									<div class="modal-header">						
										<h4 class="modal-title">Eliminar Opci&oacute;n Tipo de Usuario</h4>
										<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">
										<h3><b>Se eliminara el registro</b></h3><br/>
										<center><b>¿Está seguro?</b></center>
										<input type="hidden" class="form-control" required maxlength="11" id="optIdEdi" name="optIdEdi" >
										<input type="hidden" class="form-control" required maxlength="11" id="tusIdEdi" name="tusIdEdi" >
									</div>
									<div class="modal-footer">
										<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
										<input type="submit" class="btn btn-info" value="Eliminar" id="btnEliminarTipoMenu" name="btnEliminarTipoMenu">
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- FIN MODIFICAR -->
					<script type="text/javascript">
						function showModal(optId,tusId){
							$.ajax({
					       	    url: '/utec_voting_system/OptionTipoUsuario.do',
					       	    type: 'POST',
					       	    data: jQuery.param({id:optId,idd:tusId}) ,
					       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
					       	    success: function (response) {
					       	    	document.getElementById("optIdEdi").value = response.optId.optId;
					       	    	document.getElementById("tusIdEdi").value = response.tusId.tusId;
					       	    	$('#editOTUModal').modal('show');
					       	    },
					       	    error: function () {
					       	        alert("Error");
					       	    }
							});
						}
 					</script>
				</c:if>
	</jsp:body>
</t:template>