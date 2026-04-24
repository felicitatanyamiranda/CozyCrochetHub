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

@WebServlet("/CancelOrdersServlet")
public class CancelOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int oid = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // 1. Check Payment Method
            String checkSql = "SELECT payment_method FROM Orders WHERE order_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, oid);
            ResultSet rs = checkPs.executeQuery();
            
            String paymentMethod = "";
            if(rs.next()) {
                paymentMethod = rs.getString("payment_method");
            }
            
            // 2. Update Status
            String sql = "UPDATE Orders SET status = 'Cancelled' WHERE order_id = ? AND status = 'Pending'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                
                // 3. RESTORE STOCK
                String itemsSql = "SELECT product_id, quantity FROM Order_Items WHERE order_id = ?";
                PreparedStatement itemsPs = con.prepareStatement(itemsSql);
                itemsPs.setInt(1, oid);
                ResultSet itemsRs = itemsPs.executeQuery();
                
                while(itemsRs.next()) {
                    int pid = itemsRs.getInt("product_id");
                    int qty = itemsRs.getInt("quantity");
                    
                    String updateStock = "UPDATE Products SET stock = stock + ? WHERE product_id = ?";
                    PreparedStatement stockPs = con.prepareStatement(updateStock);
                    stockPs.setInt(1, qty);
                    stockPs.setInt(2, pid);
                    stockPs.executeUpdate();
                }

                // 4. Set Message
                if ("Cash on Delivery".equals(paymentMethod)) {
                    session.setAttribute("toast_msg", "Order Cancelled successfully.");
                } else {
                    session.setAttribute("toast_msg", "Order Cancelled. Refund will be processed in 2-3 working days.");
                }
                session.setAttribute("toast_type", "success");
            } else {
                session.setAttribute("toast_msg", "Cannot cancel (already shipped).");
                session.setAttribute("toast_type", "error");
            }
            
            con.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            session.setAttribute("toast_msg", "Error cancelling order.");
            session.setAttribute("toast_type", "error");
        } finally {
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (Exception e) {}
        }
        
        response.sendRedirect("my_orders.jsp");
    }
}