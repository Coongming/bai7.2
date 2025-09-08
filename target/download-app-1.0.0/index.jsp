<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🎶 Trang chủ</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
<div class="container">
    <h1>🎶 Ứng dụng tải nhạc</h1>

    <!-- Hiển thị thông điệp chào mừng nếu có cookie firstName -->
    <c:choose>
        <c:when test="${not empty cookie.firstName.value}">
            <p>Xin chào <strong><c:out value="${cookie.firstName.value}" /></strong>! Rất vui được gặp lại bạn.</p>
        </c:when>
        <c:otherwise>
            <p>Chào mừng bạn đến với ứng dụng tải nhạc. Vui lòng <a href="register.jsp">đăng ký</a> để bắt đầu.</p>
        </c:otherwise>
    </c:choose>

    <!-- Liên kết đến các trang khác -->
    <div class="links">
        <a href="view_cookies.jsp">🔍 Xem thông tin từ Cookie</a>
        <a href="products">🎵 Xem danh sách Album</a>
    </div>
</div>
</body>
</html>