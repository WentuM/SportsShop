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
<form><div class="main-content">
        <p class="headline">Редактирование профиля</p>
        <div class="inputs"><input type="text" placeholder="Name"><br></div>
        <div class="inputs"><input type="email" placeholder="Email"><br></div>
        <div class="inputs"><input type="text" placeholder="Number" pattern="^(8|\+7)([0-9]){10}"><br></div>
        <div class="inputs"><input id="text-count" type="password" placeholder="Old Password" required><br></div>
        <div class="inputs"><input type="password" placeholder="New Password"><br></div>
        <div class="inputs"><input type="file" required></div>
    </div>
    <input class="submit" type="submit" value="Принять изменения">
</form>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>