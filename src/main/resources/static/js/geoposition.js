async function locationSuccess(position) {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    const altitude = position.coords.altitude;
    const accuracy = position.coords.accuracy;
    const altitudeAccuracy = position.coords.altitudeAccuracy;
    const heading = position.coords.height;
    const speed = position.coords.speed;
    const timestamp = position.timestamp;

    console.debug("Location:   latitude: "+ latitude + "   longitude: "+ longitude)
    let response = await fetch("https://api.opencagedata.com/geocode/v1/json?q="
        +latitude
        +"+"
        +longitude
        +"+&key=4abf3bea6bad4dfcbd723ed0d6283bc5");
    if (response.ok) {
        let json = await response.json();
        console.log(json);
    } else {
        alert("ERROR HTTP: " + response.status);
    }

    var geoRequest = {
        //"user" : user,
        "latitude" : latitude,
        "longitude" :longitude
    }

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: "/sendGeo",
        data: JSON.stringify(geoRequest),
        success : function(data) {
            console.log("SUCCESS: ", data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()}
    });
}

function locationError(error) {
    const code = error.code;
    const message = error.message;

    // read the code and message and decide how you want to handle this!
}

navigator.geolocation.getCurrentPosition(locationSuccess, locationError);