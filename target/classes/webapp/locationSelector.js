
    //myLatLng is the variable that holds the starting location of the map - I have initialised it as uganda
    var myLatLng = {lat: 1.373333, lng: 32.290275};

    //function to get the user's position using location data

    //startPos is the variable that will hold the user's position data
    var startPos;
    //geoOptions Defines the options of the request e.g timeout
    var geoOptions = {
        //maximumAge: 5 * 60 * 1000,
        //timeout: 10 * 1000
    }
    //if there is no error getting location data, this function is ran
    var geoSuccess = function(position) {
        startPos = position;
        //log all data taken from user in console
        console.log("User location data recieved, the user info is as follows:");
        console.log(startPos);
        console.log("User's latitude: " +startPos.coords.latitude);
        console.log("User's longitude: " +startPos.coords.longitude);
        //make dummy variables to hold lat and lng
        startlat=startPos.coords.latitude;
        startlng=startPos.coords.longitude;
        //set the values of myLatLng to hold the variables
        myLatLng.lat=startlat;
        myLatLng.lng=startlng;
        //log this data in the console to show that the values of myLatLng have changed
        console.log("myLatLng dot lat is now set to: "+myLatLng.lat)
        console.log("myLatLng dot lng is now set to: "+myLatLng.lng)
    };
    //if there is an error getting location data, this function is ran
    var geoError = function(error) {
        console.log('Error occurred. Error code: ' + error.code);
        myLatLng = {lat: 56.1881, lng: -4.6262};
        console.log("for this reason myLatLng has been set to Glasgow: "+ myLatLng.lat + "," + myLatLng.lng);

        // error.code can be added later, here are options for us:
        //   0: unknown error
        //   1: permission denied
        //   2: position unavailable (error response from location provider)
        //   3: timed out
    };
    // call the geolocation position method passing it the objects from above
    navigator.geolocation.getCurrentPosition(geoSuccess, geoError, geoOptions);
    var map;
    var marker;
    //the following creates a map
    function initMap() {
        //this console log is a trace - this code should run after the code above but seems to run before it on occasion. I have absolutely no idea why this is the case
        console.log("This code should run after your location is taken, half the time this isnt the case. Here is the current latitude value as the map is being built.: "+myLatLng.lat)
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 7,
            center: new google.maps.LatLng(myLatLng)
        });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
            searchBox.setBounds(map.getBounds());
        });


        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
            var places = searchBox.getPlaces();
            if (places.length == 0) {
                return;
            }



            // For each place, get the icon, name and location. - this moves the map to where the user selects
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function(place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
        });





        //Functionality allows user to add their own markers
        google.maps.event.addListener(map, 'click', function(event) {
            placeMarker(event.latLng);
        });
    }

    function placeMarker(location) {
        //if there is a marker, then move it to the location the user is clicking
        if ( marker ) {
            marker.setPosition(location);
        }
        //if there is not a marker, make one and set it to the location the user is clicking
        else{
            marker = new google.maps.Marker({
                position: location,
                map: map
            });
        }

        //create variables storing lat and long values
        var lat = marker.getPosition().lat();
        var lng = marker.getPosition().lng();
        //latlng is a concatenation of the lat and long
        var latlng=(lat+", "+lng);
        //log this value in the console for testing
        console.log(lat);
        console.log(lng);
        //set the value of the hidden form element to hold latlng
        $("#lat").val(lat);
        $("#lng").val(lng);
        //set the center of the map to be the location of the new marker
        map.setCenter(location);
    }

