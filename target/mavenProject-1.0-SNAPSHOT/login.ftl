<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        .main-content {
            width: 40%;
            height: 200px;
        }
        .submit {
            width: auto;
            padding: 5px 40px;
            margin-left: 45.5%;
            border: 0;
        }
        input[type=checkbox], input[type=radio] {
            margin-top: 0;
        }
    </style>
    <#include "/include/bootstrap-link.ftl">
</head>

<body>
<#import "/include/header.ftl" as m>
<@m.header />
<form><div class="main-content">
        <p class="headline">Вход в профиль</p>
        <input name="email" type="email" placeholder="Email" required></p>
        <input name="password" type="password" placeholder="Password" required></p>
        <div class="form-check">
            <input name="rememberMe" type="checkbox" class="form-check-input" id="remember_me">
            <label class="form-check-label" for="remember_me">Запомнить меня</label>
        </div>
        <a href="/register" style="margin: 0 auto; color: black !important; font-size: 12px !important;">Зарегистрируйтесь здесь</a>
    </div>
    <input class="submit" type="submit" value="Войти">
</form>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>