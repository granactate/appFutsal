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
        <title>Registro rol</title>
        <link rel="stylesheet" href="../css/styles.css?v=1.0">
        <script type="text/javascript" language="javascript">
 
            var READY_STATE_UNINITIALIZED=0; 
            var READY_STATE_LOADING=1; 
            var READY_STATE_LOADED=2;
            var READY_STATE_INTERACTIVE=3; 
            var READY_STATE_COMPLETE=4;
		 
            var peticion_http;
		 
            function cargarContenido(url, metodo, funcion) {
                peticion_http = inicializa_xhr();
		 
                if(peticion_http) {
                    peticion_http.onreadystatechange = funcion;
                    peticion_http.open(metodo, url, true);
                    peticion_http.send(null);
                }
            }
		 
            function inicializa_xhr() {
                if(window.XMLHttpRequest) {
                    return new XMLHttpRequest();
                }
                else if(window.ActiveXObject) {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
		 
            function mostrarContenido() {
                if(peticion_http.readyState == READY_STATE_COMPLETE) {
                    if(peticion_http.status == 200) {
                        document.getElementById('cuerpo').innerHTML = peticion_http.responseText;
                    }
                }
            }
		 
        </script>
    </head>
    <body>
        <header>
            <div id='cabecera'>
                <div id='fotousuario'>
                    <a href='profile.php' title='My profile'><img src='images/fotousuario-".$id.".jpg' /></a>
                </div>
                <div id='infousuario'>
                    <%
                        out.print("Hello, " + usuario + " | <a href='../cerrarSesion.jsp'/>Log out</a>");
                    %>
                </div>
            </div>
            <nav>
                <a href='home.jsp'><div class='botonnavhome'>Inicio</div></a>

            </nav>
        </header>
        <section>
            <article>
                <form method='POST' action='registrarRol.jsp'>
                    <div class='titulo'>
                        <div class='imagen'>
                            <img src='images/titulopostimagen.png' width='15px' />
                        </div>
                        <div class='texto'>
                            Rol nuevo
                        </div>
                    </div>
                    <div id='cuerpo'>
                        <div class='cajaformulario'>
                            <div class='formulariosection'>
                                <input class='registro' type='text' name='nombreRol' placeholder='Nuevo rol' required>
                            </div>
                        </div>
                        <%
                            ArrayList<String> modulos = Facade.getModulosExtendido();

                            for (String m : modulos) {
                                String[] extendidos = m.split("/");
                                out.print("<div class='cajaformulario'>");
                                out.print("<div class='formulariosection'><input type='checkbox' name='rol' value='" + extendidos[0] + "'>" + extendidos[0] + "</div>");
                                for (int i = 1; i < extendidos.length; i++) {
                                    //String[] e = extendidos[i].split("*");
                                    //if (e.length == 1) {
                                        out.print("<div class='subformulariosection'><input type='checkbox' name='extendido' value='" + extendidos[i] + "'>" + extendidos[i] + "</div>");
                                    //} else {
                                    //    out.print("<div class='subformulariosection'><input type='checkbox' name='" + extendidos[i] + "' value='" + extendidos[i] + "'>(" + e[0] + ") " + e[1] + "</div>");
                                    //}
                                }
                                out.print("</div>");
                            }
                        %>
                    </div>
                    <div class='pie'>
                        <div class='boton'>
                            <input type='submit' value='Registrar'>
                        </div>
                    </div>
                </form>
            </article>
        </section>
        <footer>
            Adrian Carrascal - Mario Nieto
        </footer>
    </body>
</html>
