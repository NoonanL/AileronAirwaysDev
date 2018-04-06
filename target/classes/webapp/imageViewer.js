// Get the modal
var picmodal = document.getElementById('picModal');

// Get the button that opens the modal
var picbtn = document.getElementsByClassName("imageThumb");

// Get the <span> element that closes the modal
var picspan = document.getElementsByClassName("close")[0];


// When the user clicks an image
$(".imageThumb").click(function(){
    //shout about it into the console
    console.log("image clicked");
    //get the source of the image clicked on
    var imageSrc=$(this).src;
    //get the img tag within the modal
    var largeImage = document.getElementById("largeImage");
    //set the source of the image tag in the modal to be identical to that of the thumb
    largeImage.src=imageSrc;
    picmodal.style.display = "block";

});
//see https://stackoverflow.com/questions/5563783/jquery-class-click-multiple-elements-click-event-once
/*picbtn.onclick = function() {
    console.log("image clicked");
    var imagesrc=picbtn.src;
    var largeImage=document.getelementbyId("largeImage");
    largeImage.src=imagesrc;
    picmodal.style.display = "block";
};*/

// When the user clicks on <span> (x), close the modal
picspan.onclick = function() {
    picmodal.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        picmodal.style.display = "none";
    }
};