package com.bank.servlet;

import com.bank.connection.DBConnection;
import com.bank.util.HashUtil;
import com.bank.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String contact = request.getParameter("contact");
        String accountNumber = request.getParameter("accountNumber");
        String atmCardNumber = request.getParameter("atmCardNumber");
        String password = request.getParameter("password");
        String pin = request.getParameter("pin");

        request.setAttribute("fullName", fullName);
        request.setAttribute("contact", contact);
        request.setAttribute("accountNumber", accountNumber);
        request.setAttribute("atmCardNumber", atmCardNumber);

        if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Full name is required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidContact(contact)) {
            request.setAttribute("errorMessage", "Enter a valid email or 10-digit phone number.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidAccountNumber(accountNumber)) {
            request.setAttribute("errorMessage", "Account number must be exactly 12 digits.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidAtmCardNumber(atmCardNumber)) {
            request.setAttribute("errorMessage", "ATM card number must be 12 to 19 digits.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidPassword(password)) {
            request.setAttribute("errorMessage",
                    "Password must contain letters, numbers, special character and minimum 6 characters.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!ValidationUtil.isValidPin(pin)) {
            request.setAttribute("errorMessage", "PIN must be exactly 4 digits.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            String checkSql = "SELECT * FROM users WHERE contact=? OR account_number=? OR atm_card_number=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, contact);
            checkPs.setString(2, accountNumber);
            checkPs.setString(3, atmCardNumber);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                request.setAttribute("errorMessage",
                        "User already exists with this contact, account number, or ATM card number.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            String hashedPassword = HashUtil.sha256(password);
            String hashedPin = HashUtil.sha256(pin);

            String insertSql = "INSERT INTO users(full_name, contact, account_number, atm_card_number, password, pin, balance) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertSql);
            ps.setString(1, fullName);
            ps.setString(2, contact);
            ps.setString(3, accountNumber);
            ps.setString(4, atmCardNumber);
            ps.setString(5, hashedPassword);
            ps.setString(6, hashedPin);
            ps.setDouble(7, 0.00);

            int row = ps.executeUpdate();

            if (row > 0) {
                request.setAttribute("successMessage", "Registration successful. Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}