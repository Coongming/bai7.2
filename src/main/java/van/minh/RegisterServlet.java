package vn.edu.hcmute.fit.ex9_1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");

        // Tạo cookie
        Cookie nameCookie = new Cookie("firstName", firstName);
        Cookie emailCookie = new Cookie("email", email);

        // Thiết lập thời gian sống (ví dụ: 1 ngày)
        nameCookie.setMaxAge(60 * 60 * 24);
        emailCookie.setMaxAge(60 * 60 * 24);

        // Gửi cookie về trình duyệt
        response.addCookie(nameCookie);
        response.addCookie(emailCookie);

        // Chuyển hướng về trang chủ
        response.sendRedirect("index.jsp");
    }
}