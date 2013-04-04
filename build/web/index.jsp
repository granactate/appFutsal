<%-- 
    Document   : index
    Created on : 15-dic-2012, 19:16:45
    Author     : Mario(K y M)
--%>

<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF">
        <title>Torneo Futsal</title>
        <link rel="stylesheet" href="css/styles.css?v=1.0">
    </head>
    <body>
        <header>
            <div id='cabecera'>
                <div id='fotousuario'>
                    <a href='profile.php' title='My profile'><img src='images/logo.png' /></a>
                </div>
                <div id='infousuario'>
                    <form method='POST' action='iniciarSesion.jsp'>
                        <div class='formulario'>
                            <input type='text' name='usuario' placeholder='Usuario' required>
                            <input type='password' name='contrasenia' placeholder='Contraseña' required>
                            <select name="rol" id="rol">
                                <%
                                    ArrayList<String> aux = Facade.cargarRoles();
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
                        <div class='formulario'>
                            <input class='inputboton' type='submit' value='Entrar' />
                        </div>
                    </form>
                </div>
            </div>
            <nav>
                <a href='registroUsuario.jsp'><div class='botonnavhome'>Registrarse</div></a>
            </nav>
        </header>
        <section>

        </section>
        <footer>
            Adrian Carrascal - Mario Nieto
        </footer>
    </body>
</html>
