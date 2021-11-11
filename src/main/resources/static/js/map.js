let mymap;
let markerCounter = 0;
let markers = [];
let route;
let lines = L.polyline([0,0]);

const deleteM = document.querySelector('#deleteMarkers')
const sendRouteM = document.querySelector('#sendRoute')
const updateM = document.querySelector('#updateRoute')
deleteM.addEventListener('click', () => deleteMarkers())
sendRouteM.addEventListener('click', () => sendRoute())
if (updateM)
    updateM.addEventListener('click', ()=> updateRoute())

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

function importMarkers(){
    route.markers.forEach(element => {
        marker = new L.Marker([element.latitude, element.longtitude], {draggable:true});
        mymap.addLayer(marker);
        marker.bindTooltip(element.description,
            {
                permanent: true,
                direction: 'right'
            }
        );
        marker.addEventListener('click',()=>
        {});
        marker.on('drag', ()=>{
            updateLines();
        });
        markers.push(marker);
        createListItem(element.description);
    })

    updateLines();
    console.log(route);
}

function updateRoute(){
    route.markers = [];
    class Marker {
        constructor(desc, lat, long) {
            this.description = desc;
            this.latitude = lat;
            this.longtitude = long;
        }
    }
    markers.forEach(elem => {
        route.markers.push(new Marker(elem._tooltip._content, elem._latlng.lat, elem._latlng.lng));
    })
    let token = $("meta[name='_csrf']").attr("content");
    let paramName = $("meta[name='_csrf_parameter_name']").attr("content");
    $.ajax({
        // Request type.
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        // Request data type.
        dataType : 'json',
        // Request link.
        url: "/updateRoute",
        // Converting request object.
        data: JSON.stringify(route),
        success : function(data) {
            // Success log.
            console.log("SUCCESS: ", data);
            window.location.href = "/routeObserver/"+route.id+"?routeId="+route.id+"&submit=value";
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

function deleteMarkers(){
    markers.forEach(element => mymap.removeLayer(element));
    markerCounter = 0;
    markers = [];
    clearList();
    updateLines([0,0]);
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
    markers.push(marker);
    markers.forEach(marker => {
        marker.addEventListener('click',()=>
        {});
        marker.on('drag', ()=>{
            updateLines();
        });
    });
    createListItem(markerCounter);
    markerCounter++;

    updateLines();
};

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

function deleteMarker(index){
    markers[index].removeFrom(mymap);
    markers.splice(index,1);
    markerCounter--;
    updateLines();
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
        route.push(element._latlng.toString().split("(")[1].split(")")[0]
            + ','
            + element._tooltip._content);
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



