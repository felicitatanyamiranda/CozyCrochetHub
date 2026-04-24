package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import com.cozycrochet.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        String currentPass = request.getParameter("current");
        String newPass = request.getParameter("new");
        String confirmPass = request.getParameter("confirm");

        // 1. Check if new passwords match
        if (!newPass.equals(confirmPass)) {
            response.sendRedirect("change_password.jsp?error=New passwords do not match.");
            return;
        }

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            
            // 2. Check if current password is correct
            String checkSql = "SELECT * FROM Users WHERE user_id = ? AND password = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, user.getId());
            checkPs.setString(2, currentPass);
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next()) {
                // 3. Update password
                String updateSql = "UPDATE Users SET password = ? WHERE user_id = ?";
                PreparedStatement updatePs = con.prepareStatement(updateSql);
                updatePs.setString(1, newPass);
                updatePs.setInt(2, user.getId());
                updatePs.executeUpdate();
                
                response.sendRedirect("change_password.jsp?msg=Password updated successfully!");
            } else {
                response.sendRedirect("change_password.jsp?error=Current password is incorrect.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("change_password.jsp?error=Error updating password.");
        } finally {
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}