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
        <title>Home</title>
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
                    
                    boolean[] modulos = {false, false};
                    for (Integer i : privilegios) {
                        if (i >= 4 && i <= 6 && !modulos[0]) {
                            //out.print("<a href='equipo.jsp'><div class='botonnav'>Equipo</div></a>");
                            modulos[0] = true;
                        }
                        if (i >= 7 && i <= 14 && !modulos[1]) {
                            out.print("<a href='torneo.jsp'><div class='botonnav'>Torneos</div></a>");
                            modulos[1] = true;
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
