<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="com.cozycrochet.models.CartItem" %>
<%@page import="java.util.List" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; }
            
            /* NAVBAR */
            .nav-solid { background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }
            .nav-links { display: flex; align-items: center; gap: 30px; }
            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; }

            /* HERO HEADER */
            .page-header { background: linear-gradient(135deg, #3e2723, #5d4037); padding: 60px 20px; text-align: center; color: white; margin-bottom: 60px; }
            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }
            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }

            /* CONTAINER */
            .checkout-wrapper { max-width: 1100px; margin: 0 auto 80px; padding: 0 20px; display: grid; grid-template-columns: 1.2fr 1fr; gap: 50px; }
            @media (max-width: 900px) { .checkout-wrapper { grid-template-columns: 1fr; } }

            /* FORM CARD */
            .form-card { background: white; padding: 40px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); }
            .section-title { font-family: 'Playfair Display', serif; color: #3e2723; font-size: 24px; margin: 0 0 25px; padding-bottom: 15px; border-bottom: 2px solid #f0f0f0; }
            
            .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
            .form-group { margin-bottom: 20px; }
            .form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #555; font-size: 14px; }
            .form-control { width: 100%; padding: 14px 18px; border: 2px solid #eee; border-radius: 10px; font-size: 15px; transition: 0.3s; }
            .form-control:focus { border-color: #3e2723; outline: none; }

            /* PAYMENT OPTIONS */
            .payment-options { display: flex; flex-direction: column; gap: 15px; margin-top: 10px; }
            .payment-option {
                border: 2px solid #eee; border-radius: 12px; padding: 20px; cursor: pointer;
                display: flex; align-items: center; gap: 15px; transition: 0.3s;
            }
            .payment-option:hover { border-color: #ccc; }
            .payment-option.active { border-color: #3e2723; background: #fffbf5; }
            
            .payment-icon { width: 50px; height: 50px; background: #f9f9f9; border-radius: 10px; display: flex; align-items: center; justify-content: center; font-size: 22px; }
            .payment-info h4 { margin: 0 0 5px; color: #333; }
            .payment-info p { margin: 0; font-size: 13px; color: #777; }

            /* HIDDEN BOXES */
            .payment-details { display: none; margin-top: 15px; background: #f9f9f9; padding: 20px; border-radius: 8px; border: 1px dashed #ddd; }
            
            /* SUMMARY CARD */
            .summary-card { background: white; padding: 30px; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); height: fit-content; position: sticky; top: 100px; }
            .summary-item { display: flex; justify-content: space-between; margin-bottom: 12px; font-size: 15px; color: #555; }
            .summary-total { font-size: 22px; font-weight: bold; color: #3e2723; border-top: 2px solid #eee; padding-top: 15px; margin-top: 15px; }

            .btn-place-order {
                width: 100%; padding: 18px; background: #3e2723; color: white; border: none;
                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;
                margin-top: 30px; transition: 0.3s; text-transform: uppercase;
            }
            .btn-place-order:hover { background: #5d4037; }
            
            .secure-badge { text-align: center; margin-top: 20px; color: #27ae60; font-size: 13px; }
        </style>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            
            if (user == null || cart == null || cart.isEmpty()) {
                response.sendRedirect("shop.jsp");
                return;
            }
            
            double totalAmount = 0;
            int itemCount = 0;
            for (CartItem item : cart) {
                totalAmount += item.getTotal();
                itemCount += item.getQuantity();
            }
            double deliveryFee = (totalAmount > 500) ? 0.0 : 50.0;
            double grandTotal = totalAmount + deliveryFee;
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="cart.jsp">Cart</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- HEADER -->
        <div class="page-header">
            <h2>Checkout</h2>
            <p>Complete your order securely</p>
        </div>

        <div class="checkout-wrapper">
            <!-- LEFT: FORMS -->
            <div class="form-card">
                <form action="OrderServlet" method="post" id="checkoutForm" onsubmit="return validateForm()">
                    
                    <!-- Shipping -->
                    <h3 class="section-title"><i class="fas fa-truck"></i> Shipping Address</h3>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label>Full Name</label>
                            <input type="text" name="fullname" class="form-control" value="<%= user.getFullName() %>" required>
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input type="tel" name="phone" id="phone" class="form-control" placeholder="10-digit number" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label>Address</label>
                        <textarea name="address" class="form-control" rows="3" placeholder="House No, Street, City..." required></textarea>
                    </div>

                    <hr style="border:none; border-top:1px solid #eee; margin: 30px 0;">

                    <!-- Payment -->
                    <h3 class="section-title"><i class="fas fa-credit-card"></i> Payment Method</h3>
                    
                    <input type="hidden" name="payment" id="paymentMethodInput" value="Cash on Delivery">

                    <div class="payment-options">
                        <!-- UPI -->
                        <div class="payment-option active" onclick="selectPayment('UPI', this)">
                            <input type="radio" name="pay_radio" checked style="display:none;">
                            <div class="payment-icon" style="color:#3e2723;"><i class="fas fa-mobile-alt"></i></div>
                            <div class="payment-info">
                                <h4>UPI (GPay / PhonePe)</h4>
                                <p>Scan QR code to pay instantly</p>
                            </div>
                            <div class="payment-details" id="upi_box" style="display:block; width:100%;">
                                <div style="text-align:center;">
                                    <img src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=upi://pay?pn=CozyCrochet&pa=cozy@upi&am=<%= grandTotal %>" style="border-radius:8px;">
                                    <p style="font-size:12px; color:#888; margin-top:10px;">Amount: ₹<%= grandTotal %></p>
                                </div>
                            </div>
                        </div>

                        <!-- Card -->
                        <div class="payment-option" onclick="selectPayment('Credit Card', this)">
                            <input type="radio" name="pay_radio" style="display:none;">
                            <div class="payment-icon" style="color:#e74c3c;"><i class="fas fa-credit-card"></i></div>
                            <div class="payment-info">
                                <h4>Credit / Debit Card</h4>
                                <p>Visa, Mastercard, Rupay</p>
                            </div>
                            <div class="payment-details" id="card_box" style="width:100%;">
                                <input type="text" id="card_number" class="form-control" placeholder="Card Number (16 digits)" maxlength="19" oninput="formatCard(this)">
                                <div style="display:flex; gap:15px; margin-top:10px;">
                                    <input type="text" id="card_expiry" class="form-control" placeholder="MM/YY" maxlength="5">
                                    <input type="password" id="card_cvv" class="form-control" placeholder="CVV" maxlength="3">
                                </div>
                            </div>
                        </div>

                        <!-- COD -->
                        <div class="payment-option" onclick="selectPayment('Cash on Delivery', this)">
                            <input type="radio" name="pay_radio" style="display:none;">
                            <div class="payment-icon" style="color:#27ae60;"><i class="fas fa-money-bill-wave"></i></div>
                            <div class="payment-info">
                                <h4>Cash on Delivery</h4>
                                <p>Pay when you receive</p>
                            </div>
                            <div class="payment-details" id="cod_box" style="width:100%;">
                                <p style="font-size:14px; color:#666; margin:0;">
                                    <i class="fas fa-info-circle"></i> A convenience fee of ₹20 may apply.
                                </p>
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn-place-order">
                        <i class="fas fa-lock"></i> Place Order Securely
                    </button>
                </form>
            </div>

            <!-- RIGHT: SUMMARY -->
            <div class="summary-card">
                <h3 class="section-title" style="margin-top:0;">Order Summary</h3>
                
                <% for (CartItem item : cart) { %>
                    <div class="summary-item">
                        <span><%= item.getName() %> × <%= item.getQuantity() %></span>
                        <span>₹<%= item.getTotal() %></span>
                    </div>
                <% } %>

                <hr style="margin: 15px 0; border:none; border-top:1px solid #eee;">
                
                <div class="summary-item">
                    <span>Subtotal</span>
                    <span>₹<%= totalAmount %></span>
                </div>
                <div class="summary-item" style="color: #27ae60;">
                    <span>Delivery Fee</span>
                    <span><%= deliveryFee == 0 ? "FREE" : "₹"+deliveryFee %></span>
                </div>
                
                <!-- COD Fee Row -->
                <div id="cod-fee-row" class="summary-item" style="display:none; color: #e74c3c;">
                    <span>COD Fee</span>
                    <span>₹20</span>
                </div>
                
                <div class="summary-total">
                    <span>Total</span>
                    <span id="summary-grandtotal">₹<%= grandTotal %></span>
                </div>
                
                <div class="secure-badge">
                    <i class="fas fa-shield-alt"></i> Secure 256-bit SSL Encryption
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>

        <script>
            // Initial base total
            var baseTotal = <%= grandTotal %>;
            
            function selectPayment(method, element) {
                document.getElementById('paymentMethodInput').value = method;
                
                var cards = document.querySelectorAll('.payment-option');
                cards.forEach(card => card.classList.remove('active'));
                element.classList.add('active');
                
                element.querySelector('input[type="radio"]').checked = true;
                
                // Hide all details
                document.querySelectorAll('.payment-details').forEach(box => box.style.display = 'none');
                
                // Show correct details
                if (method === 'UPI') document.getElementById('upi_box').style.display = 'block';
                if (method === 'Credit Card') document.getElementById('card_box').style.display = 'block';
                if (method === 'Cash on Delivery') document.getElementById('cod_box').style.display = 'block';

                // Update Total Logic
                var codRow = document.getElementById('cod-fee-row');
                var totalDisplay = document.getElementById('summary-grandtotal');
                
                if (method === 'Cash on Delivery') {
                    codRow.style.display = 'flex';
                    var newTotal = baseTotal + 20;
                    totalDisplay.innerText = "₹" + newTotal;
                } else {
                    codRow.style.display = 'none';
                    totalDisplay.innerText = "₹" + baseTotal;
                }
            }

            // Format Card Number with spaces
            function formatCard(input) {
                var value = input.value.replace(/\s/g, '').replace(/\D/g, '');
                var formatted = value.match(/.{1,4}/g).join(' ');
                input.value = formatted;
            }

            // VALIDATION FUNCTION
            function validateForm() {
                var method = document.getElementById('paymentMethodInput').value;
                
                // 1. Phone Validation
                var phone = document.getElementById('phone').value;
                var phoneRegex = /^[6-9]\d{9}$/; // Starts with 6-9, total 10 digits
                if (!phoneRegex.test(phone)) {
                    alert("Please enter a valid 10-digit Phone Number.");
                    return false;
                }

                // 2. Card Validation (if selected)
                if (method === 'Credit Card') {
                    var cardNum = document.getElementById('card_number').value.replace(/\s/g, '');
                    var expiry = document.getElementById('card_expiry').value;
                    var cvv = document.getElementById('card_cvv').value;

                    // Validate Card Number (16 digits)
                    if (cardNum.length !== 16 || isNaN(cardNum)) {
                        alert("Invalid Card Number. Must be 16 digits.");
                        return false;
                    }
                    
                    // Validate Expiry (MM/YY)
                    if (expiry.length !== 5 || expiry.indexOf('/') === -1) {
                        alert("Invalid Expiry Date. Use MM/YY format.");
                        return false;
                    }
                    
                    var parts = expiry.split('/');
                    var mm = parseInt(parts[0]);
                    var yy = parseInt(parts[1]);
                    var currentYear = new Date().getFullYear() % 100;
                    var currentMonth = new Date().getMonth() + 1;

                    if (mm < 1 || mm > 12) {
                        alert("Invalid Month in Expiry Date.");
                        return false;
                    }

                    if (yy < currentYear || (yy === currentYear && mm < currentMonth)) {
                        alert("Card has expired.");
                        return false;
                    }

                    // Validate CVV
                    if (cvv.length !== 3 || isNaN(cvv)) {
                        alert("Invalid CVV. Must be 3 digits.");
                        return false;
                    }
                }
                
                return true;
            }
        </script>
    </body>
</html>