<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Products</title>
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

            .dashboard-container { padding: 40px 5%; max-width: 1400px; margin: 0 auto; }
            .page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 36px; margin-bottom: 40px; border-bottom: 2px solid #eee; padding-bottom: 15px; display: flex; justify-content: space-between; align-items: center; }
            
            /* BUTTONS */
            .btn-add-new { background: #27ae60; color: white; padding: 12px 30px; border-radius: 30px; text-decoration: none; font-weight: 700; font-size: 14px; transition: 0.3s; }
            .btn-add-new:hover { background: #2ecc71; transform: translateY(-2px); }
            
            .btn-sm { padding: 8px 16px; font-size: 12px; border-radius: 20px; border: none; cursor: pointer; text-decoration: none; color: white; display: inline-block; margin: 2px; }
            .btn-edit { background: #2980b9; }
            .btn-delete { background: #c0392b; }
            
            /* DATA TABLE */
            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; }
            .table-responsive { overflow-x: auto; }
            .admin-table { width: 100%; border-collapse: collapse; min-width: 800px; }
            .admin-table th { background: #3e2723; color: white; padding: 18px 20px; text-align: left; font-weight: 600; font-size: 14px; }
            .admin-table td { padding: 18px 20px; border-bottom: 1px solid #eee; color: #555; vertical-align: middle; }
            .admin-table tr:hover { background: #fffbf5; }
            
            .prod-img { width: 60px; height: 60px; object-fit: cover; border-radius: 8px; border: 1px solid #eee; }
            .stock-low { color: #c0392b; font-weight: 700; }
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
                <a href="admin_products.jsp" style="color:#ffab91;">Products</a>
                <a href="admin_users.jsp">Customers</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="dashboard-container">
            <div class="page-title">
                Manage Products
                <a href="add_product.jsp" class="btn-add-new"><i class="fas fa-plus"></i> Add New Product</a>
            </div>

            <div class="data-card">
                <div class="table-responsive">
                    <table class="admin-table">
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Stock</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Connection con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    String sql = "SELECT p.*, c.category_name FROM Products p JOIN Categories c ON p.category_id = c.category_id ORDER BY p.product_id DESC";
                                    Statement stmt = con.createStatement();
                                    ResultSet rs = stmt.executeQuery(sql);
                                    
                                    while(rs.next()) {
                                        int pid = rs.getInt("product_id");
                                        int stock = rs.getInt("stock");
                            %>
                                        <tr>
                                            <td><img src="<%= rs.getString("image_url") %>" class="prod-img"></td>
                                            <td><strong><%= rs.getString("name") %></strong></td>
                                            <td><%= rs.getString("category_name") %></td>
                                            <td>₹<%= rs.getDouble("price") %></td>
                                            <td class="<%= stock < 5 ? "stock-low" : "" %>">
                                                <%= stock %> <%= stock < 5 ? "(Low)" : "" %>
                                            </td>
                                            <td>
                                                <a href="edit_product.jsp?id=<%= pid %>" class="btn-sm btn-edit"><i class="fas fa-edit"></i> Edit</a>
                                                <a href="DeleteProductServlet?id=<%= pid %>" class="btn-sm btn-delete" onclick="return confirm('Delete this product?')"><i class="fas fa-trash"></i> Delete</a>
                                            </td>
                                        </tr>
                            <%
                                    }
                                } catch (Exception e) {
                                    out.println("<tr><td colspan='6' style='color:red; text-align:center;'>Error loading products</td></tr>");
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