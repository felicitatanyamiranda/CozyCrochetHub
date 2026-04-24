<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
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

            /* DASHBOARD LAYOUT */
            .dashboard-container { padding: 40px 5%; max-width: 1400px; margin: 0 auto; }
            .page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 36px; margin-bottom: 40px; border-bottom: 2px solid #eee; padding-bottom: 15px; }

            /* STAT CARDS */
            .stat-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)); gap: 25px; margin-bottom: 50px; }
            .stat-card { background: white; padding: 30px; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); display: flex; align-items: center; gap: 20px; border-left: 5px solid #8d6e63; transition: transform 0.3s; }
            .stat-card:hover { transform: translateY(-5px); }
            .stat-icon { width: 60px; height: 60px; background: #fdfbf7; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 24px; color: #c0392b; }
            .stat-info h3 { margin: 0; font-size: 32px; color: #3e2723; font-family: 'Playfair Display', serif; }
            .stat-info p { margin: 5px 0 0; color: #777; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; }

            /* DATA TABLES */
            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; margin-bottom: 40px; }
            .card-header { background: #f9f9f9; padding: 20px 25px; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; align-items: center; }
            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 22px; }

            .admin-table { width: 100%; border-collapse: collapse; min-width: 600px; }
            .admin-table th { background: #3e2723; color: white; padding: 15px; text-align: left; font-weight: 600; }
            .admin-table td { padding: 15px; border-bottom: 1px solid #eee; color: #555; }
            .admin-table tr:hover { background: #fffbf5; }
            
            /* BADGES */
            .badge { padding: 5px 12px; border-radius: 20px; font-size: 11px; font-weight: 700; text-transform: uppercase; }
            .badge-pending { background: #fff3cd; color: #856404; }
            .badge-shipped { background: #cce5ff; color: #004085; }
            .badge-delivered { background: #d4edda; color: #155724; }
            
            /* BUTTONS */
            .btn-sm { padding: 8px 16px; font-size: 12px; border-radius: 20px; border: none; cursor: pointer; text-decoration: none; color: white; display: inline-block; }
            .btn-view { background: #3e2723; }
            .btn-edit { background: #2980b9; }
            .btn-delete { background: #c0392b; }
            
            .table-responsive { overflow-x: auto; }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
            
            int totalProducts = 0; int totalOrders = 0; double totalRevenue = 0.0;
            
            // Stats Logic
            Connection conStats = null;
            try {
                conStats = DBConnection.getConnection();
                Statement stmt1 = conStats.createStatement();
                ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) FROM Products");
                if(rs1.next()) totalProducts = rs1.getInt(1);
                
                Statement stmt2 = conStats.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT COUNT(*) FROM Orders");
                if(rs2.next()) totalOrders = rs2.getInt(1);
                
                Statement stmt3 = conStats.createStatement();
                ResultSet rs3 = stmt3.executeQuery("SELECT SUM(total_amount) FROM Orders WHERE status = 'Delivered'");
                if(rs3.next()) totalRevenue = rs3.getDouble(1);
            } catch (Exception e) { 
                e.printStackTrace(); 
            } finally {
                if (conStats != null) try { conStats.close(); } catch (Exception e) {}
            }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="admin.jsp" style="color:#ffab91;">Dashboard</a>
                <a href="admin_products.jsp">Products</a> <!-- ADDED PRODUCTS LINK -->
                <a href="admin_orders.jsp">Orders</a>
                <a href="admin_analytics.jsp">Analytics</a>
                <a href="admin_users.jsp">Customers</a>
                <a href="admin_messages.jsp">Messages</a>
                <a href="shop.jsp" target="_blank">View Site</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="dashboard-container">
            <h2 class="page-title">Dashboard Overview</h2>

            <!-- STATS -->
            <div class="stat-grid">
                <div class="stat-card">
                    <div class="stat-icon"><i class="fas fa-box"></i></div>
                    <div class="stat-info"><h3><%= totalProducts %></h3><p>Products</p></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon"><i class="fas fa-shopping-cart"></i></div>
                    <div class="stat-info"><h3><%= totalOrders %></h3><p>Orders</p></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon"><i class="fas fa-rupee-sign"></i></div>
                    <div class="stat-info"><h3><%= String.format("%.2f", totalRevenue) %></h3><p>Revenue</p></div>
                </div>
            </div>

            <!-- RECENT ORDERS -->
            <div class="data-card">
                <div class="card-header">
                    <h3>Recent Orders</h3>
                    <a href="admin_orders.jsp" class="btn-sm btn-view">View All</a>
                </div>
                <div class="table-responsive">
                    <table class="admin-table">
                        <thead><tr><th>ID</th><th>Customer</th><th>Total</th><th>Status</th></tr></thead>
                        <tbody>
                            <%
                                Connection con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    String sql = "SELECT o.*, u.full_name FROM Orders o JOIN Users u ON o.user_id = u.user_id ORDER BY o.order_id DESC FETCH FIRST 5 ROWS ONLY";
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    while(rs.next()) {
                                        String status = rs.getString("status");
                                        String statusClass = "badge-pending";
                                        if("Shipped".equals(status)) statusClass = "badge-shipped";
                                        if("Delivered".equals(status)) statusClass = "badge-delivered";
                            %>
                                        <tr>
                                            <td>#<%= rs.getInt("order_id") %></td>
                                            <td><%= rs.getString("full_name") %></td>
                                            <td>₹<%= rs.getDouble("total_amount") %></td>
                                            <td><span class="badge <%= statusClass %>"><%= status %></span></td>
                                        </tr>
                            <%
                                    }
                                } catch (Exception e) { 
                                    out.println("<tr><td colspan='4'>Error</td></tr>"); 
                                } finally {
                                    if (con != null) try { con.close(); } catch (Exception e) {}
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- LOW STOCK ALERT -->
            <div class="data-card">
                <div class="card-header">
                    <h3>Low Stock Alert</h3>
                    <a href="admin_products.jsp" class="btn-sm btn-view">Manage Products</a>
                </div>
                <div class="table-responsive">
                    <table class="admin-table">
                        <thead><tr><th>Image</th><th>Name</th><th>Stock</th><th>Action</th></tr></thead>
                        <tbody>
                             <%
                                con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    Statement stmt = con.createStatement();
                                    // Show items with stock < 5
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM Products WHERE stock < 5 ORDER BY stock ASC");
                                    while(rs.next()) {
                            %>
                                        <tr>
                                            <td><img src="<%= rs.getString("image_url") %>" style="width:40px; height:40px; border-radius:5px; object-fit:cover;"></td>
                                            <td><%= rs.getString("name") %></td>
                                            <td style="color:#c0392b; font-weight:bold;"><%= rs.getInt("stock") %></td>
                                            <td>
                                                <a href="edit_product.jsp?id=<%= rs.getInt("product_id") %>" class="btn-sm btn-edit">Restock</a>
                                            </td>
                                        </tr>
                            <%
                                    }
                                } catch (Exception e) { 
                                    e.printStackTrace(); 
                                } finally {
                                    if (con != null) try { con.close(); } catch (Exception e) {}
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