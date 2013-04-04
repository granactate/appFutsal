<%-- 
    Document   : index
    Created on : 3/02/2013, 06:10:20 PM
    Author     : AdMort
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesion - Administrador</title>
        <link rel="stylesheet" href="./css/styles.css?v=1.0">
    </head>
    <body>
        <div id='infousuario'>
            <form method='POST' action='iniciarSesionAdmin.jsp'>
                <div class='formulario'>
                    <input type='text' name='usuario' placeholder='Usuario' required>
                    <input type='password' name='contrasenia' placeholder='Contraseña' required>
                </div>
                <div class='formulario'>
                    <input class='inputboton' type='submit' value='Log in' />
                </div>
            </form>
        </div>
    </body>
</html>
