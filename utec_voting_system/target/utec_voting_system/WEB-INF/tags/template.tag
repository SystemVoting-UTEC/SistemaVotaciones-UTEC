<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<!Doctype html>
<html>
<head>
<title>Sistema de votaciones</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<script src="js/jquery.validate.min.js" type="text/javascript"></script>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- hojas de estilo-->
<link href="css/font-awesome-4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="img/icono.ico">

<script src="js/sweetalert.min.js" type="text/javascript"></script>
<link href="css/sweetalert.css" rel="stylesheet" type="text/css" />

<link href="css/form.css" rel="stylesheet" type="text/css" />
<script src="js/jquery_v3.1.1.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js"></script>

<script src="js/sweetalert.min.js" type="text/javascript"></script>
<link href="css/sweetalert.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/Estilos.css">
<link rel="stylesheet" href="css/styleVotante.css">
</head>
<body onkeydown="return showKeyCode(event)" >
	<nav class="navbar navbar-expand-md navbar-ligth sidebarNavigation" data-sidebarClass="navbar-light" style="background-color: #e3f2fd;">
	        <div class="container-fluid">
	        <a class="navbar-brand" href="#">Sistema Votaciones</a>
	        <button class="navbar-toggler leftNavbarToggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
	            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	
	        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
	            <ul class="nav navbar-nav nav-flex-icons ml-auto">
	                <li class="nav-item active">
						<%
						    HttpSession sesion = request.getSession(true);
						    Object usuario = sesion.getAttribute("usuario")==null ? null : sesion.getAttribute("usuario");
				            if(usuario!=null){
			        	%>
						<form method="POST" action="LogOut.do">
							<a href="LogOut?btnCerrar=true"><i
								class="fa fa-power-off fa-2x fa-3x" aria-hidden="true"
								style="float: left; margin-top: -4px; margin-left: 20px;"></i></a> <input
								type="hidden" name="btnCerrar">
						</form>
						<%
				            } else {
						%>
						<form action="Login.do" method="POST" id="FRM_Login">
							<table style="float: right;">
								<tr>
									<td><input type="text" name="usuario" id="usuario"
										maxlength="30" size="25" autofocus autocomplete="off"
										class="form-control" placeholder="Usuario"></td>
									<td>&nbsp;</td>
									<td><input type="password" name="pass" id="pass" size="25"
										class="form-control" placeholder="Contraseña"></td>
									<td><input type="submit" id="BTN_Login"
										value="Iniciar sesión" class="button button2"></td>
								</tr>
							</table>
						</form> 
						<%
				            }
						%>
					</li>
	            </ul>
	        </div>
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
	<footer id="pagefooter">
		<div class="container-fluid">
			<center><p id="copyright"><b><h6>Copyright 2019, Grupo 11-Ing. en Sistemas y computación.</h6></b></p></center>
		</div>
	</footer>
	<script src="js/main.js" type="text/javascript"></script>
</body>
</html>