<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html>
    <body>
        <h1>Форма регистрации</h1>

        <form method="post">
            <input type="text" name="username_1" placeholder="username">
            <button type="submit">Отправить</button>
        </form>

        <%
            String username = null;
            
            if ("POST".equalsIgnoreCase(request.getMethod())){
                username = request.getParameter("username_1"); 
            } 
            if (username != null && !username.trim().isEmpty())  {

        %>
            Hello, <%= username %>
        <%
            }
        %>
    </body>
</html>