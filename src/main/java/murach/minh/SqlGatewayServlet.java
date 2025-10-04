package murach.minh;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class SqlGatewayServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String sqlStatement = request.getParameter("sqlStatement");
        String sqlResult = "";

        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Kết nối tới PostgreSQL trên Render
            String dbURL = "jdbc:postgresql://dpg-d39l2bbe5dus73bi7ks0-a.singapore-postgres.render.com:5432/app_db_yh2h?sslmode=require";
            String user = "user_db";        // user Render cung cấp
            String password = "gLcVGqO6jeB41n438F9rNbMKT7xFoXAQ"; // password Render cung cấp

            Connection connection = DriverManager.getConnection(dbURL, user, password);
            Statement statement = connection.createStatement();

            // Nếu chưa nhập thì mặc định select * from users
            if (sqlStatement == null || sqlStatement.trim().equals("")) {
                sqlStatement = "SELECT * FROM users";
            }

            sqlStatement = sqlStatement.trim();
            String sqlType = sqlStatement.substring(0, 6).toLowerCase();

            if (sqlType.equals("select")) {
                ResultSet resultSet = statement.executeQuery(sqlStatement);
                sqlResult = SQLUtil.getHtmlTable(resultSet);
                resultSet.close();
            } else {
                int i = statement.executeUpdate(sqlStatement);
                if (i == 0) {
                    sqlResult = "<p>The statement executed successfully.</p>";
                } else {
                    sqlResult = "<p>The statement executed successfully.<br>"
                              + i + " row(s) affected.</p>";
                }
            }

            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            sqlResult = "<p>Error loading the database driver:<br>"
                      + e.getMessage() + "</p>";
        } catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement:<br>"
                      + e.getMessage() + "</p>";
        }

        // Đưa kết quả sang JSP
        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);

        String url = "/index.jsp";
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
}
