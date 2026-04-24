<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_account.jsp">Account</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- DASHBOARD WRAPPER -->
        <div class="account-wrapper">
            
            <!-- SIDEBAR -->
            <aside class="sidebar-dashboard">
                <div class="sidebar-header">
                    <div class="avatar-circle"><%= user.getFullName().substring(0,1).toUpperCase() %></div>
                    <h3><%= user.getFullName() %></h3>
                    <p><%= user.getEmail() %></p>
                </div>
                
                <nav class="sidebar-nav">
                    <a href="my_account.jsp"><i class="fas fa-user-edit"></i> Edit Profile</a>
                    <a href="my_orders.jsp" class="active"><i class="fas fa-box"></i> My Orders</a>
                    <a href="wishlist.jsp"><i class="fas fa-heart"></i> Wishlist</a>
                    <a href="change_password.jsp"><i class="fas fa-lock"></i> Change Password</a>
                </nav>
            </aside>

            <!-- MAIN CONTENT -->
            <main class="main-dashboard">
                <div class="dashboard-header">
                    <h2>My Orders</h2>
                    <p>Track and manage your orders</p>
                </div>

                <div class="orders-container">
                    <%
                        Connection con = null;
                        try {
                            con = DBConnection.getConnection();
                            String sql = "SELECT * FROM Orders WHERE user_id = ? ORDER BY order_id DESC";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setInt(1, user.getId());
                            ResultSet rs = ps.executeQuery();
                            
                            boolean hasOrders = false;
                            while(rs.next()) {
                                hasOrders = true;
                                int oid = rs.getInt("order_id");
                                String status = rs.getString("status");
                                String date = rs.getString("order_date");
                                double total = rs.getDouble("total_amount");
                                String payment = rs.getString("payment_method");
                                
                                String statusClass = "status-pending";
                                if("Shipped".equals(status)) statusClass = "status-shipped";
                                if("Delivered".equals(status)) statusClass = "status-delivered";
                                if("Cancelled".equals(status)) statusClass = "status-cancelled";
                    %>
                    
                    <!-- ORDER CARD -->
                    <div class="order-card">
                        <div class="order-header">
                            <div class="order-meta">
                                <span class="order-id">#Order <%= oid %></span>
                                <span class="order-date"><i class="far fa-calendar-alt"></i> <%= date %></span>
                            </div>
                            <span class="order-status <%= statusClass %>"><%= status %></span>
                        </div>

                        <div class="order-body">
                            <%
                                PreparedStatement psItem = null;
                                ResultSet rsItem = null;
                                try {
                                    String itemSql = "SELECT oi.*, p.name, p.image_url, p.product_id FROM Order_Items oi JOIN Products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
                                    psItem = con.prepareStatement(itemSql);
                                    psItem.setInt(1, oid);
                                    rsItem = psItem.executeQuery();
                                    while(rsItem.next()) {
                                        int productId = rsItem.getInt("product_id");
                            %>
                                <div class="order-item-row">
                                    <img src="<%= rsItem.getString("image_url") %>" class="order-item-img">
                                    <div class="order-item-details">
                                        <h4><%= rsItem.getString("name") %> <span style="color:#777; font-weight:400; font-size:14px;"> × <%= rsItem.getInt("quantity") %></span></h4>
                                        <p style="margin:0; color:#555; font-size:13px;">Note: <%= rsItem.getString("customization") %></p>
                                        
                                        <!-- REVIEW BUTTON LOGIC -->
                                        <% if("Delivered".equals(status)) { %>
                                            <a href="review_product.jsp?pid=<%= productId %>" class="btn-review-link">
                                                <i class="fas fa-pen"></i> Write Review
                                            </a>
                                        <% } %>
                                        
                                    </div>
                                    <div class="order-item-price">₹<%= rsItem.getDouble("price") * rsItem.getInt("quantity") %></div>
                                </div>
                            <%
                                    }
                                } catch (Exception innerEx) {
                                    innerEx.printStackTrace();
                                } finally {
                                    if (rsItem != null) try { rsItem.close(); } catch (Exception ex) {}
                                    if (psItem != null) try { psItem.close(); } catch (Exception ex) {}
                                }
                            %>
                        </div>

                                              <div class="order-footer">
                            <div class="order-total">
                                Total Paid: <strong>₹<%= total %></strong> <br>
                                <small style="color:#777;"><%= payment %></small>
                            </div>
                            
                            <div class="order-actions">
                                <% if("Pending".equals(status)) { %>
                                    <a href="CancelOrdersServlet?id=<%= oid %>" class="btn-cancel-order" onclick="return confirm('Are you sure?')">
                                        <i class="fas fa-times"></i> Cancel Order
                                    </a>
                                <% } else if("Cancelled".equals(status)) { %>
                                    <span style="color:#721c24; font-size:13px; font-weight:600;">
                                        <i class="fas fa-ban"></i> Cancelled
                                        <% if(!"Cash on Delivery".equals(payment)) { %>
                                            <br><span style="color:#555; font-weight:400; font-size:12px;">Refund initiated (2-3 days)</span>
                                        <% } %>
                                    </span>
                                <% } else if("Delivered".equals(status)) { %>
                                    <span style="color:#155724; font-size:14px;"><i class="fas fa-check-circle"></i> Delivered</span>
                                <% } %>
                            </div>
                        </div>

                    <%
                            }
                            
                            if(!hasOrders) {
                    %>
                        <div class="empty-state">
                            <i class="fas fa-box-open"></i>
                            <h3>No orders yet</h3>
                            <p>Looks like you haven't placed any orders.</p>
                            <a href="shop.jsp" class="btn" style="background:#3e2723; margin-top:20px;">Start Shopping</a>
                        </div>
                    <%
                            }
                            
                            if (rs != null) try { rs.close(); } catch (Exception ex) {}
                            if (ps != null) try { ps.close(); } catch (Exception ex) {}
                            
                        } catch (Exception e) {
                            out.println("<p style='color:red; text-align:center;'>Error loading orders.</p>");
                            e.printStackTrace();
                        } finally {
                            if (con != null) { try { con.close(); } catch (Exception e) {} }
                        }
                    %>
                </div>
            </main>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>