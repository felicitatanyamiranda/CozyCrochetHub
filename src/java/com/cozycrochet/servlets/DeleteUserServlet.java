package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Start Transaction

            // 1. Delete Order Items associated with User's Orders
            // We need to find the order_ids first, but we can do a subquery delete
            String delItemsSql = "DELETE FROM Order_Items WHERE order_id IN (SELECT order_id FROM Orders WHERE user_id = ?)";
            PreparedStatement ps1 = con.prepareStatement(delItemsSql);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 2. Delete Orders
            String delOrdersSql = "DELETE FROM Orders WHERE user_id = ?";
            PreparedStatement ps2 = con.prepareStatement(delOrdersSql);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            // 3. Delete Cart
            String delCartSql = "DELETE FROM Cart WHERE user_id = ?";
            PreparedStatement ps3 = con.prepareStatement(delCartSql);
            ps3.setInt(1, id);
            ps3.executeUpdate();

            // 4. Delete Wishlist
            String delWishSql = "DELETE FROM Wishlist WHERE user_id = ?";
            PreparedStatement ps4 = con.prepareStatement(delWishSql);
            ps4.setInt(1, id);
            ps4.executeUpdate();
            
            // 5. Delete Reviews
            String delRevSql = "DELETE FROM Reviews WHERE user_id = ?";
            PreparedStatement ps5 = con.prepareStatement(delRevSql);
            ps5.setInt(1, id);
            ps5.executeUpdate();

            // 6. Finally, Delete User
            String delUserSql = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement ps6 = con.prepareStatement(delUserSql);
            ps6.setInt(1, id);
            int rows = ps6.executeUpdate();

            if (rows > 0) {
                con.commit(); // Commit only if user deleted successfully
            } else {
                con.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        
        response.sendRedirect("admin_users.jsp");
    }
}