package com.bank.servlet;

import com.bank.connection.DBConnection;
import com.bank.model.Transaction;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/transactionHistory")
public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
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

        List<Transaction> transactionList = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String verifySql = "SELECT pin FROM users WHERE id=?";
            PreparedStatement verifyPs = con.prepareStatement(verifySql);
            verifyPs.setInt(1, userId);

            ResultSet verifyRs = verifyPs.executeQuery();

            if (verifyRs.next()) {
                if (!verifyRs.getString("pin").equals(HashUtil.sha256(pin))) {
                    request.setAttribute("errorMessage", "Invalid PIN.");
                    request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("errorMessage", "User not found.");
                request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
                return;
            }

            String sql = "SELECT * FROM transactions WHERE user_id=? ORDER BY transaction_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction txn = new Transaction();
                txn.setTransactionId(rs.getInt("transaction_id"));
                txn.setUserId(rs.getInt("user_id"));
                txn.setTransactionType(rs.getString("transaction_type"));
                txn.setAmount(rs.getDouble("amount"));
                txn.setBalanceAfterTransaction(rs.getDouble("balance_after_transaction"));
                txn.setTransactionDate(rs.getString("transaction_date"));
                transactionList.add(txn);
            }

            request.setAttribute("transactionList", transactionList);
            request.setAttribute("successMessage", "Transaction history loaded successfully.");
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Transaction history error: " + e.getMessage());
            request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
        }
    }
}