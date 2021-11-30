
const url = "http://localhost:8888/api/advert/new"


async function sendData() {
    // let imageInput = $('#Photo')[0].files[0]
    // let image
    // if (imageInput !== undefined) {
    //     image = imageToBinary(imageInput)
    // }
    let advert = {

        // images: {
        //     picture: image,
        //     isMain: true
        // },
       // images : $('#Photo').val(),
        name: $('#Name').val(),
        description: $('#Description').val(),
        price: $('#Price').val(),
        email: $('#Contacts').val()

    }

    console.log(advert)
    fetch(url, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(advert),
    })
        //.then(response => console.log(response))
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(e => console.error(e))
}