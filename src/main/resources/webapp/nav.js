/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
//add the sidenav div to the body of the page
document.body.innerHTML += '<div id="mySidenav" class="sidenav">\n' +
    '        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()" style="font-size: 40px">&times;</a>\n' +
    '    </div>';
var sideNavContent = document.getElementById("mySidenav");

//Add an anchor tag to the list
var aTag = document.createElement('a');
aTag.setAttribute('href',"Timelines.html");
aTag.innerHTML = "Timelines";
sideNavContent.appendChild(aTag);

//Add an anchor tag to the list
var aTag = document.createElement('a');
aTag.setAttribute('href',"Events.html");
aTag.innerHTML = "Events";
sideNavContent.appendChild(aTag);