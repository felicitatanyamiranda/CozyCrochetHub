<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.cozycrochet.models.User" %>
<%@page import="java.sql.*" %>
<%@page import="com.cozycrochet.db.DBConnection" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
        <link rel="stylesheet" href="css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) { response.sendRedirect("login.jsp"); return; }
            
            // Fetch latest data
            try {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM Users WHERE user_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    session.setAttribute("user", user);
                }
                con.close();
            } catch (Exception e) { e.printStackTrace(); }
        %>

        <!-- NAVBAR -->
        <nav class="nav-solid">
            <h1>Cozy Crochet</h1>
            <div class="nav-links">
                <a href="shop.jsp">Home</a>
                <a href="cart.jsp">Cart</a>
                <a href="my_account.jsp" style="color:#ffab91;">Account</a>
                <a href="logout.jsp">Logout</a>
            </div>
        </nav>

        <!-- ACCOUNT WRAPPER -->
        <div class="account-wrapper">
            
            <!-- SIDEBAR -->
            <aside class="sidebar-dashboard">
                <div class="sidebar-header">
                    <div class="avatar-circle">
                        <%= user.getFullName().substring(0,1).toUpperCase() %>
                    </div>
                    <h3><%= user.getFullName() %></h3>
                    <p><%= user.getEmail() %></p>
                </div>
                
                <nav class="sidebar-nav">
                    <a href="my_account.jsp" class="active">
                        <i class="fas fa-user-edit"></i> Edit Profile
                    </a>
                    <a href="my_orders.jsp">
                        <i class="fas fa-box"></i> My Orders
                    </a>
                    <a href="wishlist.jsp">
                        <i class="fas fa-heart"></i> Wishlist
                    </a>
                    <a href="change_password.jsp">
                        <i class="fas fa-lock"></i> Change Password
                    </a>
                </nav>
            </aside>

            <!-- MAIN CONTENT -->
            <main class="main-dashboard">
                <div class="dashboard-header">
                    <h2>Profile Settings</h2>
                    <p>Manage your personal information</p>
                </div>

                <div class="form-card-dashboard">
                    <%
                        String msg = request.getParameter("msg");
                        if(msg != null) { 
                    %>
                            <div class="alert-success"><%= msg %></div>
                    <%
                        }
                    %>

                    <!-- ADDED ONSUBMIT VALIDATION -->
                    <form action="UpdateProfileServlet" method="post" onsubmit="return validateProfile()">
                        <div class="form-row">
                            <div class="form-group">
                                <label>Full Name</label>
                                <!-- ADDED ID -->
                                <input type="text" id="fullname" name="name" class="input-field" value="<%= user.getFullName() %>" required>
                            </div>
                            <div class="form-group">
                                <label>Email Address</label>
                                <input type="email" class="input-field" value="<%= user.getEmail() %>" disabled style="background:#eee; cursor:not-allowed;">
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label>Phone Number</label>
                                <!-- ADDED ID -->
                                <input type="tel" id="phone" name="phone" class="input-field" value="<%= user.getPhone() != null ? user.getPhone() : "" %>" placeholder="Enter 10-digit number">
                            </div>
                            <div class="form-group">
                                <label>City / State</label>
<input type="text" id="city" class="input-field" placeholder="e.g. Chennai, TN">                            </div>
                        </div>

                        <div class="form-group">
                            <label>Shipping Address</label>
                            <!-- ADDED ID -->
                            <textarea id="address" name="address" class="input-field" rows="4" placeholder="House No, Street, City..."><%= user.getAddress() != null ? user.getAddress() : "" %></textarea>
                        </div>
                        
                        <button type="submit" class="btn-save">Save Changes</button>
                    </form>
                </div>
            </main>
        </div>

        <!-- FOOTER -->
        <%@include file="footer.jsp" %>

        <!-- VALIDATION SCRIPT -->
             <!-- VALIDATION SCRIPT -->
        <script>
            function validateProfile() {
                var name = document.getElementById('fullname').value;
                var phone = document.getElementById('phone').value;
                var address = document.getElementById('address').value;
                
                // Get City Value (Add ID 'city' to input in HTML if not present)
                var cityInput = document.getElementById('city');
                var city = "";
                if(cityInput) { city = cityInput.value; }

                // 1. Name Validation (Only letters and spaces)
                var nameRegex = /^[a-zA-Z\s]+$/;
                if (!nameRegex.test(name)) {
                    alert("Invalid Name: Only letters and spaces are allowed.");
                    return false;
                }

                // 2. City Validation (Only letters, no numbers)
                if (city.length > 0) {
                    var cityRegex = /^[a-zA-Z\s]+$/;
                    if (!cityRegex.test(city)) {
                        alert("Invalid City/State: Only alphabets are allowed (No numbers).");
                        return false;
                    }
                }

                // 3. Phone Validation (Must be 10 digits if entered)
                if (phone.length > 0) {
                    var phoneRegex = /^[6-9]\d{9}$/;
                    if (!phoneRegex.test(phone)) {
                        alert("Invalid Phone: Must be a valid 10-digit Indian number.");
                        return false;
                    }
                }

                // 4. Address Validation (Minimum length)
                if (address.length > 0 && address.length < 10) {
                    alert("Address is too short. Please enter full address.");
                    return false;
                }

                return true; // Allow form submission
            }
        </script>
    </body>
</html>