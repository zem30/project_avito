$(document).ready(function () {
    restartAllUser();
});

function createTableRow(u) {
    return `<tr id="user_table_row">
        <td>${u.id}</td>
        <td><img src="data:image/png;base64,${u.images.picture}" width="80" height="80"></td>
        <td>${u.gender}</td>
        <td>${u.firstName}</td>
        <td>${u.lastName}</td>
        <td>${u.age}</td>
        <td>${u.phone}</td>
        <td>${u.email}</td>
        <td>
        <a  href="/api/${u.id}" class="btn btn-info discBtn" >Добавить скидку</a>
        </td>
    </tr>`;
}

function restartAllUser() {
    let UserTableBody = $("#user_table_body")

    UserTableBody.children().remove();

    fetch("/myshop/userlist/all")
        .then((response) => {
            response.json().then(
                data => data.forEach(function (item) {
                    let TableRow = createTableRow(item);
                    UserTableBody.append(TableRow);
                }));
        }).catch(error => {
        console.log(error);
    });
}

document.addEventListener('click', function (event) {
    event.preventDefault()

    if ($(event.target).hasClass('discBtn')) {
        $(".discount #discountModal").modal();

    }

    if ($(event.target).hasClass('discountButton')) {
        let discount = {
            minOrder: $('#minOderDiscount').val(),
            percentage: $('#percentageDiscount').val(),
            fixedDiscount: $('#fixedDiscount').val(),
            shop: $('#chooseShop').val()
        }
        discountModalButton(discount)
        console.log(discount);
    }
});

function discountModalButton(discount) {
    fetch("/myshop/userlist/addDiscount", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(discount)
    }).then(function (response) {
        $('input').val('');
        $('.discount #discountModal').modal('hide');
        restartAllUser();
    });
}