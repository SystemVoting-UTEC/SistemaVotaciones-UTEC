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
				<c:if test="${sessionScope.usuario eq null}">
					<c:redirect url="index.jsp"></c:redirect>
				</c:if>
				<c:if test="${sessionScope.usuario ne null}">
					<script type="text/javascript" src="js/controller/CandidatoController.js"></script>
						<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
							<div id="CandidatoTableContainer"></div>
						</div>
				</c:if>
	</jsp:body>
</t:template>
<%
} else {
	out.print("<script>location.replace('index.jsp')</script>");
}
%>