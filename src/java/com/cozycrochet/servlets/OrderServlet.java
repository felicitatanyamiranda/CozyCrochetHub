package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import com.cozycrochet.models.CartItem;
import com.cozycrochet.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp; // IMPORT THIS
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        
        if (user == null || cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        
        double totalAmount = 0.0;
        for (CartItem item : cart) { totalAmount += item.getTotal(); }
        if(totalAmount <= 500) { totalAmount += 50; }
        if("Cash on Delivery".equals(payment)) { totalAmount += 20; }

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // 1. STOCK CHECK
            for (CartItem item : cart) {
                String checkSql = "SELECT stock, name FROM Products WHERE product_id = ?";
                PreparedStatement checkPs = con.prepareStatement(checkSql);
                checkPs.setInt(1, item.getId());
                ResultSet rs = checkPs.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("stock") < item.getQuantity()) {
                        con.rollback();
                        session.setAttribute("toast_msg", "Sorry! '" + rs.getString("name") + "' is out of stock.");
                        session.setAttribute("toast_type", "error");
                        response.sendRedirect("cart.jsp");
                        return;
                    }
                }
            }

            // 2. INSERT ORDER (Fixed Date and Columns)
            // We use Java Timestamp for date to avoid SQL syntax errors
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            
            String orderSql = "INSERT INTO Orders (user_id, total_amount, status, payment_method, phone, address, order_date) VALUES (?, ?, 'Pending', ?, ?, ?, ?)";
            PreparedStatement orderPs = con.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            orderPs.setInt(1, user.getId());
            orderPs.setDouble(2, totalAmount);
            orderPs.setString(3, payment);
            orderPs.setString(4, phone);
            orderPs.setString(5, address);
            orderPs.setTimestamp(6, orderDate); // Set Date via Java
            
            orderPs.executeUpdate();
            
            ResultSet keys = orderPs.getGeneratedKeys();
            int orderId = 0;
            if (keys.next()) { orderId = keys.getInt(1); }

            // 3. INSERT ITEMS & DEDUCT STOCK
            String itemSql = "INSERT INTO Order_Items (order_id, product_id, quantity, price, customization) VALUES (?, ?, ?, ?, ?)";
            String updateStockSql = "UPDATE Products SET stock = stock - ? WHERE product_id = ?";
            
            for (CartItem item : cart) {
                // Insert Item
                PreparedStatement itemPs = con.prepareStatement(itemSql);
                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getId());
                itemPs.setInt(3, item.getQuantity());
                itemPs.setDouble(4, item.getPrice());
                itemPs.setString(5, item.getCustomization());
                itemPs.executeUpdate();

                // Deduct Stock
                PreparedStatement stockPs = con.prepareStatement(updateStockSql);
                stockPs.setInt(1, item.getQuantity());
                stockPs.setInt(2, item.getId());
                stockPs.executeUpdate();
            }

            con.commit();
            
            // 4. Clear Cart
            session.removeAttribute("cart");
            String delCart = "DELETE FROM Cart WHERE user_id = ?";
            PreparedStatement delPs = con.prepareStatement(delCart);
            delPs.setInt(1, user.getId());
            delPs.executeUpdate();

            // SUCCESS: Redirect to Confirmation
            response.sendRedirect("confirmation.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            
            // DEBUGGING: Print error to screen so we can see it
            PrintWriter out = response.getWriter();
            out.println("<h1 style='color:red;'>Order Failed</h1>");
            out.println("<p><b>Error Message:</b> " + e.getMessage() + "</p>");
            out.println("<p><b>Please check if columns 'phone', 'address', 'order_date' exist in your 'Orders' table.</b></p>");
            out.println("<a href='checkout.jsp'>Go Back</a>");
        } finally {
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (Exception e) {}
        }
    }
}