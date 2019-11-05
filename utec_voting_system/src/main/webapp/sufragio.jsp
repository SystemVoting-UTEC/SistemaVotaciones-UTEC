<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%
	HttpSession sesion = request.getSession(true);
	Object usuario = sesion.getAttribute("usuario") == null ? null : sesion.getAttribute("usuario");
	if (usuario != null) {
%>
<t:template>
	<jsp:body>
				<link href="css/votos.css" rel="stylesheet" type="text/css">
			    <c:if test="${sessionScope.usuario.usPerDui.perDepId.depId ne null}">
			       <h1><span>Simulador</span> de votación por <c:out value="${sessionScope.usuario.usPerDui.perDepId.depNombre}"/> </h1>
			    </c:if>
	</jsp:body>
</t:template>
<%
} else {
	out.print("<script>location.replace('index.jsp')</script>");
}
%>