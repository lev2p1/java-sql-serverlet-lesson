<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html>
    <body>
        <h1>Форма выхода</h1>

        <form method="post" action="/welcome">
            <input type="hidden" name="_method" value="DELETE">
            <button type="submit">Exit</button>
        </form>

        ${error}
    </body>
</html>