// for add item panel (select)
async function getSelectCategories() {
    await fetch("/shop/items/category/names")
        .then(response => response.json())
        .then(data => {
            let head = `<p>Категории</p>`
            let mySelect = `<select id="selectedCategories" multiple required size="${data.length}">`
            data.forEach((category) => {
                mySelect += `<option value="${category.name}">${category.name}</option>`
            })
            mySelect += `</select>`
            document.getElementById('advert-modal').querySelector('.forSelectCategoryNames').innerHTML = head + mySelect;
        })
}
getSelectCategories()

function renderAdvert(advert, el) {
    const tbody = el.getElementsByTagName('TBODY')[0]
    let output = ''
    const tr = document.createElement('tr')
    let imgs = ''

    if (advert.images.length > 1) {
        imgs = `<div id="carouselExampleControls-${advert.id}" class="carousel slide" data-bs-ride="carousel-${advert.id}">
                <div class="carousel-inner">`
        let a = 0
        let flag = ''
        for (const i of advert.images) {
            a++
            flag = (a === 1) ? ' active' : ''
            imgs += `<div class="carousel-item${flag}">
                        <img class="d-block mx-auto" height="150" src="data:image/jpg;base64, ${i.picture}" alt=""/>
                    </div>`
        }

        imgs += `</div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls-${advert.id}" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls-${advert.id}" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>`
    } else {
        imgs = `<img class="d-block mx-auto" height="150" src="data:image/jpg;base64, ${advert.images[0].picture}" alt=""/>`
    }
    let categories=Array.from(advert.categories).map(c=>c.name).toString().replaceAll(',', '<br/>')

    output += `<td>${advert.name}</td>
             <td>${advert.description}</td>
             <td>${categories}</td>
             <td>${advert.price}</td>
             <td>${imgs}</td>`
    tr.innerHTML = output
    tbody.appendChild(tr)
    return tr
}

// GET
async function getAdverts() {
    await fetch("/getUser")
        .then(response => response.json())
        .then(async user => {
            await fetch('/api/adverts/user/' + user.id)
                .then(response => response.json())
                .then(adverts => {
                    for (let advert of adverts) {
                        renderAdvert(advert, document.querySelector('.table-adverts'))
                    }
                })
        })
}
getAdverts()

async function imageToBinaryEXT(file) {
    let result_base64 = await new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => resolve(fileReader.result);
        fileReader.readAsDataURL(file);
    });
    const data = result_base64.split(',')[1];
    const binaryBlob = atob(data);
    return base64ToBinary(binaryBlob)
}

if (document.querySelector('.btn-add-advert') !== null) {
    document.querySelector('.btn-add-advert').onclick = async function () {
        const select = document.getElementById('advert-modal').getElementsByTagName("SELECT")[0]
        const selected = select.querySelectorAll('option:checked')
        const result = []
        const categories = Array.from(selected).map(el => {
            let category = {}
            category.name = el.text
            return el.text
        })
        result[0] = categories

        let files = document.getElementById('advert-images').files
        let promises = Array.from(files).map(async f => {
            return await imageToBinaryEXT(f).then(function (v) {
                return v
            })
        })

        let images = []
        for (let p of promises) {
            await p.then(function (v) {
                images.push({
                    picture: v
                })
            })
        }

        let advert = {
            images: images,
            name: $('#advert-name').val(),
            description: $('#advert-description').val(),
            price: $('#advert-price').val(),
            categoriesName: categories
        }
        let request = new Request('/api/adverts', {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(advert)
        })
        fetch(request)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();
                if (response.ok) {
                    document.getElementById('advert-modal').getElementsByTagName('FORM')[0].reset()
                    $('#advert-modal').hide()
                    location.reload()
                } else {
                    let al = `<div class="alert alert-danger alert-dismissible fade show col-12" role="alert" id="messageError">
                                ${data.errors}
                                <button type="button" class="close" data-bs-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>`;
                    $('#advert-modal').prepend(al);
                }
            })
            .catch(e => console.error(e))
    }
}

//advert home page
if (document.location.pathname.indexOf('user') !== -1 && document.location.pathname.indexOf('advert') !== -1) {
    const userId = document.location.pathname.split('/')[2]
    const advertId = document.location.pathname.split('/')[4]

// GET
    async function getUser() {
        await fetch('/getUser/' + userId)
            .then(response => response.json())
            .then(u => {
                let output = `
                    <p class="fs-4">${u.username}</p>
                    <p class="fs-4"><a href="mailto:${u.phone}">${u.phone}</a></p>
                    <p class="fs-4"><a href="mailto:${u.email}">${u.email}</a></p>
                `
                document.querySelector('.home-advert-left-body').innerHTML = output;
            })
    }
    getUser()

    // GET
    async function getAdvert() {
        await fetch('/api/adverts/' + advertId)
            .then(response => response.json())
            .then(a => {
                renderAdvert(a, document.querySelector('.home-table-adverts'))
            })
    }
    getAdvert()

}