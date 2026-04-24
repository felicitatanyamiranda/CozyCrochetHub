package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class admin_005fmessages_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Messages</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
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
      out.write("            /* CARDS LAYOUT FOR MESSAGES */\n");
      out.write("            .message-card { \n");
      out.write("                background: white; padding: 0; border-radius: 12px; margin-bottom: 20px; \n");
      out.write("                box-shadow: 0 5px 15px rgba(0,0,0,0.05); overflow: hidden; \n");
      out.write("                border-left: 5px solid #8d6e63;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            /* POP-OUT HEADER */\n");
      out.write("            .box-header {\n");
      out.write("                background: linear-gradient(135deg, #3e2723, #5d4037); \n");
      out.write("                color: white; padding: 20px; \n");
      out.write("                display: flex; justify-content: space-between; align-items: center;\n");
      out.write("            }\n");
      out.write("            .box-header h3 { margin: 0; font-family: 'Playfair Display', serif; font-size: 18px; }\n");
      out.write("            .box-header p { margin: 0; opacity: 0.8; font-size: 12px; }\n");
      out.write("\n");
      out.write("            .message-body { padding: 20px; }\n");
      out.write("            .msg-email { font-size: 14px; color: #555; margin-bottom: 5px; }\n");
      out.write("            .msg-subject { font-weight: bold; color: #3e2723; font-size: 16px; margin-bottom: 10px; }\n");
      out.write("            .msg-text { color: #666; font-size: 14px; line-height: 1.5; background: #f9f9f9; padding: 15px; border-radius: 5px; }\n");
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
      out.write("                <a href=\"admin_users.jsp\">Customers</a>\n");
      out.write("                <a href=\"admin_messages.jsp\" class=\"active\">Messages</a>\n");
      out.write("                <a href=\"shop.jsp\" target=\"_blank\">View Site</a>\n");
      out.write("                <a href=\"logout.jsp\" class=\"logout-btn\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"admin-main\">\n");
      out.write("                <h1 class=\"admin-page-title\">Contact Messages</h1>\n");
      out.write("\n");
      out.write("                ");

                    Connection con = null;
                    try {
                        con = DBConnection.getConnection();
                        String sql = "SELECT * FROM ContactMessages ORDER BY submitted_at DESC";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        
                        boolean hasMessages = false;
                        while (rs.next()) {
                            hasMessages = true;
                
      out.write("\n");
      out.write("                            <!-- Message Card -->\n");
      out.write("                            <div class=\"message-card\">\n");
      out.write("                                <div class=\"box-header\">\n");
      out.write("                                    <div>\n");
      out.write("                                        <h3>");
      out.print( rs.getString("name") );
      out.write("</h3>\n");
      out.write("                                        <p>");
      out.print( rs.getString("email") );
      out.write("</p>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div style=\"text-align:right;\">\n");
      out.write("                                        <span style=\"font-size: 12px; opacity: 0.8;\">");
      out.print( rs.getTimestamp("submitted_at") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"message-body\">\n");
      out.write("                                    <div class=\"msg-subject\">Subject: ");
      out.print( rs.getString("subject") );
      out.write("</div>\n");
      out.write("                                    <div class=\"msg-text\">");
      out.print( rs.getString("message") );
      out.write("</div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                        if(!hasMessages) {
                
      out.write("\n");
      out.write("                            <div style=\"text-align:center; padding:50px; background:white; border-radius:10px;\">\n");
      out.write("                                <h3 style=\"color:#3e2723;\">No messages found</h3>\n");
      out.write("                                <p style=\"color:#777;\">When customers use the contact form, their messages will appear here.</p>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                    } catch (Exception e) {
                        out.println("<p style='color:red;'>Error loading messages: " + e.getMessage() + "</p>");
                    } finally {
                        if (con != null) try { con.close(); } catch (Exception e) {}
                    }
                
      out.write("\n");
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
