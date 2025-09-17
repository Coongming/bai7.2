<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Download registration</title>
  <link rel="stylesheet" href="styles/main.css">
  <!-- Toàn bộ chữ đen, kể cả link -->
  <style> a{ color:#111; } </style>
</head>
<body>
  <h1>Download registration</h1>
  <div class="hr"></div>

  <p>To register for our downloads, enter your name and email address below.</p>

  <form class="form-card" action="download" method="post">
    <input type="hidden" name="action" value="registerUser"/>

    <label>Email:</label>
    <input type="email" name="email" value="${user.email}"/>

    <label>First Name:</label>
    <input type="text" name="firstName" value="${user.firstName}"/>

    <label>Last Name:</label>
    <input type="text" name="lastName" value="${user.lastName}"/>

    <input type="submit" value="Register"/>
  </form>
</body>
</html>
