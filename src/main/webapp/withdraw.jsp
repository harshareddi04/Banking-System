<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-page">
    <div class="form-card">
        <div class="header-row">
            <h2>Withdraw Money</h2>
            <a href="dashboard" class="btn ghost small">Dashboard</a>
        </div>

        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="alert success"><%= request.getAttribute("successMessage") %></div>
        <% } %>

        <form action="withdraw" method="post">
            <div class="input-group">
                <label>Amount</label>
                <input type="number" step="0.01" name="amount" placeholder="Enter amount" value="<%= request.getAttribute("amount") != null ? request.getAttribute("amount") : "" %>" required>
            </div>

            <div class="input-group">
                <label>Transaction PIN</label>
                <input type="password" name="pin" placeholder="Enter 4 digit PIN" required>
            </div>

            <button type="submit" class="btn primary">Withdraw Now</button>
        </form>
    </div>
</div>
</body>
</html>