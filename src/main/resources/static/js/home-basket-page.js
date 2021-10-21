//вызвать метод кликнув по корзина
function click_basket(){
    $(document).on("click", ".basket-btn", function (){
        basket_cookie_name();
    })
}
click_basket();

