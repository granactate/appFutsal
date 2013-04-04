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
    String nombreTorneo = request.getParameter("nombreTorneo");
    String cancha1 = request.getParameter("cancha1");
    String cancha2 = request.getParameter("cancha2");
    
    ArrayList<String> canchas = new ArrayList<String>();
    if(!cancha1.equals("Sin asignar")){
        canchas.add(cancha1);
    }
    if(!cancha2.equals("Sin asignar")){
        canchas.add(cancha2);
    }
    
    String respuesta = Facade.asignarCanchasTorneo(nombreTorneo, canchas);

    if (!respuesta.equals("")) {
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="asignacionCanchasTorneo.jsp" />
</jsp:forward>
<%    }
%>
