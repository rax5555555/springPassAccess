<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        input[type = "submit"], a {
            font-size: 14px;
            font-family: Arial, sans-serif;
            background-color: rgb(223, 223, 223);
            padding: 2px 4px;
            border-radius: 3px;
        }

        a {
            text-decoration: none;
            border: 1px solid black;
            color: black;
        }
    </style>
    <title>Пользователи</title>
</head>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }

</style>
</head>
</head>
<body>
<h1>Добавление пользователя</h1>
<form method="post" action="/users">
    <label>Имя:
        <input name="firstName" placeholder="Имя">
    </label>
    <label>Почта:
        <input type="email" name="email" placeholder="Почта">
    </label>
    <label>Пароль:
        <input type="Password" name="password" placeholder="Пароль">
    </label>
    <input type="submit" value="Добавить">
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Почта</th>
        <th>Пароль</th>
        <th>Действия</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>
                <form method="post" action="/users/${user.id}/delete">
                    <input type="submit" value="Удалить">
                </form>
                <a href="/users/${user.id}">Перейти</a>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>