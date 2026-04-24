package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Register - Cozy Crochet</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Lato:wght@300;400;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("        <style>\n");
      out.write("            body {\n");
      out.write("                margin: 0; padding: 0; font-family: 'Lato', sans-serif;\n");
      out.write("                background: url('https://i.pinimg.com/1200x/93/2c/e6/932ce6b2dc6397d5a8edf2349fce4f9e.jpg') center/cover fixed;\n");
      out.write("                height: 100vh; display: flex; align-items: center; justify-content: center;\n");
      out.write("            }\n");
      out.write("            .login-card {\n");
      out.write("                background: rgba(255, 255, 255, 0.95);\n");
      out.write("                padding: 60px 50px; border-radius: 20px;\n");
      out.write("                box-shadow: 0 25px 50px rgba(0,0,0,0.3);\n");
      out.write("                width: 100%; max-width: 420px; text-align: center;\n");
      out.write("                backdrop-filter: blur(10px); border: 1px solid rgba(255,255,255,0.2);\n");
      out.write("                animation: slideUp 0.6s ease-out;\n");
      out.write("            }\n");
      out.write("            @keyframes slideUp { from { opacity:0; transform: translateY(30px); } to { opacity:1; transform: translateY(0); } }\n");
      out.write("            \n");
      out.write("            .login-card h1 { font-family: 'Playfair Display', serif; color: #3e2723; margin: 0 0 10px; font-size: 36px; }\n");
      out.write("            .login-card h2 { margin: 0 0 30px; color: #888; font-size: 16px; font-weight: normal; }\n");
      out.write("            \n");
      out.write("            .form-group { margin-bottom: 20px; text-align: left; }\n");
      out.write("            .form-group label { display: block; margin-bottom: 8px; color: #555; font-weight: 600; font-size: 13px; text-transform: uppercase; letter-spacing: 0.5px; }\n");
      out.write("            .form-control {\n");
      out.write("                width: 100%; padding: 15px 20px; border: 2px solid #eee; border-radius: 10px;\n");
      out.write("                font-size: 15px; transition: 0.3s; background: #fafafa;\n");
      out.write("            }\n");
      out.write("            .form-control:focus { border-color: #3e2723; background: white; outline: none; box-shadow: 0 0 0 3px rgba(62,39,35,0.1); }\n");
      out.write("            \n");
      out.write("            .btn-login {\n");
      out.write("                width: 100%; padding: 16px; background: #3e2723; color: white; border: none;\n");
      out.write("                border-radius: 50px; font-size: 16px; font-weight: 700; cursor: pointer;\n");
      out.write("                transition: 0.3s; letter-spacing: 1px; text-transform: uppercase; margin-top: 10px;\n");
      out.write("            }\n");
      out.write("            .btn-login:hover { background: #5d4037; transform: translateY(-2px); box-shadow: 0 10px 20px rgba(0,0,0,0.15); }\n");
      out.write("            \n");
      out.write("            .footer-text { margin-top: 30px; color: #777; font-size: 14px; }\n");
      out.write("            .footer-text a { color: #c0392b; font-weight: bold; text-decoration: none; }\n");
      out.write("            .error-msg { background: #ffebee; color: #c62828; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 14px; }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"login-card\">\n");
      out.write("            <h1>Join Us</h1>\n");
      out.write("            <h2>Create your account</h2>\n");
      out.write("            \n");
      out.write("            ");

                String error = request.getParameter("error");
                if(error != null) { out.println("<div class='error-msg'>" + error + "</div>"); }
            
      out.write("\n");
      out.write("\n");
      out.write("            <form action=\"RegisterServlet\" method=\"post\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Full Name</label>\n");
      out.write("                    <input type=\"text\" name=\"fullname\" class=\"form-control\" placeholder=\"John Doe\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Email Address</label>\n");
      out.write("                    <input type=\"email\" name=\"email\" class=\"form-control\" placeholder=\"john@example.com\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Password</label>\n");
      out.write("                    <input type=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Create a password\" required>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"btn-login\">Create Account</button>\n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("            <div class=\"footer-text\">\n");
      out.write("                Already have an account? <a href=\"login.jsp\">Login</a>\n");
      out.write("            </div>\n");
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
