<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@include file="cache.jsp"%>
<%
	HttpSession sesion = request.getSession(true);
	Object usuario = sesion.getAttribute("usuario") == null ? null : sesion.getAttribute("usuario");
	if (usuario != null) {
%>
				
<link href="css/votos.css" rel="stylesheet" type="text/css">
<t:template>
	<jsp:body>
				<center>
					<h1><span>Simulador</span> de votación por <c:out value="${sessionScope.usuario.usPerDui.perDepId.depNombre}"/></h1>
				<br><br><br>
				<h4>ELECCIÓN DE DIPUTADOS Y DIPUTADAS A LA ASAMBLEA LEGISLATIVA 2019</h4>
				<br>
				    <div id="mensaje" class="bien">
				        <h3></h3>
				        <a id="nuevo" href="#">NUEVA PAPELETA<br> INTENTELO DE NUEVO</a> 
				        <a id="cerrar" href="#">CERRAR NOTIFICACIÓN</a>
				    </div>
				
				<div id="papeleta">	
					<span id="explicar"></span>
					<nav id="btns" class="flotar">
				        <a id="contar" href="#"><span>0</span>DE 24 POSIBLES MARCAS POR ROSTRO</a>
				        <button class="btn-success">VOTAR</button>
					</nav>
					
					 <c:forEach items="${sessionScope.parList}" var="par">
						<ul id="${par.parId}" class = "votando">
							<li class="bandera"><span>${par.parNombre}</span></li>
							<c:forEach items="${sessionScope.canList}" var="can">
								<c:if test="${can.canParId.parId eq par.parId}">
									<li class="rostro woman"><ol id="${can.canId}" class="ejerciendovotoCandidato" style="width: 100%;height: 100%;"> </ol><span style="margin-top:-60px;">${can.canPerDui.perPNombre} ${can.canPerDui.perPApellido}</span></li>
								</c:if>
							</c:forEach>
						</ul>
					 </c:forEach>
				
				</div>
				</center>
				<input type="hidden" id="dui" value="${sessionScope.usuario.usPerDui.perDui}">
				<footer class="flotar">
					
				</footer>
				<!-- Con estps hacemos el efecto de papeleta marcada -->
				<script src="js/jquery-1.11.1.min.js"></script>
				<script type="text/javascript">
				    $(document).ready(function(){
				        var voto = [];
				       $('.ejerciendovotoCandidato').click(function(){
				          voto[voto.length] = $(this).attr('id');
				
				       });
				       $('#btns').click(function (){
				          var votos = voto.toString();
				          var dui = document.getElementById("dui").value;
				          $.ajax({
				                         type: "GET",
				                         contentType: "application/json",
				                         url: "/sufragio.do",
				                         data : {voto: votos, dui: dui},
				                         //data : JSON.stringify({voto: voto.toString(), dui: dui}),
				                         success: function(data){
				                             alert("Gracias por su voto, bye!");
				                             window.location.replace("http://localhost:8080/SistemaVotaciones/LogOut?btnCerrar=true");
				                         }
				          });
				       });
				   });
				</script>
				<script src="js/jquery.lazylinepainter-1.4.1.min.js"></script> 
				<script src="js/raphael.js"></script> 
				<!-- Hasta acá -->
				<script src="js/ion.sound.js"></script>
				<script src="js/jquery.easing.1.3.js"></script>
				<script src="js/core.js"></script>
	</jsp:body>
</t:template>
<%
} else {
	out.print("<script>location.replace('index.jsp')</script>");
}
%>