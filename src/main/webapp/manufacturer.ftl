<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/main-content.css">
    <link rel="stylesheet" href="/css/footer.css">
    <!--#include virtual="include/bootstrap-link.txt"-->
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
<!--#include virtual="include/header.txt"-->
<div class="main-content"><p class="headline">Мой профиль</p>
    <div class="container">
        <div class="row">
            <div class="col">
                <img class="producer_photo" alt="My Photo" src="resources/logo-1.png">
            </div>
            <div class="col" style="border-left: 1px solid black">
                <p><b>Информация об этом производителе</b></p>
                <p>Название: <em>GEON</em><br>
                    Описание: Создателями компании являются Сергей Штерман и Михаил Сидоренко — доктора технических наук, ученые и просто настоящие фанаты своего дела, за плечами каждого из которых большой опыт научной и преподавательской деятельности в сфере производства специализированных пищевых продуктов. Суммарно их научные труды насчитывают более 200 публикаций, в том числе они имеют 25 патентов РФ на изобретения в данной области и пять опубликованных монографий.
            </div>
        </div>
    </div>
</div>
<div><form><input class="submit" type="button" onclick='location.href="edit.shtml"' value="Редактировать"></form></div>
<!--#include virtual="include/footer.txt"-->
<!--#include virtual="include/bootstrap-scripts.txt"-->
</body>
</html>