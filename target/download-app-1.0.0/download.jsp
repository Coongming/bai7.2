<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Download Album</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
<div class="container">
    <h2>📥 Tải album</h2>
    <p>Mã album: <c:out value="${param.productCode}" /></p>

    <form action="download" method="post">
        <input type="hidden" name="productCode" value="${param.productCode}" />

        <label for="name">Tên của bạn:</label>
        <input type="text" id="name" name="name" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />

        <button type="submit">Download</button>
    </form>
</div>
</body>
</html>