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
    String fechaLimiteInscripcion = request.getParameter("fechaLimiteInscripcion");
    String fechaSorteoGrupos = request.getParameter("fechaSorteoGrupos");
    String fechaSorteoPartidos = request.getParameter("fechaSorteoPartidos");
    String fechaInicioTorneo = request.getParameter("fechaInicioTorneo");

    String respuesta = Facade.crearCronogramaPlaneacionTorneo(nombreTorneo, fechaLimiteInscripcion, fechaSorteoGrupos, fechaSorteoPartidos, fechaInicioTorneo);
    //String respuesta = nombreTorneo+fechaLimiteInscripcion+fechaSorteoGrupos+fechaSorteoPartidos+fechaInicioTorneo;
    if (respuesta.substring(0, 6).equals("nombre")) {
    //if (!respuesta.isEmpty()) {
    
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="registroCronogramaPlaneacion.jsp" />
</jsp:forward>
<%    }
%>
