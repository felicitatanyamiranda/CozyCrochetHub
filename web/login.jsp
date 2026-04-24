<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Cozy Crochet</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            body {
    margin: 0; padding: 0; font-family: 'Lato', sans-serif;
    background: url('https://i.pinimg.com/1200x/cf/85/8d/cf858d900e81e498751f93152a69a3b4.jpg') center/cover fixed;
    height: 100vh; display: flex; align-items: center; justify-content: center;
}
            .login-card {
                background: rgba(255, 255, 255, 0.95);
                padding: 60px 50px; border-radius: 20px;
                box-shadow: 0 25px 50px rgba(0,0,0,0.3);
                width: 100%; max-width: 420px; text-align: center;
                backdrop-filter: blur(10px); border: 1px solid rgba(255,255,255,0.2);
                animation: slideUp 0.6s ease-out;
            }
            @keyframes slideUp { from { opacity:0; transform: translateY(30px); } to { opacity:1; transform: translateY(0); } }
            
            .login-card h1 { font-family: 'Playfair Display', serif; color: #3e2723; margin: 0 0 10px; font-size: 36px; }
            .login-card h2 { margin: 0 0 30px; color: #888; font-size: 16px; font-weight: normal; }
            
            .form-group { margin-bottom: 20px; text-align: left; }
            .form-group label { display: block; margin-bottom: 8px; color: #555; font-weight: 600; font-size: 13px; text-transform: uppercase; letter-spacing: 0.5px; }
            .form-control {
                width: 100%; padding: 15px 20px; border: 2px solid #eee; border-radius: 10px;
                font-size: 15px; transition: 0.3s; background: #fafafa;
            }
            .form-control:focus { border-color: #3e2723; background: white; outline: none; box-shadow: 0 0 0 3px rgba(62,39,35,0.1); }
            
            .btn-login {
                width: 100%; padding: 16px; background: #3e2723; color: white; border: none;
                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;
                transition: 0.3s; letter-spacing: 1px; text-transform: uppercase; margin-top: 10px;
            }
            .btn-login:hover { background: #5d4037; transform: translateY(-2px); box-shadow: 0 10px 20px rgba(0,0,0,0.15); }
            
            .footer-text { margin-top: 30px; color: #777; font-size: 14px; }
            .footer-text a { color: #c0392b; font-weight: bold; text-decoration: none; }
            .error-msg { background: #ffebee; color: #c62828; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 14px; }
        </style>
    </head>
    <body>
        <div class="login-card">
            <h1>Cozy Crochet</h1>
            <h2>Welcome Back</h2>
            
            <%
                String error = request.getParameter("error");
                if(error != null) { out.println("<div class='error-msg'>" + error + "</div>"); }
            %>

            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="email" class="form-control" placeholder="Enter your email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Enter your password" required>
                </div>
                <button type="submit" class="btn-login">Login</button>
            </form>
            
            <div class="footer-text">
                Don't have an account? <a href="register.jsp">Sign Up</a>
            </div>
        </div>
    </body>
</html>