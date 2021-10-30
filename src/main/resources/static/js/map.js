let mymap;
let markerCounter = 0;
let markers = [];
let markerName, markerDesc;

const deleteM = document.querySelector('#deleteMarkers')
const sendRouteM = document.querySelector('#sendRoute')
deleteM.addEventListener('click', () => deleteMarkers())
sendRouteM.addEventListener('click', () => sendRoute())

function printMap() {
    mymap = L.map('mapid').setView([55.752, 37.617], 11);

    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(mymap);
    mymap.on('click', onMapClick);
}

function addMarker(lat, lng){
    let newMarker = L.marker([lat,lng]).addTo(mymap);
}

function deleteMarkers(){
    markers.forEach(element => mymap.removeLayer(element));
    markerCounter = 0;
    markers = [];
    clearList();
}

function onMapClick(e) {
    marker = new L.Marker(e.latlng, {draggable:true});
    mymap.addLayer(marker);
    marker.bindTooltip(markerCounter.toString(),
        {
            permanent: true,
            direction: 'right'
        }
    );
    markers.forEach(marker => {
        marker.addEventListener('click',()=>
        {});
    });
    markers.push(marker);
    createListItem(markerCounter);
    markerCounter++;
};

function deleteMarker(index){
    markers[index].removeFrom(mymap);
    markers.splice(index,1);
    markerCounter--;
}

function changeTooltip(index,tooltip){
    markers[index].unbindTooltip();
    markers[index].bindTooltip(tooltip,
        {
            permanent: true,
            direction: 'right'
        }
    );
}

function sendRoute(){
    let route = [];
    markers.forEach(element =>
    {
        if (element._popup._content.toString().includes("Enter marker name:")) {
            element._popup._content = "<h3>Name: </h3><h4>null</h4><h3>Description: </h3><h4>null</h4>"
        }
        route.push(element._latlng.toString().split("(")[1].split(")")[0]
            + ','
            + element._popup._content);
    });

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

printMap();



