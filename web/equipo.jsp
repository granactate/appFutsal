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
    String rol = (String) miSesion.getAttribute("rol");
    ArrayList<Integer> privilegios = (ArrayList<Integer>) miSesion.getAttribute("privilegios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equipo</title>
        <link rel="stylesheet" href="css/styles.css?v=1.0">
    </head>
    <body>
        <header>
            <div id='cabecera'>
                <div id='fotousuario'>
                    <a href='profile.php' title='My profile'><img src='images/user.png' /></a>
                </div>
                <div id='infousuario'>
                    <%
                        out.print("Hello, " + usuario + " | <a href='cerrarSesion.jsp'/>Log out</a>");
                    %>
                </div>
            </div>
            <nav>
                <a href='home.jsp'><div class='botonnavhome'>
                        <img src='images/home.png' title='Inicio' />
                    </div></a>
                <%
                    for (Integer i : privilegios) {
                        if (i == 4) {
                            out.print("<a href='habilitacionJugador.jsp'><div class='botonnav'>   </div></a>");
                        }
                        if (i == 5) {
                            out.print("<a href='deshabilitacionJugador.jsp'><div class='botonnav'>   </div></a>");
                        }
                        if (i == 6) {
                            out.print("<a href='deshabilitacionEquipo.jsp'><div class='botonnav'><img src='images/desequipo.png' /></div></a>");
                        }
                    }
                %>
            </nav>
        </header>
        <section>

        </section>
        <footer>
            Adrian Carrascal - Mario Nieto
        </footer>
    </body>
</html>
