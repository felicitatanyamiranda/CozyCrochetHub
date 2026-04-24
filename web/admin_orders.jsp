<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Orders</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }
            
            /* NAVBAR */
            .nav-solid { background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }
            .nav-links { display: flex; align-items: center; gap: 30px; }
            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; }

            /* HEADER */
            .page-header { background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white; margin-bottom: 60px; }
            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }
            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }

            /* CONTAINER */
            .admin-container { max-width: 1400px; margin: 0 auto 80px; padding: 0 20px; }

            /* TABLE CARD */
            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; }
            .card-header { background: #f9f9f9; padding: 25px; border-bottom: 1px solid #eee; }
            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 24px; }

            /* TABLE STYLING */
            .admin-table { width: 100%; border-collapse: collapse; min-width: 900px; }
            .admin-table th { background: #3e2723; color: white; padding: 18px 20px; text-align: left; font-weight: 600; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; }
            .admin-table td { padding: 20px; border-bottom: 1px solid #eee; color: #555; vertical-align: top; } /* vertical-align top for multi-line items */
            .admin-table tr:last-child td { border-bottom: none; }
            .admin-table tr:hover { background: #fffbf5; }
            
            /* BADGES */
            .badge { padding: 6px 14px; border-radius: 30px; font-size: 12px; font-weight: 700; text-transform: uppercase; }
            .badge-pending { background: #fff3cd; color: #856404; }
            .badge-shipped { background: #cce5ff; color: #004085; }
            .badge-delivered { background: #d4edda; color: #155724; }
            .badge-cancelled { background: #f8d7da; color: #721c24; }

            /* ITEMS LIST STYLE */
            .items-list { list-style: none; padding: 0; margin: 0; }
            .items-list li { margin-bottom: 8px; font-size: 14px; display: flex; flex-direction: column; gap: 2px; }
            .items-list li strong { color: #333; }
            .custom-note { 
                font-size: 12px; color: #888; background: #fffbf5; padding: 4px 8px; border-radius: 4px; 
                border-left: 2px solid #ffab91; margin-top: 2px; display: inline-block; width: fit-content;
            }

            /* FORM & BUTTONS */
            .status-form { display: flex; align-items: center; gap: 10px; }
            .form-select { padding: 8px 12px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; background: white; min-width: 120px; }
            .btn-update { background: #3e2723; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-size: 14px; transition: 0.3s; }
            .btn-update:hover { background: #5d4037; }

            /* Responsive */
            .table-responsive { overflow-x: auto; }
        </style>
    </head>
    <body>
        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="admin.jsp">Dashboard</a>
                <a href="admin_orders.jsp" style="color:#ffab91;">Orders</a>
                <a href="admin_analytics.jsp">Analytics</a>
                <a href="admin_users.jsp">Customers</a>
                <a href="admin_messages.jsp">Messages</a>
                <a href="shop.jsp" target="_blank">View Site</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- HEADER -->
        <div class="page-header">
            <h2>Manage Orders</h2>
            <p>Update order status and track deliveries</p>
        </div>

        <!-- CONTENT -->
        <div class="admin-container">
            <div class="data-card">
                <div class="card-header">
                    <h3>Recent Orders</h3>
                </div>
                
                <div class="table-responsive">
                    <table class="admin-table">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Items</th> <!-- NEW COLUMN -->
                                <th>Total</th>
                                <th>Payment</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Connection con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    String sql = "SELECT o.*, u.full_name, u.email FROM Orders o JOIN Users u ON o.user_id = u.user_id ORDER BY o.order_id DESC";
                                    PreparedStatement ps = con.prepareStatement(sql);
                                    ResultSet rs = ps.executeQuery();
                                    
                                    while(rs.next()) {
                                        int oid = rs.getInt("order_id");
                                        String status = rs.getString("status");
                                        String statusClass = "badge-pending";
                                        if("Shipped".equals(status)) statusClass = "badge-shipped";
                                        if("Delivered".equals(status)) statusClass = "badge-delivered";
                                        if("Cancelled".equals(status)) statusClass = "badge-cancelled";
                            %>
                                        <tr>
                                            <td>#<%= oid %></td>
                                            <td>
                                                <strong><%= rs.getString("full_name") %></strong><br>
                                                <small style="color:#888;"><%= rs.getString("email") %></small>
                                            </td>
                                            
                                            <!-- NEW: ITEMS COLUMN -->
                                            <td>
                                                <ul class="items-list">
                                                    <%
                                                        // Fetch items for this specific order
                                                        String itemSql = "SELECT oi.quantity, oi.customization, p.name FROM Order_Items oi JOIN Products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
                                                        PreparedStatement psItem = con.prepareStatement(itemSql);
                                                        psItem.setInt(1, oid);
                                                        ResultSet rsItem = psItem.executeQuery();
                                                        
                                                        while(rsItem.next()) {
                                                            String itemName = rsItem.getString("name");
                                                            int qty = rsItem.getInt("quantity");
                                                            String customMsg = rsItem.getString("customization");
                                                    %>
                                                            <li>
                                                                <strong><%= itemName %></strong> x <%= qty %>
                                                                <% if(customMsg != null && !customMsg.isEmpty()) { %>
                                                                    <span class="custom-note">
                                                                        <i class="fas fa-comment-alt"></i> <%= customMsg %>
                                                                    </span>
                                                                <% } %>
                                                            </li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </td>

                                            <td>₹<%= rs.getDouble("total_amount") %></td>
                                            <td><%= rs.getString("payment_method") %></td>
                                            <td><span class="badge <%= statusClass %>"><%= status %></span></td>
                                            <td>
                                                <!-- LOGIC: HIDE BUTTON IF CANCELLED OR DELIVERED -->
                                                <% if("Cancelled".equals(status) || "Delivered".equals(status)) { %>
                                                    <span style="color:#999; font-size:13px; font-style:italic;">Completed</span>
                                                <% } else { %>
                                                    <form action="UpdateStatusServlet" method="post" class="status-form">
                                                        <input type="hidden" name="oid" value="<%= oid %>">
                                                        <select name="status" class="form-select">
                                                            <option value="Pending" <%= "Pending".equals(status) ? "selected" : "" %>>Pending</option>
                                                            <option value="Shipped" <%= "Shipped".equals(status) ? "selected" : "" %>>Shipped</option>
                                                            <option value="Delivered">Delivered</option>
                                                            <option value="Cancelled">Cancel</option>
                                                        </select>
                                                        <button type="submit" class="btn-update">Update</button>
                                                    </form>
                                                <% } %>
                                            </td>
                                        </tr>
                            <%
                                    }
                                    con.close();
                                } catch (Exception e) {
                                    out.println("<tr><td colspan='7' style='text-align:center; color:red;'>Error loading orders</td></tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>