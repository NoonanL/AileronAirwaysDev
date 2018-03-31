function readfile(file) {
    var reader = new FileReader();
    reader.onload = function (e) {
        var rawData = reader.result;
        $("#fileAsBinary").val(rawData);
    };
    reader.readAsBinaryString(file);
}
