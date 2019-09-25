<!DOCTYPE html>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(true);
    Object nombre = sesion.getAttribute("usuario")==null ? null : sesion.getAttribute("usuario");
            if(nombre!=null){
        %>
<html>
    <head>
        <link rel="shortcut icon" href="img/icono.ico">
        <title>Manual del Usuario</title>
    </head>
    <body>
        <embed src="ManualAdmin.pdf" style=" margin: 0px; padding: 0px; width:100%;height:618px; margin-left: 0px;">
    </body>
        <%
            }else{
                out.print("<script>location.replace('../index.jsp')</script>");
            }
        %>
</html>