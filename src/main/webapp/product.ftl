<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Информация о товаре</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        .main-content {
            text-align: left;
            height: auto;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }

        .product_photo {
            border-radius: 5px;
            width: 100%
        }

        p {
            font-family: "Palatino Linotype", serif;

        }

        .submit {
            margin-left: 43%;
        }

        .info {
            border-radius: 20px;
            margin-right: 20px;
            padding: 5px;
        }

        h6 {
            font-size: 16px !important;
        }
        button {
            text-decoration: none !important;
            color: black !important;
            padding: 0 !important;
        }
        .card-header {
            padding: 0 !important;
            background: rgba(255, 255, 255, 0.42) !important;
            border: 0 !important;
            margin: 0 !important;
            text-align: center;
        }
        .btn.focus, .btn:focus {
            box-shadow: none !important;
        }
        .description {
            font-size: 12px;
        }
        textarea {
            box-sizing: border-box;
            width: 70%;
        }
        .review, .reviews {
            text-align: center;
            border: 1px solid black;
        }
        .message {
            text-align: left;
            margin: 10px;
            background: white;
            padding: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            border-radius: 5px;
            font-size: small;
        }
        .send {
            font-size: 12px;
        }
        .send:hover {
            background: #455e84;
        }
    </style>
    <#include "/include/bootstrap-link.ftl">
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Информация о товаре</p>
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <img class="product_photo" src="${product.imageProduct}">
                <#if auth??>

                    <#else>
                        <form action="/addReview?id=#{product.id}" method="post">
                            <div class="review">
                                <h6>Оставить отзыв:
                                </h6><textarea name="reviewText" rows="5" placeholder="Введите текст..."></textarea>
                            </div>
                            <input class="send" type="submit" value="Отправить" style="margin-left: 40.5%; margin-bottom: 30px"></form>
                </#if>
                <div class="reviews"><h6>Все отзывы:</h6>
                    <#list reviews as review>
                    <div class="message">
                        Имя пользователя: ${review.user.name}<br>
                        Дата: ${review.data}<br>
                        Отзыв: ${review.text}<br>
                    </div>
                    </#list>
                </div>
            </div>
            <div class="col-sm-4">
                <div style="padding: 10px; border-left: 1px solid black; border-bottom: 1px solid black">
                    <form method="post" action="/addProduct?id=${product.id}"><h6><b>${product.name}</b></h6>
                        <table>
                            <tr>
                                <td>Кол-во</td>
                                <td><input name="quantity" type="number" min="1" max="${product.count}" required
                                           style="width: 40px !important; margin-left: 10px !important;"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="В корзину" style="margin-left: 10px" class="send">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div style="padding: 5px; border-left: 1px solid black; border-bottom: 1px solid black"><div id="accordion">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">
                                <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Характеристики
                                </button>
                            </h5>
                        </div>

                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body description">
                                Категория: ${product.category}<br>
                                Производитель: <input type="button" onclick='location.href="/manufacturer?manufacturer=${product.manufacturer.id}"' value="${product.manufacturer.name}"><br>
                                Цена: ${product.price} р/шт<br>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h5 class="mb-0">
                                <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Описание
                                </button>
                            </h5>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                            <div class="card-body description">
                                ${product.description}
                            </div>
                        </div>
                    </div>
                </div></div>
        </div>
    </div>
</div>
</div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<#include "/include/bootstrap-scripts.ftl">
</body>
</html>