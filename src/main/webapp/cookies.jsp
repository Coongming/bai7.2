<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cookies</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<div class="container">
    <h1>Cookies</h1>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <p class="download-message"><%= message %></p>
    <%
        }
    %>

    <%
        Cookie[] cookies = request.getCookies();
        boolean hasPersistent = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (!"JSESSIONID".equals(c.getName())) {
                    hasPersistent = true;
                    break;
                }
            }
        }

        if (!hasPersistent) {
    %>
    <p>All persistent cookies have been removed from this browser.</p>
    <%
    } else {
    %>
    <p>Here's a table with all of the cookies that this browser is sending to the current server.</p>
    <table>
        <tr>
            <th>Name</th>
            <th>Value</th>
        </tr>
        <%
            if (cookies != null) {
                for (Cookie c : cookies) {
        %>
        <tr>
            <td><%= c.getName() %></td>
            <td><%= c.getValue() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <p>
        <a href="<%= request.getContextPath() %>/download?action=deleteCookies">
            Delete all persistent cookies
        </a>
    </p>
    <%
        }
    %>

    <p><a href="<%= request.getContextPath() %>/index.jsp">View list of albums</a></p>
    <p><a href="<%= request.getContextPath() %>/download?action=viewCookies">View all cookies</a></p>
</div>
</body>
</html>
