let mymap;
let markers = [];
function printMap(){
    mymap = L.map('mapid').setView([55.752, 37.617], 11);
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(mymap);
}

function importMarkers(){
    markerCounter = 0
    route.forEach(element => {
        marker = new L.Marker([element.latitude, element.longtitude], {draggable:false});
        mymap.addLayer(marker);
        marker.bindPopup(element.description);
        marker.bindTooltip(markerCounter.toString(),
            {
                permanent: true,
                direction: 'right'
            }
        );
        markers.push(marker);
        markerCounter++;
    })
    console.log(route);
}

printMap();
importMarkers();