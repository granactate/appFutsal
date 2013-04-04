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
        <title>Registro de usuario</title>
        <link rel="stylesheet" href="css/styles.css?v=1.0">
    </head>
    <body>
        <header>
            <div id='cabecera'>
                <div id='fotousuario'>
                    <a href='profile.php' title='My profile'><img src='images/user.png' /></a>
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
                            <input class='inputboton' type='submit' value='Log in' />
                        </div>
                    </form>
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
                <form method='POST' action='registrarUsuario.jsp'>
                    <div class='titulo'>
                        <div class='imagen'>
                            <img src='images/titulopostimagen.png' width='15px' />
                        </div>
                        <div class='texto'>
                            Usuario nuevo
                        </div>
                    </div>
                    <div id='cuerpo'>
                        <div class='cajaformulario'>
                            <div class='formulariosection'>
                                Elegir rol: 
                                <select name="rolRegistro" clase="registro">
                                    <%
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
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='username' placeholder='Username' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='email' class='registro' name='correoelectronico' placeholder='Correo electronico' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='password' class='registro' name='contrasenia' placeholder='Contraseña' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='password' class='registro' name='recontrasenia' placeholder='Re-contraseña' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='identificacion' placeholder='Identificacion' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='nombre' placeholder='Nombre' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='apellidos' placeholder='Apellidos' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='fechanacimiento' placeholder='Fecha de nacimiento (año-mes-día)' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='direccion' placeholder='Direccion' required>
                            </div>
                            <div class='formulariosection'>
                                <input type='text' class='registro' name='telefono' placeholder='Telefono' required>
                            </div>
                        </div>
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
