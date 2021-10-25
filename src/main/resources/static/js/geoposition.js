let latitude;
let longitude;
let altitude;
let accuracy;
let altitudeAccuracy;
let heading;
let speed;
let timestamp;

async function locationSuccess(position) {
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;
    altitude = position.coords.altitude;
    accuracy = position.coords.accuracy;
    altitudeAccuracy = position.coords.altitudeAccuracy;
    heading = position.coords.height;
    speed = position.coords.speed;
    timestamp = position.timestamp;

    // Console output.
    console.debug("Location:   latitude: "+ latitude + "   longitude: "+ longitude)
    // Connecting with opencagedata service to get address, passing coords.
    let response = await fetch("https://api.opencagedata.com/geocode/v1/json?q="
        +latitude
        +"+"
        +longitude
        +"+&key=4abf3bea6bad4dfcbd723ed0d6283bc5");
    // Checking the response.
    if (response.ok) {
        let json = await response.json();
        // Logging in console the response from geo service;
        console.log(json);
    } else {
        alert("ERROR HTTP: " + response.status);
    }

    // Creating the object passing to geo controller.
    let geoRequest = {
        //"user" : user,
        "latitude" : latitude,
        "longitude" :longitude
    }

    // Creating post request with estimated info to controller.
    $.ajax({
        // Request type.
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        // Request data type.
        dataType : 'json',
        // Request link.
        url: "/sendCoords",
        // Converting request object.
        data: JSON.stringify(geoRequest),
        success : function(data) {
            // Success log.
            console.log("SUCCESS: ", data);
        },
        error : function(e) {
            // Error log.
            console.log("ERROR: ", e);
        },
        // Passing the csrf token.
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()}
    });
}

function locationError(error) {
    const code = error.code;
    const message = error.message;

    // read the code and message and decide how you want to handle this!
}

//navigator.geolocation.getCurrentPosition(locationSuccess, locationError);