package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
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

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int oid = Integer.parseInt(request.getParameter("oid"));
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Start Transaction

            // 1. Check Payment Method
            String checkSql = "SELECT payment_method FROM Orders WHERE order_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, oid);
            ResultSet rs = checkPs.executeQuery();
            
            String paymentMethod = "";
            if(rs.next()) {
                paymentMethod = rs.getString("payment_method");
            }

            // 2. Update Order Status
            String sql = "UPDATE Orders SET status = ? WHERE order_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, oid);
            ps.executeUpdate();
            
            // 3. Handle Cancellation Logic (Refund & Stock)
            if ("Cancelled".equals(status)) {
                
                // A. RESTORE STOCK
                // Get items from this order
                String itemsSql = "SELECT product_id, quantity FROM Order_Items WHERE order_id = ?";
                PreparedStatement itemsPs = con.prepareStatement(itemsSql);
                itemsPs.setInt(1, oid);
                ResultSet itemsRs = itemsPs.executeQuery();
                
                while(itemsRs.next()) {
                    int pid = itemsRs.getInt("product_id");
                    int qty = itemsRs.getInt("quantity");
                    
                    // Update Product Stock (Add back)
                    String updateStock = "UPDATE Products SET stock = stock + ? WHERE product_id = ?";
                    PreparedStatement stockPs = con.prepareStatement(updateStock);
                    stockPs.setInt(1, qty);
                    stockPs.setInt(2, pid);
                    stockPs.executeUpdate();
                }

                // B. SET REFUND MESSAGE
                if ("Cash on Delivery".equals(paymentMethod)) {
                    session.setAttribute("toast_msg", "Order #" + oid + " Cancelled. Stock restored.");
                } else {
                    session.setAttribute("toast_msg", "Order #" + oid + " Cancelled. Refund initiated (2-3 days). Stock restored.");
                }
            } else {
                session.setAttribute("toast_msg", "Order #" + oid + " updated to " + status);
            }
            
            con.commit(); // Save changes
            session.setAttribute("toast_type", "success");
            
        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            session.setAttribute("toast_msg", "Error updating status.");
            session.setAttribute("toast_type", "error");
        } finally {
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (Exception e) {}
        }
        
        response.sendRedirect("admin_orders.jsp");
    }
}