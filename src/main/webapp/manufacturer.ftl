<#ftl encoding="UTF-8"/>
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

        .producer_photo {
            border-radius: 20px;
            margin-bottom: 20px;
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
<div class="main-content"><p class="headline">Страница Производителя</p>
    <div class="container">
        <div class="row">
            <div class="col">
                <img class="producer_photo" alt="My Photo" src="${manufactur.manufacturerImage}">
            </div>
            <div class="col" style="border-left: 1px solid black">
                <p><b>Информация об этом производителе</b></p>
                <p>Название: <em>${manufactur.name}</em><br>
                    Описание: ${manufactur.description}
            </div>
        </div>
    </div>
</div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>