<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Download registration</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Downloads</h1>

<h2>Paddlefoot - The Second CD</h2>
<table>
<tr>
    <th>Song title</th>
    <th>Audio Format</th>
</tr>
<tr>
    <td>Neon Dreams</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/Neon_Dreams.mp3"/>">MP3</a>
    </td>
</tr>
<tr>
    <td>Lost in the City</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/Lost_in_the_City.mp3"/>">
        MP3</a>
    </td>
</tr>
</table>

</body>
</html>