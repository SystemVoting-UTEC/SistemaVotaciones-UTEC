<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<title>Sistema de votaciones</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<script src="js/Jquery3.1.1.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<!-- hojas de estilo-->
<link href="css/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="img/icono.ico">

<link rel="stylesheet" href="css/bootstrap.css">

<script src="js/sweetalert.min.js" type="text/javascript"></script>
<link href="css/sweetalert.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/Estilos.css">
<link rel="stylesheet" href="css/styleVotante.css">
</head> 
<body onkeydown="return showKeyCode(event)">
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">Sistema Votaciones</a>
	    </div>
	    
	    <c:if test = "${sessionScope.usuario != null}">
		    <ul class="nav navbar-nav">
		      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Mantenimientos<span class="caret"></span></a>
		        <ul class="dropdown-menu">
		        <c:forEach items="${sessionScope.optList}" var="opt">
				 	<li><a href="${opt.optURL}"><c:out value="${opt.optNombre}"/></a></li>
				 </c:forEach>
		        </ul>
		      </li>
		    </ul>
      	</c:if>
	    <ul class="nav navbar-nav navbar-right">
	     <c:if test = "${sessionScope.usuario != null}">
			<li>
				<a href="#modalLogOut" data-toggle = "modal" data-target= "#modalLogOut"><span class="glyphicon glyphicon-log-out"></span> Salir</a>
			</li>
	     </c:if>
	     <c:if test = "${sessionScope.usuario == null}">
			<li>
				<a href="#myModal" data-toggle = "modal" data-target= "#myModal"><span class="glyphicon glyphicon-log-in"></span> Ingresar</a>
			</li>
	     </c:if>
	    </ul>
	  </div>
	</nav>
    <br/>
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<jsp:doBody />			
				</div>
			</div>
		</div>
	</section>
	<form action="Login.do" method="POST" id="FRM_Login">
		<div class="modal fade" id="myModal">
		    <div class="modal-dialog">
		        <div class="modal-content"> 
		            <div class="modal-header">
		                <h5 class="modal-title">Ingresar a Sitio</h5> 
		            </div>
		            <div class="modal-body">
						      <input type="text" name="usuario" id="usuario" maxlength="30" size="25" autofocus autocomplete="off" class="form-control" placeholder="00000000-0" pattern="[0-9]{8}-[0-9]{1}">
						      <input type="password" name="pass" id="pass" size="25" class="form-control" placeholder="Contraseña" pattern="[a-zA-Z0-9-]+">
						      
		            </div>
		            <div class="modal-footer">
		            	<input type="submit" class="btn btn-primary" id="BTN_Login" value="Iniciar sesión">
		                <button type="button" class="btn btn-danger" data-dismiss = "modal" >Cancelar</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
	
	<form action="LogOut.do" method="GET" id="FRM_LogOut">
		<div class="modal fade" id="modalLogOut">
		    <div class="modal-dialog">
		        <div class="modal-content"> 
		            <div class="modal-header">
		                <h5 class="modal-title">Salir</h5> 
		            </div>
		            <div class="modal-body">
						      <h5 class="modal-title">Desea salir del sitio?</h5> 
		            </div>
		            <div class="modal-footer">
		            	<input type="submit" class="btn btn-primary" id="btnCerrar" name="btnCerrar" value="Salir">
		                <button type="button" class="btn btn-danger" data-dismiss = "modal" >Cancelar</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
	<footer id="pagefooter">
		<div class="container-fluid">
			<center><p id="copyright"><b><h6>Copyright 2019, Grupo 11-Ing. en Sistemas y computación.</h6></b></p></center>
		</div>
	</footer>
	<script src="js/main.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.js"></script>
	</body>
</html>