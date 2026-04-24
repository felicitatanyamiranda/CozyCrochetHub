<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
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

            .dashboard-container { padding: 40px 5%; max-width: 1200px; margin: 0 auto; }
            .page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 36px; margin-bottom: 40px; border-bottom: 2px solid #eee; padding-bottom: 15px; }

            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; }
            .card-header { background: #f9f9f9; padding: 20px 25px; border-bottom: 1px solid #eee; }
            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 22px; }

            .admin-table { width: 100%; border-collapse: collapse; min-width: 600px; }
            .admin-table th { background: #3e2723; color: white; padding: 15px; text-align: left; font-weight: 600; }
            .admin-table td { padding: 15px; border-bottom: 1px solid #eee; color: #555; }
            .admin-table tr:hover { background: #fffbf5; }
            
            .badge { padding: 5px 12px; border-radius: 20px; font-size: 11px; font-weight: 700; text-transform: uppercase; }
            .badge-admin { background: #e8e8e8; color: #333; }
            .badge-customer { background: #e3f2fd; color: #1565c0; }

            .btn-sm { padding: 8px 16px; font-size: 12px; border-radius: 20px; border: none; cursor: pointer; text-decoration: none; color: white; display: inline-block; }
            .btn-delete { background: #c0392b; }
            .table-responsive { overflow-x: auto; }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="admin.jsp">Dashboard</a>
                <a href="admin_orders.jsp">Orders</a>
                <a href="admin_analytics.jsp">Analytics</a>
                <a href="admin_users.jsp" style="color:#ffab91;">Customers</a>
                <a href="admin_messages.jsp">Messages</a>
                <a href="shop.jsp" target="_blank">View Site</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="dashboard-container">
            <h2 class="page-title">Customer Management</h2>

            <div class="data-card">
                <div class="card-header">
                    <h3>Registered Users</h3>
                </div>
                
                <div class="table-responsive">
                    <table class="admin-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Connection con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    String sql = "SELECT * FROM Users ORDER BY user_id DESC";
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    
                                    while (rs.next()) {
                                        int uid = rs.getInt("user_id");
                                        String role = rs.getString("role");
                            %>
                                            <tr>
                                                <td><%= uid %></td>
                                                <td><strong><%= rs.getString("full_name") %></strong></td>
                                                <td><%= rs.getString("email") %></td>
                                                <td><%= rs.getString("phone") != null ? rs.getString("phone") : "N/A" %></td>
                                                <td>
                                                    <span class="badge <%= "admin".equals(role) ? "badge-admin" : "badge-customer" %>">
                                                        <%= role %>
                                                    </span>
                                                </td>
                                                <td>
                                                    <% if(!"admin".equals(role)) { %>
                                                        <a href="DeleteUserServlet?id=<%= uid %>" class="btn-sm btn-delete" onclick="return confirm('Delete user?')">
                                                            <i class="fas fa-trash"></i> Delete
                                                        </a>
                                                    <% } else { %>
                                                        <span style="color:#999; font-size:12px;">Protected</span>
                                                    <% } %>
                                                </td>
                                            </tr>
                            <%
                                        }
                                    } catch (Exception e) {
                                        out.println("<tr><td colspan='6'>Error loading users</td></tr>");
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