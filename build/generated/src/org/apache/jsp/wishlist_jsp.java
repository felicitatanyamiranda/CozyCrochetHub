package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class wishlist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>My Wishlist</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("        <style>\n");
      out.write("            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }\n");
      out.write("            \n");
      out.write("            /* --- NAVBAR --- */\n");
      out.write("            .nav-solid {\n");
      out.write("                background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center;\n");
      out.write("                box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("            }\n");
      out.write("            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }\n");
      out.write("            .nav-links { display: flex; align-items: center; gap: 30px; }\n");
      out.write("            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; font-weight: 500; transition: 0.3s; }\n");
      out.write("            .nav-links a:hover { color: #ffab91; }\n");
      out.write("\n");
      out.write("            /* --- HEADER --- */\n");
      out.write("            .page-header {\n");
      out.write("                background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white;\n");
      out.write("                margin-bottom: 60px;\n");
      out.write("            }\n");
      out.write("            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }\n");
      out.write("            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; letter-spacing: 1px; text-transform: uppercase; }\n");
      out.write("\n");
      out.write("            /* --- CONTAINER --- */\n");
      out.write("            .main-wrapper { max-width: 1200px; margin: 0 auto; padding: 0 20px 80px; }\n");
      out.write("\n");
      out.write("            /* --- WISHLIST GRID --- */\n");
      out.write("            .wishlist-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 30px; }\n");
      out.write("\n");
      out.write("            .wishlist-card {\n");
      out.write("                background: white; border-radius: 20px; overflow: hidden;\n");
      out.write("                box-shadow: 0 10px 25px rgba(0,0,0,0.05); transition: transform 0.3s;\n");
      out.write("                position: relative; border: 1px solid rgba(0,0,0,0.02);\n");
      out.write("            }\n");
      out.write("            .wishlist-card:hover { transform: translateY(-5px); }\n");
      out.write("            \n");
      out.write("            /* Image Area */\n");
      out.write("            .card-img-wrapper { height: 260px; background: #f9f9f9; position: relative; overflow: hidden; }\n");
      out.write("            .card-img-wrapper img { width: 100%; height: 100%; object-fit: cover; transition: opacity 0.3s; }\n");
      out.write("            \n");
      out.write("            /* Out of Stock Overlay */\n");
      out.write("            .out-ofStock-img { opacity: 0.4; }\n");
      out.write("            .stock-tag {\n");
      out.write("                position: absolute; top: 15px; left: 15px; padding: 6px 15px;\n");
      out.write("                border-radius: 20px; font-size: 11px; font-weight: bold; text-transform: uppercase; letter-spacing: 0.5px;\n");
      out.write("            }\n");
      out.write("            .tag-instock { background: #e8f5e9; color: #2e7d32; }\n");
      out.write("            .tag-outofstock { background: #ffebee; color: #c62828; }\n");
      out.write("\n");
      out.write("            /* Remove Button (Top Right - Moved Closer to Corner) */\n");
      out.write("            .btn-remove {\n");
      out.write("                position: absolute; top: 10px; right: 10px; background: white;\n");
      out.write("                width: 40px; height: 40px; border-radius: 50%; display: flex;\n");
      out.write("                align-items: center; justify-content: center; color: #e74c3c;\n");
      out.write("                text-decoration: none; box-shadow: 0 5px 15px rgba(0,0,0,0.15);\n");
      out.write("                transition: 0.3s; z-index: 10; border: none; cursor: pointer;\n");
      out.write("            }\n");
      out.write("            .btn-remove:hover { background: #e74c3c; color: white; transform: scale(1.1); }\n");
      out.write("\n");
      out.write("            /* Content Area */\n");
      out.write("            .card-content { padding: 20px; text-align: center; }\n");
      out.write("            .card-title { font-family: 'Playfair Display', serif; font-size: 20px; color: #3e2723; margin-bottom: 8px; }\n");
      out.write("            .card-price { font-size: 18px; color: #c0392b; font-weight: bold; margin-bottom: 15px; display: block; }\n");
      out.write("\n");
      out.write("            /* Action Buttons */\n");
      out.write("            .action-btn {\n");
      out.write("                display: block; width: 100%; padding: 12px; border-radius: 30px;\n");
      out.write("                text-decoration: none; font-weight: 600; font-size: 14px;\n");
      out.write("                transition: 0.3s; margin-bottom: 10px; border: none; cursor: pointer;\n");
      out.write("            }\n");
      out.write("            .btn-add { background: #3e2723; color: white; }\n");
      out.write("            .btn-add:hover { background: #5d4037; }\n");
      out.write("            .btn-disabled { background: #ccc; color: #666; cursor: not-allowed; }\n");
      out.write("\n");
      out.write("            /* Empty State */\n");
      out.write("            .empty-box { text-align: center; padding: 80px 20px; background: white; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); }\n");
      out.write("            .empty-box i { font-size: 60px; color: #ddd; margin-bottom: 20px; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- NAVBAR (No Symbols) -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"shop.jsp\">Home</a>\n");
      out.write("                <a href=\"wishlist.jsp\" style=\"color:#ffab91;\">Wishlist</a>\n");
      out.write("                <a href=\"cart.jsp\">Cart</a>\n");
      out.write("                <a href=\"my_account.jsp\">Account</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- HEADER -->\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h2>My Wishlist</h2>\n");
      out.write("            <p>YOUR SAVED FAVORITES</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- CONTENT -->\n");
      out.write("        <div class=\"main-wrapper\">\n");
      out.write("            ");

                User user = (User) session.getAttribute("user");
                if (user == null) { response.sendRedirect("login.jsp"); return; }

                try {
                    Connection con = DBConnection.getConnection();
                    String sql = "SELECT p.* FROM Products p JOIN Wishlist w ON p.product_id = w.product_id WHERE w.user_id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ResultSet rs = ps.executeQuery();
                    
                    boolean hasItems = false;
            
      out.write("\n");
      out.write("                    <div class=\"wishlist-grid\">\n");
      out.write("                        ");

                            while(rs.next()) {
                                hasItems = true;
                                int pid = rs.getInt("product_id");
                                int stock = rs.getInt("stock");
                                boolean inStock = stock > 0;
                        
      out.write("\n");
      out.write("                                <div class=\"wishlist-card\">\n");
      out.write("                                    <!-- Remove Button -->\n");
      out.write("                                    <form action=\"WishlistServlet\" method=\"post\" style=\"position:absolute; top:0; right:0; margin:0; z-index:20;\">\n");
      out.write("                                        <input type=\"hidden\" name=\"action\" value=\"remove\">\n");
      out.write("                                        <input type=\"hidden\" name=\"pid\" value=\"");
      out.print( pid );
      out.write("\">\n");
      out.write("                                        <button type=\"submit\" class=\"btn-remove\" onclick=\"return confirm('Remove from wishlist?')\">\n");
      out.write("                                            <i class=\"fas fa-times\"></i>\n");
      out.write("                                        </button>\n");
      out.write("                                    </form>\n");
      out.write("\n");
      out.write("                                    <div class=\"card-img-wrapper\">\n");
      out.write("                                        <!-- Stock Tag -->\n");
      out.write("                                        <span class=\"stock-tag ");
      out.print( inStock ? "tag-instock" : "tag-outofstock" );
      out.write("\">\n");
      out.write("                                            ");
      out.print( inStock ? "In Stock" : "Out of Stock" );
      out.write("\n");
      out.write("                                        </span>\n");
      out.write("                                        \n");
      out.write("                                        <img src=\"");
      out.print( rs.getString("image_url") );
      out.write("\" alt=\"Product\" class=\"");
      out.print( inStock ? "" : "outOfStock-img" );
      out.write("\">\n");
      out.write("                                    </div>\n");
      out.write("                                    \n");
      out.write("                                    <div class=\"card-content\">\n");
      out.write("                                        <div class=\"card-title\">");
      out.print( rs.getString("name") );
      out.write("</div>\n");
      out.write("                                        <span class=\"card-price\">₹");
      out.print( rs.getDouble("price") );
      out.write("</span>\n");
      out.write("                                        \n");
      out.write("                                        ");
 if(inStock) { 
      out.write("\n");
      out.write("                                            <!-- Move to Cart -->\n");
      out.write("                                            <a href=\"WishlistServlet?action=move&pid=");
      out.print( pid );
      out.write("\" class=\"action-btn btn-add\">\n");
      out.write("    Move to Cart\n");
      out.write("                                                <input type=\"hidden\" name=\"action\" value=\"add\">\n");
      out.write("                                                <input type=\"hidden\" name=\"pid\" value=\"");
      out.print( pid );
      out.write("\">\n");
      out.write("                                                \n");
      out.write("                                                </button>\n");
      out.write("                                            </a>\n");
      out.write("                                        ");
 } else { 
      out.write("\n");
      out.write("                                            <button class=\"action-btn btn-disabled\" disabled>\n");
      out.write("                                                Unavailable\n");
      out.write("                                            </button>\n");
      out.write("                                        ");
 } 
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                        ");

                            }
                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("            ");

                    if(!hasItems) {
            
      out.write("\n");
      out.write("                        <div class=\"empty-box\">\n");
      out.write("                            <i class=\"fas fa-heart-broken\"></i>\n");
      out.write("                            <h3 style=\"font-family:'Playfair Display'; color:#3e2723;\">Your wishlist is empty</h3>\n");
      out.write("                            <p style=\"color:#777; margin-bottom:30px;\">Save items you love by clicking the heart icon on products.</p>\n");
      out.write("                            <a href=\"shop.jsp\" class=\"action-btn btn-add\" style=\"display:inline-block; width:auto; padding:15px 40px;\">Start Shopping</a>\n");
      out.write("                        </div>\n");
      out.write("            ");

                    }
                    
                    con.close();
                } catch (Exception e) {
                    out.println("<p style='color:red; text-align:center;'>Error: " + e.getMessage() + "</p>");
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("         <!-- Insert Premium Footer Here -->\n");
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
