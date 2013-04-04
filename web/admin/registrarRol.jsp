<%-- 
    Document   : iniciarSesion
    Created on : 2/02/2013, 09:50:13 AM
    Author     : AdMort
--%>

<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%
    String rol = request.getParameter("nombreRol");
    String[] extendidos = request.getParameterValues("extendido");
    ArrayList<String> ext = new ArrayList<String>();
    if (extendidos != null) {
        for (String s : extendidos) {
            ext.add(s);
        }
    }

    //String rol = request.getParameter("rol");
    String respuesta = Facade.addRol(rol, ext);

    if (respuesta.substring(0, 12).equals("Se ha creado")) {
%>
<jsp:forward page="home.jsp" />
<%} else {
%>
<jsp:forward page="registroRol.jsp" />
<%    }
%>
