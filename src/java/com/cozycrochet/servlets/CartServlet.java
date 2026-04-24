package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import com.cozycrochet.models.CartItem;
import com.cozycrochet.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        // 1. REMOVE ITEM
        if ("remove".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart != null) {
                cart.removeIf(item -> item.getId() == pid);
                session.setAttribute("cart", cart);
                updateDatabaseCart(user.getId(), cart);
            }
            response.sendRedirect("cart.jsp");
        } 
        
        // 2. UPDATE QUANTITY
        else if ("update".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int qty = Integer.parseInt(request.getParameter("qty"));
            
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart != null) {
                try (Connection con = DBConnection.getConnection()) {
                    String sql = "SELECT stock FROM Products WHERE product_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, pid);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int stock = rs.getInt("stock");
                        if (qty > stock) {
                            session.setAttribute("toast_msg", "Only " + stock + " items in stock!");
                            session.setAttribute("toast_type", "error");
                            qty = stock;
                        }
                    }
                } catch (Exception e) { e.printStackTrace(); }
                
                for (CartItem item : cart) {
                    if (item.getId() == pid) {
                        if (qty > 0) { item.setQuantity(qty); } 
                        else { cart.remove(item); }
                        break;
                    }
                }
                session.setAttribute("cart", cart);
                updateDatabaseCart(user.getId(), cart);
            }
            response.sendRedirect("cart.jsp");
        }
        
        // 3. CHECKOUT VALIDATION
        else if ("checkout".equals(action)) {
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) { response.sendRedirect("cart.jsp"); return; }

            Connection con = null;
            try {
                con = DBConnection.getConnection();
                for (CartItem item : cart) {
                    String sql = "SELECT stock, name FROM Products WHERE product_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, item.getId());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        if (rs.getInt("stock") < item.getQuantity()) {
                            session.setAttribute("toast_msg", "Sorry, '" + rs.getString("name") + "' is out of stock!");
                            session.setAttribute("toast_type", "error");
                            response.sendRedirect("cart.jsp");
                            return;
                        }
                    }
                }
                response.sendRedirect("checkout.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("cart.jsp");
            } finally {
                if (con != null) try { con.close(); } catch (Exception e) {}
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        // 1. ADD ITEM
        if ("add".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int qty = 1;
            try { qty = Integer.parseInt(request.getParameter("qty")); } catch (Exception e) { qty = 1; }
            
            // FIX: Read customization from request
            String customization = request.getParameter("customization");
            if (customization == null) { customization = ""; }
            
            String buyNow = request.getParameter("buyNow");
            
            Connection con = null;
            try {
                con = DBConnection.getConnection();
                String checkSql = "SELECT stock, name, price, image_url FROM Products WHERE product_id = ?";
                PreparedStatement checkPs = con.prepareStatement(checkSql);
                checkPs.setInt(1, pid);
                ResultSet rs = checkPs.executeQuery();
                
                if (rs.next()) {
                    int currentStock = rs.getInt("stock");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image_url");

                    if (currentStock <= 0) {
                        session.setAttribute("toast_msg", "Sorry, '" + name + "' is out of stock!");
                        session.setAttribute("toast_type", "error");
                        response.sendRedirect("shop.jsp");
                        return;
                    }
                    if (qty > currentStock) {
                        session.setAttribute("toast_msg", "Only " + currentStock + " items available for " + name);
                        session.setAttribute("toast_type", "error");
                        response.sendRedirect("shop.jsp");
                        return;
                    }

                    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
                    if (cart == null) { cart = new ArrayList<CartItem>(); }
                    
                    boolean found = false;
                    for (CartItem item : cart) {
                        if (item.getId() == pid) {
                            if (item.getQuantity() + qty > currentStock) {
                                session.setAttribute("toast_msg", "Cannot add more. Only " + currentStock + " left!");
                                session.setAttribute("toast_type", "error");
                                response.sendRedirect("cart.jsp");
                                return;
                            }
                            item.setQuantity(item.getQuantity() + qty);
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        // FIX: Pass customization to the new object
                        cart.add(new CartItem(pid, name, price, qty, customization, image));
                    }
                    
                    session.setAttribute("cart", cart);
                    updateDatabaseCart(user.getId(), cart);
                    
                    if ("true".equals(buyNow)) { response.sendRedirect("checkout.jsp"); } 
                    else { response.sendRedirect("cart.jsp"); }
                    
                } else { response.sendRedirect("shop.jsp"); }
                
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("shop.jsp");
            } finally {
                if (con != null) try { con.close(); } catch (Exception e) {}
            }
        }
    }

    private void updateDatabaseCart(int userId, List<CartItem> cart) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            String delSql = "DELETE FROM Cart WHERE user_id = ?";
            PreparedStatement psDel = con.prepareStatement(delSql);
            psDel.setInt(1, userId);
            psDel.executeUpdate();
            
            String insSql = "INSERT INTO Cart (user_id, product_id, quantity, customization) VALUES (?, ?, ?, ?)";
            for (CartItem item : cart) {
                PreparedStatement psIns = con.prepareStatement(insSql);
                psIns.setInt(1, userId);
                psIns.setInt(2, item.getId());
                psIns.setInt(3, item.getQuantity());
                psIns.setString(4, item.getCustomization());
                psIns.executeUpdate();
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try { if(con != null) con.rollback(); } catch (Exception ex) {}
        } finally {
            try { if(con != null) con.close(); } catch (Exception e) {}
        }
    }
}