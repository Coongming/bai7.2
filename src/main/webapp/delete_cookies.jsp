<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page import="jakarta.servlet.http.Cookie" %>
<%
  Cookie[] cs = request.getCookies();
  if (cs != null) {
    String path = request.getContextPath().isEmpty() ? "/" : request.getContextPath();
    for (Cookie c : cs) {
      // chỉ xoá cookie “lưu lâu” (tuỳ chính sách, ở đây xoá tất cả trừ JSESSIONID)
      if (!"JSESSIONID".equals(c.getName())) {
        c.setMaxAge(0);
        c.setPath(path);
        response.addCookie(c);
      }
    }
  }
%>
<!doctype html><html><body>
<p>Deleted all persistent cookies.</p>
<p><a href="<%=request.getContextPath()%>/index.jsp">Back</a></p>
</body></html>
