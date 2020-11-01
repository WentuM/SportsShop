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
    <#if idOrder??>
    <!— Modal —>
    <div class="modal show" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Успешный заказ</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Заказ успешно оформлен <br>
                    ID - вашего заказа - ${idOrder} <br>
                    Предъявите его на кассе, оплатите требуемую сумму и получите свой заказ.
                </div>
            </div>
        </div>
    </div>
    </#if>
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
<script type="text/javascript">
    $(window).on('load',function(){
        $('#exampleModal').modal('show');
    });
</script>
</body>
</html>