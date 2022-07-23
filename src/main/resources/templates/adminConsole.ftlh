<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin console</title>
</head>
<head>
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
<body>
<h1>Добавление работника</h1>
<form method="post" action="/all/admin">
    <label>Имя:
        <input name="firstName" placeholder="Имя">
    </label>
    <label>Фамилия:
        <input name="lastname" placeholder="Фамилия">
    </label>
    <label>UID:
        <input name="uid" placeholder="UID">
    </label>
    <input type="submit" value="Добавить">
</form>
<br/>
<h2>Таблица работников</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>uid</th>
        <th>статус входа</th>
        <th>дата входа/выхода</th>
        <th>действия</th>
    </tr>
    <#list uid as allUid>
        <tr>
            <td>${allUid.id}</td>
            <td>${allUid.firstName}</td>
            <td>${allUid.lastName}</td>
            <td>${allUid.uid}</td>
            <td>${allUid.status?c}</td>
            <td>${allUid.datatime}</td>
            <td>
                <form method="post" action="/all/admin/${allUid.id}/delete">
                    <input type="submit" value="Удалить">
                </form>
                <a href="/all/admin/${allUid.id}">Перейти</a>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>