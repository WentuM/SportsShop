<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Форум</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <#include "/include/bootstrap-link.ftl">
    <style>
        .headline {
            margin-bottom: 0;
        }

        .main-content {
            width: 50%;
            height: 50%;
            margin-bottom: 30px;
        }

        .slides {
            margin: 20px 20px 0;
        }

        .carousel-caption {
            background: rgba(0, 0, 0, 0.42);
            border-radius: 20px;
        }
    </style>
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Активные акции</p>
    <div class="slides">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="5000" data-wrap="true">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="resources/firstOrder.jpeg" alt="Первый заказ">
                    <div class="carousel-caption d-none d-md-block">
                        <h3>СКИДКА 10%</h3>
                        <p>на первый онлайн-заказ</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="resources/protein.jpg" alt="Второй слайд">
                    <div class="carousel-caption d-none d-md-block">
                        <h3>2+1=2</h3>
                        <p>при покупке двух,<br> третий протеиновый батончик - в подарок</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="resources/1500.jpeg" alt="Третий слайд">
                    <div class="carousel-caption d-none d-md-block">
                        <h3>-200 рублей</h3>
                        <p>при стоимости заказа от 2000 рублей</p>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <small>*акции и скидки между собой не суммируются</small>
</div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>