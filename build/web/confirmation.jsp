<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Placed</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body { background: #fdfbf7; margin: 0; font-family: 'Lato', sans-serif; display: flex; flex-direction: column; min-height: 100vh; }
            
            /* Use Solid Navbar */
            .nav-solid { background: #3e2723; padding: 20px 5%; display: flex; justify-content: space-between; align-items: center; }
            .nav-solid h1 { margin: 0; font-family: 'Playfair Display', serif; color: white; font-size: 28px; }
            .nav-links { display: flex; gap: 30px; }
            .nav-links a { color: rgba(255,255,255,0.8); text-decoration: none; font-size: 15px; transition: 0.3s; }
            .nav-links a:hover { color: #ffab91; }

            .content-wrapper { flex: 1; display: flex; align-items: center; justify-content: center; padding: 40px 20px; }
            
            .success-box {
                background: white; padding: 60px; border-radius: 20px;
                box-shadow: 0 20px 40px rgba(0,0,0,0.05); text-align: center; max-width: 500px;
            }
            .icon-circle {
                width: 80px; height: 80px; background: #e8f5e9; border-radius: 50%;
                display: flex; align-items: center; justify-content: center; margin: 0 auto 30px;
                font-size: 36px; color: #2e7d32;
            }
            .success-box h2 { font-family: 'Playfair Display', serif; color: #3e2723; margin: 0 0 15px; font-size: 32px; }
            .success-box p { color: #666; margin-bottom: 30px; font-size: 16px; line-height: 1.6; }
            .btn-continue {
                display: inline-block; padding: 16px 40px; background: #3e2723; color: white;
                text-decoration: none; border-radius: 50px; font-weight: 600; transition: 0.3s;
            }
            .btn-continue:hover { background: #5d4037; transform: translateY(-2px); }
        </style>
    </head>
    <body>
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="cart.jsp">Cart</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <div class="content-wrapper">
            <div class="success-box">
                <div class="icon-circle">
                    <i class="fas fa-check"></i>
                </div>
                <h2>Order Placed Successfully!</h2>
                <p>Thank you for shopping with us. Your handmade items will be crafted and shipped soon.</p>
                <a href="shop.jsp" class="btn-continue">Continue Shopping</a>
            </div>
        </div>
        
        <!-- Insert Premium Footer Here -->
        <%@include file="footer.jsp" %>
    </body>
</html>