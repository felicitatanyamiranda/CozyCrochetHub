package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class my_005faccount_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>My Account</title>\n");
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
      out.write("\n");
      out.write("            /* CONTAINER */\n");
      out.write("            .account-wrapper { max-width: 1100px; margin: 0 auto 80px; padding: 0 20px; display: grid; grid-template-columns: 300px 1fr; gap: 50px; }\n");
      out.write("            @media (max-width: 900px) { .account-wrapper { grid-template-columns: 1fr; } }\n");
      out.write("\n");
      out.write("            /* SIDEBAR */\n");
      out.write("            .sidebar-card { background: white; padding: 30px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); text-align: center; height: fit-content; }\n");
      out.write("            .profile-avatar {\n");
      out.write("                width: 100px; height: 100px; background: #3e2723; color: white; border-radius: 50%;\n");
      out.write("                display: flex; align-items: center; justify-content: center; font-size: 40px; margin: 0 auto 20px;\n");
      out.write("                font-family: 'Playfair Display', serif;\n");
      out.write("            }\n");
      out.write("            .sidebar-card h3 { margin: 0 0 5px; font-family: 'Playfair Display', serif; color: #3e2723; }\n");
      out.write("            .sidebar-card p { margin: 0 0 20px; color: #888; font-size: 14px; }\n");
      out.write("            \n");
      out.write("            .sidebar-nav { text-align: left; }\n");
      out.write("            .sidebar-nav a { display: block; padding: 12px 15px; color: #3e2723; text-decoration: none; border-radius: 8px; margin-bottom: 5px; transition: 0.3s; }\n");
      out.write("            .sidebar-nav a:hover { background: #fdfbf7; }\n");
      out.write("            .sidebar-nav a.active { background: #3e2723; color: white; font-weight: 600; }\n");
      out.write("\n");
      out.write("            /* MAIN FORM */\n");
      out.write("            .main-card { background: white; padding: 40px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); }\n");
      out.write("            .main-card h3 { font-family: 'Playfair Display', serif; color: #3e2723; margin-top: 0; font-size: 28px; border-bottom: 2px solid #f0f0f0; padding-bottom: 15px; }\n");
      out.write("            \n");
      out.write("            .form-group { margin-bottom: 20px; }\n");
      out.write("            .form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #555; }\n");
      out.write("            .form-control { width: 100%; padding: 12px; border: 2px solid #eee; border-radius: 8px; font-size: 15px; transition: 0.3s; }\n");
      out.write("            .form-control:focus { border-color: #3e2723; outline: none; }\n");
      out.write("            \n");
      out.write("            .btn-save { background: #3e2723; color: white; padding: 14px 35px; border: none; border-radius: 50px; cursor: pointer; font-weight: 600; transition: 0.3s; }\n");
      out.write("            .btn-save:hover { background: #5d4037; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            // Fetch latest data
            try {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM Users WHERE user_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    session.setAttribute("user", user);
                }
                con.close();
            } catch (Exception e) { e.printStackTrace(); }
        
      out.write("\n");
      out.write("\n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"shop.jsp\">Home</a>\n");
      out.write("                <a href=\"cart.jsp\">Cart</a>\n");
      out.write("                <a href=\"my_account.jsp\" style=\"color:#ffab91;\">Account</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- HEADER -->\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h2>My Account</h2>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- CONTENT -->\n");
      out.write("        <div class=\"account-wrapper\">\n");
      out.write("            <!-- SIDEBAR -->\n");
      out.write("            <div class=\"sidebar-card\">\n");
      out.write("                <div class=\"profile-avatar\">");
      out.print( user.getFullName().substring(0,1).toUpperCase() );
      out.write("</div>\n");
      out.write("                <h3>");
      out.print( user.getFullName() );
      out.write("</h3>\n");
      out.write("                <p>");
      out.print( user.getEmail() );
      out.write("</p>\n");
      out.write("                \n");
      out.write("                <div class=\"sidebar-nav\">\n");
      out.write("                    <a href=\"my_account.jsp\" class=\"active\">Profile Details</a>\n");
      out.write("                    <a href=\"my_orders.jsp\">My Orders</a>\n");
      out.write("                    <a href=\"wishlist.jsp\">Wishlist</a>\n");
      out.write("                    <a href=\"change_password.jsp\">Change Password</a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- MAIN -->\n");
      out.write("            <div class=\"main-card\">\n");
      out.write("                <h3>Profile Details</h3>\n");
      out.write("                \n");
      out.write("                ");

                    String msg = request.getParameter("msg");
                    if(msg != null) { out.println("<div style='background:#e8f5e9; color:#2e7d32; padding:15px; border-radius:8px; margin-bottom:20px;'>" + msg + "</div>"); }
                
      out.write("\n");
      out.write("\n");
      out.write("                <form action=\"UpdateProfileServlet\" method=\"post\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Full Name</label>\n");
      out.write("                        <input type=\"text\" name=\"name\" class=\"form-control\" value=\"");
      out.print( user.getFullName() );
      out.write("\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Email Address</label>\n");
      out.write("                        <input type=\"email\" class=\"form-control\" value=\"");
      out.print( user.getEmail() );
      out.write("\" disabled style=\"background:#eee;\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Phone Number</label>\n");
      out.write("                        <input type=\"tel\" name=\"phone\" class=\"form-control\" value=\"");
      out.print( user.getPhone() != null ? user.getPhone() : "" );
      out.write("\" placeholder=\"Enter 10-digit number\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Address</label>\n");
      out.write("                        <textarea name=\"address\" class=\"form-control\" style=\"height: 100px;\" placeholder=\"House No, Street, City\">");
      out.print( user.getAddress() != null ? user.getAddress() : "" );
      out.write("</textarea>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <button type=\"submit\" class=\"btn-save\">Save Changes</button>\n");
      out.write("                </form>\n");
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
