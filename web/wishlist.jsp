<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Wishlist</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }
            
            /* --- NAVBAR --- */
            .nav-solid {
                background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            }
            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }
            .nav-links { display: flex; align-items: center; gap: 30px; }
            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; font-weight: 500; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; }

            /* --- HEADER --- */
            .page-header {
                background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white;
                margin-bottom: 60px;
            }
            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }
            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; letter-spacing: 1px; text-transform: uppercase; }

            /* --- CONTAINER --- */
            .main-wrapper { max-width: 1200px; margin: 0 auto; padding: 0 20px 80px; }

            /* --- WISHLIST GRID --- */
            .wishlist-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 30px; }

            .wishlist-card {
                background: white; border-radius: 20px; overflow: hidden;
                box-shadow: 0 10px 25px rgba(0,0,0,0.05); transition: transform 0.3s;
                position: relative; border: 1px solid rgba(0,0,0,0.02);
            }
            .wishlist-card:hover { transform: translateY(-5px); }
            
            /* Image Area */
            .card-img-wrapper { height: 260px; background: #f9f9f9; position: relative; overflow: hidden; }
            .card-img-wrapper img { width: 100%; height: 100%; object-fit: cover; transition: opacity 0.3s; }
            
            /* Out of Stock Overlay */
            .out-ofStock-img { opacity: 0.4; }
            .stock-tag {
                position: absolute; top: 15px; left: 15px; padding: 6px 15px;
                border-radius: 20px; font-size: 11px; font-weight: bold; text-transform: uppercase; letter-spacing: 0.5px;
            }
            .tag-instock { background: #e8f5e9; color: #2e7d32; }
            .tag-outofstock { background: #ffebee; color: #c62828; }

            /* Remove Button (Top Right - Moved Closer to Corner) */
            .btn-remove {
                position: absolute; top: 10px; right: 10px; background: white;
                width: 40px; height: 40px; border-radius: 50%; display: flex;
                align-items: center; justify-content: center; color: #e74c3c;
                text-decoration: none; box-shadow: 0 5px 15px rgba(0,0,0,0.15);
                transition: 0.3s; z-index: 10; border: none; cursor: pointer;
            }
            .btn-remove:hover { background: #e74c3c; color: white; transform: scale(1.1); }

            /* Content Area */
            .card-content { padding: 20px; text-align: center; }
            .card-title { font-family: 'Playfair Display', serif; font-size: 20px; color: #3e2723; margin-bottom: 8px; }
            .card-price { font-size: 18px; color: #c0392b; font-weight: bold; margin-bottom: 15px; display: block; }

            /* Action Buttons */
            .action-btn {
                display: block; width: 100%; padding: 12px; border-radius: 30px;
                text-decoration: none; font-weight: 600; font-size: 14px;
                transition: 0.3s; margin-bottom: 10px; border: none; cursor: pointer;
            }
            .btn-add { background: #3e2723; color: white; }
            .btn-add:hover { background: #5d4037; }
            .btn-disabled { background: #ccc; color: #666; cursor: not-allowed; }

            /* Empty State */
            .empty-box { text-align: center; padding: 80px 20px; background: white; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); }
            .empty-box i { font-size: 60px; color: #ddd; margin-bottom: 20px; }
        </style>
    </head>
    <body>
        <!-- NAVBAR (No Symbols) -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="wishlist.jsp" style="color:#ffab91;">Wishlist</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_account.jsp">Account</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- HEADER -->
        <div class="page-header">
            <h2>My Wishlist</h2>
            <p>YOUR SAVED FAVORITES</p>
        </div>

        <!-- CONTENT -->
        <div class="main-wrapper">
            <%
                User user = (User) session.getAttribute("user");
                if (user == null) { response.sendRedirect("login.jsp"); return; }

                try {
                    Connection con = DBConnection.getConnection();
                    String sql = "SELECT p.* FROM Products p JOIN Wishlist w ON p.product_id = w.product_id WHERE w.user_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ResultSet rs = ps.executeQuery();
                    
                    boolean hasItems = false;
            %>
                    <div class="wishlist-grid">
                        <%
                            while(rs.next()) {
                                hasItems = true;
                                int pid = rs.getInt("product_id");
                                int stock = rs.getInt("stock");
                                boolean inStock = stock > 0;
                        %>
                                <div class="wishlist-card">
                                    <!-- Remove Button -->
                                    <form action="WishlistServlet" method="post" style="position:absolute; top:0; right:0; margin:0; z-index:20;">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="pid" value="<%= pid %>">
                                        <button type="submit" class="btn-remove" onclick="return confirm('Remove from wishlist?')">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </form>

                                    <div class="card-img-wrapper">
                                        <!-- Stock Tag -->
                                        <span class="stock-tag <%= inStock ? "tag-instock" : "tag-outofstock" %>">
                                            <%= inStock ? "In Stock" : "Out of Stock" %>
                                        </span>
                                        
                                        <img src="<%= rs.getString("image_url") %>" alt="Product" class="<%= inStock ? "" : "outOfStock-img" %>">
                                    </div>
                                    
                                    <div class="card-content">
                                        <div class="card-title"><%= rs.getString("name") %></div>
                                        <span class="card-price">₹<%= rs.getDouble("price") %></span>
                                        
                                        <% if(inStock) { %>
                                            <!-- Move to Cart -->
                                            <a href="WishlistServlet?action=move&pid=<%= pid %>" class="action-btn btn-add">
    Move to Cart
                                                <input type="hidden" name="action" value="add">
                                                <input type="hidden" name="pid" value="<%= pid %>">
                                                
                                                </button>
                                            </a>
                                        <% } else { %>
                                            <button class="action-btn btn-disabled" disabled>
                                                Unavailable
                                            </button>
                                        <% } %>
                                    </div>
                                </div>
                        <%
                            }
                        %>
                    </div>
            <%
                    if(!hasItems) {
            %>
                        <div class="empty-box">
                            <i class="fas fa-heart-broken"></i>
                            <h3 style="font-family:'Playfair Display'; color:#3e2723;">Your wishlist is empty</h3>
                            <p style="color:#777; margin-bottom:30px;">Save items you love by clicking the heart icon on products.</p>
                            <a href="shop.jsp" class="action-btn btn-add" style="display:inline-block; width:auto; padding:15px 40px;">Start Shopping</a>
                        </div>
            <%
                    }
                    
                    con.close();
                } catch (Exception e) {
                    out.println("<p style='color:red; text-align:center;'>Error: " + e.getMessage() + "</p>");
                }
            %>
        </div>

         <!-- Insert Premium Footer Here -->
        <%@include file="footer.jsp" %>
    </body>
</html>