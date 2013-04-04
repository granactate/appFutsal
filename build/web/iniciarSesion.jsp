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
    String rol = request.getParameter("rol");
    ArrayList<Integer> privilegios = Facade.validarUsuario(usuario, contrasenia, rol);
    if (privilegios!=null && !privilegios.isEmpty()) {
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("usuario", usuario);
        miSesion.setAttribute("rol", rol);
        miSesion.setAttribute("privilegios", privilegios);
%>
    <jsp:forward page="home.jsp" />
<%  
    } else {
%>
    <jsp:forward page="index.jsp" />
<%
    }
%>
