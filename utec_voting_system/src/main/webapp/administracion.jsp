<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test = "${sessionScope.usuario == null}">
	 <c:redirect url = "index.jsp"/>
</c:if>
<c:if test = "${sessionScope.usuario != null}">
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
</c:if>