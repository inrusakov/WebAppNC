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

function deleteMarkers(){
    markers.forEach(element => mymap.removeLayer(element));
    markerCounter = 0;
    markers = [];
}

function onMapClick(e) {
    marker = new L.Marker(e.latlng, {draggable:true});
    mymap.addLayer(marker);
    popup = L.popup().openPopup();
    popup.setContent("");
    marker.bindPopup(popup);
    marker.bindTooltip(markerCounter.toString(),
        {
            permanent: true,
            direction: 'right'
        }
    );
    markerCounter++;
    markers.push(marker);
    markers.forEach(marker => {
        marker.addEventListener('popupopen',()=>{
            setMarkerEvents();
        });
        marker.addEventListener('click',()=>
        {
            if (marker.getPopup().getContent().toString() == "") {
                marker._popup.setContent(
                    "<label for=\"name\">Enter marker name:</label>\n" +
                    "<input type=\"text\" id=\"name\">\n" +
                    "<br>" +
                    "<label for=\"desc\">Enter marker description:</label>\n" +
                    "<input type=\"text\" id=\"desc\">\n" +
                    "<br>" +
                    "<button id='saveButton'>Save</button>" +
                    "<br>" +
                    "<button id='editButton'>Edit</button>");
            }
            const saveButton = document.getElementById('saveButton')
            if (saveButton)
                saveButton.addEventListener('click', () => save(marker))
            // const editButton = document.getElementById('editButton')
            // if (editButton)
            //     editButton.addEventListener('click', () => edit(marker))
        });
    });
};

function setMarkerEvents(){
    markers.forEach(marker => {

    })
}

function save(save){
    const popup = save._popup;
    let name;
    let desc;
    try{
        name = document.getElementById('name').value;
        desc = document.getElementById('desc').value;
    } catch (err) {
    }
    popup.setContent("<h3>Name: </h3>" +
        "<h4 id='name'>"+name+"</h4>" +
        "<h3>Description: </h3>" +
        "<h4>"+desc+"</h4>"+
        "<br>"+
        "<button id='editButton'>Edit</button>");
}

function edit(edit){
    const popup = edit._popup;
    popup.setContent(
        "<label for=\"name\">Enter marker name:</label>\n" +
        "<input type=\"text\" id=\"name\">\n" +
        "<br>"+
        "<label for=\"desc\">Enter marker description:</label>\n" +
        "<input type=\"text\" id=\"desc\">\n" +
        "<br>"+
        "<button id='saveButton'>Save</button>"+
        "<br>"+
        "<button id='editButton'>Edit</button>");
    setMarkerEvents();
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



