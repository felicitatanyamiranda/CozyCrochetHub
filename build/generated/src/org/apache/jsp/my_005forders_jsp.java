package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import com.cozycrochet.db.DBConnection;

public final class my_005forders_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>My Orders</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <style>\n");
      out.write("            .order-card { background: white; padding: 25px; border-radius: 15px; margin-bottom: 20px; box-shadow: 0 5px 15px rgba(0,0,0,0.05); border-left: 5px solid #3e2723; }\n");
      out.write("            .order-header { display: flex; justify-content: space-between; border-bottom: 1px solid #eee; padding-bottom: 10px; margin-bottom: 15px; flex-wrap: wrap; }\n");
      out.write("            .status-pill { padding: 5px 15px; border-radius: 20px; font-size: 12px; font-weight: bold; text-transform: uppercase; }\n");
      out.write("            .status-pending { background: #fff3cd; color: #856404; }\n");
      out.write("            .status-shipped { background: #cce5ff; color: #004085; }\n");
      out.write("            .status-delivered { background: #d4edda; color: #155724; }\n");
      out.write("            .item-list { background: #f9f9f9; padding: 15px; border-radius: 8px; margin-top: 15px; }\n");
      out.write("            .custom-note { font-size: 12px; color: #d32f2f; font-weight: bold; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"navbar\">\n");
      out.write("                <a href=\"shop.jsp\">Home</a>\n");
      out.write("                <a href=\"my_orders.jsp\" style=\"color:#ffab91;\">My Orders</a>\n");
      out.write("                <a href=\"cart.jsp\">Cart</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"shop-container\">\n");
      out.write("            <h2 style=\"font-family: 'Playfair Display', serif; color: #3e2723; margin-bottom: 30px;\">My Order History</h2>\n");
      out.write("            \n");
      out.write("            ");

                User user = (User) session.getAttribute("user");
                Connection con = null;
                try {
                    con = DBConnection.getConnection();
                    String sql = "SELECT * FROM Orders WHERE user_id = ? ORDER BY order_id DESC";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, user.getId());
                    ResultSet rs = ps.executeQuery();
                    
                    boolean hasOrders = false;
                    
                    while(rs.next()) {
                        hasOrders = true;
                        String status = rs.getString("status");
                        String statusClass = "status-pending";
                        if("Shipped".equals(status)) statusClass = "status-shipped";
                        if("Delivered".equals(status)) statusClass = "status-delivered";
            
      out.write("\n");
      out.write("            \n");
      out.write("                    <div class=\"order-card\">\n");
      out.write("                        <div class=\"order-header\">\n");
      out.write("                            <div>\n");
      out.write("                                <h3 style=\"margin:0; color:#3e2723;\">Order #");
      out.print( rs.getInt("order_id") );
      out.write("</h3>\n");
      out.write("                                <p style=\"margin:5px 0 0; color:#777; font-size:14px;\">Placed on: ");
      out.print( rs.getDate("order_date") );
      out.write("</p>\n");
      out.write("                            </div>\n");
      out.write("                            <div style=\"text-align:right;\">\n");
      out.write("                                <span class=\"status-pill ");
      out.print( statusClass );
      out.write('"');
      out.write('>');
      out.print( status );
      out.write("</span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <p style=\"margin:0;\"><strong>Total:</strong> ₹");
      out.print( rs.getDouble("total_amount") );
      out.write("</p>\n");
      out.write("                        <p style=\"margin:5px 0;\"><strong>Payment:</strong> ");
      out.print( rs.getString("payment_method") );
      out.write("</p>\n");
      out.write("                        \n");
      out.write("                        <div class=\"item-list\">\n");
      out.write("                            ");
 
                                int orderId = rs.getInt("order_id");
                                String itemSql = "SELECT oi.*, p.name FROM Order_Items oi JOIN Products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";
                                PreparedStatement psItem = con.prepareStatement(itemSql);
                                psItem.setInt(1, orderId);
                                ResultSet rsItem = psItem.executeQuery();
                                while(rsItem.next()) {
                                    int productId = rsItem.getInt("product_id");
                            
      out.write("\n");
      out.write("                                <div style=\"display:flex; justify-content:space-between; align-items:center; margin-bottom:8px; font-size:14px;\">\n");
      out.write("                                    <div>\n");
      out.write("                                        ");
      out.print( rsItem.getString("name") );
      out.write(" (x");
      out.print( rsItem.getInt("quantity") );
      out.write(")\n");
      out.write("                                        ");
 
                                            String cust = rsItem.getString("customization");
                                            if(cust != null && !cust.trim().equals("")) {
                                        
      out.write("\n");
      out.write("                                            <span class=\"custom-note\">(");
      out.print( cust );
      out.write(")</span>\n");
      out.write("                                        ");
 } 
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                    \n");
      out.write("                                    <div style=\"text-align:right;\">\n");
      out.write("                                        <span style=\"color:#555;\">₹");
      out.print( rsItem.getDouble("price") * rsItem.getInt("quantity") );
      out.write("</span>\n");
      out.write("                                        \n");
      out.write("                                        <!-- FIX: WRITE REVIEW BUTTON -->\n");
      out.write("                                        ");
 if("Delivered".equals(status)) { 
      out.write("\n");
      out.write("                                            <br>\n");
      out.write("                                            <a href=\"review_product.jsp?pid=");
      out.print( productId );
      out.write("\" \n");
      out.write("                                               style=\"font-size:12px; color:#3e2723; font-weight:bold; text-decoration:underline; cursor:pointer;\">\n");
      out.write("                                                ✍ Write Review\n");
      out.write("                                            </a>\n");
      out.write("                                        ");
 } 
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            ");

                                }
                            
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        ");
 if("Pending".equals(status)) { 
      out.write("\n");
      out.write("                            <a href=\"CancelOrdersServlet?id=");
      out.print( rs.getInt("order_id") );
      out.write("\" class=\"btn btn-danger\" style=\"margin-top:15px; font-size:12px;\" onclick=\"return confirm('Cancel this order?')\">Cancel Order</a>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </div>\n");
      out.write("            \n");
      out.write("            ");

                    }
                    
                    if(!hasOrders) {
            
      out.write("\n");
      out.write("                <div style=\"text-align:center; padding:50px; background:white; border-radius:10px;\">\n");
      out.write("                    <h3 style=\"color:#3e2723;\">No orders yet</h3>\n");
      out.write("                    <p style=\"color:#777;\">Looks like you haven't placed any orders.</p>\n");
      out.write("                    <a href=\"shop.jsp\" class=\"btn\" style=\"background:#3e2723; color:white; text-decoration:none;\">Start Shopping</a>\n");
      out.write("                </div>\n");
      out.write("            ");

                    }
                    
                } catch (Exception e) {
                    out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
                } finally {
                    if(con != null) try { con.close(); } catch (Exception e) {}
                }
            
      out.write("\n");
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
