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

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("balance.jsp").forward(request, response);
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

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT pin, balance FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (!rs.getString("pin").equals(HashUtil.sha256(pin))) {
                    request.setAttribute("errorMessage", "Invalid PIN.");
                    request.getRequestDispatcher("balance.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("balanceValue", rs.getDouble("balance"));
                request.setAttribute("successMessage", "Balance fetched successfully.");
                request.getRequestDispatcher("balance.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "User not found.");
                request.getRequestDispatcher("balance.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Balance error: " + e.getMessage());
            request.getRequestDispatcher("balance.jsp").forward(request, response);
        }
    }
}