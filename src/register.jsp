<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html>
    <body>
        <h1>Форма регистрации</h1>

        <form method="post" action="/register/save">
            <input type="text" name="username_1" placeholder="username">
            <input type="text" name="email_1" placeholder="email">
            <input type="text" name="password_1" placeholder="password">
            <button type="submit">Отправить</button>
        </form>

        ${username_1}
    </body>
</html>