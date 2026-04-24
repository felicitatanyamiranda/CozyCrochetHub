<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
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
                    <a href="my_orders.jsp"><i class="fas fa-box"></i> My Orders</a>
                    <a href="wishlist.jsp"><i class="fas fa-heart"></i> Wishlist</a>
                    <a href="change_password.jsp" class="active"><i class="fas fa-lock"></i> Change Password</a>
                </nav>
            </aside>

            <!-- MAIN CONTENT -->
            <main class="main-dashboard">
                <div class="dashboard-header">
                    <h2>Change Password</h2>
                    <p>Update your security credentials</p>
                </div>

                <div class="form-card-dashboard">
                    <%
                        String msg = request.getParameter("msg");
                        if(msg != null) { 
                    %>
                            <div class="alert-success"><%= msg %></div>
                    <%
                        }
                        String error = request.getParameter("error");
                        if(error != null) { 
                    %>
                            <div class="alert-success" style="background:#ffebee; color:#c62828; border-color:#c62828;"><%= error %></div>
                    <%
                        }
                    %>

                    <form action="ChangePasswordServlet" method="post">
                        <div class="form-group">
                            <label>Current Password</label>
                            <input type="password" name="current" class="input-field" placeholder="Enter current password" required>
                        </div>

                        <div class="form-group">
                            <label>New Password</label>
                            <input type="password" name="new" class="input-field" placeholder="Enter new password" required>
                        </div>

                        <div class="form-group">
                            <label>Confirm New Password</label>
                            <input type="password" name="confirm" class="input-field" placeholder="Confirm new password" required>
                        </div>
                        
                        <button type="submit" class="btn-save">Update Password</button>
                    </form>
                </div>
            </main>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>