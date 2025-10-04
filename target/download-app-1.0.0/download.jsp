<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="murach.download.bai9_1.Product" %>
<%@ page import="murach.download.bai9_1.User" %>
<%@ page import="java.util.List" %>
<%
    Product product = (Product) session.getAttribute("product");
    String downloadMessage = (String) session.getAttribute("downloadMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Downloads</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<div class="container">
    <h1>Downloads</h1>

    <h2><%= product != null ? product.getDescription() : "No album selected" %></h2>

    <% if (downloadMessage != null) { %>
    <div class="download-message"><%= downloadMessage %></div>
    <% session.removeAttribute("downloadMessage"); %>
    <% } %>

    <table>
        <tr>
            <th>Song title</th>
            <th>Audio Format</th>
        </tr>
        <% if (product != null) {
            for (String song : product.getSongs()) { %>
        <tr>
            <td><%= song %></td>
            <td>
                <a href="${pageContext.request.contextPath}/download?action=downloadSong&productCode=<%=product.getCode()%>&song=<%=song%>">
                    MP3
                </a>
            </td>
        </tr>
        <%   }
        } %>
    </table>

    <p><a href="${pageContext.request.contextPath}/download?action=viewAlbums">View list of albums</a></p>
</div>
</body>
</html>
