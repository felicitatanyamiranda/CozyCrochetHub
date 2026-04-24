package com.cozycrochet.servlets;

import com.cozycrochet.db.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO ContactMessages (name, email, subject, message) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, subject);
            ps.setString(4, message);
            
            ps.executeUpdate();
            response.sendRedirect("contact.jsp?success=Message sent successfully! We will get back to you soon.");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("contact.jsp?error=Error sending message.");
        }
    }
}