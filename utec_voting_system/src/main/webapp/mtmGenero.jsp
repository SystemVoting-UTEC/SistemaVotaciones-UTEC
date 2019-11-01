<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sesion = request.getSession(true);
	Object usuario = sesion.getAttribute("usuario") == null ? null : sesion.getAttribute("usuario");
	if (usuario != null) {
%>
<t:template>
	<jsp:body>
	<script  type="text/javascript">
    function selectGenero(id) {
    	$.ajax({
    	    url: "genero.do?genId="+id,
    	    success: function(response) {
    	        //Do Something
    	    },
    	    error: function(xhr) {
    	        //Do Something to handle error
    	    }
    	});
    }
</script>
	<jsp:useBean id="generoController" class="com.utec.voting.controller.GeneroController"/>
	<a href="#frmNewGeneroModal" data-toggle = "modal" data-target= "#frmNewGeneroModal" class="btn btn-primary btn-xs pull-left"><span class="glyphicon glyphicon-plus"></span> Nuevo</a>
	<table class="table table-striped custab">
		<thead>
			<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th class="text-center">Acción</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="gen" items="${genList}">
					<tr>
						<td>
							<c:out value="${gen.genId}"/>
						</td>
						<td>
							<c:out value="${gen.genGenero}"/>
						</td>
						<td class="text-center">
						<a href="#frmNewGeneroModal" onclick="selectGenero(${gen.genId});" data-toggle = "modal" data-target= "#frmNewGeneroModal" class='btn btn-info btn-xs'><span class="glyphicon glyphicon-edit"></span> Editar</a>
						</td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
	<form action="genero.do" method="POST" id="frmNewGenero">
		<div class="modal fade" id="frmNewGeneroModal">
		    <div class="modal-dialog">
		        <div class="modal-content"> 
		            <div class="modal-header">
		                <h5 class="modal-title">Crear Genero</h5> 
		            </div>
		            <div class="modal-body">
				      <input type="text" name="gen_genero" id="gen_genero" maxlength="1" size="25" autofocus autocomplete="off" class="form-control" placeholder="Genero">
		            </div>
		            <div class="modal-footer">
		            	<input type="submit" class="btn btn-primary" id="btnNewGenero" value="Guardar">
		                <button type="button" class="btn btn-danger" data-dismiss = "modal" >Cancelar</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
	
	<form action="genero.do" method="POST" id="frmEdGenero">
		<div class="modal fade" id="frmEdGeneroModal">
		    <div class="modal-dialog">
		        <div class="modal-content"> 
		            <div class="modal-header">
		                <h5 class="modal-title">Editar Genero</h5> 
		            </div>
		            <div class="modal-body">
				      <input type="text" name="gen_genero" id="gen_genero" maxlength="1" size="25" autofocus autocomplete="off" class="form-control" placeholder="Genero">
		            </div>
		            <div class="modal-footer">
		            	<input type="submit" class="btn btn-primary" id="btnNewGenero" value="Guardar">
		                <button type="button" class="btn btn-danger" data-dismiss = "modal" >Cancelar</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
	</jsp:body>
</t:template>
<%
	} else {
		out.print("<script>location.replace('index.jsp')</script>");
	}
%>