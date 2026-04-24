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

@WebServlet("/WishlistServlet")
public class WishlistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }
        
        // 1. REMOVE FROM WISHLIST (Handles Links)
        if ("remove".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM Wishlist WHERE user_id = ? AND product_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ps.setInt(2, pid);
                ps.executeUpdate();
            } catch (Exception e) { e.printStackTrace(); }
            response.sendRedirect("wishlist.jsp");
        } 
        
        // 2. ADD TO WISHLIST (From Shop Page Link)
        else if ("add".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            try (Connection con = DBConnection.getConnection()) {
                // Check if exists
                String checkSql = "SELECT * FROM Wishlist WHERE user_id = ? AND product_id = ?";
                PreparedStatement checkPs = con.prepareStatement(checkSql);
                checkPs.setInt(1, user.getId());
                checkPs.setInt(2, pid);
                ResultSet rsCheck = checkPs.executeQuery();
                
                if (rsCheck.next()) {
                    // Already in wishlist
                } else {
                    String sql = "INSERT INTO Wishlist (user_id, product_id) VALUES (?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ps.setInt(2, pid);
                    ps.executeUpdate();
                }
            } catch (Exception e) { e.printStackTrace(); }
            
            String referer = request.getHeader("referer");
            if (referer != null && referer.contains("wishlist.jsp")) {
                response.sendRedirect("wishlist.jsp");
            } else {
                response.sendRedirect("shop.jsp"); 
            }
        }
        
        // 3. MOVE TO CART (From Wishlist Button)
        else if ("move".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            
            // A. Remove from Wishlist DB
            try (Connection con = DBConnection.getConnection()) {
                String delSql = "DELETE FROM Wishlist WHERE user_id = ? AND product_id = ?";
                PreparedStatement psDel = con.prepareStatement(delSql);
                psDel.setInt(1, user.getId());
                psDel.setInt(2, pid);
                psDel.executeUpdate();
            } catch (Exception e) { e.printStackTrace(); }
            
            // B. Add to Cart Session
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null) { cart = new ArrayList<CartItem>(); }
            
            try (Connection con = DBConnection.getConnection()) {
                String prodSql = "SELECT stock, name, price, image_url FROM Products WHERE product_id = ?";
                PreparedStatement psProd = con.prepareStatement(prodSql);
                psProd.setInt(1, pid);
                ResultSet rsProd = psProd.executeQuery();
                
                if (rsProd.next()) {
                    int stock = rsProd.getInt("stock");
                    String name = rsProd.getString("name");
                    double price = rsProd.getDouble("price");
                    String image = rsProd.getString("image_url");
                    
                    if (stock > 0) {
                        boolean found = false;
                        for (CartItem item : cart) {
                            if (item.getId() == pid) {
                                item.setQuantity(item.getQuantity() + 1);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            cart.add(new CartItem(pid, name, price, 1, "", image));
                        }
                        session.setAttribute("cart", cart);
                        updateDatabaseCart(user.getId(), cart);
                    } else {
                        session.setAttribute("toast_msg", "Item is out of stock!");
                        session.setAttribute("toast_type", "error");
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
            
            response.sendRedirect("cart.jsp");
        }
    }

    // Handles POST Requests (From Forms)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("login.jsp"); return; }

        // 1. REMOVE FROM WISHLIST (From Form Button)
        if ("remove".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            try (Connection con = DBConnection.getConnection()) {
                String sql = "DELETE FROM Wishlist WHERE user_id = ? AND product_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ps.setInt(2, pid);
                ps.executeUpdate();
            } catch (Exception e) { e.printStackTrace(); }
            response.sendRedirect("wishlist.jsp");
        }
    }

    // Helper method to sync DB Cart
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