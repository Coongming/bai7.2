<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/taglibs/standard-3.0" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
<h1>Register</h1>

<p>Please enter your info. We’ll store your first name in a cookie.</p>

<form action="register" method="post">
  <label for="firstName">First name</label>
  <input id="firstName" name="firstName" type="text" required><br>

  <label for="email">Email</label>
  <input id="email" name="email" type="email"><br>

  <input type="submit" value="Save & Continue">
</form>

<hr>
<p>
  Current cookie (firstName): 
  <c:out value="${cookie.firstName.value}" default="(none)"/>
</p>

<p><a href="index.jsp">Back to CD list</a> · <a href="view_cookies.jsp">View all cookies</a></p>
</body>
</html>
