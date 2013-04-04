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
                <form method='POST' action='registrarEquipo.jsp'>
                    <div class='titulo'>
                        <div class='imagen'>
                            <img src='images/titulopostimagen.png' width='15px' />
                        </div>
                        <div class='texto'>
                            Equipo nuevo
                        </div>
                    </div>
                    <div id='cuerpo'>
                        <div class='cajaformulario'>
                            <div class='formulariosection'>
                                Elegir un torneo:
                                <select name="nombreTorneo" class="registro">

                                    <%
                                        ArrayList<String> aux = Facade.getTorneosDisponiblesInscripcion();
                                        if (aux == null || aux.isEmpty()) {
                                            out.print("<script> alert('no se pudo realizar la conexion con la base de datos')</script> ");
                                            return;
                                        }
                                        for (String texto : aux) {
                                            out.print("<option>" + texto + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                        <div class='cajaformulario'>
                            <div class='formulariosection'>
                                <input class='registro' type='text' name='nombreEquipo' placeholder='Nombre equipo' required>
                            </div>
                        </div>
                        <%

                            for (int i = 0; i < 12; i++) {
                                if (i < 7) {
                                    out.print("<div class='cajaformulario'>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='nombreJugador' placeholder='Nombre jugador' required></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='apellidosJugador' placeholder='Apellidos jugador' required></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='identificacionJugador' placeholder='Identificacion jugador' required></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='fechaNacimientoJugador' placeholder='Fecha nacimiento jugador (año-mes-dia)' required></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='estaturaJugador' placeholder='Estatura jugador' required></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='pesoJugador' placeholder='Peso jugador' required></div>");
                                    out.print("</div>");
                                } else {

                                    out.print("<div class='cajaformulario'>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='nombreJugador' placeholder='Nombre jugador'></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='apellidosJugador' placeholder='Apellidos jugador'></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='identificacionJugador' placeholder='Identificacion jugador'></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='fechaNacimientoJugador' placeholder='Fecha nacimiento jugador (año-mes-dia)'></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='estaturaJugador' placeholder='Estatura jugador'></div>");
                                    out.print("<div class='formulariosection'><input class='registro' type='text' name='pesoJugador' placeholder='Peso jugador'></div>");
                                    out.print("</div>");
                                }
                            }
                        %>
                    </div>
                    <div class='pie'>
                        <div class='boton'>
                            <input type='submit' value='Registrar'>
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
