package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import com.cozycrochet.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // --- VALIDATION START ---
        
        // 1. Name Validation (Only Letters and Spaces)
        if (name == null || !name.matches("^[a-zA-Z\\s]+$")) {
            response.sendRedirect("my_account.jsp?error=Name must contain only letters.");
            return;
        }

        // 2. Phone Validation (10 Digits)
        if (phone != null && !phone.isEmpty()) {
            if (!phone.matches("\\d{10}")) {
                response.sendRedirect("my_account.jsp?error=Phone must be 10 digits.");
                return;
            }
        }
        
        // --- VALIDATION END ---

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            String sql = "UPDATE Users SET full_name = ?, phone = ?, address = ? WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setInt(4, user.getId());
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                user.setFullName(name);
                user.setPhone(phone);
                user.setAddress(address);
                session.setAttribute("user", user);
                response.sendRedirect("my_account.jsp?msg=Profile updated successfully!");
            } else {
                response.sendRedirect("my_account.jsp?error=No changes made.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("my_account.jsp?error=Database error.");
        } finally {
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}