<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="https://jakarta.ee/taglibs/standard-3.0" %>
<%@ taglib prefix="fn" uri="https://jakarta.ee/taglibs/standard/functions-3.0" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Email List - Thanks</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
<h1>Thanks for joining our email list</h1>

<p>Here is the information that you entered:</p>
<label>Email:</label> <span>${user.email}</span><br/>
<label>First Name:</label> <span>${user.firstName}</span><br/>
<label>Last Name:</label> <span>${user.lastName}</span><br/>

<hr>
<p><strong>Today:</strong> ${requestScope.currentDate}</p>

<hr>
<p><strong>First two users (session):</strong></p>
<c:if test="${not empty sessionScope.users}">
  <c:forEach var="u" items="${sessionScope.users}" begin="0" end="1">
    <div>- ${u.firstName} ${u.lastName} &lt;${u.email}&gt;</div>
  </c:forEach>
</c:if>

<hr>
<p><strong>Customer Service:</strong> ${initParam.custServEmail}</p>

<p>To enter another email address, click Return.</p>
<form action="emailList" method="post">
    <input type="hidden" name="action" value="join"/>
    <input type="submit" value="Return"/>
</form>

<%@ include file="includes/footer.jsp" %>
</body>
</html>
