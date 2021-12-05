const pathname = document.location.pathname; // shop/control/?
let shopName;
let shopId;
const key = 20212.895;
//----------------------------------------------------------------------------------------------------------------------
function getNormalDate(date){
    return date.getDate() + "." + (date.getMonth()+1) + "." + date.getFullYear();
}
//----------------------------------------------------------------------------------------------------------------------
async function userShops() {
    await fetch("http://localhost:8888/shop_api/shop/" + pathname.charAt(14))
        .then(res => res.json())
        .then(shop => {
            shopName = shop.name;
            shopId = shop.id;
            // shop logo -----------------------------------------------------------------------------------------------
            let logo;
            logo = `<div class="shop-div">
                        <img src="data:image/png;base64,${shop.logo[0].picture}" class="img-thumbnail">
                        <ul>
                            <h3><li>${shop.name}</li></h3>
                            <h3><li>${shop.location.name}</li></h3>
                        </ul>
                    </div>`;
            // shop items ----------------------------------------------------------------------------------------------
            let anotherItems = ``;
            let activeItem1 = ``;
            shop.items.forEach((i) => {
                if (activeItem1 === ``) {
                    activeItem1 +=  `<div class="carousel-item active">
                                        <img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail">
                                        <div class="carousel-caption d-none d-md-block">
                                         <h6 style="color: black">${i.name}</h6>
                                         <input type="button" class="btn btn-info edit-btn" id="${i.id}" data-toggle="modal" data-target="#updateModal" value="Редактировать">
                                         <input type="button" class="btn btn-danger del-btn"  id="${i.id}" value="Удалить">
                                        </div>
                                      </div>`
                } else {
                    anotherItems += `<div class="carousel-item">
                                        <img src="data:image/png;base64,${i.images[0].picture}" class="img-thumbnail">
                                        <div class="carousel-caption d-none d-md-block">
                                            <h6 style="color: black">${i.name}</h6>
                                            <input type="button" class="btn btn-info edit-btn" id="${i.id}" data-toggle="modal" data-target="#updateModal" value="Редактировать">
                                            <input type="button" class="btn btn-danger del-btn"  id="${i.id}" value="Удалить">
                                        </div>
                                      </div>`
                }
            })
            // shop orders ---------------------------------------------------------------------------------------------
            let myClients = ``;
            let i = 0;
            let order_items = ``;
            shop.orders.forEach((order) => {
                console.log(order)
                order.items.forEach((item) => {
                    order_items += `<img src="data:image/png;base64,${item.images[0].picture}" style="height: 100px; width: 100px">
                                   <h6 style="color: black">${item.name}</h6>`
                })
                myClients += `<tr>
                                <th>${order.buyerName}</th>
                                <th>${order.buyerPhone}</th>
                                <th>${order_items}</th>
                                <th>${order.total}</th>
                                <th>${order.status}</th>
                                `;
                if (order.status !== "COMPLETE") {
                    myClients +=`<th><input type="button" class="btn btn-info" data-id="${order.user}" value="дать купон" data-toggle="modal"  data-target="#addCoupon"></th>
                            </tr>`;
                } else {
                    myClients +=`<th><input type="button" class="btn btn-info" data-id="${order.user}" value="дать купон" data-toggle="modal" disabled data-target="#addCoupon"></th>
                            </tr>`;
                }
                i++;
                order_items = ``;
            })
            // end -----------------------------------------------------------------------------------------------------
            document.querySelector(".carousel-inner").innerHTML = activeItem1 + anotherItems;
            document.querySelector(".shop-info").innerHTML = logo;
            document.querySelector(".buyerTable").innerHTML = myClients;
        })
    // for modal coupons -----------------------------------------------------------------------------------------------
    let shopActiveCoupons = ``;
    await fetch("http://localhost:8888/api/coupon/" + shopName)
        .then(res => res.json())
        .then((couponList) => {
            let now = new Date();
            couponList.forEach((coupon) => {
                let date = new Date(coupon.end);
                if (now === date) {
                    updateToOverdue(coupon.id);
                }
                shopActiveCoupons += `<tr>
                                        <th>${coupon.id * key}</th>
                                        <th>${getNormalDate(date)}</th>
                                        <th>${coupon.status}</th>
                                        <th>${coupon.sum}</th>
                                        <th>
                                            <div class="form-check">
                                              <input class="form-check-input" type="checkbox" value="${coupon.id}" id="flexCheckDefault">
                                              <label class="form-check-label" for="flexCheckDefault"></label>
                                            </div>
                                        </th>
                                      </tr>`;
            })
            document.querySelector(".coupon-table-for-users").innerHTML = shopActiveCoupons;
        })
}
userShops();
//----------------------------------------------------------------------------------------------------------------------
$(document).on('click', '.del-btn', function ()  {
    fetch('/shop/item/' + $(this).attr('id'), {method: 'DELETE',})
        .then((res) => {
            console.log(res);
            alert("Товар на рассмотрении")
        })
});
//----------------------------------------------------------------------------------------------------------------------
let arrForEditImages = []
$(document).on('click', '.edit-btn', function () {
    fetch("/shop/item/" + $(this).attr('id'), {method: "GET", dataType: 'json',})
        .then((res) => {
            res.json().then((item) => {
                $('#editId').val(item.id);
                $('#editName').val(item.name);
                $('#editCount').val(item.count);
                $('#editPrice').val(item.price);
                $('#editDescription').val(item.description);
                $('#editRating').val(item.rating);
                $('#editShopName').val(shopName);
                $('#editCategoriesName').val(item.categories[0].name);
                $('#editCategories').val(item.categories);
                arrForEditImages = item.images
            })
        })
})
$('#update').on('click', (event) => {
    event.preventDefault()
    fetch('http://localhost:8888/shop/item', {
        method: 'PATCH',
        body: JSON.stringify({
            id: $('#editId').val(),
            name: $('#editName').val(),
            price: $('#editPrice').val(),
            description: $('#editDescription').val(),
            count: $('#editCount').val(),
            rating: $('#editRating').val(),
            shopName: $('#editShopName').val(),
            categoriesName: $('#editCategoriesName').val(),
            categories: $('#editCategories').val(),
            isModerateAccept: false,
            isModerated: false,
            isPretendentToBeDeleted: false,
            moderatedRejectReason: "",
            reviews: null,
            shopId: shopId,
            images : arrForEditImages
        }),
        headers: { "Content-Type": "application/json; charset=utf-8" }
    }).then((res) => {
        console.log(res);
    })
});
//----------------------------------------------------------------------------------------------------------------------
// let arrImage = []
// let categories = []
// $("#addItem").on('click', () => {
//     categories.push(document.getElementById("categories"));
//     arrImage.push(document.getElementById("inpFile"));
//     $.ajax({
//         url: 'http://localhost:8088/shop/item',
//         method: 'POST',
//         dataType: 'json',
//         contentType: 'application/json; charset=utf-8',
//         data: JSON.stringify({
//             name: $('#name').val(),
//             price: $('#price').val(),
//             description: $('#description').val(),
//             count: $('#count').val(),
//             reviews: [],
//             rating: 5,
//             discount: {},
//             shopId: shopId,
//             shopName: shopName,
//             isModerated: false,
//             isModerateAccept: false,
//             moderatedRejectReason: null,
//             isPretendentToBeDeleted: false,
//             categories: categories,
//             images: arrImage
//         }),
//         success: function () {
//             console.log("success");
//         },
//         error: function () {
//             alert('error add-item');
//         }
//     });
//     // const { myForm } = document.forms;
//     // myForm.reset();
// });
//----------------------------------------------------------------------------------------------------------------------