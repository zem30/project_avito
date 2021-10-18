const basket_add = () =>{
    $(document).on("click", ".basket-add", function (e){
        let id = e.target.id;
        console.log(id)
        fetch("http://localhost:8888/api/basket",{
            method : "POST",
            headers : {
                "Content-Type": "application/json;charset=utf-8"
            },
            body : JSON.stringify({"id": id})
        }).then()
    })
}
basket_add();

const basket_click = () => {
    $(document).on("click", ".basket-btn", function (){
        fetch("http://localhost:8888/api/basket")
            .then(res => res.json())
            .then(r => console.log(r))
    })
}
basket_click();