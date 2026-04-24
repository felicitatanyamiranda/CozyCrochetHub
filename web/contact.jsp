<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us - Cozy Crochet</title>
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
            .page-header {
                background: linear-gradient(135deg, #3e2723, #5d4037); padding: 80px 20px; text-align: center; color: white;
            }
            .page-header h2 { font-family: 'Playfair Display', serif; font-size: 48px; margin: 0 0 10px; }
            .page-header p { margin: 0; opacity: 0.8; font-size: 16px; }

            /* CONTAINER */
            .contact-wrapper { max-width: 1100px; margin: 80px auto; padding: 0 20px; display: grid; grid-template-columns: 1fr 1fr; gap: 60px; align-items: start; }
            @media (max-width: 900px) { .contact-wrapper { grid-template-columns: 1fr; } }

            /* FORM CARD */
            .form-card { background: white; padding: 50px; border-radius: 20px; box-shadow: 0 20px 50px rgba(0,0,0,0.05); }
            .form-card h3 { font-family: 'Playfair Display', serif; color: #3e2723; margin-top: 0; font-size: 28px; margin-bottom: 30px; }
            
            .form-group { margin-bottom: 25px; }
            .form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #555; }
            .form-control { width: 100%; padding: 15px; border: 2px solid #eee; border-radius: 10px; font-family: 'Lato', sans-serif; transition: 0.3s; }
            .form-control:focus { border-color: #3e2723; outline: none; box-shadow: 0 0 0 3px rgba(62,39,35,0.1); }
            .btn-send { background: #3e2723; color: white; padding: 15px 40px; border: none; border-radius: 50px; font-weight: 600; cursor: pointer; font-size: 16px; transition: 0.3s; }
            .btn-send:hover { background: #5d4037; transform: translateY(-2px); }

            /* SUCCESS MESSAGE */
            .alert-success {
                background: #e8f5e9; color: #2e7d32; padding: 15px 20px; border-radius: 10px; margin-bottom: 25px; 
                border-left: 5px solid #2e7d32; font-weight: 600;
            }

            /* INFO BOX */
            .info-box { padding: 20px; background: white; border-radius: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.05); }
            .info-item { display: flex; align-items: flex-start; gap: 20px; margin-bottom: 40px; }
            .info-icon { 
                width: 60px; height: 60px; background: linear-gradient(135deg, #ffab91, #3e2723); border-radius: 50%; 
                display: flex; align-items: center; justify-content: center; font-size: 20px; color: white; flex-shrink: 0;
                box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            }
            .info-content h4 { margin: 0 0 5px; font-family: 'Playfair Display', serif; color: #3e2723; font-size: 20px; }
            .info-content p { margin: 0; color: #666; line-height: 1.6; }
        </style>
    </head>
    <body>
        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="contact.jsp" style="color:#ffab91;">Contact</a>
                <a href="wishlist.jsp">Wishlist</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_account.jsp">Account</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- HEADER -->
        <div class="page-header">
            <h2>Get In Touch</h2>
            <p>We'd love to hear from you</p>
        </div>

        <!-- CONTENT -->
        <div class="contact-wrapper">
            <div class="form-card">
                <h3>Send a Message</h3>
                <%
                    String success = request.getParameter("success");
                    if(success != null) {
                %>
                        <div class="alert-success">
                            <i class="fas fa-check-circle"></i> <%= success %>
                        </div>
                <%
                    }
                %>
                <form action="ContactServlet" method="post">
                    <div class="form-group">
                        <label>Your Name</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email Address</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Subject</label>
                        <input type="text" name="subject" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Message</label>
                        <textarea name="message" rows="5" class="form-control" required></textarea>
                    </div>
                    <button type="submit" class="btn-send"><i class="fas fa-paper-plane"></i> Send Message</button>
                </form>
            </div>

            <div class="info-box">
                <h3 style="font-family: 'Playfair Display', serif; color: #3e2723; margin-top:0; margin-bottom: 30px;">Contact Information</h3>
                
                <div class="info-item">
                    <div class="info-icon"><i class="fas fa-map-marker-alt"></i></div>
                    <div class="info-content">
                        <h4>Visit Us</h4>
                        <p>123 Crochet Lane,<br>Chennai, Tamil Nadu, India</p>
                    </div>
                </div>

                <div class="info-item">
                    <div class="info-icon"><i class="fas fa-phone-alt"></i></div>
                    <div class="info-content">
                        <h4>Call Us</h4>
                        <p>+91 98765 43210</p>
                    </div>
                </div>

                <div class="info-item">
                    <div class="info-icon"><i class="fas fa-envelope"></i></div>
                    <div class="info-content">
                        <h4>Email Us</h4>
                        <p>support@cozycrochethub.com</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
    </body>
</html>