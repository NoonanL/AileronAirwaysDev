// Get the modal
var picmodal = document.getElementById('picModal');

// Get the <span> element that closes the modal
var picspan = document.getElementsByClassName("close")[0];

$(document).on('click','.imageThumb',function(){
    //shout about it into the console
    console.log("image clicked");
    console.log(this.src);
    //get the source of the image clicked on
    var imageSrc=$(this).attr("src");
    console.log(imageSrc);
    //set the LargeImage src to the imagesrc
    $("#largeImage").attr("src", imageSrc);
    //open the modal
    picmodal.style.display = "block";
});
//see https://stackoverflow.com/questions/5563783/jquery-class-click-multiple-elements-click-event-once

// When the user clicks on <span> (x), close the modal
picspan.onclick = function() {
    picmodal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == picmodal) {
        picmodal.style.display = "none";
    }
};