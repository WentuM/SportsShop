<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <#include "/include/bootstrap-link.ftl">
    <style>
        .main-content {
            width: 40%;
            height: 325px;
        }
        .submit {
            margin-left: 41.5%;
        }
        .inputs {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<form method="post"><div class="main-content">
        <p class="headline">Регистрация</p>
        <div class="inputs"><input name="name" type="text" placeholder="Name" required><br></div>
        <div class="inputs"><input name="email" type="email" placeholder="Email" required><br><small>*Пример: example@ex.com</small></div>

        <div class="inputs"><input name="number" type="text" placeholder="Number" pattern="^(8|+7)+[0-9]{10}" required><br><small>*Без разделителей, 8../+7..</small></div>
        <div class="inputs"><input name="password" type="password" placeholder="Password" required><br><small>*Не менее 6 символов</small></div>
        <a href="/login" style="margin: 0 auto; color: black !important; font-size: 20px !important;">Войти в аккаунт</a>
    </div>
    <input class="submit" type="submit" value="Зарегистрироваться">
</form>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>