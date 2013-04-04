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
    ArrayList<String> jugadores = new ArrayList<String>();

    for (int i = 0; i < 12; i++) {
        if (nombreJugadores[i] != null && apellidosJugadores[i] != null && identificacionJugadores[i] != null
                && fechaNacimientoJugadores[i] != null && estaturaJugadores[i] != null) {
            String jugador = nombreJugadores[i]+"/"+apellidosJugadores[i]+"/"+identificacionJugadores[i]+"/"+
                fechaNacimientoJugadores[i]+"/"+estaturaJugadores[i];
            jugadores.add(jugador);
        }
    }
    String respuesta = Facade.registrarEquipo(jugadores, nombreTorneo, usuario, nombreEquipo);

    if (respuesta.equals("")) {
%>
<jsp:forward page="mensaje.jsp" />
<%} else {
%>
<jsp:forward page="error.jsp" />
<%    }
%>
