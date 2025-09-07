package murach.cart;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import murach.business.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if (action == null) action = "cart";   // mặc định

        String url = "/index.jsp";

        if ("shop".equals(action)) {
            url = "/index.jsp";

        } else if ("cart".equals(action)) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) cart = new Cart();

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) quantity = 1;
            } catch (Exception e) {
                quantity = 1;
            }

            // Giả lập ProductIO.getProduct(...)
            Product product = getProductStub(productCode);

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);

            if (quantity > 0) {
                cart.addItem(lineItem);
            } else { // = 0
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";

        } else if ("checkout".equals(action)) {
            url = "/checkout.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    // Mock sản phẩm
    private Product getProductStub(String code) {
        switch (code) {
            case "8601": return new Product("8601", "86 (the band) - True Life Songs and Pictures", 14.95);
            case "pf01": return new Product("pf01", "Paddlefoot - The First CD", 12.95);
            case "pf02": return new Product("pf02", "Paddlefoot - The Second CD", 14.95);
            case "jr01": return new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95);
            default:     return new Product(code, "Unknown", 0.0);
        }
    }
}
