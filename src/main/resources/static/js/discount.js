$(document).ready(function () {
    restartAllUser();
});

function createTableRow(u) {
    return `<tr id="user_table_row">
        <td>${u.id}</td>
        <td><img src="data:image/png;base64,${u.images.picture()}" width="80" height="80"></td>
        <td>${u.gender}</td>
        <td>${u.firstName}</td>
        <td>${u.lastName}</td>
        <td>${u.age}</td>
        <td>${u.phone}</td>
        <td>${u.email}</td>
        <td>${u.birthday}</td>

        <td>
        <a  href="/myshop/userlist/${u.id}" class="btn btn-info discBtn" >Добавить скидку</a>
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

let userId = null;
document.addEventListener('click', function (event) {
    event.preventDefault()
    if ($(event.target).hasClass('discBtn')) {
        let href = $(event.target).attr("href");
        $(".discount #discountModal").modal();
        userId = $.get(href, function (user) {
            fetch('/myshop/userlist/' + user.id, {
                method: 'GET'
            }).then(function (response) {
                response.json().then(function (user) {
                    userId = user.id;
                });
            });
        });
    }


    if ($(event.target).hasClass('discountButton')) {
        $(event.target).attr("href");
        $(".discount #discountModal").modal();
        console.log($(event.target));

        if (!document.querySelector('.first').classList.contains('active')) {
            $('#percentageDiscount').val('');
        } else if (!document.querySelector('.second').classList.contains('active')) {
            $('#fixedDiscount').val('');
        }

        let discount = {
            id: userId,
            minOrder: $('#minOderDiscount').val(),
            percentage: $('#percentageDiscount').val(),
            fixedDiscount: $('#fixedDiscount').val(),
            shop: $('#chooseShop').val()
        }
        discountModalButton(discount)
    }
});

function discountModalButton(discount) {
    console.log(discount)
    fetch("/myshop/userlist/addDiscount", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(discount)
    }).then(function () {
        $('.discount #discountModal').modal('hide');
        restartAllUser();
    });
}