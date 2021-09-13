$(document).ready(function () {
    restartAllUser();
});

function createTableRow(u) {
    return `<tr id="user_table_row">
        <td>${u.id}</td>
        <td>${u.image}</td>
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

    fetch("/api/all")
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
        let href = $(event.target).attr("href");
        $(".discount #discountModal").modal();
        $.get(href, function () {
            $(".discount #minOderDiscount").val();
            $(".discount #percentageDiscount").val();
            $(".discount #fixedDiscount").val();
            // if(document.getElementById('percentage').checked) {
            //     $(".discount #percentageDiscount").val();
            //     $(".discount #fixedDiscount").hide();
            // }else if(document.getElementById('fixDiscount').checked) {
            //     $(".discount #percentageDiscount").hide();
            //     $(".discount #fixedDiscount").val();
            // }
        });
    }

    if ($(event.target).hasClass('discountButton')) {
        let discount = {
            minOrder: $('#minOderDiscount').val(),
            percentage: $('#percentageDiscount').val(),
            fixedDiscount: $('#fixedDiscount').val(),
        }
        discountModalButton(discount)
        console.log(discount);
    }
});

function discountModalButton(discount) {
    fetch("api/addDiscount", {
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

