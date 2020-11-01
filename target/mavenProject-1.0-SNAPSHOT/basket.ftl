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
            border-top: none !important;
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
    <#if errorMessage??>
        <span style="color: red">${errorMessage}</span>
    </#if>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Название</th>
            <th scope="col">Цена</th>
            <th scope="col">Кол-во</th>
            <th scope="col">-</th>
        </tr>
        </thead>
        <tbody>
        <#assign count = 1>
        <#if products??>
            <#list products as product>
                <tr>
                    <th scope="row">${count}</th>
                    <#assign count = count + 1>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.count}</td>
                    <td><input type="submit" value="Удалить"
                               onclick='location.href="/deleteProduct?idProduct=${product.id}&idOrder=${orderId}&count=${product.count}"'>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
    <p style="margin-bottom: 5px"><em>Суммарная цена: ${allprice} рублей</em></p>
</div>
<form method="post" action="/basket">
    <input class="submit" type="submit" value="Оформить">
</form>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>