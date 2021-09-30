class CartItem {
    constructor(item, shop, quantity) {
        this.id = 0
        this.itemDto = item
        this.shopDto = shop
        this.quantity = quantity
    }

    id
    itemDto
    shopDto
    quantity
}

let cartItems = document.querySelector('#cartItemsList');

function sendAddToShoppingCartRequest(body) {
    console.dir(body)
    fetch("/shoppingCart/addCartItem", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
        .then(res => {
            console.dir(res)
        })
    console.dir(body)
}

function addToShoppingCart(id, quantity) {
    fetch("/shoppingCart/getShoppingCart")
        .then(res => res.json())
        .then(data => {
            console.dir(data)
        })
    fetch("shop/item/" + id)
        .then(res => res.json())
        .then(item => {
            fetch("/shop_api/" + item.shopName)
                .then(res => res.json())
                .then(shop => {
                    let cartItem = new CartItem(item, shop, quantity)
                    if (localStorage.getItem('cart') === null) {
                        let tmpCart = []
                        tmpCart.push(cartItem)
                        localStorage.setItem('cart', JSON.stringify(tmpCart))
                        sendAddToShoppingCartRequest(cartItem)
                    } else {
                        let shoppingCart = JSON.parse(localStorage.getItem('cart'));
                        for (let i = 0; i < shoppingCart.length; i++) {
                            if (parseInt(cartItem.itemDto.id) === parseInt(shoppingCart[i].itemDto.id)) {
                                shoppingCart[i].quantity = shoppingCart[i].quantity + quantity
                                localStorage.setItem('cart', JSON.stringify(shoppingCart))
                                sendAddToShoppingCartRequest(cartItem)
                                return
                            }
                        }
                        shoppingCart.push(cartItem)
                        localStorage.setItem('cart', JSON.stringify(shoppingCart))
                        sendAddToShoppingCartRequest(cartItem)
                    }
                })
        })
}

function addOneToShoppingCart(id) {
    let shoppingCart = JSON.parse(localStorage.getItem('cart'));
    for (let i = 0; i < shoppingCart.length; i++) {
        if (parseInt(shoppingCart[i].itemDto.id) === id) {
            shoppingCart[i].quantity++
        }
    }
    localStorage.setItem('cart', JSON.stringify(shoppingCart))
    drawShoppingCart()
}

function deleteOneFromShoppingCart(id) {
    let shoppingCart = JSON.parse(localStorage.getItem('cart'));
    for (let i = 0; i < shoppingCart.length; i++) {
        if (parseInt(shoppingCart[i].itemDto.id) === id && parseInt(shoppingCart[i].quantity) >= 2) {
            shoppingCart[i].quantity--
        }
    }
    localStorage.setItem('cart', JSON.stringify(shoppingCart))
    drawShoppingCart()
}

function deletePositionFromCart(id) {
    let shoppingCart = JSON.parse(localStorage.getItem('cart'));
    let tmpCart = [];
    for (let i = 0; i < shoppingCart.length; i++) {
        if (parseInt(shoppingCart[i].itemDto.id) !== id) {
            tmpCart.push(shoppingCart[i])
        }
    }
    localStorage.setItem('cart', JSON.stringify(tmpCart))
    drawShoppingCart()
}

function drawShoppingCart() {
    cartItems.innerHTML = '';
    let tmpCart = JSON.parse(localStorage.getItem('cart'));
    let sum = 0
    if (tmpCart != null) {
        for (let i = 0; i < tmpCart.length; i++) {
            sum += tmpCart[i].itemDto.price * tmpCart[i].quantity
            cartItems.innerHTML += `
            <tr>
                <td>${tmpCart[i].itemDto.name}</td>
                <td>&nbsp;</td>
                <td>
                    <button type="button" class="btn btn-danger" onclick="deleteOneFromShoppingCart(${tmpCart[i].itemDto.id})">-</button>
                </td>
                <td>${tmpCart[i].quantity}</td>
                <td>
                    <button type="button" class="btn btn-success" onclick="addOneToShoppingCart(${tmpCart[i].itemDto.id})">+</button>
                </td>
                <td>&nbsp;</td>
                <td>${tmpCart[i].itemDto.price}</td>
                <td>&nbsp;</td>
                <td>${tmpCart[i].itemDto.price * tmpCart[i].quantity}</td>
                <td>&nbsp;</td>
                <td>
                <button type="button" class="btn btn-danger" onclick="deletePositionFromCart(${tmpCart[i].itemDto.id})">Удалить позицию</button>
                </td>
            </tr>
        `;
        }
    }
    cartItems.innerHTML += `
        <tr>
            <td colspan="11">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="3" align="left">Общая сумма:</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="3" align="right">${sum}</td>
        </tr>`;
}

$('#staticBackdropBasket').on('show.bs.modal', function () {
    drawShoppingCart()
})