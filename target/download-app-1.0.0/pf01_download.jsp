<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Downloads</title>
  <link rel="stylesheet" href="styles/main.css">
</head>
<body>
  <h1>Downloads</h1>
  <h2>86 (the band) - True Life Songs and Pictures</h2>

  <table>
    <tr><th>Song title</th><th>Audio Format</th></tr>
    <tr>
      <td>You Are a Star</td>
      <td><a href="/musicStore/sound/${sessionScope.productCode}/star.mp3">MP3</a></td>
    </tr>
    <tr>
      <td>Don't Make No Difference</td>
      <td><a href="/musicStore/sound/${sessionScope.productCode}/no_difference.mp3">MP3</a></td>
    </tr>
  </table>
</body>
</html>
