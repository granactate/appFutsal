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
    String nombreEquipo = request.getParameter("nombreEquipo");
    String[] nombreJugadores = request.getParameterValues("nombreJugador");
    String[] apellidosJugadores = request.getParameterValues("apellidosJugador");
    String[] identificacionJugadores = request.getParameterValues("identificacionJugador");
    String[] fechaNacimientoJugadores = request.getParameterValues("fechaNacimientoJugador");
    String[] estaturaJugadores = request.getParameterValues("estaturaJugador");
    String[] pesoJugadores = request.getParameterValues("pesoJugador");
    ArrayList<String> jugadores = new ArrayList<String>();

    for (int i = 0; i < 12; i++) {
        if (nombreJugadores[i] != null && apellidosJugadores[i] != null && identificacionJugadores[i] != null
                && fechaNacimientoJugadores[i] != null && estaturaJugadores[i] != null && pesoJugadores[i] != null &&
                !nombreJugadores[i].isEmpty() && !apellidosJugadores[i].isEmpty() && !identificacionJugadores[i].isEmpty()
                && !fechaNacimientoJugadores[i].isEmpty() && !estaturaJugadores[i].isEmpty() && !pesoJugadores[i].isEmpty()) {
            String jugador = nombreJugadores[i]+"/"+apellidosJugadores[i]+"/"+identificacionJugadores[i]+"/"+
                fechaNacimientoJugadores[i]+"/"+estaturaJugadores[i]+"/"+pesoJugadores[i];
            jugadores.add(jugador);
        }
    }
    
    String jug = nombreJugadores[0]+"/"+apellidosJugadores[0]+"/"+identificacionJugadores[0]+"/"+
                fechaNacimientoJugadores[0]+"/"+estaturaJugadores[0]+"/"+pesoJugadores[0];
    //ArrayList<String> jugs = new ArrayList<String>();
    //jugs.add(jug);
    
    String respuesta = Facade.registrarEquipo(jugadores, nombreTorneo, usuario, nombreEquipo);
    //String respuesta = nombreJugadores[0]+"/"+apellidosJugadores[0]+"/"+identificacionJugadores[0]+"/"+
    //            fechaNacimientoJugadores[0]+"/"+estaturaJugadores[0]+"/"+pesoJugadores[0];
    if (respuesta.substring(0, 5).equals("Se ha")) {
    //if(1<2){
%>
<jsp:forward page="mensaje.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
</jsp:forward>
<%} else {
%>
<jsp:forward page="error.jsp">
    <jsp:param name="msj" value="<%=respuesta%>" />
    <jsp:param name="url" value="registroEquipo.jsp" />
</jsp:forward>
<%    }
%>
