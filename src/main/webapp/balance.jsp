<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance Enquiry | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-page">
    <div class="form-card">
        <div class="header-row">
            <h2>Balance Enquiry</h2>
            <a href="dashboard" class="btn ghost small">Dashboard</a>
        </div>

        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="alert success"><%= request.getAttribute("successMessage") %></div>
        <% } %>

        <form action="balance" method="post">
            <div class="input-group">
                <label>Transaction PIN</label>
                <input type="password" name="pin" placeholder="Enter 4 digit PIN" required>
            </div>

            <button type="submit" class="btn primary">Check Balance</button>
        </form>

        <% if(request.getAttribute("balanceValue") != null) { %>
            <div class="result-card">
                <div class="result-title">Current Balance</div>
                <div class="amount">₹ <%= request.getAttribute("balanceValue") %></div>
            </div>
        <% } %>
    </div>
</div>
</body>
</html>