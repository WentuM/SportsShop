<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактирование профиля</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        .main-content {
            width: 40%;
        }
        .submit {
            margin-left: 42%;
        }
    </style>
    <#include "/include/bootstrap-link.ftl">
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<form method="post"><div class="main-content">
        <p class="headline">Редактирование профиля</p>
        <div class="inputs"><input name="name" type="text" placeholder="Name" value="${name}"><br></div>
        <div class="inputs"><input name="email" type="email" placeholder="Email" value="${email}"><br></div>
        <div class="inputs"><input name="number" type="text" placeholder="Number" value="${number}" pattern="^(8|\+7)([0-9]){10}"><br></div>
<#--        <div class="inputs"><input id="text-count" type="password" placeholder="Old Password"><br></div>-->
<#--        <div class="inputs"><input type="password" placeholder="New Password"><br></div>-->
        <div class="inputs"><input type="file"></div>
    </div>
    <input class="submit" type="submit" value="Принять изменения">
</form>
<div><input class="submit" type="button" onclick='location.href="/profile"' value="Отмена"></div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>