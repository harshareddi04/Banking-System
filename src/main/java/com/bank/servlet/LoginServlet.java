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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contact = request.getParameter("contact");
        String password = request.getParameter("password");

        request.setAttribute("contact", contact);

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE contact=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, contact);
            ps.setString(2, HashUtil.sha256(password));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }

                HttpSession session = request.getSession(true);
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("fullName", rs.getString("full_name"));
                session.setMaxInactiveInterval(15 * 60);

                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("errorMessage", "Invalid login credentials.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Login error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}