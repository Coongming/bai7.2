<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Albums</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<div class="container">
    <h1>List of albums</h1>

    <div class="album-list">
        <p><a href="${pageContext.request.contextPath}/download?action=checkUser&productCode=8601">
            86 (the band) - True Life Songs and Pictures
        </a></p>
        <p><a href="${pageContext.request.contextPath}/download?action=checkUser&productCode=pf01">
            Paddlefoot - The First CD
        </a></p>
        <p><a href="${pageContext.request.contextPath}/download?action=checkUser&productCode=pf02">
            Paddlefoot - The Second CD
        </a></p>
        <p><a href="${pageContext.request.contextPath}/download?action=checkUser&productCode=jr01">
            Joe Rut - Genuine Wood Grained Finish
        </a></p>
    </div>

    <p><a href="${pageContext.request.contextPath}/download?action=viewCookies">View all cookies</a></p>
</div>
</body>
</html>
