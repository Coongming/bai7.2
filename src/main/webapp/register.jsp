<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>📝 Đăng ký thông tin</title>
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
<div class="container">
  <h2>📝 Nhập thông tin để tải nhạc</h2>

  <form action="register" method="post">
    <label for="firstName">Họ tên:</label>
    <input type="text" name="firstName" id="firstName" required />

    <label for="email">Email:</label>
    <input type="email" name="email" id="email" required />

    <button type="submit">Đăng ký</button>
  </form>
</div>
</body>
</html>