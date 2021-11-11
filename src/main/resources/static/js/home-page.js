//получить популярные товары
async function popular_item() {
    await fetch("http://localhost:8888/shop/items")
        .then(res => res.json())
        .then(items => {
            let logs = ``;
            items.forEach((i) => {
                logs += `<div class="item-div">
                         <a href="/item/${i.id}"><img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail"></a>
                         <h6>${i.name}</h6>
                         <h6>${i.price}</h6>
                         <p>${i.description}</p>
                         <p class="star">★ ${Math.round(i.rating,1)}</p>
                         <button type="button" class="btn btn-primary basket-plus-div" id="${i.id}">В корзину</button>
                         </div>`;
            })
            document.querySelector('.item-container').innerHTML = logs;
        })
}

//получить популярные магазины
async function popular_shops() {
    await fetch("http://localhost:8888/shop_api/shops")
        .then(res => res.json())
        .then(async shops => {
            let logs1 = ``;
            shops.forEach((s) => {
                logs1 += `<div class="shop-div">
        <a href="/shop/${s.id}"><img src="data:image/png;base64,${s.logo[0].picture}" class="img-thumbnail"></a>
        <h6>${s.name}</h6>
        <p>${s.description}</p>
        <p class="star">★ ${Math.round(s.rating,1)}</p>
        <a href="/shop/${s.id}"><button type="button" class="btn btn-primary">Перейти</button></a>
        </div>`;
            })
            document.querySelector(".shop-container").innerHTML = logs1;
        })
}

//получение предметов по поиску
async function findItems() {
    // получаем значение поиска
    const searchInput = document.getElementById('search-input');
    // обрабатываем нажатие кнопки поиска
    $(document.getElementById('search-button')).on('click', async function () {
        console.log(searchInput.value)
        await fetch("http://localhost:8888/shop/items")
            .then(res => res.json())
            .then(items => {
                let logs = ``;
                let isEmpty = true;
                items.forEach((i) => {
                    if (i.name === searchInput.value || searchInput.value === '') {
                        isEmpty = false;
                        logs += `<div class="item-div">
                         <a href="/item/${i.id}"><img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail"></a>
                         <h6>${i.name}</h6>
                         <h6>${i.price}</h6>
                         <p>${i.description}</p>
                         
                         <button type="button" class="btn btn-primary basket-plus-div" id="${i.id}">В корзину</button>
                         </div>`;
                    }
                })

                // Проверка что нету предметов
                if (isEmpty) {
                    logs += `<h4>Ничего не найдено</h4>`
                }

                document.querySelector('.item-container').innerHTML = logs;
            })
    });
}

//получить name cookies
function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

//прибавить количество товара
function basket_plus_click() {
    $(document).on("click", ".basket-plus-div", function (e) {
        console.log(e.target.id)
        let id = e.target.id
        let cookie_value = getCookie(id)
        if (cookie_value !== undefined) {
            let value = Number(cookie_value) + 1;
            document.cookie = id + "=" + value + "; path=/";
        } else {
            document.cookie = id + "=" + 1 + "; path=/";
        }
    })
}

async function start() {
    await popular_shops();
    await popular_item();
}

start();


