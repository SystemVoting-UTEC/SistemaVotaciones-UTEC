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
<!-- Include one of jTable styles. -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"
	type="text/css" />
<!-- Include jTable script file. -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jtable/jquery.jtable.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<!-- hojas de estilo-->
<link href="css/font-awesome-4.4.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="img/icono.ico">

<link rel="stylesheet" href="css/bootstrap.css">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/Estilos.css">
<link rel="stylesheet" href="css/styleVotante.css">


</head>
<body onkeydown="return showKeyCode(event)">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="#">Sistema Votaciones</a>
						</div>
						<div class="nav navbar-nav navbar-right">
							<c:if test="${sessionScope.usuario != null}">
								<a class="navbar-brand pull-right" href="#modalLogOut"
									data-toggle="modal" data-target="#modalLogOut"><span
									class="glyphicon glyphicon-log-out"></span> Salir</a>
							</c:if>
							<c:if test="${sessionScope.usuario == null}">
								<a class="navbar-brand pull-right" href="#myModal"
									data-toggle="modal" data-target="#myModal"><span
									class="glyphicon glyphicon-log-in"></span> Ingresar</a>
							</c:if>
						</div>
					</div>
				</nav>
			</div>
			<div class="col-xs-2">
				<h3>Opciones</h3>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<c:if test="${sessionScope.usuario != null}">
							<c:if test="${sessionScope.usuario.usTusId.tusId eq 1}">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne"><span
											class="glyphicon glyphicon-folder-close"> </span>
											Mantenimiento</a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="panel-body">
										<table class="table">
											<c:forEach items="${sessionScope.optList}" var="opt">
												<c:if test="${opt.optNombre eq 'Votar'}">
														<c:if test="${sessionScope.eleccion != null}">
															<tr>
																<td><span
																	class="glyphicon glyphicon-pencil text-primary"></span><a
																	href="${opt.optURL}"> ${opt.optNombre}</a></td>
															</tr>
														</c:if>
												</c:if>
												<c:if test="${opt.optNombre ne 'Votar'}">
													<tr>
														<td><span
															class="glyphicon glyphicon-pencil text-primary"></span><a
															href="${opt.optURL}"> ${opt.optNombre}</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
									</div>
								</div>
							</c:if>
						</c:if>
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo"><span
									class="glyphicon glyphicon-folder-close"> </span> General</a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse in">
							<div class="panel-body">
								<table class="table">
									<c:if test="${sessionScope.usuario != null}">
										<c:if test="${sessionScope.usuario.usTusId.tusId eq 2}">
											<c:forEach items="${sessionScope.optList}" var="opt">
												<c:if test="${opt.optNombre eq 'Votar'}">
														<c:if test="${sessionScope.eleccion != null}">
															<tr>
																<td><span
																	class="glyphicon glyphicon-pencil text-primary"></span><a
																	href="${opt.optURL}"> ${opt.optNombre}</a></td>
															</tr>
														</c:if>
												</c:if>
												<c:if test="${opt.optNombre ne 'Votar'}">
													<tr>
														<td><span
															class="glyphicon glyphicon-pencil text-primary"></span><a
															href="${opt.optURL}"> ${opt.optNombre}</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</c:if>
									</c:if>
									<tr>
										<td><span
											class="glyphicon glyphicon-eye-open text-primary"></span><a
											href="#"> Gráficos en linea</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-10">
				<jsp:doBody />
			</div>
			<div class="col-xs-12">
					<p id="copyright" style="text-align:center">
						<b>Copyright 2019, Grupo 11-Ing. en Sistemas y
								computación.</b>
					</p>
			</div>
		</div>
	</div>
	<form action="Login.do" method="POST" id="FRM_Login">
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Ingresar a Sitio</h5>
					</div>
					<div class="modal-body">
						<input type="text" name="usuario" id="usuario" maxlength="30"
							size="25" autofocus autocomplete="off" class="form-control"
							placeholder="00000000-0" pattern="[0-9]{8}-[0-9]{1}"> <input
							type="password" name="pass" id="pass" size="25"
							class="form-control" placeholder="Contraseña"
							pattern="[a-zA-Z0-9-]+">

					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="BTN_Login"
							value="Iniciar sesión">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
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
						<input type="submit" class="btn btn-primary" id="btnCerrar"
							name="btnCerrar" value="Salir">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
<!-- 		<script src="js/main.js" type="text/javascript"></script> -->
	<script src="js/bootstrap.js"></script>
</body>
</html>