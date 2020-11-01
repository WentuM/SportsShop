<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Каталог</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/main-content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
        .main-content {
            margin-bottom: 30px;
        }

        table {
            text-align: left;
        }

        .sub {
            width: 120px;
            height: 30px;
        }

        .sub:hover {
            background: #455e84;
        }

        .search {
            overflow: hidden;
            width: 70%;
            margin-bottom: 20px;
        }

        .card-img-top {
            height: 120px;

        }

        .card-title {
            margin-bottom: 0 !important;
        }

        .card-body {
            padding: 8px !important;
        }

        .card {
            height: 186px;
            border: 1px solid black !important;
            background: rgba(255, 255, 255, 0.42) !important;
        }

        card:hover {
            box-shadow: 0 0 10px rgb(0, 0, 0);
        !important;
        }

        .search-but {
            margin-bottom: 5px;
        }

        .search-but:hover {
            background: #455e84;
        }
    </style>
    <script>
        function updateFeed(organizations, divElement) {
            let htmlElement = '';
            for (let i = 0; i < organizations.length; i++) {
                htmlElement += '<div class="col">\n';
                htmlElement += '    <a style="display: block; color: black !important;" href="/product?id=' + organizations[i]['id'] + '">';
                htmlElement += '        <div class="card">';
                htmlElement += '            <input type="hidden" name="product" value="id"/>';
                htmlElement += '                <img class="card-img-top" src="' + organizations[i]['imageProduct'] + '" alt="Card image cap">';
                htmlElement += '            <div class="card-body">';
                htmlElement += '            <h5 class="card-title">' + organizations[i]['name'] + '</h5>';
                htmlElement += '                <p class="card-text">' + organizations[i]['price'] + ' рублей</p>';
                htmlElement += '                </div>';
                htmlElement += '            </div>';
                htmlElement += '        <input class="submit" type="submit" value="В наличии" disabled>';
                htmlElement += '        </a>';
                htmlElement += '</div>';
            }

            divElement.html(htmlElement);
        }

        function sendAjax(text) {
            let data = {"search": text};

            $.ajax({
                type: "POST",
                url: "/catalog",
                data: JSON.stringify(data),
                success: function (response) {
                    updateFeed(response, $('#table'))
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js" charset="utf-8"></script>
    <#include "/include/bootstrap-link.ftl">
</head>
<body>
<#import "/include/header.ftl" as m>
<@m.header />
<div class="main-content"><p class="headline">Каталог</p>
    <div class="container">
        <div class="row">
            <div class="col-sm-4"><p><b>Фильтры</b></p>
                <form id="filter">
                    <table>
                        <th>По категории:</th>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="vitamins" value="vitamins">
                                    <label class="form-check-label" for="vitamins">Витамины</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="protein" value="protein">
                                    <label class="form-check-label" for="protein">Протеиновые батончики</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="izo" value="izo">
                                    <label class="form-check-label" for="protein">Изотоник</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="minerals" value="minerals">
                                    <label class="form-check-label" for="protein">Минералы и микроэлементы</label>
                                </div>
                            </td>
                        </tr>
                        <th>По цене:</th>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="low" value="low">
                                    <label class="form-check-label" for="low">до 500 рублей</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="medium" value="medium">
                                    <label class="form-check-label" for="low">от 500 до 2000 рублей</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="many" value="many">
                                    <label class="form-check-label" for="low">от 2000 рублей</label>
                                </div>
                            </td>
                        </tr>
                        <th>По наличию:</th>
                        <tr>
                            <td>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="checkbox" id="" value="">
                                    <label class="form-check-label" for="">В наличии</label>
                                </div>
                            </td>
                        </tr>

                    </table>
                    <input class="sub" type="submit" value="Принять"></form>
            </div>
            <div class="col-sm-8" style="border-left: 1px solid black">
                <form autocomplete="off">
                    <input id="myInput" type="text" class="search" placeholder="Поиск по названию...">
                    <input type="button" onclick="sendAjax($('#myInput').val())"
                                                class="search-but" value="Искать"
                                                style="margin-left: 5px">
                </form>
                <div class="container">
                    <div class="row" id="table">
                        <#list products as product>
                            <div class="col"> <!--Общее для всех продуктов-->
                                <a style="display: block; color: black !important;" href="/product?id=#{product.id}">
                                    <div class="card">
                                        <input type="hidden" name="product" value="id"/>
                                        <!--Скрытое поле, результат чего отправляется на сервак при нажатии кнопки; value - PK продукта-->
                                        <img class="card-img-top" src="${product.imageProduct}" alt="Card image cap">
                                        <div class="card-body">
                                            <h5 class="card-title">${product.name}</h5>
                                            <p class="card-text">${product.price} рублей</p>
                                        </div>
                                    </div>
                                    <#if (product.count > 0)>
                                        <input class="submit" type="submit" value="В наличии" disabled>
                                    <#else>
                                        <input class="submit" type="submit" value="Нет в наличии" disabled>
                                    </#if>
                                </a>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#import "/include/footer.ftl" as n>
<@n.footer />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>