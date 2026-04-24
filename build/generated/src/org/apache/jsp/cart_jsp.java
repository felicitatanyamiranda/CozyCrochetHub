package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import com.cozycrochet.models.CartItem;
import java.util.List;

public final class cart_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>My Cart</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("        <style>\n");
      out.write("            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }\n");
      out.write("            \n");
      out.write("            /* NAVBAR */\n");
      out.write("            .nav-solid {\n");
      out.write("                background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center;\n");
      out.write("                box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("            }\n");
      out.write("            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }\n");
      out.write("            .nav-links { display: flex; align-items: center; gap: 20px; }\n");
      out.write("            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; font-weight: 500; transition: 0.3s; }\n");
      out.write("            .nav-links a:hover { color: #ffab91; }\n");
      out.write("\n");
      out.write("            /* HEADER */\n");
      out.write("            .page-header {\n");
      out.write("                background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white;\n");
      out.write("                margin-bottom: 60px;\n");
      out.write("            }\n");
      out.write("            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }\n");
      out.write("            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }\n");
      out.write("\n");
      out.write("            /* CONTAINER */\n");
      out.write("            .cart-container { max-width: 1000px; margin: 0 auto 80px; padding: 0 20px; display: grid; grid-template-columns: 2fr 1fr; gap: 40px; }\n");
      out.write("            @media (max-width: 900px) { .cart-container { grid-template-columns: 1fr; } }\n");
      out.write("\n");
      out.write("            /* --- ITEMS LIST --- */\n");
      out.write("            .cart-items-col { display: flex; flex-direction: column; gap: 20px; }\n");
      out.write("            \n");
      out.write("            .cart-item {\n");
      out.write("                background: white; padding: 20px; border-radius: 20px;\n");
      out.write("                box-shadow: 0 10px 30px rgba(0,0,0,0.05); display: flex; gap: 25px; align-items: center;\n");
      out.write("                transition: transform 0.3s;\n");
      out.write("            }\n");
      out.write("            .cart-item:hover { transform: translateY(-3px); }\n");
      out.write("            \n");
      out.write("            .item-image {\n");
      out.write("                width: 120px; height: 120px; background: #f9f9f9; border-radius: 15px; object-fit: cover;\n");
      out.write("                border: 1px solid #eee; flex-shrink: 0;\n");
      out.write("            }\n");
      out.write("            .item-details { flex-grow: 1; }\n");
      out.write("            .item-details h3 { margin: 0 0 5px; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 20px; }\n");
      out.write("            .item-desc { color: #888; font-size: 13px; margin-bottom: 5px; display: block; }\n");
      out.write("            .item-price { font-size: 18px; color: #c0392b; font-weight: bold; }\n");
      out.write("\n");
      out.write("            .item-actions { text-align: right; }\n");
      out.write("            .item-total { font-size: 20px; font-weight: bold; color: #3e2723; margin-bottom: 10px; display: block; }\n");
      out.write("            \n");
      out.write("            /* Quantity Controls */\n");
      out.write("            .qty-control { display: inline-flex; align-items: center; gap: 0; border: 1px solid #eee; border-radius: 25px; overflow: hidden; }\n");
      out.write("            .qty-btn {\n");
      out.write("                width: 35px; height: 35px; background: #f9f9f9; border: none; cursor: pointer;\n");
      out.write("                font-size: 14px; color: #555; transition: 0.2s; text-decoration: none; display: flex; align-items: center; justify-content: center;\n");
      out.write("            }\n");
      out.write("            .qty-btn:hover { background: #3e2723; color: white; }\n");
      out.write("            .qty-display { width: 40px; text-align: center; border: none; font-weight: bold; font-size: 16px; background: white; }\n");
      out.write("\n");
      out.write("            .btn-remove { background: none; border: none; color: #e74c3c; cursor: pointer; font-size: 13px; margin-top: 10px; opacity: 0.7; transition: 0.2s; }\n");
      out.write("            .btn-remove:hover { opacity: 1; text-decoration: underline; }\n");
      out.write("\n");
      out.write("            /* --- SUMMARY CARD --- */\n");
      out.write("            .summary-card {\n");
      out.write("                background: white; padding: 40px; border-radius: 20px;\n");
      out.write("                box-shadow: 0 15px 35px rgba(0,0,0,0.05); height: fit-content; position: sticky; top: 100px;\n");
      out.write("            }\n");
      out.write("            .summary-card h3 { margin: 0 0 30px; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 24px; border-bottom: 1px solid #eee; padding-bottom: 15px; }\n");
      out.write("            \n");
      out.write("            .summary-row { display: flex; justify-content: space-between; margin-bottom: 15px; font-size: 16px; color: #555; }\n");
      out.write("            .summary-row.total { font-size: 24px; font-weight: bold; color: #3e2723; margin-top: 20px; padding-top: 20px; border-top: 2px dashed #eee; }\n");
      out.write("            \n");
      out.write("            .btn-checkout {\n");
      out.write("                width: 100%; padding: 18px; background: #3e2723; color: white; border: none;\n");
      out.write("                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer; margin-top: 30px;\n");
      out.write("                transition: 0.3s; letter-spacing: 1px; text-transform: uppercase; text-decoration: none; display: block; text-align: center;\n");
      out.write("            }\n");
      out.write("            .btn-checkout:hover { background: #5d4037; transform: translateY(-2px); box-shadow: 0 10px 20px rgba(0,0,0,0.15); }\n");
      out.write("\n");
      out.write("            /* Empty State */\n");
      out.write("            .empty-box { text-align: center; padding: 80px 20px; background: white; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); }\n");
      out.write("            .empty-box i { font-size: 60px; color: #ddd; margin-bottom: 20px; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        
      out.write("\n");
      out.write("\n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-solid\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"shop.jsp\">Home</a>\n");
      out.write("                <a href=\"cart.jsp\">Cart</a>\n");
      out.write("                <a href=\"my_orders.jsp\">Orders</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- HEADER -->\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h2>Shopping Cart</h2>\n");
      out.write("            <p>Review your items</p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"cart-container\">\n");
      out.write("            <!-- Items Column -->\n");
      out.write("            <div class=\"cart-items-col\">\n");
      out.write("                ");

                    if (cart == null || cart.isEmpty()) {
                
      out.write("\n");
      out.write("                    <div class=\"empty-box\">\n");
      out.write("                        <i class=\"fas fa-shopping-cart\"></i>\n");
      out.write("                        <h3 style=\"font-family:'Playfair Display'; color:#3e2723;\">Your cart is empty.</h3>\n");
      out.write("                        <p style=\"color:#777; margin-bottom:30px;\">Looks like you haven't added anything yet.</p>\n");
      out.write("                        <a href=\"shop.jsp\" class=\"btn-checkout\" style=\"width:auto; padding:15px 40px; background:#c0392b;\">Start Shopping</a>\n");
      out.write("                    </div>\n");
      out.write("                ");

                    } else {
                        for (CartItem item : cart) {
                
      out.write("\n");
      out.write("                    <div class=\"cart-item\">\n");
      out.write("                        <img src=\"");
      out.print( item.getImage() );
      out.write("\" alt=\"Product\" class=\"item-image\">\n");
      out.write("                        \n");
      out.write("                        <div class=\"item-details\">\n");
      out.write("                            <h3>");
      out.print( item.getName() );
      out.write("</h3>\n");
      out.write("                            <span class=\"item-desc\">\n");
      out.write("                                ");
      out.print( item.getCustomization() != null && !item.getCustomization().isEmpty() ? "Note: " + item.getCustomization() : "" );
      out.write("\n");
      out.write("                            </span>\n");
      out.write("                            <span class=\"item-price\">₹");
      out.print( item.getPrice() );
      out.write("</span>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"item-actions\">\n");
      out.write("                            <span class=\"item-total\">₹");
      out.print( item.getTotal() );
      out.write("</span>\n");
      out.write("                            \n");
      out.write("                            <!-- Quantity Controls -->\n");
      out.write("                            <div class=\"qty-control\">\n");
      out.write("                                <a href=\"CartServlet?action=update&pid=");
      out.print( item.getId() );
      out.write("&qty=");
      out.print( item.getQuantity() - 1 );
      out.write("\" class=\"qty-btn\">-</a>\n");
      out.write("                                <span class=\"qty-display\">");
      out.print( item.getQuantity() );
      out.write("</span>\n");
      out.write("                                <a href=\"CartServlet?action=update&pid=");
      out.print( item.getId() );
      out.write("&qty=");
      out.print( item.getQuantity() + 1 );
      out.write("\" class=\"qty-btn\">+</a>\n");
      out.write("                            </div>\n");
      out.write("                            \n");
      out.write("                            <br>\n");
      out.write("                            <a href=\"CartServlet?action=remove&pid=");
      out.print( item.getId() );
      out.write("\" class=\"btn-remove\">\n");
      out.write("                                <i class=\"fas fa-trash\"></i> Remove\n");
      out.write("                            </a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                ");

                        }
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Summary Column -->\n");
      out.write("            ");
 if (cart != null && !cart.isEmpty()) { 
                double totalAmount = 0;
                for (CartItem item : cart) { totalAmount += item.getTotal(); }
            
      out.write("\n");
      out.write("            <div class=\"summary-card\">\n");
      out.write("                <h3>Order Summary</h3>\n");
      out.write("                \n");
      out.write("                <div class=\"summary-row\">\n");
      out.write("                    <span>Subtotal</span>\n");
      out.write("                    <span>₹");
      out.print( totalAmount );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"summary-row\" style=\"color:#27ae60;\">\n");
      out.write("                    <span>Delivery</span>\n");
      out.write("                    <span>");
      out.print( totalAmount > 500 ? "FREE" : "₹50" );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"summary-row total\">\n");
      out.write("                    <span>Total</span>\n");
      out.write("                    <span>₹");
      out.print( totalAmount + (totalAmount > 500 ? 0 : 50) );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <!-- UPDATED LINK TO SERVLET -->\n");
      out.write("                <a href=\"CartServlet?action=checkout\" class=\"btn-checkout\">\n");
      out.write("                    Proceed to Checkout <i class=\"fas fa-arrow-right\"></i>\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- TOAST CONTAINER -->\n");
      out.write("        <div id=\"toast-container\" class=\"toast-container\"></div>\n");
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
      out.write("\n");
      out.write("        <!-- TOAST SCRIPT -->\n");
      out.write("        <script>\n");
      out.write("            ");

                String toastMsg = (String) session.getAttribute("toast_msg");
                String toastType = (String) session.getAttribute("toast_type");
                if (toastMsg != null) {
                    session.removeAttribute("toast_msg");
                    session.removeAttribute("toast_type");
            
      out.write("\n");
      out.write("                    window.onload = function() { showToast(\"");
      out.print( toastMsg );
      out.write("\", \"");
      out.print( toastType );
      out.write("\"); };\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("\n");
      out.write("            function showToast(message, type) {\n");
      out.write("                var container = document.getElementById('toast-container');\n");
      out.write("                if (!container) return;\n");
      out.write("                var toast = document.createElement('div');\n");
      out.write("                toast.className = 'toast ' + type;\n");
      out.write("                toast.innerText = message;\n");
      out.write("                container.appendChild(toast);\n");
      out.write("                setTimeout(function() { toast.classList.add('show'); }, 100);\n");
      out.write("                setTimeout(function() {\n");
      out.write("                    toast.classList.remove('show');\n");
      out.write("                    setTimeout(function() { toast.remove(); }, 500);\n");
      out.write("                }, 3000);\n");
      out.write("            }\n");
      out.write("        </script>\n");
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
