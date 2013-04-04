<%@page import="BUSINESS.Facade"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<String> modulos = Facade.getModulosExtendido();
    ArrayList<String> modulosPorRol = Facade.getModulosExtendidosPorRol("Arbitro");

    for (String m : modulos) {
        String[] extendidos = m.split("/");
        for (String e : extendidos) {
            out.print("<input type='checkbox' name='' value='" + (String) e + "' checked>");
        }
    }
%>