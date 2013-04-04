<%-- 
    Document   : error
    Created on : 9/02/2013, 12:55:24 PM
    Author     : AdMort
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
   		<meta charset='utf-8-spanish_ci'>
		<title>
			Error
		</title>
		<link rel='stylesheet' type="text/css" href='http://necolas.github.com/normalize.css/2.1.0/normalize.css'>
		<link rel="stylesheet" href="css/styles.css?v=1.0">
	</head>
	<body>
		<div id='error'>
                    <p>
                    <%
                        out.print(request.getParameter("msj"));
                    %>
                    </p>
                    <%
                        if(request.getSession().getAttribute("usuario").equals("null")){
                    %>
                            <a href='index.jsp'>Ir a inicio</a>
                    <%
                        } else {
                    %>
                            <a href='home.jsp'>Ir a inicio</a>
                    <%
                        }
                    %>
                    <a href='<% out.print(request.getParameter("url")); %>'>Volver al formulario</a>
                </div>
	</body>
</html>
