<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sesion = request.getSession(true);
	Object nombre = sesion.getAttribute("usuario") == null ? null : sesion.getAttribute("usuario");
	if (nombre != null) {
%>
<t:template>
	<jsp:body>
			<div  class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						Panel de control
					</div>
				</div>			
			</div>
	</jsp:body>
</t:template>
<%
	} else {
		out.print("<script>location.replace('index.jsp')</script>");
	}
%>