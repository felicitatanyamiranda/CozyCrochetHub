package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class add_005fproduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Add Product</title>\n");
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
      out.write("            .form-container { max-width: 800px; margin: 60px auto; padding: 0 20px; }\n");
      out.write("            \n");
      out.write("            .form-card {\n");
      out.write("                background: white; padding: 50px; border-radius: 20px;\n");
      out.write("                box-shadow: 0 20px 50px rgba(0,0,0,0.05);\n");
      out.write("            }\n");
      out.write("            .form-header { margin-bottom: 40px; text-align: center; }\n");
      out.write("            .form-header h2 { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 32px; margin: 0 0 10px; }\n");
      out.write("            .form-header p { color: #777; margin: 0; }\n");
      out.write("\n");
      out.write("            .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; }\n");
      out.write("            .form-group { margin-bottom: 20px; }\n");
      out.write("            .form-group.full-width { grid-column: 1 / -1; }\n");
      out.write("            \n");
      out.write("            label { display: block; margin-bottom: 8px; font-weight: 700; color: #555; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; }\n");
      out.write("            \n");
      out.write("            .input-field {\n");
      out.write("                width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px;\n");
      out.write("                font-size: 15px; transition: all 0.3s; font-family: 'Lato', sans-serif;\n");
      out.write("            }\n");
      out.write("            .input-field:focus { border-color: #3e2723; outline: none; box-shadow: 0 0 0 3px rgba(62,39,35,0.05); }\n");
      out.write("            \n");
      out.write("            select.input-field { appearance: none; background: white; cursor: pointer; }\n");
      out.write("            \n");
      out.write("            textarea.input-field { resize: vertical; min-height: 120px; }\n");
      out.write("            \n");
      out.write("            .btn-submit {\n");
      out.write("                background: #3e2723; color: white; padding: 16px 40px; border: none;\n");
      out.write("                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;\n");
      out.write("                transition: all 0.3s; width: 100%; margin-top: 20px;\n");
      out.write("            }\n");
      out.write("            .btn-submit:hover { background: #5d4037; transform: translateY(-2px); }\n");
      out.write("\n");
      out.write("            @media (max-width: 768px) { .form-grid { grid-template-columns: 1fr; } }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) { response.sendRedirect("login.jsp"); return; }
        
      out.write("\n");
      out.write("\n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"admin_products.jsp\">Products</a>\n");
      out.write("                <a href=\"admin_orders.jsp\">Orders</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div class=\"form-container\">\n");
      out.write("            <div class=\"form-card\">\n");
      out.write("                <div class=\"form-header\">\n");
      out.write("                    <h2>Add New Product</h2>\n");
      out.write("                    <p>Fill in the details below to add a new item to the store.</p>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <form action=\"AddProductServlet\" method=\"post\">\n");
      out.write("                    <div class=\"form-grid\">\n");
      out.write("                        <!-- Name -->\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Product Name</label>\n");
      out.write("                            <input type=\"text\" name=\"name\" class=\"input-field\" placeholder=\"e.g. Handmade Crochet Bag\" required>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!-- Price -->\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Price (₹)</label>\n");
      out.write("                            <input type=\"number\" name=\"price\" class=\"input-field\" placeholder=\"e.g. 599\" step=\"0.01\" required>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!-- Category -->\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Category</label>\n");
      out.write("                            <select name=\"category\" class=\"input-field\" required>\n");
      out.write("                                <option value=\"\">Select Category</option>\n");
      out.write("                                ");

                                    Connection con = null;
                                    try {
                                        con = DBConnection.getConnection();
                                        Statement stmt = con.createStatement();
                                        ResultSet rs = stmt.executeQuery("SELECT * FROM Categories");
                                        while(rs.next()) {
                                
      out.write("\n");
      out.write("                                            <option value=\"");
      out.print( rs.getInt("category_id") );
      out.write('"');
      out.write('>');
      out.print( rs.getString("category_name") );
      out.write("</option>\n");
      out.write("                                ");

                                        }
                                    } catch (Exception e) { e.printStackTrace(); }
                                    finally { if(con != null) try { con.close(); } catch(Exception e){} }
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!-- Stock -->\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Stock Quantity</label>\n");
      out.write("                            <input type=\"number\" name=\"stock\" class=\"input-field\" placeholder=\"e.g. 10\" required>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!-- Image URL -->\n");
      out.write("                        <div class=\"form-group full-width\">\n");
      out.write("                            <label>Image URL</label>\n");
      out.write("                            <input type=\"text\" name=\"image\" class=\"input-field\" placeholder=\"https://example.com/image.jpg\" required>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <!-- Description -->\n");
      out.write("                        <div class=\"form-group full-width\">\n");
      out.write("                            <label>Description</label>\n");
      out.write("                            <textarea name=\"desc\" class=\"input-field\" placeholder=\"Write a detailed description...\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <button type=\"submit\" class=\"btn-submit\">\n");
      out.write("                        <i class=\"fas fa-plus-circle\"></i> Add Product\n");
      out.write("                    </button>\n");
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
