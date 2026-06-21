<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">
        <div class="brand">Secure Bank</div>
        <h2>Welcome Back</h2>
        <p class="subtitle">Login to access your account</p>

        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="alert success"><%= request.getAttribute("successMessage") %></div>
        <% } %>

        <form action="login" method="post">
            <div class="input-group">
                <label>Email or Phone</label>
                <input type="text" name="contact" placeholder="Enter email or phone" value="<%= request.getAttribute("contact") != null ? request.getAttribute("contact") : "" %>" required>
            </div>

            <div class="input-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Enter password" required>
            </div>

            <button type="submit" class="btn primary">Login</button>
        </form>

        <div class="switch-link">
            New user? <a href="register.jsp">Create account</a>
        </div>
    </div>
</div>
</body>
</html>