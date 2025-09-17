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

<h2>Joe Rut - Genuine Wood Grained Finish</h2>
<table>
<tr>
    <th>Song title</th>
    <th>Audio Format</th>
</tr>
<tr>
    <td>Wood Grain</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/Wood_Grain.mp3"/>">MP3</a>
    </td>
</tr>
<tr>
    <td>Rustic Finish</td>
    <td>
        <a href="<c:url value="/musicStore/sound/${productCode}/Rustic_Finish.mp3"/>">
        MP3</a>
    </td>
</tr>
</table>

</body>
</html>