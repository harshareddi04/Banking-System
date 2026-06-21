<%@ page import="java.util.List" %>
<%@ page import="com.bank.model.Transaction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="table-page">
    <div class="table-card">
        <div class="header-row">
            <h2>Transaction History</h2>
            <a href="dashboard" class="btn ghost small">Dashboard</a>
        </div>

        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="alert error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="alert success"><%= request.getAttribute("successMessage") %></div>
        <% } %>

        <form action="transactionHistory" method="post" class="pin-form">
            <div class="input-group">
                <label>Transaction PIN</label>
                <input type="password" name="pin" placeholder="Enter 4 digit PIN" required>
            </div>
            <button type="submit" class="btn primary">View History</button>
        </form>

        <%
            List<Transaction> transactionList = (List<Transaction>) request.getAttribute("transactionList");
            if (transactionList != null) {
        %>
        <div class="table-wrapper">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Balance After</th>
                    <th>Date</th>
                </tr>
                <% for (Transaction t : transactionList) { %>
                <tr>
                    <td><%= t.getTransactionId() %></td>
                    <td><%= t.getTransactionType() %></td>
                    <td>₹ <%= t.getAmount() %></td>
                    <td>₹ <%= t.getBalanceAfterTransaction() %></td>
                    <td><%= t.getTransactionDate() %></td>
                </tr>
                <% } %>
            </table>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>