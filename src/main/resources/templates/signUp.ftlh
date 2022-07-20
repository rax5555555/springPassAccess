<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
</head>
<body>
<form method="post" action="/signUp">
    <label>Имя:
        <input name="firstName" placeholder="Введите имя">
    </label>
    <label>Email:
        <input type="email" name="email" placeholder="Введите email">
    </label>
    <label>Пароль:
        <input type="password" name="password" placeholder="Введите пароль">
    </label>
    <input type="submit" value="Регистрация">
</form>
</body>
</html>