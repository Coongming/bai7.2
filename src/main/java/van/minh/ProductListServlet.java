package vn.edu.hcmute.fit.ex9_1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tạo danh sách sản phẩm mẫu
        List<Product> products = new ArrayList<>();
        products.add(new Product("8601", "808s the band - True Life Songs and Pictures"));
        products.add(new Product("pf01", "Paddlefoot - The First CD"));
        products.add(new Product("pf02", "Paddlefoot - The Second CD"));
        products.add(new Product("jr01", "Joe RM - Genuine Wood Grained Finish"));

        // Gửi danh sách sang JSP
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list_albums.jsp");
        dispatcher.forward(request, response);
    }
}