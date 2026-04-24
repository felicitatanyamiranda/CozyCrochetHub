<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
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

            .form-container { max-width: 800px; margin: 60px auto; padding: 0 20px; }
            
            .form-card { background: white; padding: 50px; border-radius: 20px; box-shadow: 0 20px 50px rgba(0,0,0,0.05); }
            .form-header { margin-bottom: 40px; text-align: center; }
            .form-header h2 { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 32px; margin: 0 0 10px; }
            .form-header p { color: #777; margin: 0; }

            .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; }
            .form-group { margin-bottom: 20px; }
            .form-group.full-width { grid-column: 1 / -1; }
            
            label { display: block; margin-bottom: 8px; font-weight: 700; color: #555; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; }
            
            .input-field { width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px; font-size: 15px; transition: all 0.3s; font-family: 'Lato', sans-serif; }
            .input-field:focus { border-color: #3e2723; outline: none; }
            
            select.input-field { appearance: none; background: white; cursor: pointer; }
            textarea.input-field { resize: vertical; min-height: 120px; }
            
            .current-img { text-align: center; margin-bottom: 20px; }
            .current-img img { max-height: 150px; border-radius: 10px; border: 1px solid #eee; }
            
            .btn-submit {
                background: #2980b9; color: white; padding: 16px 40px; border: none;
                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;
                transition: all 0.3s; width: 100%; margin-top: 20px;
            }
            .btn-submit:hover { background: #3498db; transform: translateY(-2px); }

            @media (max-width: 768px) { .form-grid { grid-template-columns: 1fr; } }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
            
            String idStr = request.getParameter("id");
            if (idStr == null) { response.sendRedirect("admin_products.jsp"); return; }
            int pid = Integer.parseInt(idStr);
            
            String name = ""; String desc = ""; String image = ""; 
            double price = 0.0; int stock = 0; int currentCat = 0;
            
            Connection con = null;
            try {
                con = DBConnection.getConnection();
                String sql = "SELECT * FROM Products WHERE product_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, pid);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    name = rs.getString("name");
                    desc = rs.getString("description");
                    image = rs.getString("image_url");
                    price = rs.getDouble("price");
                    stock = rs.getInt("stock");
                    currentCat = rs.getInt("category_id");
                }
            } catch (Exception e) {
                out.println("Error loading product.");
            } finally {
                if(con != null) try { con.close(); } catch(Exception e){}
            }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="admin.jsp">Dashboard</a>
                <a href="admin_products.jsp">Products</a>
                <a href="admin_orders.jsp">Orders</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="form-container">
            <div class="form-card">
                <div class="form-header">
                    <h2>Edit Product</h2>
                    <p>Updating: "<%= name %>"</p>
                </div>

                <div class="current-img">
                    <img src="<%= image %>" alt="Product Image">
                </div>

                <!-- ACTION IS SET TO 'update' -->
                <form action="ProductServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= pid %>">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label>Product Name</label>
                            <input type="text" name="name" class="input-field" value="<%= name %>" required>
                        </div>

                        <div class="form-group">
                            <label>Price (₹)</label>
                            <input type="number" name="price" class="input-field" value="<%= price %>" step="0.01" required>
                        </div>

                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="input-field" required>
                                <option value="">Select Category</option>
                                <%
                                    Connection conCat = null;
                                    try {
                                        conCat = DBConnection.getConnection();
                                        Statement stmt = conCat.createStatement();
                                        ResultSet rsCat = stmt.executeQuery("SELECT * FROM Categories");
                                        while(rsCat.next()) {
                                            int cid = rsCat.getInt("category_id");
                                            String cname = rsCat.getString("category_name");
                                %>
                                            <option value="<%= cid %>" <%= (cid == currentCat) ? "selected" : "" %>>
                                                <%= cname %>
                                            </option>
                                <%
                                        }
                                    } catch (Exception e) { e.printStackTrace(); }
                                    finally { if(conCat != null) try { conCat.close(); } catch(Exception e){} }
                                %>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Stock Quantity</label>
                            <input type="number" name="stock" class="input-field" value="<%= stock %>" min="0" required>
                        </div>

                        <div class="form-group full-width">
                            <label>Image URL</label>
                            <input type="text" name="image" class="input-field" value="<%= image %>" required>
                        </div>

                        <div class="form-group full-width">
                            <label>Description</label>
                            <textarea name="desc" class="input-field" required><%= desc %></textarea>
                        </div>
                    </div>

                    <button type="submit" class="btn-submit">
                        <i class="fas fa-save"></i> Save Changes
                    </button>
                </form>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>