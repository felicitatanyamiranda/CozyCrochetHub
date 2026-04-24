<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="com.cozycrochet.models.CartItem" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }
            
            /* NAVBAR */
            .nav-solid {
                background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            }
            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }
            .nav-links { display: flex; align-items: center; gap: 20px; }
            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; font-weight: 500; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; }

            /* HEADER */
            .page-header {
                background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white;
                margin-bottom: 60px;
            }
            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }
            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }

            /* CONTAINER */
            .cart-container { max-width: 1000px; margin: 0 auto 80px; padding: 0 20px; display: grid; grid-template-columns: 2fr 1fr; gap: 40px; }
            @media (max-width: 900px) { .cart-container { grid-template-columns: 1fr; } }

            /* --- ITEMS LIST --- */
            .cart-items-col { display: flex; flex-direction: column; gap: 20px; }
            
            .cart-item {
                background: white; padding: 20px; border-radius: 20px;
                box-shadow: 0 10px 30px rgba(0,0,0,0.05); display: flex; gap: 25px; align-items: center;
                transition: transform 0.3s;
            }
            .cart-item:hover { transform: translateY(-3px); }
            
            .item-image {
                width: 120px; height: 120px; background: #f9f9f9; border-radius: 15px; object-fit: cover;
                border: 1px solid #eee; flex-shrink: 0;
            }
            .item-details { flex-grow: 1; }
            .item-details h3 { margin: 0 0 5px; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 20px; }
            .item-desc { color: #888; font-size: 13px; margin-bottom: 5px; display: block; }
            .item-price { font-size: 18px; color: #c0392b; font-weight: bold; }

            .item-actions { text-align: right; }
            .item-total { font-size: 20px; font-weight: bold; color: #3e2723; margin-bottom: 10px; display: block; }
            
            /* Quantity Controls */
            .qty-control { display: inline-flex; align-items: center; gap: 0; border: 1px solid #eee; border-radius: 25px; overflow: hidden; }
            .qty-btn {
                width: 35px; height: 35px; background: #f9f9f9; border: none; cursor: pointer;
                font-size: 14px; color: #555; transition: 0.2s; text-decoration: none; display: flex; align-items: center; justify-content: center;
            }
            .qty-btn:hover { background: #3e2723; color: white; }
            .qty-display { width: 40px; text-align: center; border: none; font-weight: bold; font-size: 16px; background: white; }

            .btn-remove { background: none; border: none; color: #e74c3c; cursor: pointer; font-size: 13px; margin-top: 10px; opacity: 0.7; transition: 0.2s; }
            .btn-remove:hover { opacity: 1; text-decoration: underline; }

            /* --- SUMMARY CARD --- */
            .summary-card {
                background: white; padding: 40px; border-radius: 20px;
                box-shadow: 0 15px 35px rgba(0,0,0,0.05); height: fit-content; position: sticky; top: 100px;
            }
            .summary-card h3 { margin: 0 0 30px; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 24px; border-bottom: 1px solid #eee; padding-bottom: 15px; }
            
            .summary-row { display: flex; justify-content: space-between; margin-bottom: 15px; font-size: 16px; color: #555; }
            .summary-row.total { font-size: 24px; font-weight: bold; color: #3e2723; margin-top: 20px; padding-top: 20px; border-top: 2px dashed #eee; }
            
            .btn-checkout {
                width: 100%; padding: 18px; background: #3e2723; color: white; border: none;
                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer; margin-top: 30px;
                transition: 0.3s; letter-spacing: 1px; text-transform: uppercase; text-decoration: none; display: block; text-align: center;
            }
            .btn-checkout:hover { background: #5d4037; transform: translateY(-2px); box-shadow: 0 10px 20px rgba(0,0,0,0.15); }

            /* Empty State */
            .empty-box { text-align: center; padding: 80px 20px; background: white; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.03); }
            .empty-box i { font-size: 60px; color: #ddd; margin-bottom: 20px; }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_orders.jsp">Orders</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- HEADER -->
        <div class="page-header">
            <h2>Shopping Cart</h2>
            <p>Review your items</p>
        </div>

        <div class="cart-container">
            <!-- Items Column -->
            <div class="cart-items-col">
                <%
                    if (cart == null || cart.isEmpty()) {
                %>
                    <div class="empty-box">
                        <i class="fas fa-shopping-cart"></i>
                        <h3 style="font-family:'Playfair Display'; color:#3e2723;">Your cart is empty.</h3>
                        <p style="color:#777; margin-bottom:30px;">Looks like you haven't added anything yet.</p>
                        <a href="shop.jsp" class="btn-checkout" style="width:auto; padding:15px 40px; background:#c0392b;">Start Shopping</a>
                    </div>
                <%
                    } else {
                        for (CartItem item : cart) {
                %>
                    <div class="cart-item">
                        <img src="<%= item.getImage() %>" alt="Product" class="item-image">
                        
                        <div class="item-details">
                            <h3><%= item.getName() %></h3>
                            <span class="item-desc">
                                <%= item.getCustomization() != null && !item.getCustomization().isEmpty() ? "Note: " + item.getCustomization() : "" %>
                            </span>
                            <span class="item-price">₹<%= item.getPrice() %></span>
                        </div>

                        <div class="item-actions">
                            <span class="item-total">₹<%= item.getTotal() %></span>
                            
                            <!-- Quantity Controls -->
                            <div class="qty-control">
                                <a href="CartServlet?action=update&pid=<%= item.getId() %>&qty=<%= item.getQuantity() - 1 %>" class="qty-btn">-</a>
                                <span class="qty-display"><%= item.getQuantity() %></span>
                                <a href="CartServlet?action=update&pid=<%= item.getId() %>&qty=<%= item.getQuantity() + 1 %>" class="qty-btn">+</a>
                            </div>
                            
                            <br>
                            <a href="CartServlet?action=remove&pid=<%= item.getId() %>" class="btn-remove">
                                <i class="fas fa-trash"></i> Remove
                            </a>
                        </div>
                    </div>
                <%
                        }
                    }
                %>
            </div>

            <!-- Summary Column -->
            <% if (cart != null && !cart.isEmpty()) { 
                double totalAmount = 0;
                for (CartItem item : cart) { totalAmount += item.getTotal(); }
            %>
            <div class="summary-card">
                <h3>Order Summary</h3>
                
                <div class="summary-row">
                    <span>Subtotal</span>
                    <span>₹<%= totalAmount %></span>
                </div>
                <div class="summary-row" style="color:#27ae60;">
                    <span>Delivery</span>
                    <span><%= totalAmount > 500 ? "FREE" : "₹50" %></span>
                </div>
                
                <div class="summary-row total">
                    <span>Total</span>
                    <span>₹<%= totalAmount + (totalAmount > 500 ? 0 : 50) %></span>
                </div>
                
                <!-- UPDATED LINK TO SERVLET -->
                <a href="CartServlet?action=checkout" class="btn-checkout">
                    Proceed to Checkout <i class="fas fa-arrow-right"></i>
                </a>
            </div>
            <% } %>
        </div>

        <!-- TOAST CONTAINER -->
        <div id="toast-container" class="toast-container"></div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>

        <!-- TOAST SCRIPT -->
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
        </script>
    </body>
</html>