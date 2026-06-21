<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard | Secure Bank</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="page">
    <div class="topbar">
        <div>
            <h1>Secure Bank Dashboard</h1>
            <p class="top-subtitle">Manage your account securely</p>
        </div>
        <a href="logout" class="btn danger small">Logout</a>
    </div>

    <div class="dashboard-grid">
        <div class="info-card highlight">
            <h3>Account Holder</h3>
            <p><strong>Name:</strong> ${fullName}</p>
            <p><strong>Account Number:</strong> ${accountNumber}</p>
            <p><strong>ATM Card Number:</strong> ${atmCardNumber}</p>
        </div>

        <div class="info-card balance-card">
            <h3>Available Balance</h3>
            <div class="amount">₹ ${balance}</div>
            <p class="muted">Updated directly from database</p>
        </div>
    </div>

    <div class="action-grid">
        <a href="deposit" class="action-card">
            <span class="action-title">Deposit</span>
            <span class="action-desc">Add money to your account</span>
        </a>

        <a href="withdraw" class="action-card">
            <span class="action-title">Withdraw</span>
            <span class="action-desc">Withdraw funds securely</span>
        </a>

        <a href="balance" class="action-card">
            <span class="action-title">Balance Enquiry</span>
            <span class="action-desc">Check current balance with PIN</span>
        </a>

        <a href="transactionHistory" class="action-card">
            <span class="action-title">Transaction History</span>
            <span class="action-desc">View complete transaction list</span>
        </a>

        <a href="miniStatement" class="action-card">
            <span class="action-title">Mini Statement</span>
            <span class="action-desc">View latest 5 transactions</span>
        </a>
    </div>
</div>
</body>
</html>