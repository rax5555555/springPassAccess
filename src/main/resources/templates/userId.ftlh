<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${user.firstName}</title>
</head>
<body>
<h3>Имя - ${user.firstName}</h3>
<h3>Почта - ${user.email}</h3>
<h3>Пароль - ${user.password}</h3>
<br>
<form method="post" action="/users/${user.id}/update">
    <label>
        Имя: <input name="firstName" value="${user.firstName}">
    </label>
    <label>
        Почта: <input name="email" value="${user.email}">
    </label>
    <label>
        Пароль: <input name="password" value="${user.password}">
    </label>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>