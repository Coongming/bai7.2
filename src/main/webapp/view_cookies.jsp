<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-3.0" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>View Cookies</title>
  <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
<h1>Cookies</h1>

<c:choose>
  <c:when test="${empty pageContext.request.cookies}">
    <p>No cookies.</p>
  </c:when>
  <c:otherwise>
    <table>
      <tr><th>Name</th><th>Value</th><th>Max-Age</th><th>Domain</th><th>Path</th></tr>
      <c:forEach var="ck" items="${pageContext.request.cookies}">
        <tr>
          <td><c:out value="${ck.name}"/></td>
          <td><c:out value="${ck.value}"/></td>
          <td><c:out value="${ck.maxAge}"/></td>
          <td><c:out value="${ck.domain}"/></td>
          <td><c:out value="${ck.path}"/></td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<p><a href="index.jsp">Back to CD list</a> · <a href="register.jsp">Register</a></p>
</body>
</html>
