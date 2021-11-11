const url = 'http://localhost:8888'
const userService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    getUserShops: async (id) => await fetch('/getUserShops/' + id, {
        method: 'GET',
        headers: userService.head
    }),
    getUserItems: async (id) => await fetch('/getUserOrders/' + id, {
        method: 'GET',
        headers: userService.head
    })
}
$(document).ready(function () {
    console.log('ok')
    fillUsersShops()
    addImageToUser()
    fillUserItemTable()

})


async function fillUserItemTable() {
    let id = $('.user-id').val()
    let responce = await userService.getUserItems(id)
    let items
    if (responce.ok) {
        items = await responce.json().then(result => result)
        console.log(items)
        userOrdersTableFill(items)
    }
}


function userOrdersTableFill(items) {
    let itemsTable = $('#items-table')
    let rows
    console.log(items)
    for (let i = 0; items[i] !== undefined; i++) {
        rows +=
            `
            <tr>
                <th><img src="data:image/png;base64,${items[i].images[0].picture}" height="60" width="60"></th>
                <th>${items[i].name}</th>
                <th>${items[i].categories[0]?.name}</th>
                <th>${items[i].price}</th>
            </tr>
        `
    }
    itemsTable.append(rows)
}


async function fillUsersShops() {
    let newShopCard =
        `<div class="card shadow-sm " style="width: 15rem;height: 21rem">
                <img src="https://sc02.alicdn.com/kf/HTB1qiaROCzqK1RjSZPxq6A4tVXaB/223383126/HTB1qiaROCzqK1RjSZPxq6A4tVXaB.jpg_.webp" class="card-img-top" height="200" >
                <div class="card-body" >
                   <div class="card-title"><h4>Новый магазин</h4></div>
                    <div class="card-text" style="margin-bottom: 10px">Зарегестрируйте новый магазин тут!</div>
                    <button type="submit" class="btn btn-primary">Регистрация</button>
                </div>
        </div>`
    let id = $('.user-id').val()
    let response = await userService.getUserShops(id)
    let shops
    let cards
    if (response.ok) {
        shops = await response.json().then(result => result)
        cards = makeShopsCards(shops)
    } else {
        cards = new Array()
    }
    cards.push(newShopCard)
    let grid = ''
    for (let i = 0; i < cards.length; i += 3) {
        grid += makeGrid(cards[i], cards[i + 1], cards[i + 2])
    }
    $('#user-shops').append(grid)
}

function makeShopsCards(shops) {
    console.log(shops)
    let cards = new Array()
    for (let i = 0; shops[i] !== undefined; i++) {
        let shop = shops[i]
        let massage =
            `<div class="card shadow-sm " style="width: 15rem;height: 21rem">
                <img src="data:image/png;base64,${shop.logo.picture}" class="card-img-top" height="200">
                <div class="card-body" >
                    <div class="card-title"><h4>${shop.name}</h4></div>
                    <div class="card-text" style="margin-bottom: 5px">Рейтинг магазина:${shop.rating}/10</div>
                    <form action="/shop/${shop.id}">
                        <button type="submit" class="btn btn-primary">Страница магазина</button>
                      </form>
                </div>
            </div>`

        cards.push(massage)
    }
    return cards
}

function makeGrid(firtsCard, secondCard, thirdCard) {
    let grid =
        `
    <div class="container-fluid">
        <div class="row flex-row flex-nowrap">
            <div class="col-5">
                ${firtsCard.toString()}
            </div><div class="col-5">
                ${secondCard == null ? '' : secondCard.toString()}
            </div>
            <div class="col-5">
                ${thirdCard == null ? '' : thirdCard.toString()}
            </div>
        </div>
    </div>`
    return grid

}


function addImageToUser() {
    let array = Object.values($('.avatar-byte-array').val().split(',')).map(Number)

    let image = ''
    if (array.length < 5) {
        image = 'http://s3.amazonaws.com/37assets/svn/765-default-avatar.png'
    } else {
        image = `data:image/png;base64,${arrayBufferToBase64(array)}`
    }

    $('#avatar-image').attr('src', image);
}

//convert byte array to base64
function arrayBufferToBase64(buffer) {
    let binary = '';
    let bytes = new Uint8Array(buffer);
    let len = bytes.byteLength;
    for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);

}

//получаем пользователя и добавляем поля
function getProfileForEdit(id) {
    fetch(url + '/getUser/' + id)
        .then(response => response.json())
        .then(user => {
            console.log(user)
            let dob = new Date(user.birthday)
            dob = dob.toISOString().substring(0, 10);
            let gender = user.gender
            document.getElementById('edit-id').value = user.id
            document.getElementById('edit-name').value = user.firstName
            document.getElementById('edit-surname').value = user.lastName
            document.getElementById('edit-username').value = user.username
            document.getElementById('edit-date').value = dob
            document.getElementById('edit-email').value = user.email
            document.getElementById('edit-phone').value = user.phone
            document.getElementById(gender).checked = true
            document.getElementById('edit-address-id').value = user.address.id
            document.getElementById('edit-country').value = user.address.country
            document.getElementById('edit-city').value = user.address.city
            document.getElementById('edit-street').value = user.address.street
            document.getElementById('edit-house').value = user.address.house
            document.getElementById('edit-cityIndex').value = user.address.cityIndex
        })
        .catch(e => console.error(e))
}
//считываем все поля и отправляем
function sendProfileForEdit() {
    event.preventDefault();
    let genderCheck = document.getElementsByClassName('form-check-input')
    let gender;
    for (let i = 0; i < genderCheck.length; i++) {
        if (genderCheck[i].checked) {
            gender = genderCheck[i].id
            i = genderCheck.length
        }
    }
    let address ={
        id: $('#edit-address-id').val(),
        country:  $('#edit-country').val(),
        city: $('#edit-city').val(),
        cityIndex: $('#edit-cityIndex').val(),
        house: $('#edit-house').val(),
        street: $('#edit-street').val()
    }
    let user = {
        id: $('#edit-id').val(),
        firstName: $('#edit-name').val(),
        lastName: $('#edit-surname').val(),
        birthday: $('#edit-date').val(),
        email: $('#edit-email').val(),
        username: $('#edit-username').val(),
        password: $('#edit-password').val(),
        phone: $('#edit-phone').val(),
        gender: gender,
        address: address
    }
    console.log("отправка пользователя на сервер для обновления: " + JSON.stringify(user))
    fetch(url + '/user', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    })
        // .then(response => response.json())
        .then(data => console.log(data))
        .catch(err => console.error(err))
    $('.btn-close').click();
}
// деактивирует пользователя
function sendProfileForDeactivate() {
    event.preventDefault();
    let user_id = $('#edit-id').val();
    fetch(url + '/user/' + user_id, {method: 'DELETE'})
        .then(response => console.log(response.status, response.body))
        .then(() => document.location.href = url + '/logout')
        .catch(e => console.error(e))
}
//при нажатии на поле country очистка полей страны и города
function clearField() {
    document.getElementById('edit-country').value = null
    document.getElementById('edit-city').value = null
}