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
				<script type="text/javascript" src="js/controller/GeneroController.js"></script>
				<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
				<div id="GeneroTableContainer"></div>
			</div>
	</jsp:body>
</t:template>
<%
} else {
	out.print("<script>location.replace('index.jsp')</script>");
}
%>