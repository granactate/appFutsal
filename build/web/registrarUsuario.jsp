<%-- 
    Document   : iniciarSesion
    Created on : 2/02/2013, 09:50:13 AM
    Author     : AdMort
--%>

<%@page import="java.sql.Date"%>
<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%
    String rol = request.getParameter("rolRegistro");
    String identificacion = request.getParameter("identificacion");
    String username = request.getParameter("username");
    String correoelectronico = request.getParameter("correoelectronico");
    String contrasenia = request.getParameter("contrasenia");
    String recontrasenia = request.getParameter("recontrasenia");
    String nombre = request.getParameter("nombre");
    String apellidos = request.getParameter("apellidos");
    String fechanacimiento = request.getParameter("fechanacimiento");
    String direccion = request.getParameter("direccion");
    String telefono = request.getParameter("telefono");

    if (contrasenia.equals(recontrasenia)) {
        String respuesta = Facade.registrarUsuario(identificacion, nombre, apellidos, fechanacimiento, username, contrasenia, telefono, direccion, correoelectronico, rol);
        
        if (respuesta.substring(0, 10).equals("el usuario")) {
            
            ArrayList<Integer> privilegios = Facade.validarUsuario(username, contrasenia, rol);
            if (privilegios!=null && !privilegios.isEmpty()) {
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("usuario", username);
                miSesion.setAttribute("rol", rol);
                miSesion.setAttribute("privilegios", privilegios);
            }
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%
} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="registroUsuario.jsp" />
</jsp:forward>
<%    }
} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="Las contraseñas no coinciden" />
    <jsp:param name="url" value="registroUsuario.jsp" />
</jsp:forward>
<%    }
%>
