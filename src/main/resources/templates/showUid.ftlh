<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User console</title>
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
<h2>Таблица работников</h2>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>статус входа</th>
        <th>дата входа/выхода</th>
    </tr>
    <#list uid as allUid>
        <tr>
            <td>${allUid.firstName}</td>
            <td>${allUid.lastName}</td>
            <td>${allUid.status?c}</td>
            <td>${allUid.datatime}</td>
        </tr>
    </#list>
</table>
</body>
</html>