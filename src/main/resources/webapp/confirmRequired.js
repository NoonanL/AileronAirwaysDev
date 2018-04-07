// console.log("the confirmRequired Script is in use");
// $('.confirmRequired').submit(function() {
//     var c = confirm("Are you sure? This action cannot be undone");
//     console.log(c);
//     return c; //you can just return c because it will be true or false
// });
$(document).on('submit','.confirmRequired',function(){
    var c = confirm("Are you sure? This action cannot be undone");
    console.log(c);
    return c; //you can just return c because it will be true or false
});