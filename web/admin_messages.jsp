<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Messages</title>
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

            .dashboard-container { padding: 40px 5%; max-width: 1000px; margin: 0 auto; }
            .page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 36px; margin-bottom: 40px; border-bottom: 2px solid #eee; padding-bottom: 15px; }

            /* MESSAGE CARDS */
            .message-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; margin-bottom: 25px; }
            .msg-header { background: #f9f9f9; padding: 20px; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; align-items: center; }
            .msg-header h4 { margin: 0; color: #3e2723; font-family: 'Playfair Display', serif; font-size: 20px; }
            .msg-header span { font-size: 12px; color: #888; }
            .msg-body { padding: 25px; }
            .msg-body p { margin: 0; color: #555; line-height: 1.6; }
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
                <a href="admin_users.jsp">Customers</a>
                <a href="admin_messages.jsp" style="color:#ffab91;">Messages</a>
                <a href="shop.jsp" target="_blank">View Site</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="dashboard-container">
            <h2 class="page-title">Contact Messages</h2>

            <%
                Connection con = null;
                try {
                    con = DBConnection.getConnection();
                    String sql = "SELECT * FROM ContactMessages ORDER BY submitted_at DESC";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    boolean hasMsg = false;
                    while(rs.next()) {
                        hasMsg = true;
            %>
                        <div class="message-card">
                            <div class="msg-header">
                                <h4><%= rs.getString("name") %> <small style="font-weight:400; color:#555;">(<%= rs.getString("email") %>)</small></h4>
                                <span><%= rs.getTimestamp("submitted_at") %></span>
                            </div>
                            <div class="msg-body">
                                <p><strong>Subject:</strong> <%= rs.getString("subject") %></p>
                                <hr style="border:none; border-top:1px solid #eee; margin:15px 0;">
                                <p><%= rs.getString("message") %></p>
                            </div>
                        </div>
            <%
                    }
                    
                    if(!hasMsg) {
            %>
                        <div style="text-align:center; padding:50px; background:white; border-radius:10px;">
                            <i class="fas fa-inbox" style="font-size:40px; color:#ddd;"></i>
                            <h3 style="color:#3e2723;">No Messages</h3>
                        </div>
            <%
                    }
                } catch (Exception e) {
                    out.println("<p style='color:red; text-align:center;'>Error loading messages</p>");
                } finally {
                    if (con != null) try { con.close(); } catch (Exception e) {}
                }
            %>
        </div>
        
        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>