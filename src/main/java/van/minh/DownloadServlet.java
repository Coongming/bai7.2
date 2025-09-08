package van.minh;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String productCode = request.getParameter("productCode");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!doctype html>");
            out.println("<html><head><meta charset='utf-8'><title>Download</title></head><body>");
            out.println("<h2>Cảm ơn " + safe(name) + " đã tải album " + safe(productCode) + "!</h2>");
            out.println("<p>Thông tin đã được gửi đến email: " + safe(email) + "</p>");
            out.println("<p><a href='" + request.getContextPath() + "/index.jsp'>Quay lại</a></p>");
            out.println("</body></html>");
        }
    }

    // Nếu người dùng gọi GET, chuyển về POST cho tiện
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    // Escape đơn giản để tránh HTML injection
    private String safe(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
