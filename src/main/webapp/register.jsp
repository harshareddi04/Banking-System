<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-card">
        <div class="brand">Secure Bank</div>
        <h2>Create Account</h2>
        <p class="subtitle">Start your secure banking journey</p>

        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <form action="register" method="post">
            <div class="input-group">
                <label>Full Name</label>
                <input type="text" name="fullName" placeholder="Enter full name" value="<%= request.getAttribute("fullName") != null ? request.getAttribute("fullName") : "" %>" required>
            </div>

            <div class="input-group">
                <label>Email or Phone</label>
                <input type="text" name="contact" placeholder="Enter email or phone" value="<%= request.getAttribute("contact") != null ? request.getAttribute("contact") : "" %>" required>
            </div>

            <div class="input-group">
                <label>Account Number</label>
                <input type="text" name="accountNumber" placeholder="12 digit account number" value="<%= request.getAttribute("accountNumber") != null ? request.getAttribute("accountNumber") : "" %>" required>
            </div>

            <div class="input-group">
                <label>ATM Card Number</label>
                <input type="text" name="atmCardNumber" placeholder="12 to 19 digit ATM card number" value="<%= request.getAttribute("atmCardNumber") != null ? request.getAttribute("atmCardNumber") : "" %>" required>
            </div>

            <div class="input-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Letters, numbers, special char" required>
            </div>

            <div class="input-group">
                <label>Transaction PIN</label>
                <input type="password" name="pin" placeholder="4 digit PIN" required>
            </div>

            <button type="submit" class="btn primary">Register</button>
        </form>

        <div class="switch-link">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>
</div>
</body>
</html>