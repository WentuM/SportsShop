<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <#include "/include/bootstrap-link.ftl">
    <style>
        .main-content {
            text-align: left;
            height: auto;
        }

        .profile_photo {
            border-radius: 20px;
            margin-bottom: 20px;
            border: 1px solid black;
            width: 270px;
        }
        p {
            font-family: "Palatino Linotype", serif;
        }
        .submit {
            margin-left: 43%;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Мой профиль</p>
    <div class="container">
        <div class="row">
            <div class="col">
                <img class="profile_photo" alt="My Photo" src="resources/user.png">
            </div>
            <div class="col" style="border-left: 1px solid black">
                <p><b>Информация об этом пользователе</b></p>
                <p>Имя: <em>Иван Иванов</em><br>
                    Номер телефона: <em>88005553535</em><br>
                    Email: <em>ivan@example.com</em><br></p>
            </div>
        </div>
    </div>
</div>
<div><form><input class="submit" type="button" onclick='location.href="edit.shtml"' value="Редактировать"></form></div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>