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

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int pid = Integer.parseInt(request.getParameter("pid"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        try (Connection con = DBConnection.getConnection()) {
            // Check if user has purchased and received this product
            String checkSql = "SELECT COUNT(*) FROM Orders o " +
                              "JOIN Order_Items oi ON o.order_id = oi.order_id " +
                              "WHERE o.user_id = ? AND oi.product_id = ? AND o.status = 'Delivered'";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, user.getId());
            checkPs.setInt(2, pid);
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                String sql = "INSERT INTO Reviews (product_id, user_id, rating, comment, review_date) VALUES (?, ?, ?, ?, CURRENT_DATE)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, pid);
                ps.setInt(2, user.getId());
                ps.setInt(3, rating);
                ps.setString(4, comment);
                ps.executeUpdate();
                
                session.setAttribute("toast_msg", "Review Submitted Successfully!");
                session.setAttribute("toast_type", "success");
            } else {
                session.setAttribute("toast_msg", "You can only review delivered orders!");
                session.setAttribute("toast_type", "error");
            }
            
            response.sendRedirect("shop.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("shop.jsp?error=Error submitting review");
        }
    }
}