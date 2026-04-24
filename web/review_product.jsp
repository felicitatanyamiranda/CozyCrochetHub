<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Write Review</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            .review-page-wrapper {
                max-width: 800px;
                margin: 50px auto;
                padding: 0 20px;
            }
            .product-header {
                background: white;
                padding: 30px;
                border-radius: 15px;
                text-align: center;
                margin-bottom: 30px;
                box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            }
            .product-header img {
                width: 120px;
                height: 120px;
                border-radius: 50%;
                object-fit: cover;
                border: 3px solid #3e2723;
                margin-bottom: 15px;
            }
            .reviews-list {
                background: white;
                padding: 30px;
                border-radius: 15px;
                margin-bottom: 30px;
                box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            }
            .review-item {
                border-bottom: 1px solid #eee;
                padding-bottom: 15px;
                margin-bottom: 15px;
            }
            .review-item:last-child { border-bottom: none; margin-bottom: 0; }
            .reviewer-name { font-weight: bold; color: #3e2723; }
            .review-date { font-size: 12px; color: #999; float: right; }
            .review-stars { color: #f39c12; margin-bottom: 5px; }
            .review-text { color: #555; margin-top: 5px; }
            
            .write-review-box {
                background: white;
                padding: 30px;
                border-radius: 15px;
                box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            String pidStr = request.getParameter("pid");
            if (pidStr == null) { response.sendRedirect("my_orders.jsp"); return; }
            
            int pid = Integer.parseInt(pidStr);
            
            String pname = "";
            String pimg = "";
            try {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM Products WHERE product_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, pid);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    pname = rs.getString("name");
                    pimg = rs.getString("image_url");
                }
                con.close();
            } catch(Exception e) {}
        %>

        <div class="header">
            <h1>Cozy Crochet</h1>
            <div class="navbar">
                <a href="shop.jsp">Home</a>
                <a href="my_orders.jsp">My Orders</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </div>

        <div class="review-page-wrapper">
            
            <div class="product-header">
                <img src="<%= pimg %>">
                <h2 style="font-family:'Playfair Display'; color:#3e2723; margin:0;"><%= pname %></h2>
            </div>

            <div class="reviews-list">
                <h3 style="font-family:'Playfair Display'; color:#3e2723; margin-top:0;">What others are saying</h3>
                <%
                    try {
                        Connection conRev = DBConnection.getConnection();
                        String sqlRev = "SELECT r.rating, r.comment, r.review_date, u.full_name " +
                                        "FROM Reviews r " +
                                        "JOIN Users u ON r.user_id = u.user_id " +
                                        "WHERE r.product_id = ? " +
                                        "ORDER BY r.review_date DESC";
                        PreparedStatement psRev = conRev.prepareStatement(sqlRev);
                        psRev.setInt(1, pid);
                        ResultSet rsRev = psRev.executeQuery();
                        
                        boolean hasReviews = false;
                        while(rsRev.next()) {
                            hasReviews = true;
                            int ratingVal = rsRev.getInt("rating");
                %>
                            <div class="review-item">
                                <div>
                                    <span class="reviewer-name"><%= rsRev.getString("full_name") %></span>
                                    <span class="review-date"><%= rsRev.getDate("review_date") %></span>
                                </div>
                                <div class="review-stars">
                                    <% for(int i=1; i<=5; i++) { if(i <= ratingVal) { %> ★ <% } else { %> ☆ <% } } %>
                                </div>
                                <div class="review-text"><%= rsRev.getString("comment") %></div>
                            </div>
                <%
                        }
                        
                        if(!hasReviews) {
                            out.println("<p style='text-align:center; color:#777;'>No reviews yet. Be the first!</p>");
                        }
                        conRev.close();
                    } catch (Exception e) {
                        out.println("<p style='color:red;'>Error loading reviews: " + e.getMessage() + "</p>");
                    }
                %>
            </div>

            <div class="write-review-box">
                <h3 style="font-family:'Playfair Display'; color:#3e2723; margin-top:0;">Write Your Review</h3>
                
                <form action="ReviewServlet" method="post">
                    <input type="hidden" name="pid" value="<%= pid %>">
                    
                    <div class="form-group">
                        <label>Rating:</label>
                        <select name="rating" class="input-field" required>
                            <option value="5">⭐⭐⭐⭐⭐ (Excellent)</option>
                            <option value="4">⭐⭐⭐⭐ (Good)</option>
                            <option value="3">⭐⭐⭐ (Average)</option>
                            <option value="2">⭐⭐ (Poor)</option>
                            <option value="1">⭐ (Terrible)</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Your Review:</label>
                        <textarea name="comment" class="input-field" rows="4" placeholder="Share your thoughts..." required></textarea>
                    </div>

                    <button type="submit" class="btn btn-full" style="background:#3e2723;">Submit Review</button>
                    <a href="my_orders.jsp" style="display:block; text-align:center; margin-top:15px; color:#8d6e63; text-decoration:none;">Cancel</a>
                </form>
            </div>

        </div>
    </body>
</html>