let markerCounter = 0;
let markers = [];

const deleteM = document.querySelector('#deleteMarkers')
const sendRouteM = document.querySelector('#sendRoute')
deleteM.addEventListener('click', () => deleteMarkers())
sendRouteM.addEventListener('click', () => sendRoute())

mymap.on('click', onMapClick);

function addMarker(lat, lng){
    let newMarker = L.marker([lat,lng]).addTo(mymap);
}

function deleteMarkers(){
    markers.forEach(element => mymap.removeLayer(element));
    markerCounter = 0;
    markers = [];
}

function onMapClick(e) {
    marker = new L.Marker(e.latlng, {draggable:true});
    mymap.addLayer(marker);
    marker.bindPopup("<b>Hello!</b>");
    marker.bindTooltip(markerCounter.toString(),
        {
            permanent: true,
            direction: 'right'
        }
    );
    markerCounter++;
    markers.push(marker);
};

function sendRoute(){
    let route = [];
    markers.forEach(element => route.push(element._latlng.toString().split("(")[1].split(")")[0]));

    $.ajax({
        // Request type.
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        // Request data type.
        dataType : 'json',
        // Request link.
        url: "/sendRoute",
        // Converting request object.
        data: JSON.stringify(route),
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
