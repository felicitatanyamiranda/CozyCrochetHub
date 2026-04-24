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

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get all parameters from the form
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int catId = Integer.parseInt(request.getParameter("category"));

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            
            // 2. Insert Query
            String sql = "INSERT INTO Products (name, description, price, image_url, category_id, stock) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setDouble(3, price);
            ps.setString(4, image);
            ps.setInt(5, catId);
            ps.setInt(6, stock);
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                // Success - Go back to Products List
                response.sendRedirect("admin_products.jsp");
            } else {
                // Failure - Go back to Add Form
                response.sendRedirect("add_product.jsp?error=Failed to save product");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("add_product.jsp?error=" + e.getMessage());
        } finally {
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}