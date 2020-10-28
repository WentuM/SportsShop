<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        .main-content {
            padding-bottom: 20px;
        }
        .submit {
            width: 165px;
            padding: 5px 40px;
            margin-left: 43.5%;
            border: 0;
        }
    </style>
    <#include "/include/bootstrap-link.ftl">
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Оформление</p>
    <div class="container">
        <div class="row">
            <div class="col-sm-4" style="text-align: left; border-right: 1px solid black">
                <p>Заказчик: <input type="text" placeholder="Вася" disabled><br>
                    Номер телефона: <input type="text" placeholder="88005553535" disabled><br></p>
                <small><a href="/editProfile" style="color: #455e84 !important;">Редактировать профиль</a></small>
            </div>
            <div class="col-sm-8"  style="text-align: left;" >
                Сумма заказа: 2350 рублей (+ учёт скидок)<br>
                Способ оплаты: при получении<br>
                Способ доставки: самовывоз из магазина (Россия, респ. Татарстан, г. Казань, Кремлёвская 35)<br>
                Стоимость доставки: бесплатно<br>
                Период получения: от даты оформления - +3 дня к дате оформления
            </div>
        </div>
    </div>
</div>
<input type="submit" value="Подтвердить" class="submit">
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>