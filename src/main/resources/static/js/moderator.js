// $('.nav li a').on('click', function() {
//     // alert("class added " + $(this).parent().find('a').attr('href'));
//     $(this).parent().parent().find('.active').removeClass('active');
//     $(this).parent().addClass('active');
// });
// const itemButton = document.querySelector('.itemButton');
// let output = '';
// const URL_items = 'http://localhost:8888/moderator/item';
//
// // <div class="btn-group" role="group" aria-label="Basic example">
// //     <button type="button" class="btn btn-secondary">Принять</button>
// //     <button type="button" class="btn btn-warning">Отклонить</button>
// // </div>
//
//
// // <img src="/images/pathToYourImage.png" class="img-fluid" alt="Responsive image">
//
// // text
// // <div class="mb-3">
// //   <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
// //   <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
// // </div>
// const getAllItems = async () => {
//     const allItems = [];
//     fetch(URL_items, {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json;charset=utf-8'
//         }
//     })
//         .then(response => response.json())
//         .then(item => {
//             item.forEach(i => {
//                 let img = [];
//                 let name = '';
//                 img = i.images;
//                 img.forEach( image => {
//                     document.write('<img src="data:image/jpg;base64, ${image.picture}"/>');
//                     console.log(image.picture)
//                 })
//                 name = i.name;
//                 output += `
//      <h5>${name}</h5>
//      `
//                 itemButton.innerHTML = output;
//                 return allItems;
//             })
//         })
// }
//         getAllItems();
//                 {/*data:image/jpg;base64, ${img.picture}*/}