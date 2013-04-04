<%-- 
    Document   : iniciarSesion
    Created on : 2/02/2013, 09:50:13 AM
    Author     : AdMort
--%>

<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%
    String usuario = request.getParameter("usuario");
    String contrasenia = request.getParameter("contrasenia");
    //String rol = request.getParameter("rol");
    ArrayList<Integer> respuesta = Facade.validarUsuario(usuario, contrasenia, "Administrador Pagina");
    if (respuesta != null) {
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("usuario", usuario);
%>
    <jsp:forward page="home.jsp" />
<%  
    } else {
%>
    <jsp:forward page="index.jsp" />
<%
    }
%>
