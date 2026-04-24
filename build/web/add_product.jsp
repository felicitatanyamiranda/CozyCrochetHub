<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
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
            
            .form-card {
                background: white; padding: 50px; border-radius: 20px;
                box-shadow: 0 20px 50px rgba(0,0,0,0.05);
            }
            .form-header { margin-bottom: 40px; text-align: center; }
            .form-header h2 { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 32px; margin: 0 0 10px; }
            .form-header p { color: #777; margin: 0; }

            .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; }
            .form-group { margin-bottom: 20px; }
            .form-group.full-width { grid-column: 1 / -1; }
            
            label { display: block; margin-bottom: 8px; font-weight: 700; color: #555; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; }
            
            .input-field {
                width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px;
                font-size: 15px; transition: all 0.3s; font-family: 'Lato', sans-serif;
            }
            .input-field:focus { border-color: #3e2723; outline: none; box-shadow: 0 0 0 3px rgba(62,39,35,0.05); }
            
            select.input-field { appearance: none; background: white; cursor: pointer; }
            
            textarea.input-field { resize: vertical; min-height: 120px; }
            
            .btn-submit {
                background: #3e2723; color: white; padding: 16px 40px; border: none;
                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;
                transition: all 0.3s; width: 100%; margin-top: 20px;
            }
            .btn-submit:hover { background: #5d4037; transform: translateY(-2px); }

            @media (max-width: 768px) { .form-grid { grid-template-columns: 1fr; } }
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
                <a href="admin_products.jsp">Products</a>
                <a href="admin_orders.jsp">Orders</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="form-container">
            <div class="form-card">
                <div class="form-header">
                    <h2>Add New Product</h2>
                    <p>Fill in the details below to add a new item to the store.</p>
                </div>

                <form action="AddProductServlet" method="post">
                    <div class="form-grid">
                        <!-- Name -->
                        <div class="form-group">
                            <label>Product Name</label>
                            <input type="text" name="name" class="input-field" placeholder="e.g. Handmade Crochet Bag" required>
                        </div>

                        <!-- Price -->
                        <div class="form-group">
                            <label>Price (₹)</label>
                            <input type="number" name="price" class="input-field" placeholder="e.g. 599" step="0.01" required>
                        </div>

                        <!-- Category -->
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="input-field" required>
                                <option value="">Select Category</option>
                                <%
                                    Connection con = null;
                                    try {
                                        con = DBConnection.getConnection();
                                        Statement stmt = con.createStatement();
                                        ResultSet rs = stmt.executeQuery("SELECT * FROM Categories");
                                        while(rs.next()) {
                                %>
                                            <option value="<%= rs.getInt("category_id") %>"><%= rs.getString("category_name") %></option>
                                <%
                                        }
                                    } catch (Exception e) { e.printStackTrace(); }
                                    finally { if(con != null) try { con.close(); } catch(Exception e){} }
                                %>
                            </select>
                        </div>

                        <!-- Stock -->
                        <div class="form-group">
                            <label>Stock Quantity</label>
                            <input type="number" name="stock" class="input-field" placeholder="e.g. 10" required>
                        </div>

                        <!-- Image URL -->
                        <div class="form-group full-width">
                            <label>Image URL</label>
                            <input type="text" name="image" class="input-field" placeholder="https://example.com/image.jpg" required>
                        </div>

                        <!-- Description -->
                        <div class="form-group full-width">
                            <label>Description</label>
                            <textarea name="desc" class="input-field" placeholder="Write a detailed description..." required></textarea>
                        </div>
                    </div>

                    <button type="submit" class="btn-submit">
                        <i class="fas fa-plus-circle"></i> Add Product
                    </button>
                </form>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>