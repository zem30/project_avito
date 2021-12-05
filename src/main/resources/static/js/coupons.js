let key = 20212.895;
function createCard(coupon){
    let card = `<div class="card text-dark bg-light mb-3 text-center">`;
        card += `<div class="card-header"><h5>${coupon.id * key}</h5></div>`;
        card += `<div class="card-body">`;
        card += `<div class="card-text">`;
        card += `<p><strong>Срок действия - До: </strong>` + getNormalDate(new Date(coupon.end)) + `</p>`;
        card += `<p><strong>Магазин:</strong> ` + coupon.shop.name + `</p>`;
        card += `<p><strong>Статус купона:</strong> ` + coupon.status + `</p>`;
        card += `<p><strong>Купон на сумму:</strong> ` + coupon.sum + `</p>`;
        card += `</div></div></div>`;
    return card;
}
//----------------------------------------------------------------------------------------------------------------------
let count = 0;
async function showCoupons(){
    let actual = "";
    let overdue = "";
    let used = "";
    let all = "";
    await fetch("http://localhost:8888/getUser")
        .then(res => res.json())
        .then(user => {
            let now = new Date();
            for (let i = 0; i < user.coupons.length; i++) {
                all += createCard(user.coupons[i]);
                if (user.coupons[i].status === "ACTUAL") {
                    let date = new Date(user.coupons[i].end)
                    if (now === date && count === 0) {
                        updateToOverdue(user.coupons[i].id)
                        count++;
                        showCoupons();
                    } else {
                        actual += createCard(user.coupons[i]);
                    }
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
async function updateToOverdue(id){
    await fetch("http://localhost:8888/api/coupon/update/overdue/" + id)
        .then(res => res.json())
        .then(res => {
            console.log(res);
        })
}
function getNormalDate(date){
    return date.getDate() + "." + (date.getMonth()+1) + "." + date.getFullYear();
}