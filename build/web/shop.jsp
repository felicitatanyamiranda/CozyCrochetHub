<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cozy Crochet Hub</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            /* --- BASE --- */
            * { box-sizing: border-box; }
            body { margin: 0; padding: 0; font-family: 'Lato', sans-serif; background: #fdfbf7; color: #333; }
            
            /* --- 1. NAVBAR --- */
            .nav-overlay {
                position: absolute; top: 0; left: 0; width: 100%; z-index: 100;
                padding: 25px 5%; display: flex; justify-content: space-between; align-items: center;
                background: linear-gradient(to bottom, rgba(0,0,0,0.7), transparent);
            }
            .nav-overlay h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 30px; text-shadow: 0 2px 10px rgba(0,0,0,0.5); }
            .nav-links { display: flex; align-items: center; gap: 30px; }
            .nav-links a { color: rgba(255,255,255,0.9); text-decoration: none; font-size: 14px; font-weight: 500; text-transform: uppercase; letter-spacing: 1px; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; text-shadow: 0 0 10px rgba(255,171,145,0.5); }
            .search-nav { position: relative; }
            .search-nav input {
                background: rgba(255,255,255,0.15); border: 1px solid rgba(255,255,255,0.3);
                padding: 10px 20px; border-radius: 30px; color: white; width: 200px;
                font-size: 13px; outline: none; transition: 0.4s; backdrop-filter: blur(5px);
            }
            .search-nav input::placeholder { color: rgba(255,255,255,0.6); }
            .search-nav input:focus { background: rgba(255,255,255,0.25); width: 250px; border-color: #ffab91; }

            /* --- 2. HERO --- */
            .hero-section {
                height: 100vh; display: flex; align-items: center; justify-content: center;
                background: url('https://i.pinimg.com/1200x/6d/e4/9a/6de49a938f344f0937f63f75417839b2.jpg') center/cover fixed;
                position: relative; text-align: center; color: white;
            }
            .hero-overlay { position: absolute; top:0; left:0; width:100%; height:100%; background: linear-gradient(135deg, rgba(62, 39, 35, 0.9), rgba(0,0,0,0.6)); }
            .hero-content { position: relative; z-index: 2; max-width: 850px; padding: 0 20px; }
            .hero-content h1 {
                font-family: 'Playfair Display', serif; font-size: 90px; margin: 0 0 20px;
                text-shadow: 0 10px 30px rgba(0,0,0,0.5); line-height: 1.1; animation: fadeUp 1s ease;
            }
            .hero-content p { font-size: 22px; margin-bottom: 50px; opacity: 0.9; font-weight: 300; letter-spacing: 1px; animation: fadeUp 1.2s ease; }
            .btn-hero {
                display: inline-block; padding: 18px 55px; background: white; color: #3e2723;
                text-decoration: none; border-radius: 50px; font-weight: 700;
                text-transform: uppercase; letter-spacing: 2px; font-size: 14px;
                box-shadow: 0 15px 40px rgba(0,0,0,0.3); transition: transform 0.3s, box-shadow 0.3s;
                animation: fadeUp 1.4s ease;
            }
            .btn-hero:hover { transform: translateY(-5px); box-shadow: 0 20px 50px rgba(0,0,0,0.4); background: #fffbf5; }
            @keyframes fadeUp { from { opacity:0; transform: translateY(30px); } to { opacity:1; transform: translateY(0); } }

            /* --- 3. PRODUCTS (PROFESSIONAL GRID) --- */
            .main-content-wrapper { padding: 100px 20px; background: #fdfbf7; position: relative; z-index: 2; }

            .section-header { text-align: center; margin-bottom: 70px; }
            .section-header h2 { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 50px; margin: 0; }
            .section-header p { color: #888; font-size: 16px; margin-top: 15px; font-style: italic; }

            .filter-bar { margin-bottom: 60px; text-align: center; }
            .filter-bar a {
                text-decoration: none; display: inline-block; margin: 5px; padding: 12px 28px;
                border-radius: 30px; font-size: 14px; font-weight: 600; letter-spacing: 0.5px;
                background: white; color: #3e2723; border: 2px solid transparent;
                box-shadow: 0 5px 15px rgba(0,0,0,0.05); transition: all 0.3s;
            }
            .filter-bar a:hover, .filter-bar a.active { background: #3e2723; color: white; transform: translateY(-3px); box-shadow: 0 10px 20px rgba(62,39,35,0.15); }

            .product-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 50px; max-width: 1400px; margin: 0 auto; }
            
            /* Professional Card Style */
            .product-card {
                background: white; border-radius: 15px; overflow: hidden;
                box-shadow: 0 15px 35px rgba(0,0,0,0.06); transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
                position: relative; border: 1px solid rgba(0,0,0,0.02);
            }
            .product-card:hover { transform: translateY(-15px); box-shadow: 0 30px 60px rgba(0,0,0,0.12); }
            
            .product-img-box { height: 340px; overflow: hidden; position: relative; cursor: pointer; }
            .product-img-box img { width: 100%; height: 100%; object-fit: cover; transition: transform 0.6s; }
            .product-card:hover .product-img-box img { transform: scale(1.08); }

            /* Hover Overlay */
            .product-overlay {
                position: absolute; top:0; left:0; width:100%; height:100%;
                background: rgba(62, 39, 35, 0.75); display: flex; align-items: center; justify-content: center;
                opacity: 0; transition: opacity 0.4s;
            }
            .product-card:hover .product-overlay { opacity: 1; }
            .view-btn {
                background: white; color: #3e2723; padding: 15px 30px; border-radius: 30px;
                font-weight: 700; font-size: 14px; text-transform: uppercase; letter-spacing: 1px;
                transform: translateY(20px); transition: 0.3s; border: none; cursor: pointer;
            }
            .product-card:hover .view-btn { transform: translateY(0); }

            .product-info { padding: 28px; text-align: center; background: white; }
            .product-title { font-family: 'Playfair Display', serif; font-size: 22px; color: #3e2723; margin-bottom: 8px; }
            .product-price { font-size: 20px; color: #c0392b; font-weight: 700; }

            /* Badges */
            .badge-container { position: absolute; top: 20px; left: 20px; z-index: 10; display: flex; flex-direction: column; gap: 10px; }
            .badge-tag {
                padding: 8px 18px; border-radius: 30px; font-size: 11px; font-weight: 700;
                text-transform: uppercase; letter-spacing: 0.5px; box-shadow: 0 5px 15px rgba(0,0,0,0.15);
                backdrop-filter: blur(5px); display: flex; align-items: center; gap: 6px;
            }
            .badge-sale { background: linear-gradient(135deg, #ff416c, #ff4b2b); color: white; }
            .badge-out { background: #2d3436; color: white; }

            /* Wishlist */
            .wishlist-btn {
                position: absolute; top: 20px; right: 20px; background: white;
                width: 50px; height: 50px; border-radius: 50%; display: flex;
                align-items: center; justify-content: center; box-shadow: 0 8px 20px rgba(0,0,0,0.15);
                color: #ccc; font-size: 22px; transition: all 0.3s; z-index: 5;
            }
            .wishlist-btn:hover, .wishlist-btn.active { color: #ff4757; transform: scale(1.15) rotate(10deg); }

            /* --- 4. ABOUT US (EDITORIAL STYLE) --- */
            .about-story-section {
                padding: 120px 5%; background: white; position: relative; overflow: hidden;
            }
            .about-container {
                max-width: 1300px; margin: 0 auto; display: grid; grid-template-columns: 1fr 1fr; gap: 100px; align-items: center;
            }
            
            /* Left: Image with Decorative Border */
            .about-visual { position: relative; }
            .about-visual::before {
                content: ''; position: absolute; width: 100%; height: 100%;
                border: 4px solid #3e2723; top: 30px; left: 30px; border-radius: 12px; z-index: 0;
            }
            .about-visual img {
                width: 100%; height: 600px; object-fit: cover; border-radius: 12px;
                box-shadow: 0 30px 60px rgba(0,0,0,0.15); position: relative; z-index: 1;
            }
            
            /* Right: Text Content */
            .about-text { position: relative; }
            .about-label {
                display: inline-block; font-size: 14px; color: #c0392b; text-transform: uppercase;
                font-weight: 700; letter-spacing: 3px; margin-bottom: 20px;
            }
            .about-text h2 {
                font-family: 'Playfair Display', serif; font-size: 52px; color: #3e2723;
                margin: 0 0 30px; line-height: 1.2;
            }
            .about-text p { font-size: 18px; line-height: 1.9; color: #555; margin-bottom: 40px; }
            
            /* Stats Row */
            .stats-row { display: flex; gap: 50px; margin-top: 20px; }
            .stat-item { text-align: left; }
            .stat-item h3 { font-family: 'Playfair Display', serif; font-size: 42px; color: #3e2723; margin: 0; }
            .stat-item p { font-size: 14px; color: #888; text-transform: uppercase; letter-spacing: 1px; margin-top: 5px; }

            /* --- 5. MODAL --- */
            .modal-overlay { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.85); z-index: 99999; display: none; align-items: center; justify-content: center; padding: 20px; }
            .modal-content {
                background: white; width: 950px; max-width: 100%; height: auto; max-height: 95vh;
                border-radius: 20px; display: flex; overflow: hidden; position: relative;
                box-shadow: 0 40px 80px rgba(0,0,0,0.5); animation: popIn 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            }
            @keyframes popIn { from { transform: scale(0.8); opacity: 0; } to { transform: scale(1); opacity: 1; } }
            
            .modal-left { width: 50%; background: #f5f5dc; display: flex; align-items: center; justify-content: center; padding: 40px; }
            .modal-left img { max-width: 100%; max-height: 85vh; border-radius: 12px; box-shadow: 0 20px 40px rgba(0,0,0,0.2); }
            
            .modal-right { width: 50%; padding: 50px; display: flex; flex-direction: column; overflow-y: auto; max-height: 95vh; position: relative; }
            .modal-close {
                position: absolute; top: 25px; right: 25px; font-size: 24px; color: #333; background: #f0f0f0;
                width: 40px; height: 40px; border-radius: 50%; display: flex; align-items: center; justify-content: center;
                cursor: pointer; z-index: 10; transition: 0.3s; font-weight: bold;
            }
            .modal-close:hover { background: #3e2723; color: white; }
            
            .input-field { width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px; font-size: 15px; transition: 0.3s; }
            .input-field:focus { border-color: #3e2723; outline: none; }

            @media (max-width: 900px) { 
                .modal-content, .about-container { flex-direction: column; width: 100%; grid-template-columns: 1fr; gap: 50px;
            .about-visual::before { display: none; }
            .hero-content h1 { font-size: 50px; }
            }
        </style>
    </head>
    <body>
        <%
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
        %>
        
        <div id="toast-container" class="toast-container"></div>
        
        <!-- NAVBAR -->
        <nav class="nav-overlay">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="wishlist.jsp">Wishlist</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_account.jsp">Account</a>
                <a href="contact.jsp">Contact</a>
                <a href="logout.jsp">Logout</a>
                <div class="search-nav">
                    <form action="shop.jsp#products" method="get">
                        <input type="text" name="search" placeholder="Search...">
                    </form>
                </div>
            </div>
        </nav>

        <!-- HERO -->
        <div class="hero-section">
            <div class="hero-overlay"></div>
            <div class="hero-content">
                <h1>Handmade With Love</h1>
                <p>Discover unique, handcrafted crochet pieces designed to bring warmth into your life.</p>
                <a href="#products" class="btn-hero">Explore Collection</a>
            </div>
        </div>

        <div class="main-content-wrapper" id="products">
            <div class="section-header">
                <h2>Our Collection</h2>
                <p>Find your next favorite piece</p>
            </div>
            
            <div class="filter-bar">
                <a href="shop.jsp#products" class="<%= (request.getParameter("category") == null) ? "active" : "" %>">All</a>
                <%
                    Connection conNav = DBConnection.getConnection();
                    Statement stmtNav = conNav.createStatement();
                    ResultSet rsNav = stmtNav.executeQuery("SELECT * FROM Categories");
                    while(rsNav.next()) {
                        int cid = rsNav.getInt("category_id");
                        String cname = rsNav.getString("category_name");
                        String currentCat = request.getParameter("category");
                        boolean isActive = (currentCat != null && currentCat.equals(String.valueOf(cid)));
                %>
                        <a href="shop.jsp?category=<%= cid %>#products" class="<%= isActive ? "active" : "" %>"><%= cname %></a>
                <%
                    }
                    conNav.close();
                %>
            </div>

            <%
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
            %>
            <div class="product-grid">
                <%
                    while (rs.next()) {
                        int pid = rs.getInt("product_id");
                        int stock = rs.getInt("stock");
                        double price = rs.getDouble("price");
                        boolean isAdmin = "admin".equals(user.getRole());
                        
                        boolean isOutOfStock = (stock <= 0);
                        boolean isOnSale = (!isOutOfStock && price < 300);
                %>
                        <div class="product-card" 
                             data-name="<%= rs.getString("name") %>" 
                             data-desc="<%= rs.getString("description") %>"
                             data-price="<%= price %>"
                             data-img="<%= rs.getString("image_url") %>"
                             data-pid="<%= pid %>"
                             data-stock="<%= stock %>">

                            <div class="badge-container">
                                <% if(isOutOfStock) { %>
                                    <span class="badge-tag badge-out"><i class="fas fa-times-circle"></i> Out of Stock</span>
                                <% } else if(isOnSale) { %>
                                    <span class="badge-tag badge-sale"><i class="fas fa-bolt"></i> On Sale</span>
                                <% } %>
                            </div>

                            <% if(!isAdmin) { %>
                                <a href="WishlistServlet?action=add&pid=<%= pid %>" class="wishlist-btn <%= wishIds.contains(pid) ? "active" : "" %>" onclick="event.stopPropagation();">♥</a>
                            <% } %>

                            <div class="product-img-box" onclick="openProductModal(this.parentNode)">
                                <img src="<%= rs.getString("image_url") %>">
                                <div class="product-overlay">
                                    <button class="view-btn">Quick View</button>
                                </div>
                            </div>
                            
                            <div class="product-info">
                                <div class="product-title"><%= rs.getString("name") %></div>
                                <% if(!isOutOfStock && stock <= 5) { %>
                                    <p style="color: #d32f2f; font-size: 13px; font-weight: bold;">🔥 Only <%= stock %> left!</p>
                                <% } %>
                                <div class="product-price">
                                    <% if(isOnSale) { %>
                                        <span style="text-decoration:line-through; color:#999; font-size:16px; margin-right:10px;">₹<%= price + 100 %></span>
                                    <% } %>
                                    ₹<%= price %>
                                </div>
                            </div>
                            
                            <div id="reviews-data-<%= pid %>" style="display:none;">
                                <%
                                    String revSql = "SELECT r.rating, r.comment, u.full_name FROM Reviews r JOIN Users u ON r.user_id = u.user_id WHERE r.product_id = ?";
                                    PreparedStatement psRev = con.prepareStatement(revSql);
                                    psRev.setInt(1, pid);
                                    ResultSet rsRev = psRev.executeQuery();
                                    boolean hasRev = false;
                                    while(rsRev.next()) { hasRev = true; int rVal = rsRev.getInt("rating");
                                %>
                                    <div style="background:#f9f9f9; padding:15px; border-radius:8px; margin-bottom:10px; border-left:3px solid #8d6e63;">
                                        <div style="display:flex; justify-content:space-between; margin-bottom:5px;">
                                            <strong style="font-size:14px;"><%= rsRev.getString("full_name") %></strong>
                                            <div style="color:#f39c12; font-size:12px;">
                                                <% for(int i=1; i<=5; i++) { if(i <= rVal) { %>★<% } else { %>☆<% } } %>
                                            </div>
                                        </div>
                                        <p style="margin:0; color:#555; font-size:13px;"><%= rsRev.getString("comment") %></p>
                                    </div>
                                <%
                                    }
                                    if(!hasRev) { out.println("<p style='text-align:center; color:#777; font-size:14px;'>No reviews yet.</p>"); }
                                %>
                            </div>
                        </div>
                <%
                    }
                } catch (Exception e) {
                    out.println("<p style='color:red; text-align:center;'>Error loading products</p>");
                } finally {
                    if (con != null) try { con.close(); } catch (Exception e) {}
                }
            %>
            </div>
            
            <!-- About Us Section -->
            <div class="about-story-section">
                <div class="about-container">
                    <div class="about-visual">
                        <img src="https://i.pinimg.com/736x/b9/cf/2f/b9cf2fd61dfcd3ed268619bd2a7bc936.jpg" alt="Cozy Crochet">
                    </div>
                    <div class="about-text">
                        <span class="about-label">Our Story</span>
                        <h2>Handmade with Love & Passion</h2>
                        <p>Welcome to Cozy Crochet Hub. We are a small team of passionate artisans who believe in the beauty of handmade products. What started as a hobby has grown into a community of craft lovers.</p>
                        <p>We use only the highest quality yarns to ensure every item is soft, durable, and beautiful. Thank you for supporting our dream!</p>
                        
                        <div class="stats-row">
                            <div class="stat-item">
                                <h3>5+</h3>
                                <p>Years Experience</p>
                            </div>
                            <div class="stat-item">
                                <h3>1000+</h3>
                                <p>Happy Customers</p>
                            </div>
                            <div class="stat-item">
                                <h3>100%</h3>
                                <p>Handmade</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>

        <!-- MODAL -->
        <div id="quickViewModal" class="modal-overlay">
            <div class="modal-content" onclick="event.stopPropagation()">
                <span class="modal-close" onclick="closeModal()">&times;</span>
                
                <div class="modal-left">
                    <img id="modal-img" src="" alt="Product">
                </div>
                
                <div class="modal-right">
                    <h2 id="modal-title" style="font-family: 'Playfair Display', serif; color: #3e2723; margin-top:0;"></h2>
                    <p id="modal-stock" style="font-size: 14px; font-weight: bold; margin-bottom: 10px;"></p>
                    <p id="modal-desc" style="color: #555; line-height: 1.6;"></p>
                    <h3 id="modal-price" style="color: #3e2723; font-size: 28px; margin: 20px 0;"></h3>
                    
                    <% if(!"admin".equals(user.getRole())) { %>
                    <form action="CartServlet" method="post" id="cartForm">
                        <input type="hidden" name="pid" id="modal-pid">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="buyNow" id="buyNowFlag" value="false">
                        
                        <div style="margin-bottom: 15px;">
                            <label style="font-size: 14px; color: #555;">Customization:</label>
                            <input type="text" name="customization" class="input-field" placeholder="Add a note (optional)">
                        </div>
                        <div style="display: flex; gap: 10px; align-items: center;">
                            <input type="number" name="qty" value="1" min="1" style="width: 60px; padding: 12px; border: 2px solid #eee; border-radius: 8px;">
                            <button type="submit" id="modal-add-btn" class="btn" style="background:#3e2723; flex:1; border:none; cursor:pointer;">Add to Cart</button>
                        </div>
                        <button type="button" id="buy-now-btn" class="btn btn-full" style="margin-top:10px; background:#27ae60; border:none; cursor:pointer;">Buy Now</button>
                    </form>
                    <script>
                        document.getElementById('buy-now-btn').addEventListener('click', function() {
                            document.getElementById('buyNowFlag').value = "true";
                            document.getElementById('cartForm').submit();
                        });
                    </script>
                    <% } else { %>
                        <p style="color:#c0392b; text-align:center; padding:20px; background:#ffebee; border-radius:5px;">Admin View</p>
                    <% } %>

                    <hr style="margin: 25px 0; border-color:#eee;">
                    <h4 style="font-family:'Playfair Display'; color:#3e2723; margin:0 0 15px;">Customer Reviews</h4>
                    <div id="modal-reviews-container"></div>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>

        <!-- SCRIPTS -->
        <script>
            <%
                String toastMsg = (String) session.getAttribute("toast_msg");
                String toastType = (String) session.getAttribute("toast_type");
                if (toastMsg != null) {
                    session.removeAttribute("toast_msg");
                    session.removeAttribute("toast_type");
            %>
                    window.onload = function() { showToast("<%= toastMsg %>", "<%= toastType %>"); };
            <%
                }
            %>

            function showToast(message, type) {
                var container = document.getElementById('toast-container');
                if (!container) return;
                var toast = document.createElement('div');
                toast.className = 'toast ' + type;
                toast.innerText = message;
                container.appendChild(toast);
                setTimeout(function() { toast.classList.add('show'); }, 100);
                setTimeout(function() {
                    toast.classList.remove('show');
                    setTimeout(function() { toast.remove(); }, 500);
                }, 3000);
            }

            function openProductModal(el) {
                var name = el.getAttribute('data-name');
                var desc = el.getAttribute('data-desc');
                var price = el.getAttribute('data-price');
                var img = el.getAttribute('data-img');
                var pid = el.getAttribute('data-pid');
                var stock = el.getAttribute('data-stock');

                document.getElementById('modal-title').innerText = name;
                document.getElementById('modal-desc').innerText = desc;
                
                var priceFloat = parseFloat(price);
                document.getElementById('modal-price').innerText = "₹" + priceFloat.toLocaleString('en-IN');
                
                document.getElementById('modal-img').src = img;
                document.getElementById('modal-pid').value = pid;
                
                var hiddenReviews = document.getElementById('reviews-data-' + pid);
                var reviewContainer = document.getElementById('modal-reviews-container');
                if (hiddenReviews) { reviewContainer.innerHTML = hiddenReviews.innerHTML; } 
                else { reviewContainer.innerHTML = "<p style='color:#777;'>No reviews yet.</p>"; }
                
                var stockElement = document.getElementById('modal-stock');
                var addBtn = document.getElementById('modal-add-btn');
                
                if (parseInt(stock) > 0) {
                    stockElement.innerHTML = "<span style='color:#27ae60;'><i class='fas fa-check-circle'></i> In Stock</span>";
                    if(addBtn) { addBtn.disabled = false; addBtn.style.backgroundColor = "#3e2723"; }
                } else {
                    stockElement.innerHTML = "<span style='color:#c0392b;'><i class='fas fa-times-circle'></i> Out of Stock</span>";
                    if(addBtn) { addBtn.disabled = true; addBtn.style.backgroundColor = "#ccc"; }
                }

                document.getElementById('quickViewModal').style.display = 'flex';
            }

            function closeModal() { document.getElementById('quickViewModal').style.display = 'none'; }
            document.getElementById('quickViewModal').addEventListener('click', function(e) { if (e.target === this) { closeModal(); } });
        </script>
    </body>
</html>