package com.bank.servlet;

import com.bank.connection.DBConnection;
import com.bank.util.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String pin = request.getParameter("pin");
        String amountStr = request.getParameter("amount");

        request.setAttribute("amount", amountStr);

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Enter a valid amount.");
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
            return;
        }

        if (amount <= 0) {
            request.setAttribute("errorMessage", "Withdraw amount must be greater than zero.");
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String fetchSql = "SELECT pin, balance FROM users WHERE id=?";
            PreparedStatement fetchPs = con.prepareStatement(fetchSql);
            fetchPs.setInt(1, userId);

            ResultSet rs = fetchPs.executeQuery();

            if (rs.next()) {
                String dbPin = rs.getString("pin");
                double currentBalance = rs.getDouble("balance");

                if (!dbPin.equals(HashUtil.sha256(pin))) {
                    request.setAttribute("errorMessage", "Invalid PIN.");
                    request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                    return;
                }

                if (amount > currentBalance) {
                    request.setAttribute("errorMessage", "Insufficient balance.");
                    request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                    return;
                }

                double newBalance = currentBalance - amount;

                String updateSql = "UPDATE users SET balance=? WHERE id=?";
                PreparedStatement updatePs = con.prepareStatement(updateSql);
                updatePs.setDouble(1, newBalance);
                updatePs.setInt(2, userId);
                updatePs.executeUpdate();

                String insertTxn = "INSERT INTO transactions(user_id, transaction_type, amount, balance_after_transaction) VALUES(?,?,?,?)";
                PreparedStatement txnPs = con.prepareStatement(insertTxn);
                txnPs.setInt(1, userId);
                txnPs.setString(2, "WITHDRAW");
                txnPs.setDouble(3, amount);
                txnPs.setDouble(4, newBalance);
                txnPs.executeUpdate();

                request.setAttribute("successMessage", "Withdraw successful. Updated balance: ₹ " + newBalance);
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "User not found.");
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Withdraw error: " + e.getMessage());
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
        }
    }
}