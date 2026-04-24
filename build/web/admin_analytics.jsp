<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Analytics</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

            .chart-card { background: white; padding: 0; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; }
            .card-header { background: #f9f9f9; padding: 20px 25px; border-bottom: 1px solid #eee; }
            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 22px; }

            .chart-container { padding: 30px; position: relative; height: 400px; width: 100%; }
        </style>
    </head>
    <body>
        <%
            if (session.getAttribute("user") == null) response.sendRedirect("login.jsp");
            
            List<String> chartLabels = new ArrayList<String>();
            List<Double> chartData = new ArrayList<Double>();

            Connection con = null;
            try {
                con = DBConnection.getConnection();
                String sql = "SELECT c.category_name, SUM(oi.quantity * oi.price) as total_rev " +
                             "FROM Order_Items oi " +
                             "JOIN Products p ON oi.product_id = p.product_id " +
                             "JOIN Categories c ON p.category_id = c.category_id " +
                             "GROUP BY c.category_name";
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                while(rs.next()) {
                    chartLabels.add(rs.getString("category_name"));
                    chartData.add(rs.getDouble("total_rev"));
                }
            } catch (Exception e) { e.printStackTrace(); }
            finally { if (con != null) try { con.close(); } catch (Exception e) {} }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="admin.jsp">Dashboard</a>
                <a href="admin_orders.jsp">Orders</a>
                <a href="admin_analytics.jsp" style="color:#ffab91;">Analytics</a>
                <a href="admin_users.jsp">Customers</a>
                <a href="admin_messages.jsp">Messages</a>
                <a href="shop.jsp" target="_blank">View Site</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="dashboard-container">
            <h2 class="page-title">Sales Analytics</h2>

            <div class="chart-card">
                <div class="card-header">
                    <h3>Revenue by Category</h3>
                </div>
                
                <div class="chart-container">
                    <canvas id="revenueChart"></canvas>
                </div>
            </div>
        </div>

        <script>
            const ctx = document.getElementById('revenueChart');
            
            <%
                String labelStr = "[";
                for (int i = 0; i < chartLabels.size(); i++) {
                    labelStr += "'" + chartLabels.get(i) + "'";
                    if (i < chartLabels.size() - 1) labelStr += ", ";
                }
                labelStr += "]";
                
                String dataStr = "[";
                for (int i = 0; i < chartData.size(); i++) {
                    dataStr += chartData.get(i);
                    if (i < chartData.size() - 1) dataStr += ", ";
                }
                dataStr += "]";
            %>

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: <%= labelStr %>,
                    datasets: [{
                        label: 'Revenue (₹)',
                        data: <%= dataStr %>,
                        backgroundColor: [
                            'rgba(62, 39, 35, 0.7)',
                            'rgba(141, 110, 99, 0.7)',
                            'rgba(192, 57, 43, 0.7)',
                            'rgba(241, 196, 15, 0.7)'
                        ],
                        borderColor: [
                            'rgba(62, 39, 35, 1)',
                            'rgba(141, 110, 99, 1)',
                            'rgba(192, 57, 43, 1)',
                            'rgba(241, 196, 15, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: { y: { beginAtZero: true } }
                }
            });
        </script>
    </body>
</html>