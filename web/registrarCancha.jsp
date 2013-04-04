<%-- 
    Document   : iniciarSesion
    Created on : 2/02/2013, 09:50:13 AM
    Author     : AdMort
--%>

<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%
    HttpSession miSesion = request.getSession();
    String administradorCancha = request.getParameter("administradorCancha");
    String nombreCancha = request.getParameter("nombreCancha");
    String direccionCancha = request.getParameter("direccionCancha");
    
    String cedulaAdministrador = administradorCancha.split("/")[1];
    
    String respuesta = Facade.registrarCancha(cedulaAdministrador, nombreCancha, direccionCancha);
    if (respuesta.substring(0, 5).equals("Se ha")) {
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="registroCancha.jsp" />
</jsp:forward>
<%    }
%>
