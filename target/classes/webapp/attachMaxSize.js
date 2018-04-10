//script to limit the size of an input box
var uploadField = document.getElementById("attachments");
uploadField.onchange = function() {
    //if the file is larger than 2MB
    if(this.files[0].size > 2097152){
        //tell the user its too big and they can select another file
        alert("Max file size is 2MB, please select another file.");
        //reset the inputbox value
        this.value = "";
    }
};