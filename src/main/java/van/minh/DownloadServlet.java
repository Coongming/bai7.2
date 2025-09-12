package van.minh;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/download")
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
      // đọc cookie userEmail nếu có
      String emailCookie = null;
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie c : cookies) {
          if ("userEmail".equals(c.getName())) {
            emailCookie = c.getValue();
            break;
          }
        }
      }
      if (emailCookie == null || emailCookie.isEmpty()) {
        url = "/register.jsp";
      } else {
        // nạp User từ file
        String path = getServletContext().getRealPath("/WEB-INF/EmailList.txt");
        user = UserIO.getUser(emailCookie, path);
        session.setAttribute("user", user);
        url = "/" + productCode + "_download.jsp";
      }
    } else {
      url = "/" + productCode + "_download.jsp";
    }
    return url;
  }

  private String registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String email = request.getParameter("email");
    String fn = request.getParameter("firstName");
    String ln = request.getParameter("lastName");

    User user = new User();
    user.setEmail(email);
    user.setFirstName(fn);
    user.setLastName(ln);

    // lưu file “giả”
    String path = getServletContext().getRealPath("/WEB-INF/EmailList.txt");
    UserIO.add(user, path);

    // session
    HttpSession session = request.getSession();
    session.setAttribute("user", user);

    // COOKIE theo đề: tên userEmail, sống 3 năm
    Cookie cookie = new Cookie("userEmail", email);
    cookie.setMaxAge(60 * 60 * 24 * 365 * 3);           // 3 năm
    cookie.setHttpOnly(true);
    cookie.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath());
    response.addCookie(cookie);

    String productCode = (String) session.getAttribute("productCode");
    return "/" + productCode + "_download.jsp";
  }
}
