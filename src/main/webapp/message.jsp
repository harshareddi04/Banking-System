<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-page">
    <div class="form-card">
        <h2>System Message</h2>
        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>
        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="alert success"><%= request.getAttribute("successMessage") %></div>
        <% } %>
        <div class="center-links">
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
            <a href="dashboard">Dashboard</a>
        </div>
    </div>
</div>
</body>
</html>