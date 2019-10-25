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
	<a href="#" class="btn btn-primary btn-xs pull-right"><b>+</b>Nuevo</a>
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
							<a class='btn btn-info btn-xs' href="#">
							<span class="glyphicon glyphicon-edit"></span> Editar</a>
						</td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
	</jsp:body>
</t:template>
<%
	} else {
		out.print("<script>location.replace('index.jsp')</script>");
	}
%>