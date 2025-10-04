package murach.download.bai9_1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DownloadServlet", urlPatterns = {"/download"})
public class DownloadServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAlbums";

        String url = "/index.jsp";
        switch (action) {
            case "viewAlbums":
                url = "/index.jsp";
                break;
            case "checkUser":
                url = checkUser(request);
                break;
            case "downloadSong":
                url = downloadSong(request);
                break;
            case "viewCookies":
                url = "/cookies.jsp";
                break;
            case "deleteCookies":
                deleteCookies(request, response);
                url = "/cookies.jsp";
                break;
            default:
                url = "/index.jsp";
                break;
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = "/index.jsp";

        if ("registerUser".equals(action)) {
            url = registerUser(request, response);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String checkUser(HttpServletRequest request) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();

        Product product = productService.getProduct(productCode);
        session.setAttribute("product", product);

        User user = (User) session.getAttribute("user");
        return (user == null) ? "/register.jsp" : "/download.jsp";
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User(firstName, lastName, email);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // 🔹 Luôn tạo cookie với path="/" để dễ xóa
        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 24 * 30); // 30 ngày
        emailCookie.setPath("/");
        response.addCookie(emailCookie);

        Cookie firstNameCookie = new Cookie("firstNameCookie", firstName);
        firstNameCookie.setMaxAge(60 * 60 * 24 * 30);
        firstNameCookie.setPath("/");
        response.addCookie(firstNameCookie);

        return "/download.jsp";
    }

    private String downloadSong(HttpServletRequest request) {
        String productCode = request.getParameter("productCode");
        String songName = request.getParameter("song");

        HttpSession session = request.getSession();
        session.setAttribute("downloadMessage", "Downloading: " + songName + " from " + productCode);

        Product product = productService.getProduct(productCode);
        session.setAttribute("product", product);

        return "/download.jsp";
    }

    private void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (!"JSESSIONID".equals(c.getName())) {
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
            }
        }
        request.setAttribute("message", "✅ All persistent cookies have been deleted.");
    }
}
