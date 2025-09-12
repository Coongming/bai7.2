<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="https://jakarta.ee/taglibs/standard-3.0" %>
<%@ taglib prefix="fn" uri="https://jakarta.ee/taglibs/standard/functions-3.0" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html><body>
<h2>All Cookies</h2>
<table border="1" cellpadding="6">
  <tr><th>Name</th><th>Value</th></tr>
  <c:forEach var="ck" items="${pageContext.request.cookies}">
    <tr>
      <td>${ck.name}</td>
      <td><c:out value="${ck.value}"/></td>
    </tr>
  </c:forEach>
</table>
</body></html>
