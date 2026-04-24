package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        // --- UPDATE PRODUCT ---
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            double price = Double.parseDouble(request.getParameter("price"));
            String image = request.getParameter("image");
            int stock = Integer.parseInt(request.getParameter("stock"));
            int catId = Integer.parseInt(request.getParameter("category"));

            Connection con = null;
            try {
                con = DBConnection.getConnection();
                
                // CORRECTED SQL: Includes category_id
                String sql = "UPDATE Products SET name=?, description=?, price=?, image_url=?, stock=?, category_id=? WHERE product_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                
                ps.setString(1, name);
                ps.setString(2, desc);
                ps.setDouble(3, price);
                ps.setString(4, image);
                ps.setInt(5, stock);
                ps.setInt(6, catId);
                ps.setInt(7, id);
                
                int rows = ps.executeUpdate();
                
                if(rows > 0) {
                    response.sendRedirect("admin_products.jsp"); // Go to list
                } else {
                    response.sendRedirect("edit_product.jsp?id=" + id + "&error=Update Failed");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("admin_products.jsp?error=Error: " + e.getMessage());
            } finally {
                if (con != null) try { con.close(); } catch (Exception e) {}
            }
        }
    }
}