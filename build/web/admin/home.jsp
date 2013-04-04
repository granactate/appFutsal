<%-- 
    Document   : home
    Created on : 31/01/2013, 03:28:07 PM
    Author     : AdMort
--%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.FalseLiteral"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BUSINESS.Facade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    HttpSession miSesion = request.getSession();
    String usuario = (String) miSesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="../css/styles.css?v=1.0">
    </head>
    <body>
        <header>
            <div id='cabecera'>
                <div id='fotousuario'>
                    <a href='' title='My profile'><img src='' /></a>
                </div>
                <div id='infousuario'>
                    <%
                        out.print("Hello, " + usuario + " | <a href='../cerrarSesion.jsp'/>Log out</a>");
                    %>
                </div>
            </div>
            <nav>
                <a href='home.jsp'><div class='botonnavhome'>Inicio</div></a>
                <a href='registroRol.jsp'><div class='botonnavhome'>Crear Rol</div></a>
                <a href='modificacionRol.jsp'><div class='botonnavhome'>Modificar Rol</div></a>
            </nav>
        </header>
        <section>

        </section>
        <footer>
            Adrian Carrascal - Mario Nieto
        </footer>
    </body>
</html>
