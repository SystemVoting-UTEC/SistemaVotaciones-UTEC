<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<t:template>
	<jsp:body>
		<c:if test="${sessionScope.usuario eq null}">
					<c:redirect url="index.jsp"></c:redirect>
				</c:if>
				<c:if test="${sessionScope.usuario ne null}">
					<center>
						<h1>
					<span>Votación por</span> <c:out value="${sessionScope.usuario.usPerDui.perDepId.depNombre}" />
				</h1>
					
					<table border="1">
							<tr>
								<td><button type="button" class="btn btn-success" id="btns"> Votar </button></td>
							</tr>
						  <tr>
						  	<c:forEach items="${sessionScope.parList}" var="par">
						    	<th><div style="height: 150px; width: 150px;"><input type="radio" name="part" id="par-${par.parNombre}" class="partido"> ${par.parNombre}</div></th>
						  	</c:forEach>
						  </tr>
						   <tr>
							   <c:forEach items="${sessionScope.parList}" var="par">
								   <td>
								   		<table border="1">
											<c:forEach items="${sessionScope.canList}" var="can">
												<c:if test="${can.canParId.parId eq par.parId}">
												  <tr>
												    <td>
												     <div style="height: 150px; width: 150px;">
														    <input type="checkbox" class="case-par-${par.parNombre}" name="${can.canPerDui.perPNombre} ${can.canPerDui.perPApellido}" value="${can.canId}"/>
														   		${can.canPerDui.perPNombre} ${can.canPerDui.perPApellido}
													   </div>
												    </td>
												  </tr>
												</c:if>
											</c:forEach>
								   		</table>
								   </td>
							 </c:forEach>
						   </tr>
						  </table>
					<input type="hidden" id="dui" value="${sessionScope.usuario.usPerDui.perDui}">
					</center>
					<script>
					var voto = [];
					$(".partido").on("click", function() {  
						$("input[type='checkbox']").prop("checked", false);
					  	$(".case-"+$(this).attr('id')).prop("checked", this.checked);  
					});
					$('#btns').click(function (){
						 $(':checkbox:checked').each( function () {
						     voto[voto.length] = $(this).attr('value');
						 }); 
						 var votos = voto.toString();
				          var dui = document.getElementById("dui").value;
				          $.ajax({
				        	    url: '/utec_voting_system/sufragio.do',
				        	    type: 'POST',
				        	    data: jQuery.param({voto: votos, dui: dui}) ,
				        	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				        	    success: function (response) {
				        	        alert(response.status);
				        	    },
				        	    error: function () {
				        	        alert("error");
				        	    }
				        	});
					});
					</script>
				</c:if>
	</jsp:body>
</t:template>