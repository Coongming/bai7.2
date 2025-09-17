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

    private static final String VIEW_ALBUMS_ACTION = "viewAlbums";
    private static final String CHECK_USER_ACTION = "checkUser";
    private static final String REGISTER_USER_ACTION = "registerUser";
    private static final String EMAIL_COOKIE_NAME = "emailCookie";
    private static final String EMAIL_LIST_PATH = "/WEB-INF/EmailList.txt";
    private static final String REGISTER_PAGE = "/register.jsp";
    private static final String INDEX_PAGE = "/index.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = VIEW_ALBUMS_ACTION;
        }

        String url = INDEX_PAGE;
        if (VIEW_ALBUMS_ACTION.equals(action)) {
            url = INDEX_PAGE;
        } else if (CHECK_USER_ACTION.equals(action)) {
            url = checkUser(request);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = INDEX_PAGE;
        if (REGISTER_USER_ACTION.equals(action)) {
            url = registerUser(request, response);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private String checkUser(HttpServletRequest request) {
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);

        User user = (User) session.getAttribute("user");
        String url;

        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String email = CookieUtil.getCookieValue(cookies, EMAIL_COOKIE_NAME);

            if (email == null || email.isEmpty()) {
                url = REGISTER_PAGE;
            } else {
                ServletContext sc = getServletContext();
                String path = sc.getRealPath(EMAIL_LIST_PATH);
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
        String last = request.getParameter("lastName");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(first);
        user.setLastName(last);

        ServletContext sc = getServletContext();
        String path = sc.getRealPath(EMAIL_LIST_PATH);
        UserIO.add(user, path);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        Cookie c = new Cookie(EMAIL_COOKIE_NAME, email);
        c.setMaxAge(-1); //(60 * 60 * 24 * 365 * 2); // 2 năm
        c.setPath("/");
        response.addCookie(c);

        String productCode = (String) session.getAttribute("productCode");
        return "/" + productCode + "_download.jsp";
    }
}