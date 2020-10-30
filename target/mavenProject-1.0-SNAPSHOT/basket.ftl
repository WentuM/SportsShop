<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Корзина</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        td {
            border-top: none !important;
            border-left: 1px solid black !important;
        }
        th {
            border-top: none!important;
            border-bottom: none !important;
        }
        .submit {
            margin-left: 44%;
            width: 150px;
            margin-bottom: 30px;
        }
    </style>
    <#include "/include/bootstrap-link.ftl">
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Корзина</p>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Цена </th>
            <th scope="col">Кол-во</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <#list order.productList as product>
        <tr>
            <th scope="row">1</th>
            <td>product.name</td>
            <td>product.salary</td>
            <td>product.count</td>
            <td><input type="submit" value="Удалить"></td>
        </tr>
        </#list>
        </tbody>
    </table>
    <p style="margin-bottom: 5px"><em>Суммарная цена: 2350 рублей</em></p>
</div>
<input class="submit" type="submit" value="Оформить" onclick='location.href="/order"'>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>