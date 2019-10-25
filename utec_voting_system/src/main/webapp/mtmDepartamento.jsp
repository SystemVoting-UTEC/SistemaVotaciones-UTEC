<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<c:forEach var="dpt" items="${listaDepartamento}">
		<td><c:out value="${dpt.depId}"></c:out></td>
		<td><c:out value="${dpt.depNombre}"></c:out></td>
		<td class="text-center">
			<a class='btn btn-info btn-xs' href="#">
			<span class="glyphicon glyphicon-edit"></span> Editar</a>
		</td>
	</c:forEach>
    </table>
	</jsp:body>
</t:template>