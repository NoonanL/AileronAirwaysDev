//script to limit the size of an input box
var uploadField = document.getElementById("attachments");
uploadField.onchange = function() {
    //if the file is larger than 2MB
    if(this.files[0].size > 2097152){
        //tell the user its too big
        alert("File is too big!");
        //reset the inputbox value
        this.value = "";
    };
};