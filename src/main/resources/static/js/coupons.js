function createCard(coupon){
    let card = `<div class="card text-dark bg-light mb-3 text-center">`;
        card += `<div class="card-header"><h5>Купон</h5></div>`;
        card += `<div class="card-body">`;
        card += `<div class="card-text">`;
        card += `<p><strong>Срок действия:</strong> ` + coupon.start + ` <strong>До</strong> ` + coupon.end + `</p>`;
        card += `<p><strong>Магазин:</strong> ` + coupon.shop.name + `</p>`;
        card += `<p><strong>Статус купона:</strong> ` + coupon.status + `</p>`;
        card += `<p><strong>Купон на сумму:</strong> ` + coupon.sum + `</p>`;
        card += `</div></div></div>`;
    return card;
}
//----------------------------------------------------------------------------------------------------------------------
async function showCoupons(){
    let actual = "";
    let overdue = "";
    let used = "";
    let all = "";
    await fetch("http://localhost:8888/getUser")
        .then(res => res.json())
        .then(user => {
            for (let i = 0; i < user.coupons.length; i++) {
                all += createCard(user.coupons[i]);
                if (user.coupons[i].status === "ACTUAL") {
                    actual += createCard(user.coupons[i]);
                } else if (user.coupons[i].status === "USED") {
                    used += createCard(user.coupons[i]);
                } else if (user.coupons[i].status === "OVERDUE") {
                    overdue += createCard(user.coupons[i]);
                }
            }

            document.querySelector(".allCoupons").innerHTML = all;
            document.querySelector(".actualCoupons").innerHTML = actual;
            document.querySelector(".usedCoupons").innerHTML = used;
            document.querySelector(".overdueCoupons").innerHTML = overdue;
        })
}
showCoupons();
//----------------------------------------------------------------------------------------------------------------------
