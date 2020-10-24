<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <#include "/include/bootstrap-link.ftl">
    <style>
        .main-content {
            font-family: "Palatino Linotype", serif;
        }

        span {
            color: brown;
        }

        .description {
            padding-left: 30px;
            padding-right: 30px;
        }
    </style>
</head>

<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Добро пожаловать в INPROTEIN!</p>
    <div class="description">
        <p>
            Занятия спортом – дело хорошее, но для того,
            чтобы достичь необходимых результатов подход к спорту должен быть грамотным.
            <span>Особое внимание нужно уделить питанию</span>, так как именно необходимое
            количество питательных веществ позволяет добиться необходимого эффекта
            при спортивных тренировках.
        </p>
        <p>
            Наш магазин предлагает <span>широкий спектр</span> разного рода биологически активных добавок
            к пище, что помогут Вам правильно выстроить свой рацион и поспособствуют
            достижению необходимых результатов в спорте.
        </p>
    </div>
</div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>