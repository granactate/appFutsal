<%-- 
    Document   : mensaje
    Created on : 4/02/2013, 10:36:14 AM
    Author     : AdMort
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
   		<meta charset='utf-8-spanish_ci'>
		<title>
			Mensaje
		</title>
		<link rel='stylesheet' type="text/css" href='http://necolas.github.com/normalize.css/2.1.0/normalize.css'>
		<link rel="stylesheet" href="css/styles.css?v=1.0">
	</head>
	<body>
		<div id='mensaje'>
                    <p>
                    <%
                        out.print(request.getParameter("msj"));
                    %>
                    </p>
                    <a href='home.jsp'>Ir a inicio</a>
                    <!--<a href='<% out.print(request.getParameter("url")); %>'>Volver al formulario</a>-->
		</div>
	</body>
</html>
