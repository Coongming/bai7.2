package murach.download;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.business.User;
import murach.data.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAlbums";

        String url = "/index.jsp";
        if ("viewAlbums".equals(action)) {
            url = "/index.jsp";
        } else if ("checkUser".equals(action)) {
            url = checkUser(request, response);
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

    private String checkUser(HttpServletRequest request, HttpServletResponse response) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);

        User user = (User) session.getAttribute("user");
        String url;

        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String email = CookieUtil.getCookieValue(cookies, "emailCookie");
            if (email == null || email.isEmpty()) {
                url = "/register.jsp";
            } else {
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.txt");
                user = UserIO.getUser(email, path);
                session.setAttribute("user", user);
                url = "/" + productCode + "_download.jsp";
            }
        } else {
            url = "/" + productCode + "_download.jsp";
        }
        return url;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String first = request.getParameter("firstName");
        String last  = request.getParameter("lastName");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(first);
        user.setLastName(last);

        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/EmailList.txt");
        UserIO.add(user, path);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        Cookie c = new Cookie("emailCookie", email);
        c.setMaxAge(60 * 60 * 24 * 365 * 2); // 2 năm
        c.setPath("/");
        response.addCookie(c);

        String productCode = (String) session.getAttribute("productCode");
        return "/" + productCode + "_download.jsp";
    }
}
