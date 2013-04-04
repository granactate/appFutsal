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
    try{
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
                        boolean[] modulos = {false, false, false, false, false};
                        for (Integer i : privilegios) {
                            if (i >= 1 && i <= 3 && !modulos[0]) {
                                out.print("<a href='gestionPermisos.jsp'><div class='botonnav'>G. Permisos</div></a>");
                                modulos[0] = true;
                            }
                            if (i >= 4 && i <= 14 && !modulos[1]) {
                                out.print("<a href='planificacionTorneo.jsp'><div class='botonnav'><img src='images/torneo.png' title='Planificar Torneo' /></div></a>");
                                modulos[1] = true;
                            }
                            if (i >= 15 && i <= 16 && !modulos[2]) {
                                out.print("<a href='inscripcionEquipo.jsp'><div class='botonnav'><img src='images/equipo.png' title='Inscribir Equipo' /></div></a>");
                                modulos[2] = true;
                            }
                            if (i >= 17 && i <= 22 && !modulos[3]) {
                                out.print("<a href='gestionCanchas.jsp'><div class='botonnav'><img src='images/cancha.png' title='Gestionar Cancha' /></div></a>");
                                modulos[3] = true;
                            }
                            if (i >= 23 && i <= 25 && !modulos[4]) {
                                //out.print("<a href='consultas.jsp'><div class='botonnav'><img src='images/consultas.png' title='Consultar' /></div></a>");
                                modulos[4] = true;
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
<%
} catch(Exception e) {
%>
<jsp:forward page="index.jsp" />
<%
}
%>