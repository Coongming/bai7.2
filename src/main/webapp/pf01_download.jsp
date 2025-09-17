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

<h2>Paddlefoot - The First CD</h2>
<table>
<tr>
    <th>Song title</th>
    <th>Audio Format</th>
</tr>
<tr>
    <td>Wait</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/Wait.mp3"/>">MP3</a>
    </td>
</tr>
<tr>
    <td>I Won't Let You Down</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/I_Won't_Let_You_Down.mp3"/>">
        MP3</a>
    </td>
</tr>
</table>

</body>
</html>