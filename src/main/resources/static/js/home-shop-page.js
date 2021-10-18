const pathname = document.location.pathname;
const shop_items = () => {
    fetch("http://localhost:8888/shop_api"+pathname)
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
        <button type="button" class="btn btn-primary" id="${i.id}">В корзину</button>
        </div>`
            })
            document.querySelector(".home-shop-right-body").innerHTML = text + items;
            document.querySelector(".home-shop-left-body").innerHTML = logo;
            })
}

const item_present = () => {
    fetch("http://localhost:8888/shop"+pathname)
        .then(res => res.json())
        .then(item => {
            let item_present = `<div>
                                <h3>name: ${item.name}</h3>
                                <h3>count: ${item.count}</h3>
                                <h3>shopName: ${item.shopName}</h3>
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

const shop_or_item = () => {
    if(pathname.indexOf("shop") > -1){
        shop_items();
    }
    if (pathname.indexOf("item") > -1){
        item_present();
    }
}
shop_or_item();



