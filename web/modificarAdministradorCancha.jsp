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
    String usuario = (String) miSesion.getAttribute("usuario");
%>
<%
    String cancha = request.getParameter("cancha");
    String administradorCancha = request.getParameter("administradorCancha");
    String cedulaAdministrador = administradorCancha.split("/")[1];
    
    String respuesta = Facade.modificarAdministradorCancha(cancha,cedulaAdministrador);

    if (respuesta.substring(0, 2).equals("el")) {
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="modificacionAdministradorCancha.jsp" />
</jsp:forward>
<%    }
%>
