function sendData(url, data, method){
    const response = fetch(url, {
        method : method,
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: data,
    }).then(res => res.json())
        .then(basket_item => {
            let count = 0;
            let basket = ``;
            basket_item.items.forEach((i) => {
                count += Number(i.price)
                console.log(count)
                basket += `<div class="basket-div">
                                <img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail">
                                <h6>${i.name}</h6>
                                <h6>${i.price}</h6>
                                <p>${i.description}</p>
                                </div>`
            })
            let all_price = `<h3>Итого:</h3>
                             <h3>${count}</h3>`;
            document.querySelector(".home-shop-right-body").innerHTML = basket;
            document.querySelector(".home-shop-left-body").innerHTML = all_price;

        })
}
function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
function basket_plus_click(){
    $(document).on("click", ".basket-plus-div", function (e) {
        let id = e.target.id
        console.log("id: " + id)
        let cookie_value = getCookie(id)
        if (cookie_value !== undefined) {
            let value = Number(cookie_value) + 1;
            console.log("value: " + value)
            document.cookie = id + "=" + value + "; path=/";
        } else {
            document.cookie = id + "=" + 1  + "; path=/";
        }
    })
}
basket_plus_click();

function basket_minus_click(){
    $(document).on("click", ".basket-minus-div", function (e) {
        let id = e.target.id
        console.log("id: " + id)
        let cookie_value = getCookie(id)
        if (Number(cookie_value) !== 0) {
            if (cookie_value !== undefined) {
                let value = Number(cookie_value) - 1;
                console.log("value: " + value)
                document.cookie = id + "=" + value;
            } else {
                document.cookie = id + "=" + 1;
            }
        }
    })
}
basket_minus_click();

function basket_cookie_name(){
    let cookie_split = document.cookie.split(";");
    let cookie_name = [];
    for (let i = 0; i < cookie_split.length; i++) {
        let x = cookie_split[i].split("=")
        cookie_name[i] = x[0];
    }
    console.log(cookie_name)
    let x = []
    for (let i = 0; i < cookie_name.length; i++) {
        x[i] = {
            id : cookie_name[i]
        }
    }
    console.log(x)
    let data = {
        items : x
    }
    sendData("http://localhost:8888/api/cart-item",JSON.stringify(data),"POST")
}
// basket_cookie_name()