<%-- 
    Document   : logout
    Created on : 19 Mar, 2026, 2:29:22 PM
    Author     : tanya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>