//script to make all time Inputs with value="now" default to current time
$('input[type="time"][value="now"]').each(function(){
    //create a date object
    var d = new Date(),
        //get the hours and the minutes from the system clock
        h = d.getHours(),
        m = d.getMinutes();
    //if the hours or minutes are single digits, append a 0 to the front of them for formatting
    if(h < 10) h = '0' + h;
    if(m < 10) m = '0' + m;
    //set the value of the box to the hours and the minutes of the user's system clock
    $(this).attr({
        'value': h + ':' + m
    });
});