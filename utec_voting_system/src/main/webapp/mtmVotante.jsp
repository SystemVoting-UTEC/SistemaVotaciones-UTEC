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
		 $(".datepicker").datepicker({
		        dateFormat: "yy-mm-dd"
		    });
	});
	// Activate tooltip
</script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
				        <div class="table-wrapper">
				            <div class="table-title">
				                <div class="row">
				                    <div class="col-sm-6">
										<h2>Mantenimiento de <b>Votante</b>
												</h2>
									</div>
									<div class="col-sm-6">
										<a href="#addVotanteModal" class="btn btn-success" data-toggle="modal"><span>Agregar Votante</span></a>				
									</div>
				                </div>
				            </div>
				            <table class="table table-striped table-hover">
				                <thead>
				                    <tr>
				                        <th>Votante</th>
				                        <th>Fecha Expira</th>
										<th>Fecha Vence</th>
										<th>Estado</th>
										<th>Opciones</th>
				                    </tr>
				                </thead>
				                <tbody>
				                <c:forEach items="${requestScope.votList}" var="vota">
				                    <tr>
				                        <td>${vota.votPerDui.perDui} [${vota.votPerDui.perPNombre} ${vota.votPerDui.perPApellido}]</td>
				                        <td>${vota.votFechaVence}</td>
										<td>${vota.votFechaExp}</td>
										<td>${vota.estado eq 1? 'Activo':'Inactivo' }</td>
				                        <td>
				                            <input type="submit" class="btn btn-info" value="Editar" onclick="showModal('${vota.votPerDui.perDui}');">
				                        </td>
				                    </tr>
				                </c:forEach>
				                </tbody>
				            </table>
    		</div>
	<!-- Edit Modal HTML -->
	<div id="addVotanteModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="saveVotante" name="saveVotante" action="votante.do" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Agregar Votante</h4>
						<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Votante</label>
							<select id="votPerDui" name="votPerDui" class="form-control">
					            <c:forEach var="vot" items="${requestScope.perList}">
					                <option value="${vot.perDui}">${vot.perDui} (${vot.perPNombre} ${vot.perPApellido})</option>
					            </c:forEach>
					        </select>
						</div>					
						<div class="form-group">
							<label>Fecha Vence</label>
							<input type="text" class="form-control datepicker" required  id="votFechaVence" name="votFechaVence" >
						</div>
						<div class="form-group">
							<label>Fecha Expiración</label>
							<input type="text" class="form-control datepicker" required maxlength="100" id="votFechaExp" name="votFechaExp">
						</div>	
						<div class="form-group">
							<label>Estado</label>
							<select id="estado" name="estado" class="form-control">
					                <option value="1">Activo</option>
					                <option value="0">Inactivo</option>
					        </select>
						</div>				
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-success" value="Guardar" id="btnInsertarVotante" name="btnInsertarVotante">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Edit Modal HTML -->
	<div id="editVotanteModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="editVotante" name="editVotante" action="votante.do" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Editar Votante</h4>
						<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Votante</label>
							<input type="text" class="form-control" required  id="votPerDuiEdiShow" name="votPerDuiEdiShow" readonly="readonly">
							<input type="hidden" id="votPerDuiEdi" name="votPerDuiEdi">
						</div>
						<div class="form-group">
							<label>Fecha Vence</label>
							<input type="text" class="form-control datepicker" required  id="votFechaVenceEdi" name="votFechaVenceEdi">
						</div>
						<div class="form-group">
							<label>Fecha Expiración</label>
							<input type="text" class="form-control datepicker" required  id="votFechaExpEdi" name="votFechaExpEdi" >
						</div>
						<div class="form-group">
							<label>Estado</label>
							<select id="estadoEdi" name="estadoEdi" class="form-control">
					                <option value="1">Activo</option>
					                <option value="0">Inactivo</option>
					        </select>
						</div>				
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Guardar" id="btnModificarVotante" name="btnModificarVotante">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function showModal(votId){
			$.ajax({
	       	    url: '/utec_voting_system/votante.do',
	       	    type: 'POST',
	       	    data: jQuery.param({id:votId}) ,
	       	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	       	    success: function (response) {
	       	    	document.getElementById("votPerDuiEdiShow").value = response.votPerDui.perDui+" ["+response.votPerDui.perPNombre+" "+response.votPerDui.perPApellido+"]";
	       	    	document.getElementById("votPerDuiEdi").value = response.votPerDui.perDui;
	       	    	document.getElementById("votFechaVenceEdi").value = response.votFechaVence;
	       	    	document.getElementById("votFechaExpEdi").value = response.votFechaExp;
	       	    	document.getElementById("estadoEdi").value = response.estado;
	       	    	$('#editVotanteModal').modal('show');
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