//получить pathname url
const pathname = document.location.pathname;
//получить все товары этого магазина
function shop_items(){
    fetch("http://localhost:8888/shop_api" + pathname)
        .then(res => res.json())
        .then(shop => {
            let logo = `<div class="shop-div">
        <img src="data:image/png;base64,${shop.logo[0].picture}" class="img-thumbnail">
        <h3>${shop.name}</h3>
        <h3>${shop.location.name}</h3>
        </div>`;
            let text = `<div><h3>Самые популярные товары</h3></div>`
            let items = ``;
            shop.items.forEach((i) => {
                items += `<div class="item-div">
        <a href="/item/${i.id}"><img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail"></a>
        <h6>${i.name}</h6>
        <h6>${i.price}</h6>
        <p>${i.description}</p>
        <button type="button" class="btn btn-primary basket-plus-div" id="${i.id}">В корзину</button>
        </div>`
            })
            document.querySelector(".home-shop-right-body").innerHTML = text + items;
            document.querySelector(".home-shop-left-body").innerHTML = logo;
        })
}
//получить страницу товара
function item_present(){
    fetch("http://localhost:8888/shop" + pathname)
        .then(res => res.json())
        .then(item => {
            let item_present = `<div>
                                <h3>name: ${item.name}</h3>
                                <h3>price: ${item.price}</h3>
                                <h3>shopName: ${item.shopName}</h3>
                                <button type="button" class="btn btn-primary basket-plus-div" id="${item.id}">В корзину</button>
                                </div>`;
            let item_img = ``;
            item.images.forEach((i) => {
                item_img += `<div class="item-div">
                <img src="data:image/png;base64,${i.picture}" class="img-thumbnail" style="width: 300px">
                </div>`
            })
            document.querySelector(".home-shop-right-body").innerHTML = item_img + item_present;
        })
}
//определить с какого контроллера зашли на страницу
function shop_or_item(){
    if (pathname.indexOf("basket") > -1) {
        basket_cookie_name();
    } else if (pathname.indexOf("shop") > -1) {
        shop_items();
    } else if (pathname.indexOf("item") > -1) {
        item_present();
    }
}
shop_or_item();



