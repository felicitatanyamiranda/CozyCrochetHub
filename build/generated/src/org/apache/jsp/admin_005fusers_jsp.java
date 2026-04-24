package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class admin_005fusers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      out.write("        <title>Manage Users</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <style>\n");
      out.write("            .admin-wrapper { display: flex; min-height: 100vh; background: #f4f4f4; }\n");
      out.write("            .admin-sidebar { width: 260px; background: #3e2723; color: white; display: flex; flex-direction: column; position: fixed; height: 100vh; box-shadow: 2px 0 10px rgba(0,0,0,0.2); z-index: 100; }\n");
      out.write("            .admin-sidebar h2 { font-family: 'Playfair Display', serif; text-align: center; padding: 30px 20px; margin: 0; border-bottom: 1px solid #5d4037; color: #ffab91; }\n");
      out.write("            .admin-sidebar a { padding: 18px 30px; color: #d7ccc8; text-decoration: none; transition: 0.3s; border-left: 4px solid transparent; }\n");
      out.write("            .admin-sidebar a:hover, .admin-sidebar a.active { background: #5d4037; color: white; border-left-color: #ffab91; }\n");
      out.write("            .admin-sidebar .logout-btn { margin-top: auto; background: #1b0f0d; text-align: center; }\n");
      out.write("\n");
      out.write("            .admin-main { margin-left: 260px; padding: 40px; width: 100%; box-sizing: border-box; }\n");
      out.write("            .admin-page-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 32px; margin-bottom: 30px; }\n");
      out.write("\n");
      out.write("            .data-box { background: white; border-radius: 12px; box-shadow: 0 5px 15px rgba(0,0,0,0.05); overflow: hidden; }\n");
      out.write("            \n");
      out.write("            .box-header {\n");
      out.write("                background: linear-gradient(135deg, #3e2723, #5d4037); \n");
      out.write("                color: white; padding: 25px; \n");
      out.write("                display: flex; justify-content: space-between; align-items: center;\n");
      out.write("            }\n");
      out.write("            .box-header h3 { margin: 0; font-family: 'Playfair Display', serif; font-size: 24px; }\n");
      out.write("            .box-header p { margin: 5px 0 0; opacity: 0.8; font-size: 14px; }\n");
      out.write("\n");
      out.write("            .table-container { padding: 25px; overflow-x: auto; }\n");
      out.write("            \n");
      out.write("            .data-table { width: 100%; border-collapse: collapse; }\n");
      out.write("            .data-table th { background: #f9f9f9; color: #3e2723; padding: 15px; text-align: left; font-weight: 600; border-bottom: 2px solid #e0e0e0; }\n");
      out.write("            .data-table td { padding: 15px; border-bottom: 1px solid #eee; color: #555; }\n");
      out.write("            .data-table tr:hover { background: #fffbf5; }\n");
      out.write("            \n");
      out.write("            .badge { padding: 5px 10px; border-radius: 20px; font-size: 11px; font-weight: bold; text-transform: uppercase; }\n");
      out.write("            .badge-admin { background: #e8e8e8; color: #333; }\n");
      out.write("            .badge-customer { background: #e3f2fd; color: #1565c0; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"admin-wrapper\">\n");
      out.write("            <div class=\"admin-sidebar\">\n");
      out.write("                <h2>Cozy Admin</h2>\n");
      out.write("                <a href=\"admin.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"admin_orders.jsp\">Orders</a>\n");
      out.write("                <a href=\"admin_analytics.jsp\">Analytics</a>\n");
      out.write("                <a href=\"admin_users.jsp\" class=\"active\">Customers</a>\n");
      out.write("                <a href=\"admin_messages.jsp\">Messages</a>\n");
      out.write("                <a href=\"shop.jsp\" target=\"_blank\">View Site</a>\n");
      out.write("                <a href=\"logout.jsp\" class=\"logout-btn\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"admin-main\">\n");
      out.write("                <h1 class=\"admin-page-title\">Customer Management</h1>\n");
      out.write("\n");
      out.write("                <div class=\"data-box\">\n");
      out.write("                    <div class=\"box-header\">\n");
      out.write("                        <div>\n");
      out.write("                            <h3>Registered Users</h3>\n");
      out.write("                            <p>Manage customer accounts</p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"table-container\">\n");
      out.write("                        <table class=\"data-table\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>ID</th>\n");
      out.write("                                    <th>Name</th>\n");
      out.write("                                    <th>Email</th>\n");
      out.write("                                    <th>Phone</th>\n");
      out.write("                                    <th>Role</th>\n");
      out.write("                                    <th>Action</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("                                ");

                                    Connection con = null;
                                    try {
                                        con = DBConnection.getConnection();
                                        String sql = "SELECT * FROM Users ORDER BY user_id DESC";
                                        Statement stmt = con.createStatement();
                                        ResultSet rs = stmt.executeQuery(sql);
                                        
                                        while (rs.next()) {
                                            int uid = rs.getInt("user_id");
                                            String role = rs.getString("role");
                                    
      out.write("\n");
      out.write("                                            <tr>\n");
      out.write("                                                <td>");
      out.print( uid );
      out.write("</td>\n");
      out.write("                                                <td><strong>");
      out.print( rs.getString("full_name") );
      out.write("</strong></td>\n");
      out.write("                                                <td>");
      out.print( rs.getString("email") );
      out.write("</td>\n");
      out.write("                                                <td>");
      out.print( rs.getString("phone") != null ? rs.getString("phone") : "N/A" );
      out.write("</td>\n");
      out.write("                                                <td>\n");
      out.write("                                                    <span class=\"badge ");
      out.print( "admin".equals(role) ? "badge-admin" : "badge-customer" );
      out.write("\">\n");
      out.write("                                                        ");
      out.print( role );
      out.write("\n");
      out.write("                                                    </span>\n");
      out.write("                                                </td>\n");
      out.write("                                                <td>\n");
      out.write("                                                    ");
 if(!"admin".equals(role)) { 
      out.write("\n");
      out.write("                                                        <a href=\"DeleteUserServlet?id=");
      out.print( uid );
      out.write("\" \n");
      out.write("                                                           class=\"btn btn-danger\" \n");
      out.write("                                                           style=\"padding:5px 10px; font-size:12px;\"\n");
      out.write("                                                           onclick=\"return confirm('Are you sure you want to delete this user?')\">\n");
      out.write("                                                           Delete\n");
      out.write("                                                        </a>\n");
      out.write("                                                    ");
 } else { 
      out.write("\n");
      out.write("                                                        <span style=\"color:#999; font-size:12px;\">Protected</span>\n");
      out.write("                                                    ");
 } 
      out.write("\n");
      out.write("                                                </td>\n");
      out.write("                                            </tr>\n");
      out.write("                                    ");

                                        }
                                    } catch (Exception e) {
                                        out.println("<tr><td colspan='6'>Error loading users</td></tr>");
                                    } finally {
                                        if (con != null) try { con.close(); } catch (Exception e) {}
                                    }
                                
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
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
