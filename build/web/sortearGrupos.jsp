<%-- 
    Document   : iniciarSesion
    Created on : 2/02/2013, 09:50:13 AM
    Author     : AdMort
--%>

<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%
    String nombreTorneo = request.getParameter("nombreTorneo");

    String respuesta = Facade.generarGruposTorneo(nombreTorneo);
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%= respuesta %>" />
</jsp:forward>>
