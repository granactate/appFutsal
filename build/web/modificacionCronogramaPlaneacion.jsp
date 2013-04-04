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
        <title>Torneo</title>
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
            </nav>
        </header>
        <section>
            <article>
                <form method='POST' action='modificarCronogramaPlaneacion.jsp'>
                    <div class='titulo'>
                        <div class='imagen'>
                            <img src='images/titulopostimagen.png' width='15px' />
                        </div>
                        <div class='texto'>
                            Modificar cronograma de planeación a un torneo
                        </div>
                    </div>
                    <div id='cuerpo'>
                        <div class='cajaformulario'>
                            <div class='formulariosection'>
                                <select name="nombreTorneo" id="nombreTorneo" autofocus>
                                    <%
                                        ArrayList<String> torneos = Facade.listarTorneosCronograma(usuario);
                                        if (torneos == null || torneos.isEmpty()) {
                                            out.print("<script> alert('no se pudo realizar la conexion con la base de datos')</script> ");
                                            return;
                                        }
                                        for (String texto : torneos) {
                                            out.print("<option>" + texto + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='fechaLimiteInscripcion' placeholder='Fecha límite de inscripción' required>
                                <input type='text' class='registro' name='fechaSorteoGrupos' placeholder='Fecha para el sorteo de los grupos' required>
                                <input type='text' class='registro' name='fechaSorteoPartidos' placeholder='Fecha para el sorteo de los partidos' required>
                                <input type='text' class='registro' name='fechaInicioTorneo' placeholder='Fecha inicio del torneo' required>
                            </div>
                        </div>
                    </div>
                    <div class='pie'>
                        <div class='boton'>
                            <input type='submit' value='Asignar'>
                        </div>
                    </div>
                </form>
            </article>>
        </section>
        <footer>
            Adrian Carrascal - Mario Nieto
        </footer>
    </body>
</html>
