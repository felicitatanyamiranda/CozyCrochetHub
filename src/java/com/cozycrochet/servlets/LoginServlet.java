package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import com.cozycrochet.models.User;
import com.cozycrochet.models.CartItem;
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // --- LOAD CART FROM DATABASE ---
                List<CartItem> cart = new ArrayList<CartItem>();
                try {
                    String cartSql = "SELECT c.*, p.name, p.price, p.image_url FROM Cart c JOIN Products p ON c.product_id = p.product_id WHERE c.user_id = ?";
                    PreparedStatement psCart = con.prepareStatement(cartSql);
                    psCart.setInt(1, user.getId());
                    ResultSet rsCart = psCart.executeQuery();
                    
                    while(rsCart.next()) {
                        CartItem item = new CartItem(
                            rsCart.getInt("product_id"),
                            rsCart.getString("name"),
                            rsCart.getDouble("price"),
                            rsCart.getInt("quantity"),
                            rsCart.getString("customization"),
                            rsCart.getString("image_url")
                        );
                        cart.add(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Handle potential table missing error
                }
                session.setAttribute("cart", cart);
                // -------------------------------
                
                session.setAttribute("toast_msg", "Welcome back, " + user.getFullName() + "!");
                session.setAttribute("toast_type", "success");
                
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin.jsp"); 
                } else {
                    response.sendRedirect("shop.jsp"); 
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid Email or Password");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Database Error");
        }
    }
}