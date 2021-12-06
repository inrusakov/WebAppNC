let mymap;
let markers = [];
let lines = L.polyline([0,0]);
let id = document.querySelector("input[name='id']").value;
// const editB = document.querySelector('#editRoute')
// editB.addEventListener('click',
//     () => window.location.href = "/editRoute/"+routeid+"?routeId="+routeid+"&submit=value")

function printMap(){
    mymap = L.map('mapid').setView([55.752, 37.617], 11);
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}' +
        '?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
        maxZoom: 18,
        attribution:
            'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, ' +
            'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(mymap);
}

function importMarkers(){
    let imported;
    let token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        // Request type.
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        // Request data type.
        dataType : 'json',
        // Request link.
        url: "/routes/"+id,
        // Converting request object.
        success : function(data) {
            // Success log.
            console.log("SUCCESS: ", data);
            imported = data;
            imported.markers.forEach(element => {
                marker = new L.Marker([element.latitude, element.longtitude], {draggable:false});
                mymap.addLayer(marker);
                marker.bindTooltip(element.description,
                    {
                        permanent: true,
                        direction: 'right'
                    }
                );
                markers.push(marker);
            })
            updateLines();
        },
        error : function(e) {
            // Error log.
            console.log("ERROR: ", e);
        },
        // Passing the csrf token.
        headers: {'X-CSRF-TOKEN': token}
    });
}

function connectTheDots(data){
    let c = [];
    data.forEach(marker => {
        let x = marker._latlng.lat;
        let y = marker._latlng.lng;
        c.push([x, y]);
    })
    return c;
}

function updateLines(dat){
    if (dat!=null){
        mymap.removeLayer(lines);
        lines = L.polyline(connectTheDots(dat));
        lines.addTo(mymap)
    } else {
        mymap.removeLayer(lines);
        lines = L.polyline(connectTheDots(markers));
        lines.addTo(mymap)
    }
}

printMap();
importMarkers();