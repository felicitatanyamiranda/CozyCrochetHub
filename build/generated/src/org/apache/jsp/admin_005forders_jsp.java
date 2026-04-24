package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class admin_005forders_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Manage Orders</title>\n");
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
      out.write("            /* HEADER */\n");
      out.write("            .page-header { background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white; margin-bottom: 60px; }\n");
      out.write("            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }\n");
      out.write("            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }\n");
      out.write("\n");
      out.write("            /* CONTAINER */\n");
      out.write("            .admin-container { max-width: 1200px; margin: 0 auto 80px; padding: 0 20px; }\n");
      out.write("\n");
      out.write("            /* TABLE CARD */\n");
      out.write("            .data-card { background: white; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); overflow: hidden; }\n");
      out.write("            .card-header { background: #f9f9f9; padding: 25px; border-bottom: 1px solid #eee; }\n");
      out.write("            .card-header h3 { margin: 0; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 24px; }\n");
      out.write("\n");
      out.write("            /* TABLE STYLING */\n");
      out.write("            .admin-table { width: 100%; border-collapse: collapse; min-width: 800px; }\n");
      out.write("            .admin-table th { background: #3e2723; color: white; padding: 18px 20px; text-align: left; font-weight: 600; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; }\n");
      out.write("            .admin-table td { padding: 20px; border-bottom: 1px solid #eee; color: #555; vertical-align: middle; } /* KEY FIX: vertical-align */\n");
      out.write("            .admin-table tr:last-child td { border-bottom: none; }\n");
      out.write("            .admin-table tr:hover { background: #fffbf5; }\n");
      out.write("            \n");
      out.write("            /* BADGES */\n");
      out.write("            .badge { padding: 6px 14px; border-radius: 20px; font-size: 12px; font-weight: 700; text-transform: uppercase; }\n");
      out.write("            .badge-pending { background: #fff3cd; color: #856404; }\n");
      out.write("            .badge-shipped { background: #cce5ff; color: #004085; }\n");
      out.write("            .badge-delivered { background: #d4edda; color: #155724; }\n");
      out.write("            .badge-cancelled { background: #f8d7da; color: #721c24; }\n");
      out.write("\n");
      out.write("            /* FORM & BUTTONS */\n");
      out.write("            .status-form { display: flex; align-items: center; gap: 10px; } /* KEY FIX: Flexbox for alignment */\n");
      out.write("            .form-select { padding: 8px 12px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; background: white; min-width: 120px; }\n");
      out.write("            .btn-update { background: #3e2723; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; font-size: 14px; transition: 0.3s; }\n");
      out.write("            .btn-update:hover { background: #5d4037; }\n");
      out.write("\n");
      out.write("            /* Responsive */\n");
      out.write("            .table-responsive { overflow-x: auto; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"admin_orders.jsp\" style=\"color:#ffab91;\">Orders</a>\n");
      out.write("                <a href=\"admin_analytics.jsp\">Analytics</a>\n");
      out.write("                <a href=\"admin_users.jsp\">Customers</a>\n");
      out.write("                <a href=\"admin_messages.jsp\">Messages</a>\n");
      out.write("                <a href=\"shop.jsp\" target=\"_blank\">View Site</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- HEADER -->\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h2>Manage Orders</h2>\n");
      out.write("            <p>Update order status and track deliveries</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- CONTENT -->\n");
      out.write("        <div class=\"admin-container\">\n");
      out.write("            <div class=\"data-card\">\n");
      out.write("                <div class=\"card-header\">\n");
      out.write("                    <h3>Recent Orders</h3>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"table-responsive\">\n");
      out.write("                    <table class=\"admin-table\">\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Order ID</th>\n");
      out.write("                                <th>Customer</th>\n");
      out.write("                                <th>Total</th>\n");
      out.write("                                <th>Payment</th>\n");
      out.write("                                <th>Status</th>\n");
      out.write("                                <th>Action</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

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
                            
      out.write("\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td>#");
      out.print( oid );
      out.write("</td>\n");
      out.write("                                            <td>\n");
      out.write("                                                <strong>");
      out.print( rs.getString("full_name") );
      out.write("</strong><br>\n");
      out.write("                                                <small style=\"color:#888;\">");
      out.print( rs.getString("email") );
      out.write("</small>\n");
      out.write("                                            </td>\n");
      out.write("                                            <td>₹");
      out.print( rs.getDouble("total_amount") );
      out.write("</td>\n");
      out.write("                                            <td>");
      out.print( rs.getString("payment_method") );
      out.write("</td>\n");
      out.write("                                            <td><span class=\"badge ");
      out.print( statusClass );
      out.write('"');
      out.write('>');
      out.print( status );
      out.write("</span></td>\n");
      out.write("                                            <td>\n");
      out.write("                                                <!-- LOGIC: HIDE BUTTON IF CANCELLED OR DELIVERED -->\n");
      out.write("                                                ");
 if("Cancelled".equals(status) || "Delivered".equals(status)) { 
      out.write("\n");
      out.write("                                                    <span style=\"color:#999; font-size:13px; font-style:italic;\">Completed</span>\n");
      out.write("                                                ");
 } else { 
      out.write("\n");
      out.write("                                                    <form action=\"UpdateStatusServlet\" method=\"post\" class=\"status-form\">\n");
      out.write("                                                        <input type=\"hidden\" name=\"oid\" value=\"");
      out.print( oid );
      out.write("\">\n");
      out.write("                                                        <select name=\"status\" class=\"form-select\">\n");
      out.write("                                                            <option value=\"Pending\" ");
      out.print( "Pending".equals(status) ? "selected" : "" );
      out.write(">Pending</option>\n");
      out.write("                                                            <option value=\"Shipped\" ");
      out.print( "Shipped".equals(status) ? "selected" : "" );
      out.write(">Shipped</option>\n");
      out.write("                                                            <option value=\"Delivered\">Delivered</option>\n");
      out.write("                                                            <option value=\"Cancelled\">Cancel</option>\n");
      out.write("                                                        </select>\n");
      out.write("                                                        <button type=\"submit\" class=\"btn-update\">Update</button>\n");
      out.write("                                                    </form>\n");
      out.write("                                                ");
 } 
      out.write("\n");
      out.write("                                            </td>\n");
      out.write("                                        </tr>\n");
      out.write("                            ");

                                    }
                                    con.close();
                                } catch (Exception e) {
                                    out.println("<tr><td colspan='6' style='text-align:center; color:red;'>Error: " + e.getMessage() + "</td></tr>");
                                }
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
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
