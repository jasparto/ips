/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function display(img) {
    // Get the modal
    var modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
//    var img = document.getElementById(img.id);
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");
//    img.onclick = function () {
    modal.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = img.alt;
//    };

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };
    
    span.onkeypress = function () {
        modal.style.display = "none";
    };

}