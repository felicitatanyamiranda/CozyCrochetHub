package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class Registerservlet extends HttpServlet {

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // --- SERVER SIDE VALIDATION ---
        
        // 1. Name Validation
        if (name == null || !name.matches("^[a-zA-Z\\s]+$")) {
            response.sendRedirect("register.jsp?error=Name must contain only letters.");
            return;
        }

        // 2. Password Validation
        if (password == null || password.length() < 4) {
            response.sendRedirect("register.jsp?error=Password must be at least 4 characters.");
            return;
        }

        // --- INSERT INTO DATABASE ---
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            
            // Check if email exists
            String checkSql = "SELECT user_id FROM Users WHERE email = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, email);
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next()) {
                response.sendRedirect("register.jsp?error=Email already registered.");
            } else {
                String sql = "INSERT INTO Users (full_name, email, password, role) VALUES (?, ?, ?, 'customer')";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.executeUpdate();
                response.sendRedirect("login.jsp?msg=Registration Successful! Please login.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Database Error");
        } finally {
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}