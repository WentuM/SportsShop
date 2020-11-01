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
<#if errorMessage??>
    <span style="color: red; text-align: center">${errorMessage}</span>
</#if>
<form method="post" action="/editProfile" enctype="multipart/form-data">
    <div class="main-content">
        <p class="headline">Редактирование профиля</p>
        <div class="inputs"><input name="name" type="text" placeholder="Name" value="${name}" maxlength="20"><br></div>
        <div class="inputs"><input name="email" type="email" placeholder="Email" value="${email}"><br></div>
        <div class="inputs"><input name="number" type="text" placeholder="Number" value="${number}"
                                   pattern="^(8|\+7)([0-9]){10}" maxlength="12"><br></div>

        <div class="inputs"><input type="file" name="filename" accept=".jpg, .jpeg, .png"></div>
    </div>
    <input class="submit" type="submit" value="Принять изменения">
</form>
<div><input class="submit" type="button" onclick='location.href="/profile"' value="Отмена"></div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>