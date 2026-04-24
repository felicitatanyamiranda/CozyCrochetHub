package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.cozycrochet.models.User;
import java.sql.*;
import java.util.*;
import com.cozycrochet.db.DBConnection;

public final class shop_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Cozy Crochet Hub</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("        <style>\n");
      out.write("            /* --- BASE --- */\n");
      out.write("            * { box-sizing: border-box; }\n");
      out.write("            body { margin: 0; padding: 0; font-family: 'Lato', sans-serif; background: #fdfbf7; color: #333; }\n");
      out.write("            \n");
      out.write("            /* --- 1. NAVBAR --- */\n");
      out.write("            .nav-overlay {\n");
      out.write("                position: absolute; top: 0; left: 0; width: 100%; z-index: 100;\n");
      out.write("                padding: 25px 5%; display: flex; justify-content: space-between; align-items: center;\n");
      out.write("                background: linear-gradient(to bottom, rgba(0,0,0,0.7), transparent);\n");
      out.write("            }\n");
      out.write("            .nav-overlay h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 30px; text-shadow: 0 2px 10px rgba(0,0,0,0.5); }\n");
      out.write("            .nav-links { display: flex; align-items: center; gap: 30px; }\n");
      out.write("            .nav-links a { color: rgba(255,255,255,0.9); text-decoration: none; font-size: 14px; font-weight: 500; text-transform: uppercase; letter-spacing: 1px; transition: 0.3s; }\n");
      out.write("            .nav-links a:hover { color: #ffab91; text-shadow: 0 0 10px rgba(255,171,145,0.5); }\n");
      out.write("            .search-nav { position: relative; }\n");
      out.write("            .search-nav input {\n");
      out.write("                background: rgba(255,255,255,0.15); border: 1px solid rgba(255,255,255,0.3);\n");
      out.write("                padding: 10px 20px; border-radius: 30px; color: white; width: 200px;\n");
      out.write("                font-size: 13px; outline: none; transition: 0.4s; backdrop-filter: blur(5px);\n");
      out.write("            }\n");
      out.write("            .search-nav input::placeholder { color: rgba(255,255,255,0.6); }\n");
      out.write("            .search-nav input:focus { background: rgba(255,255,255,0.25); width: 250px; border-color: #ffab91; }\n");
      out.write("\n");
      out.write("            /* --- 2. HERO --- */\n");
      out.write("            .hero-section {\n");
      out.write("                height: 100vh; display: flex; align-items: center; justify-content: center;\n");
      out.write("                background: url('https://i.pinimg.com/1200x/6d/e4/9a/6de49a938f344f0937f63f75417839b2.jpg') center/cover fixed;\n");
      out.write("                position: relative; text-align: center; color: white;\n");
      out.write("            }\n");
      out.write("            .hero-overlay { position: absolute; top:0; left:0; width:100%; height:100%; background: linear-gradient(135deg, rgba(62, 39, 35, 0.9), rgba(0,0,0,0.6)); }\n");
      out.write("            .hero-content { position: relative; z-index: 2; max-width: 850px; padding: 0 20px; }\n");
      out.write("            .hero-content h1 {\n");
      out.write("                font-family: 'Playfair Display', serif; font-size: 90px; margin: 0 0 20px;\n");
      out.write("                text-shadow: 0 10px 30px rgba(0,0,0,0.5); line-height: 1.1; animation: fadeUp 1s ease;\n");
      out.write("            }\n");
      out.write("            .hero-content p { font-size: 22px; margin-bottom: 50px; opacity: 0.9; font-weight: 300; letter-spacing: 1px; animation: fadeUp 1.2s ease; }\n");
      out.write("            .btn-hero {\n");
      out.write("                display: inline-block; padding: 18px 55px; background: white; color: #3e2723;\n");
      out.write("                text-decoration: none; border-radius: 50px; font-weight: 700;\n");
      out.write("                text-transform: uppercase; letter-spacing: 2px; font-size: 14px;\n");
      out.write("                box-shadow: 0 15px 40px rgba(0,0,0,0.3); transition: transform 0.3s, box-shadow 0.3s;\n");
      out.write("                animation: fadeUp 1.4s ease;\n");
      out.write("            }\n");
      out.write("            .btn-hero:hover { transform: translateY(-5px); box-shadow: 0 20px 50px rgba(0,0,0,0.4); background: #fffbf5; }\n");
      out.write("            @keyframes fadeUp { from { opacity:0; transform: translateY(30px); } to { opacity:1; transform: translateY(0); } }\n");
      out.write("\n");
      out.write("            /* --- 3. PRODUCTS (PROFESSIONAL GRID) --- */\n");
      out.write("            .main-content-wrapper { padding: 100px 20px; background: #fdfbf7; position: relative; z-index: 2; }\n");
      out.write("\n");
      out.write("            .section-header { text-align: center; margin-bottom: 70px; }\n");
      out.write("            .section-header h2 { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 50px; margin: 0; }\n");
      out.write("            .section-header p { color: #888; font-size: 16px; margin-top: 15px; font-style: italic; }\n");
      out.write("\n");
      out.write("            .filter-bar { margin-bottom: 60px; text-align: center; }\n");
      out.write("            .filter-bar a {\n");
      out.write("                text-decoration: none; display: inline-block; margin: 5px; padding: 12px 28px;\n");
      out.write("                border-radius: 30px; font-size: 14px; font-weight: 600; letter-spacing: 0.5px;\n");
      out.write("                background: white; color: #3e2723; border: 2px solid transparent;\n");
      out.write("                box-shadow: 0 5px 15px rgba(0,0,0,0.05); transition: all 0.3s;\n");
      out.write("            }\n");
      out.write("            .filter-bar a:hover, .filter-bar a.active { background: #3e2723; color: white; transform: translateY(-3px); box-shadow: 0 10px 20px rgba(62,39,35,0.15); }\n");
      out.write("\n");
      out.write("            .product-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 50px; max-width: 1400px; margin: 0 auto; }\n");
      out.write("            \n");
      out.write("            /* Professional Card Style */\n");
      out.write("            .product-card {\n");
      out.write("                background: white; border-radius: 15px; overflow: hidden;\n");
      out.write("                box-shadow: 0 15px 35px rgba(0,0,0,0.06); transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);\n");
      out.write("                position: relative; border: 1px solid rgba(0,0,0,0.02);\n");
      out.write("            }\n");
      out.write("            .product-card:hover { transform: translateY(-15px); box-shadow: 0 30px 60px rgba(0,0,0,0.12); }\n");
      out.write("            \n");
      out.write("            .product-img-box { height: 340px; overflow: hidden; position: relative; cursor: pointer; }\n");
      out.write("            .product-img-box img { width: 100%; height: 100%; object-fit: cover; transition: transform 0.6s; }\n");
      out.write("            .product-card:hover .product-img-box img { transform: scale(1.08); }\n");
      out.write("\n");
      out.write("            /* Hover Overlay */\n");
      out.write("            .product-overlay {\n");
      out.write("                position: absolute; top:0; left:0; width:100%; height:100%;\n");
      out.write("                background: rgba(62, 39, 35, 0.75); display: flex; align-items: center; justify-content: center;\n");
      out.write("                opacity: 0; transition: opacity 0.4s;\n");
      out.write("            }\n");
      out.write("            .product-card:hover .product-overlay { opacity: 1; }\n");
      out.write("            .view-btn {\n");
      out.write("                background: white; color: #3e2723; padding: 15px 30px; border-radius: 30px;\n");
      out.write("                font-weight: 700; font-size: 14px; text-transform: uppercase; letter-spacing: 1px;\n");
      out.write("                transform: translateY(20px); transition: 0.3s; border: none; cursor: pointer;\n");
      out.write("            }\n");
      out.write("            .product-card:hover .view-btn { transform: translateY(0); }\n");
      out.write("\n");
      out.write("            .product-info { padding: 28px; text-align: center; background: white; }\n");
      out.write("            .product-title { font-family: 'Playfair Display', serif; font-size: 22px; color: #3e2723; margin-bottom: 8px; }\n");
      out.write("            .product-price { font-size: 20px; color: #c0392b; font-weight: 700; }\n");
      out.write("\n");
      out.write("            /* Badges */\n");
      out.write("            .badge-container { position: absolute; top: 20px; left: 20px; z-index: 10; display: flex; flex-direction: column; gap: 10px; }\n");
      out.write("            .badge-tag {\n");
      out.write("                padding: 8px 18px; border-radius: 30px; font-size: 11px; font-weight: 700;\n");
      out.write("                text-transform: uppercase; letter-spacing: 0.5px; box-shadow: 0 5px 15px rgba(0,0,0,0.15);\n");
      out.write("                backdrop-filter: blur(5px); display: flex; align-items: center; gap: 6px;\n");
      out.write("            }\n");
      out.write("            .badge-sale { background: linear-gradient(135deg, #ff416c, #ff4b2b); color: white; }\n");
      out.write("            .badge-out { background: #2d3436; color: white; }\n");
      out.write("\n");
      out.write("            /* Wishlist */\n");
      out.write("            .wishlist-btn {\n");
      out.write("                position: absolute; top: 20px; right: 20px; background: white;\n");
      out.write("                width: 50px; height: 50px; border-radius: 50%; display: flex;\n");
      out.write("                align-items: center; justify-content: center; box-shadow: 0 8px 20px rgba(0,0,0,0.15);\n");
      out.write("                color: #ccc; font-size: 22px; transition: all 0.3s; z-index: 5;\n");
      out.write("            }\n");
      out.write("            .wishlist-btn:hover, .wishlist-btn.active { color: #ff4757; transform: scale(1.15) rotate(10deg); }\n");
      out.write("\n");
      out.write("            /* --- 4. ABOUT US (EDITORIAL STYLE) --- */\n");
      out.write("            .about-story-section {\n");
      out.write("                padding: 120px 5%; background: white; position: relative; overflow: hidden;\n");
      out.write("            }\n");
      out.write("            .about-container {\n");
      out.write("                max-width: 1300px; margin: 0 auto; display: grid; grid-template-columns: 1fr 1fr; gap: 100px; align-items: center;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            /* Left: Image with Decorative Border */\n");
      out.write("            .about-visual { position: relative; }\n");
      out.write("            .about-visual::before {\n");
      out.write("                content: ''; position: absolute; width: 100%; height: 100%;\n");
      out.write("                border: 4px solid #3e2723; top: 30px; left: 30px; border-radius: 12px; z-index: 0;\n");
      out.write("            }\n");
      out.write("            .about-visual img {\n");
      out.write("                width: 100%; height: 600px; object-fit: cover; border-radius: 12px;\n");
      out.write("                box-shadow: 0 30px 60px rgba(0,0,0,0.15); position: relative; z-index: 1;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            /* Right: Text Content */\n");
      out.write("            .about-text { position: relative; }\n");
      out.write("            .about-label {\n");
      out.write("                display: inline-block; font-size: 14px; color: #c0392b; text-transform: uppercase;\n");
      out.write("                font-weight: 700; letter-spacing: 3px; margin-bottom: 20px;\n");
      out.write("            }\n");
      out.write("            .about-text h2 {\n");
      out.write("                font-family: 'Playfair Display', serif; font-size: 52px; color: #3e2723;\n");
      out.write("                margin: 0 0 30px; line-height: 1.2;\n");
      out.write("            }\n");
      out.write("            .about-text p { font-size: 18px; line-height: 1.9; color: #555; margin-bottom: 40px; }\n");
      out.write("            \n");
      out.write("            /* Stats Row */\n");
      out.write("            .stats-row { display: flex; gap: 50px; margin-top: 20px; }\n");
      out.write("            .stat-item { text-align: left; }\n");
      out.write("            .stat-item h3 { font-family: 'Playfair Display', serif; font-size: 42px; color: #3e2723; margin: 0; }\n");
      out.write("            .stat-item p { font-size: 14px; color: #888; text-transform: uppercase; letter-spacing: 1px; margin-top: 5px; }\n");
      out.write("\n");
      out.write("            /* --- 5. MODAL --- */\n");
      out.write("            .modal-overlay { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.85); z-index: 99999; display: none; align-items: center; justify-content: center; padding: 20px; }\n");
      out.write("            .modal-content {\n");
      out.write("                background: white; width: 950px; max-width: 100%; height: auto; max-height: 95vh;\n");
      out.write("                border-radius: 20px; display: flex; overflow: hidden; position: relative;\n");
      out.write("                box-shadow: 0 40px 80px rgba(0,0,0,0.5); animation: popIn 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);\n");
      out.write("            }\n");
      out.write("            @keyframes popIn { from { transform: scale(0.8); opacity: 0; } to { transform: scale(1); opacity: 1; } }\n");
      out.write("            \n");
      out.write("            .modal-left { width: 50%; background: #f5f5dc; display: flex; align-items: center; justify-content: center; padding: 40px; }\n");
      out.write("            .modal-left img { max-width: 100%; max-height: 85vh; border-radius: 12px; box-shadow: 0 20px 40px rgba(0,0,0,0.2); }\n");
      out.write("            \n");
      out.write("            .modal-right { width: 50%; padding: 50px; display: flex; flex-direction: column; overflow-y: auto; max-height: 95vh; position: relative; }\n");
      out.write("            .modal-close {\n");
      out.write("                position: absolute; top: 25px; right: 25px; font-size: 24px; color: #333; background: #f0f0f0;\n");
      out.write("                width: 40px; height: 40px; border-radius: 50%; display: flex; align-items: center; justify-content: center;\n");
      out.write("                cursor: pointer; z-index: 10; transition: 0.3s; font-weight: bold;\n");
      out.write("            }\n");
      out.write("            .modal-close:hover { background: #3e2723; color: white; }\n");
      out.write("            \n");
      out.write("            .input-field { width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px; font-size: 15px; transition: 0.3s; }\n");
      out.write("            .input-field:focus { border-color: #3e2723; outline: none; }\n");
      out.write("\n");
      out.write("            @media (max-width: 900px) { \n");
      out.write("                .modal-content, .about-container { flex-direction: column; width: 100%; grid-template-columns: 1fr; gap: 50px;\n");
      out.write("            .about-visual::before { display: none; }\n");
      out.write("            .hero-content h1 { font-size: 50px; }\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            List<Integer> wishIds = new ArrayList<Integer>();
            try {
                Connection conW = DBConnection.getConnection();
                String wSql = "SELECT product_id FROM Wishlist WHERE user_id = ?";
                PreparedStatement psW = conW.prepareStatement(wSql);
                psW.setInt(1, user.getId());
                ResultSet rsW = psW.executeQuery();
                while(rsW.next()) { wishIds.add(rsW.getInt(1)); }
                conW.close();
            } catch(Exception e) {}
        
      out.write("\n");
      out.write("        \n");
      out.write("        <div id=\"toast-container\" class=\"toast-container\"></div>\n");
      out.write("        \n");
      out.write("        <!-- NAVBAR -->\n");
      out.write("        <nav class=\"nav-overlay\">\n");
      out.write("            <h1>Cozy Crochet</h1>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"shop.jsp\">Home</a>\n");
      out.write("                <a href=\"wishlist.jsp\">Wishlist</a>\n");
      out.write("                <a href=\"cart.jsp\">Cart</a>\n");
      out.write("                <a href=\"my_account.jsp\">Account</a>\n");
      out.write("                <a href=\"contact.jsp\">Contact</a>\n");
      out.write("                <a href=\"logout.jsp\">Logout</a>\n");
      out.write("                <div class=\"search-nav\">\n");
      out.write("                    <form action=\"shop.jsp#products\" method=\"get\">\n");
      out.write("                        <input type=\"text\" name=\"search\" placeholder=\"Search...\">\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- HERO -->\n");
      out.write("        <div class=\"hero-section\">\n");
      out.write("            <div class=\"hero-overlay\"></div>\n");
      out.write("            <div class=\"hero-content\">\n");
      out.write("                <h1>Handmade With Love</h1>\n");
      out.write("                <p>Discover unique, handcrafted crochet pieces designed to bring warmth into your life.</p>\n");
      out.write("                <a href=\"#products\" class=\"btn-hero\">Explore Collection</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"main-content-wrapper\" id=\"products\">\n");
      out.write("            <div class=\"section-header\">\n");
      out.write("                <h2>Our Collection</h2>\n");
      out.write("                <p>Find your next favorite piece</p>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"filter-bar\">\n");
      out.write("                <a href=\"shop.jsp#products\" class=\"");
      out.print( (request.getParameter("category") == null) ? "active" : "" );
      out.write("\">All</a>\n");
      out.write("                ");

                    Connection conNav = DBConnection.getConnection();
                    Statement stmtNav = conNav.createStatement();
                    ResultSet rsNav = stmtNav.executeQuery("SELECT * FROM Categories");
                    while(rsNav.next()) {
                        int cid = rsNav.getInt("category_id");
                        String cname = rsNav.getString("category_name");
                        String currentCat = request.getParameter("category");
                        boolean isActive = (currentCat != null && currentCat.equals(String.valueOf(cid)));
                
      out.write("\n");
      out.write("                        <a href=\"shop.jsp?category=");
      out.print( cid );
      out.write("#products\" class=\"");
      out.print( isActive ? "active" : "" );
      out.write('"');
      out.write('>');
      out.print( cname );
      out.write("</a>\n");
      out.write("                ");

                    }
                    conNav.close();
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");

                Connection con = null;
                try {
                    con = DBConnection.getConnection();
                    
                    String search = request.getParameter("search");
                    String catId = request.getParameter("category");
                    
                    String sql = "SELECT p.*, c.category_name FROM Products p JOIN Categories c ON p.category_id = c.category_id WHERE 1=1";
                    if (search != null && !search.trim().equals("")) sql += " AND (p.name LIKE '%"+search+"%' OR p.description LIKE '%"+search+"%')";
                    if (catId != null && !catId.equals("")) sql += " AND p.category_id = " + catId;
                    sql += " ORDER BY p.stock DESC";
                    
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
            
      out.write("\n");
      out.write("            <div class=\"product-grid\">\n");
      out.write("                ");

                    while (rs.next()) {
                        int pid = rs.getInt("product_id");
                        int stock = rs.getInt("stock");
                        double price = rs.getDouble("price");
                        boolean isAdmin = "admin".equals(user.getRole());
                        
                        boolean isOutOfStock = (stock <= 0);
                        boolean isOnSale = (!isOutOfStock && price < 300);
                
      out.write("\n");
      out.write("                        <div class=\"product-card\" \n");
      out.write("                             data-name=\"");
      out.print( rs.getString("name") );
      out.write("\" \n");
      out.write("                             data-desc=\"");
      out.print( rs.getString("description") );
      out.write("\"\n");
      out.write("                             data-price=\"");
      out.print( price );
      out.write("\"\n");
      out.write("                             data-img=\"");
      out.print( rs.getString("image_url") );
      out.write("\"\n");
      out.write("                             data-pid=\"");
      out.print( pid );
      out.write("\"\n");
      out.write("                             data-stock=\"");
      out.print( stock );
      out.write("\">\n");
      out.write("\n");
      out.write("                            <div class=\"badge-container\">\n");
      out.write("                                ");
 if(isOutOfStock) { 
      out.write("\n");
      out.write("                                    <span class=\"badge-tag badge-out\"><i class=\"fas fa-times-circle\"></i> Out of Stock</span>\n");
      out.write("                                ");
 } else if(isOnSale) { 
      out.write("\n");
      out.write("                                    <span class=\"badge-tag badge-sale\"><i class=\"fas fa-bolt\"></i> On Sale</span>\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            ");
 if(!isAdmin) { 
      out.write("\n");
      out.write("                                <a href=\"WishlistServlet?action=add&pid=");
      out.print( pid );
      out.write("\" class=\"wishlist-btn ");
      out.print( wishIds.contains(pid) ? "active" : "" );
      out.write("\" onclick=\"event.stopPropagation();\">♥</a>\n");
      out.write("                            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                            <div class=\"product-img-box\" onclick=\"openProductModal(this.parentNode)\">\n");
      out.write("                                <img src=\"");
      out.print( rs.getString("image_url") );
      out.write("\">\n");
      out.write("                                <div class=\"product-overlay\">\n");
      out.write("                                    <button class=\"view-btn\">Quick View</button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            \n");
      out.write("                            <div class=\"product-info\">\n");
      out.write("                                <div class=\"product-title\">");
      out.print( rs.getString("name") );
      out.write("</div>\n");
      out.write("                                ");
 if(!isOutOfStock && stock <= 5) { 
      out.write("\n");
      out.write("                                    <p style=\"color: #d32f2f; font-size: 13px; font-weight: bold;\">🔥 Only ");
      out.print( stock );
      out.write(" left!</p>\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                                <div class=\"product-price\">\n");
      out.write("                                    ");
 if(isOnSale) { 
      out.write("\n");
      out.write("                                        <span style=\"text-decoration:line-through; color:#999; font-size:16px; margin-right:10px;\">₹");
      out.print( price + 100 );
      out.write("</span>\n");
      out.write("                                    ");
 } 
      out.write("\n");
      out.write("                                    ₹");
      out.print( price );
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            \n");
      out.write("                            <div id=\"reviews-data-");
      out.print( pid );
      out.write("\" style=\"display:none;\">\n");
      out.write("                                ");

                                    String revSql = "SELECT r.rating, r.comment, u.full_name FROM Reviews r JOIN Users u ON r.user_id = u.user_id WHERE r.product_id = ?";
                                    PreparedStatement psRev = con.prepareStatement(revSql);
                                    psRev.setInt(1, pid);
                                    ResultSet rsRev = psRev.executeQuery();
                                    boolean hasRev = false;
                                    while(rsRev.next()) { hasRev = true; int rVal = rsRev.getInt("rating");
                                
      out.write("\n");
      out.write("                                    <div style=\"background:#f9f9f9; padding:15px; border-radius:8px; margin-bottom:10px; border-left:3px solid #8d6e63;\">\n");
      out.write("                                        <div style=\"display:flex; justify-content:space-between; margin-bottom:5px;\">\n");
      out.write("                                            <strong style=\"font-size:14px;\">");
      out.print( rsRev.getString("full_name") );
      out.write("</strong>\n");
      out.write("                                            <div style=\"color:#f39c12; font-size:12px;\">\n");
      out.write("                                                ");
 for(int i=1; i<=5; i++) { if(i <= rVal) { 
      out.write('★');
 } else { 
      out.write('☆');
 } } 
      out.write("\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                        <p style=\"margin:0; color:#555; font-size:13px;\">");
      out.print( rsRev.getString("comment") );
      out.write("</p>\n");
      out.write("                                    </div>\n");
      out.write("                                ");

                                    }
                                    if(!hasRev) { out.println("<p style='text-align:center; color:#777; font-size:14px;'>No reviews yet.</p>"); }
                                
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                ");

                    }
                } catch (Exception e) {
                    out.println("<p style='color:red; text-align:center;'>Error loading products</p>");
                } finally {
                    if (con != null) try { con.close(); } catch (Exception e) {}
                }
            
      out.write("\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- About Us Section -->\n");
      out.write("            <div class=\"about-story-section\">\n");
      out.write("                <div class=\"about-container\">\n");
      out.write("                    <div class=\"about-visual\">\n");
      out.write("                        <img src=\"https://i.pinimg.com/736x/b9/cf/2f/b9cf2fd61dfcd3ed268619bd2a7bc936.jpg\" alt=\"Cozy Crochet\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"about-text\">\n");
      out.write("                        <span class=\"about-label\">Our Story</span>\n");
      out.write("                        <h2>Handmade with Love & Passion</h2>\n");
      out.write("                        <p>Welcome to Cozy Crochet Hub. We are a small team of passionate artisans who believe in the beauty of handmade products. What started as a hobby has grown into a community of craft lovers.</p>\n");
      out.write("                        <p>We use only the highest quality yarns to ensure every item is soft, durable, and beautiful. Thank you for supporting our dream!</p>\n");
      out.write("                        \n");
      out.write("                        <div class=\"stats-row\">\n");
      out.write("                            <div class=\"stat-item\">\n");
      out.write("                                <h3>5+</h3>\n");
      out.write("                                <p>Years Experience</p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"stat-item\">\n");
      out.write("                                <h3>1000+</h3>\n");
      out.write("                                <p>Happy Customers</p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"stat-item\">\n");
      out.write("                                <h3>100%</h3>\n");
      out.write("                                <p>Handmade</p>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- MODAL -->\n");
      out.write("        <div id=\"quickViewModal\" class=\"modal-overlay\">\n");
      out.write("            <div class=\"modal-content\" onclick=\"event.stopPropagation()\">\n");
      out.write("                <span class=\"modal-close\" onclick=\"closeModal()\">&times;</span>\n");
      out.write("                \n");
      out.write("                <div class=\"modal-left\">\n");
      out.write("                    <img id=\"modal-img\" src=\"\" alt=\"Product\">\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"modal-right\">\n");
      out.write("                    <h2 id=\"modal-title\" style=\"font-family: 'Playfair Display', serif; color: #3e2723; margin-top:0;\"></h2>\n");
      out.write("                    <p id=\"modal-stock\" style=\"font-size: 14px; font-weight: bold; margin-bottom: 10px;\"></p>\n");
      out.write("                    <p id=\"modal-desc\" style=\"color: #555; line-height: 1.6;\"></p>\n");
      out.write("                    <h3 id=\"modal-price\" style=\"color: #3e2723; font-size: 28px; margin: 20px 0;\"></h3>\n");
      out.write("                    \n");
      out.write("                    ");
 if(!"admin".equals(user.getRole())) { 
      out.write("\n");
      out.write("                    <form action=\"CartServlet\" method=\"post\" id=\"cartForm\">\n");
      out.write("                        <input type=\"hidden\" name=\"pid\" id=\"modal-pid\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"add\">\n");
      out.write("                        <input type=\"hidden\" name=\"buyNow\" id=\"buyNowFlag\" value=\"false\">\n");
      out.write("                        \n");
      out.write("                        <div style=\"margin-bottom: 15px;\">\n");
      out.write("                            <label style=\"font-size: 14px; color: #555;\">Customization:</label>\n");
      out.write("                            <input type=\"text\" name=\"customization\" class=\"input-field\" placeholder=\"Add a note (optional)\">\n");
      out.write("                        </div>\n");
      out.write("                        <div style=\"display: flex; gap: 10px; align-items: center;\">\n");
      out.write("                            <input type=\"number\" name=\"qty\" value=\"1\" min=\"1\" style=\"width: 60px; padding: 12px; border: 2px solid #eee; border-radius: 8px;\">\n");
      out.write("                            <button type=\"submit\" id=\"modal-add-btn\" class=\"btn\" style=\"background:#3e2723; flex:1; border:none; cursor:pointer;\">Add to Cart</button>\n");
      out.write("                        </div>\n");
      out.write("                        <button type=\"button\" id=\"buy-now-btn\" class=\"btn btn-full\" style=\"margin-top:10px; background:#27ae60; border:none; cursor:pointer;\">Buy Now</button>\n");
      out.write("                    </form>\n");
      out.write("                    <script>\n");
      out.write("                        document.getElementById('buy-now-btn').addEventListener('click', function() {\n");
      out.write("                            document.getElementById('buyNowFlag').value = \"true\";\n");
      out.write("                            document.getElementById('cartForm').submit();\n");
      out.write("                        });\n");
      out.write("                    </script>\n");
      out.write("                    ");
 } else { 
      out.write("\n");
      out.write("                        <p style=\"color:#c0392b; text-align:center; padding:20px; background:#ffebee; border-radius:5px;\">Admin View</p>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("                    <hr style=\"margin: 25px 0; border-color:#eee;\">\n");
      out.write("                    <h4 style=\"font-family:'Playfair Display'; color:#3e2723; margin:0 0 15px;\">Customer Reviews</h4>\n");
      out.write("                    <div id=\"modal-reviews-container\"></div>\n");
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
      out.write("        <!-- SCRIPTS -->\n");
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
      out.write("\n");
      out.write("            function openProductModal(el) {\n");
      out.write("                var name = el.getAttribute('data-name');\n");
      out.write("                var desc = el.getAttribute('data-desc');\n");
      out.write("                var price = el.getAttribute('data-price');\n");
      out.write("                var img = el.getAttribute('data-img');\n");
      out.write("                var pid = el.getAttribute('data-pid');\n");
      out.write("                var stock = el.getAttribute('data-stock');\n");
      out.write("\n");
      out.write("                document.getElementById('modal-title').innerText = name;\n");
      out.write("                document.getElementById('modal-desc').innerText = desc;\n");
      out.write("                \n");
      out.write("                var priceFloat = parseFloat(price);\n");
      out.write("                document.getElementById('modal-price').innerText = \"₹\" + priceFloat.toLocaleString('en-IN');\n");
      out.write("                \n");
      out.write("                document.getElementById('modal-img').src = img;\n");
      out.write("                document.getElementById('modal-pid').value = pid;\n");
      out.write("                \n");
      out.write("                var hiddenReviews = document.getElementById('reviews-data-' + pid);\n");
      out.write("                var reviewContainer = document.getElementById('modal-reviews-container');\n");
      out.write("                if (hiddenReviews) { reviewContainer.innerHTML = hiddenReviews.innerHTML; } \n");
      out.write("                else { reviewContainer.innerHTML = \"<p style='color:#777;'>No reviews yet.</p>\"; }\n");
      out.write("                \n");
      out.write("                var stockElement = document.getElementById('modal-stock');\n");
      out.write("                var addBtn = document.getElementById('modal-add-btn');\n");
      out.write("                \n");
      out.write("                if (parseInt(stock) > 0) {\n");
      out.write("                    stockElement.innerHTML = \"<span style='color:#27ae60;'><i class='fas fa-check-circle'></i> In Stock</span>\";\n");
      out.write("                    if(addBtn) { addBtn.disabled = false; addBtn.style.backgroundColor = \"#3e2723\"; }\n");
      out.write("                } else {\n");
      out.write("                    stockElement.innerHTML = \"<span style='color:#c0392b;'><i class='fas fa-times-circle'></i> Out of Stock</span>\";\n");
      out.write("                    if(addBtn) { addBtn.disabled = true; addBtn.style.backgroundColor = \"#ccc\"; }\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                document.getElementById('quickViewModal').style.display = 'flex';\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function closeModal() { document.getElementById('quickViewModal').style.display = 'none'; }\n");
      out.write("            document.getElementById('quickViewModal').addEventListener('click', function(e) { if (e.target === this) { closeModal(); } });\n");
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
