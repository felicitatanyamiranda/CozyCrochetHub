package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Dashboard</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("        <style>\n");
      out.write("            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }\n");
      out.write("            \n");
      out.write("            /* NAVBAR */\n");
      out.write("            .nav-solid { background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }\n");
      out.write("            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }\n");
      out.write("            .nav-links { display: flex; align-items: center; gap: 30px; }\n");
      out.write("            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; transition: 0.3s; }\n");
      out.write("            .nav-links a:hover { color: #ffab91; }\n");
      out.write("\n");
      out.write("            /* DASHBOARD LAYOUT */\n");
      out.write("            .dashboard-container { padding: 40px 5%; max-width: 1400px; margin: 0 auto; }\n");
      out.write("            .page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 36px; margin-bottom: 40px; border-bottom: 2px solid #eee; padding-bottom: 15px; }\n");
      out.write("\n");
      out.write("            /* STAT CARDS */\n");
      out.write("            .stat-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)); gap: 25px; margin-bottom: 50px; }\n");
      out.write("            .stat-card { background: white; padding: 30px; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); display: flex; align-items: center; gap: 20px; border-left: 5px solid #8d6e63; transition: transform 0.3s; }\n");
      out.write("            .stat-card:hover { transform: translateY(-5px); }\n");
      out.write("            .stat-icon { width: 60px; height: 60px; background: #fdfbf7; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 24px; color: #c0392b; }\n");
      out.write("            .stat-info h3 { margin: 0; font-size: 32px; color: #3e2723; font-family: 'Playfair Display', serif; }\n");
      out.write("            .stat-info p { margin: 5px 0 0; color: #777; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; }\n");
      out.write("\n");
      out.write("            /* DATA TABLES */\n");
      out.write("            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; margin-bottom: 40px; }\n");
      out.write("            .card-header { background: #f9f9f9; padding: 20px 25px; border-bottom: 1px solid #eee; display: flex; justify-content: space-between; align-items: center; }\n");
      out.write("            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 22px; }\n");
      out.write("\n");
      out.write("            .admin-table { width: 100%; border-collapse: collapse; min-width: 600px; }\n");
      out.write("            .admin-table th { background: #3e2723; color: white; padding: 15px; text-align: left; font-weight: 600; }\n");
      out.write("            .admin-table td { padding: 15px; border-bottom: 1px solid #eee; color: #555; }\n");
      out.write("            .admin-table tr:hover { background: #fffbf5; }\n");
      out.write("            \n");
      out.write("            /* BADGES */\n");
      out.write("            .badge { padding: 5px 12px; border-radius: 20px; font-size: 11px; font-weight: 700; text-transform: uppercase; }\n");
      out.write("            .badge-pending { background: #fff3cd; color: #856404; }\n");
      out.write("            .badge-shipped { background: #cce5ff; color: #004085; }\n");
      out.write("            .badge-delivered { background: #d4edda; color: #155724; }\n");
      out.write("            \n");
      out.write("            /* BUTTONS */\n");
      out.write("            .btn-sm { padding: 8px 16px; font-size: 12px; border-radius: 20px; border: none; cursor: pointer; text-decoration: none; color: white; display: inline-block; }\n");
      out.write("            .btn-view { background: #3e2723; }\n");
      out.write("            .btn-edit { background: #2980b9; }\n");
      out.write("            .btn-delete { background: #c0392b; }\n");
      out.write("            \n");
      out.write("            .table-responsive { overflow-x: auto; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("\n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin.jsp\" style=\"color:#ffab91;\">Dashboard</a>\n");
      out.write("                <a href=\"admin_products.jsp\">Products</a> <!-- ADDED PRODUCTS LINK -->\n");
      out.write("                <a href=\"admin_orders.jsp\">Orders</a>\n");
      out.write("                <a href=\"admin_analytics.jsp\">Analytics</a>\n");
      out.write("                <a href=\"admin_users.jsp\">Customers</a>\n");
      out.write("                <a href=\"admin_messages.jsp\">Messages</a>\n");
      out.write("                <a href=\"shop.jsp\" target=\"_blank\">View Site</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"dashboard-container\">\n");
      out.write("            <h2 class=\"page-title\">Dashboard Overview</h2>\n");
      out.write("\n");
      out.write("            <!-- STATS -->\n");
      out.write("            <div class=\"stat-grid\">\n");
      out.write("                <div class=\"stat-card\">\n");
      out.write("                    <div class=\"stat-icon\"><i class=\"fas fa-box\"></i></div>\n");
      out.write("                    <div class=\"stat-info\"><h3>");
      out.print( totalProducts );
      out.write("</h3><p>Products</p></div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"stat-card\">\n");
      out.write("                    <div class=\"stat-icon\"><i class=\"fas fa-shopping-cart\"></i></div>\n");
      out.write("                    <div class=\"stat-info\"><h3>");
      out.print( totalOrders );
      out.write("</h3><p>Orders</p></div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"stat-card\">\n");
      out.write("                    <div class=\"stat-icon\"><i class=\"fas fa-rupee-sign\"></i></div>\n");
      out.write("                    <div class=\"stat-info\"><h3>");
      out.print( String.format("%.2f", totalRevenue) );
      out.write("</h3><p>Revenue</p></div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- RECENT ORDERS -->\n");
      out.write("            <div class=\"data-card\">\n");
      out.write("                <div class=\"card-header\">\n");
      out.write("                    <h3>Recent Orders</h3>\n");
      out.write("                    <a href=\"admin_orders.jsp\" class=\"btn-sm btn-view\">View All</a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"table-responsive\">\n");
      out.write("                    <table class=\"admin-table\">\n");
      out.write("                        <thead><tr><th>ID</th><th>Customer</th><th>Total</th><th>Status</th></tr></thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td>#");
      out.print( rs.getInt("order_id") );
      out.write("</td>\n");
      out.write("                                            <td>");
      out.print( rs.getString("full_name") );
      out.write("</td>\n");
      out.write("                                            <td>₹");
      out.print( rs.getDouble("total_amount") );
      out.write("</td>\n");
      out.write("                                            <td><span class=\"badge ");
      out.print( statusClass );
      out.write('"');
      out.write('>');
      out.print( status );
      out.write("</span></td>\n");
      out.write("                                        </tr>\n");
      out.write("                            ");

                                    }
                                } catch (Exception e) { 
                                    out.println("<tr><td colspan='4'>Error</td></tr>"); 
                                } finally {
                                    if (con != null) try { con.close(); } catch (Exception e) {}
                                }
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- LOW STOCK ALERT -->\n");
      out.write("            <div class=\"data-card\">\n");
      out.write("                <div class=\"card-header\">\n");
      out.write("                    <h3>Low Stock Alert</h3>\n");
      out.write("                    <a href=\"admin_products.jsp\" class=\"btn-sm btn-view\">Manage Products</a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"table-responsive\">\n");
      out.write("                    <table class=\"admin-table\">\n");
      out.write("                        <thead><tr><th>Image</th><th>Name</th><th>Stock</th><th>Action</th></tr></thead>\n");
      out.write("                        <tbody>\n");
      out.write("                             ");

                                con = null;
                                try {
                                    con = DBConnection.getConnection();
                                    Statement stmt = con.createStatement();
                                    // Show items with stock < 5
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM Products WHERE stock < 5 ORDER BY stock ASC");
                                    while(rs.next()) {
                            
      out.write("\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td><img src=\"");
      out.print( rs.getString("image_url") );
      out.write("\" style=\"width:40px; height:40px; border-radius:5px; object-fit:cover;\"></td>\n");
      out.write("                                            <td>");
      out.print( rs.getString("name") );
      out.write("</td>\n");
      out.write("                                            <td style=\"color:#c0392b; font-weight:bold;\">");
      out.print( rs.getInt("stock") );
      out.write("</td>\n");
      out.write("                                            <td>\n");
      out.write("                                                <a href=\"edit_product.jsp?id=");
      out.print( rs.getInt("product_id") );
      out.write("\" class=\"btn-sm btn-edit\">Restock</a>\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                            ");

                                    }
                                } catch (Exception e) { 
                                    e.printStackTrace(); 
                                } finally {
                                    if (con != null) try { con.close(); } catch (Exception e) {}
                                }
                             
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- FOOTER -->\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<!-- PREMIUM FOOTER -->\n");
      out.write("<footer class=\"footer-new\">\n");
      out.write("    <div class=\"footer-container\">\n");
      out.write("        <!-- Column 1: About -->\n");
      out.write("        <div class=\"footer-col\">\n");
      out.write("            <h4>Cozy Crochet</h4>\n");
      out.write("            <p>Handmade crochet items crafted with love. We believe in sustainable, beautiful art that warms your heart.</p>\n");
      out.write("            <div class=\"social-icons\">\n");
      out.write("                <a href=\"#\" class=\"social-icon\"><i class=\"fab fa-facebook-f\"></i></a>\n");
      out.write("                <a href=\"#\" class=\"social-icon\"><i class=\"fab fa-instagram\"></i></a>\n");
      out.write("                <a href=\"#\" class=\"social-icon\"><i class=\"fab fa-pinterest-p\"></i></a>\n");
      out.write("                <a href=\"#\" class=\"social-icon\"><i class=\"fab fa-twitter\"></i></a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Column 2: Quick Links -->\n");
      out.write("        <div class=\"footer-col\">\n");
      out.write("            <h4>Quick Links</h4>\n");
      out.write("            <ul class=\"footer-links\">\n");
      out.write("                <li><a href=\"shop.jsp\">Shop All</a></li>\n");
      out.write("          \n");
      out.write("                <li><a href=\"contact.jsp\">Contact</a></li>\n");
      out.write("                <li><a href=\"my_account.jsp\">My Account</a></li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    \n");
      out.write("\n");
      out.write("        <!-- Column 4: Newsletter -->\n");
      out.write("        <div class=\"footer-col\">\n");
      out.write("            <h4>Stay Updated</h4>\n");
      out.write("            <p>Subscribe to get special offers and updates.</p>\n");
      out.write("            <form class=\"newsletter-form\">\n");
      out.write("                <input type=\"email\" placeholder=\"Enter your email\" class=\"newsletter-input\">\n");
      out.write("                <button type=\"submit\" class=\"newsletter-btn\"><i class=\"fas fa-paper-plane\"></i></button>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!-- Footer Bottom -->\n");
      out.write("    <div class=\"footer-bottom\">\n");
      out.write("        <span>&copy; 2024 Cozy Crochet Hub. All Rights Reserved.</span>\n");
      out.write("        <span>Made with <i class=\"fas fa-heart\" style=\"color:#c0392b;\"></i> in India</span>\n");
      out.write("    </div>\n");
      out.write("</footer>");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
