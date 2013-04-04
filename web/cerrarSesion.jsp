<%-- 
    Document   : cerrarSesion
    Created on : 2/02/2013, 09:30:02 AM
    Author     : AdMort
--%>

<%@ page session="true" %>
<%
    HttpSession miSesion = request.getSession();
    miSesion.invalidate();
%>
<jsp:forward page="index.jsp"/>
