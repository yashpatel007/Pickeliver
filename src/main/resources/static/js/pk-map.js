/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//var fareperkm = [[${farekm.toString()}]];




var myLatLng = {
    lat: 0.0,
    lng: 0.0
};
var mapOptions = {
    center: myLatLng,
    zoom: 1,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

// Hide result box
document.getElementById("output").style.display = "none";


// Create/Init map
var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);

// Create a DirectionsService object to use the route method and get a result for our request
var directionsService = new google.maps.DirectionsService();

// Create a DirectionsRenderer object which we will use to display the route
var directionsDisplay = new google.maps.DirectionsRenderer();

// Bind the DirectionsRenderer to the map
directionsDisplay.setMap(map);


let current_routing = {
    distance: null ,
    duration : null
}


// Define calcRoute function
function calcRoute() {
    //create request
    var request = {
        origin: document.getElementById("location-1").value,
        destination: document.getElementById("location-2").value,
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC
    }

    // Routing
    directionsService.route(request, function (result, status) {
        if (status == google.maps.DirectionsStatus.OK) {

            //Get distance and time            

            $("#output").html("<div class='result-table'> Driving distance: " + result.routes[0].legs[0].distance.text + ".<br />Duration: " + result.routes[0].legs[0].duration.text + ".</div>");
            document.getElementById("output").style.display = "block";
            //display route
            directionsDisplay.setDirections(result);
           
            
        } else {
            //delete route from map
            directionsDisplay.setDirections({
                routes: []
            });
            //center map in London
            map.setCenter(myLatLng);

            //Show error message
            alert("Can't find road! Please try again!");
            clearRoute();
        }
          current_routing.distance = result.routes[0].legs[0].distance;
            current_routing.duration =  result.routes[0].legs[0].duration;
    });
    
   

}

// Clear results

function clearRoute() {
    document.getElementById("output").style.display = "none";
    document.getElementById("location-1").value = "";
    document.getElementById("location-2").value = "";
    directionsDisplay.setDirections({
        routes: []
    });

}

function sleep(miliseconds) {
   var currentTime = new Date().getTime();

   while (currentTime + miliseconds >= new Date().getTime()) {
   }
}

function getQuote(){
    let dist_duration =  calcRoute();
    calcRoute();
    if(current_routing != null){
        console.log(current_routing.distance);
    }
    
    document.getElementById("res-source").innerHTML = "Source : "+ document.getElementById("location-1").value;
    document.getElementById("res-destination").innerHTML = "Destination : "+ document.getElementById("location-2").value;
    document.getElementById("res-jobType").innerHTML = "JobType : "+ document.getElementById("myJobType").value;
    document.getElementById("res-distance").innerHTML = "Distance : "+ current_routing.distance.text;
    document.getElementById("res-charge").innerHTML = "Charge : $"+parseInt(current_routing.distance.value/1000)*fareperkm;
    
    
}

function placeOrder(){
    getQuote();
    document.getElementById("order-distance").value = parseFloat(current_routing.distance.value/1000);
    document.getElementById("order-charge").value = (parseInt(current_routing.distance.value)/1000)*fareperkm;
    document.getElementById("orderform").submit();
    
}

// Create autocomplete objects for all inputs

var options = {
    types: ['address']
}


var input1 = document.getElementById("location-1");
var autocomplete1 = new google.maps.places.Autocomplete(input1, options);

var input2 = document.getElementById("location-2");
var autocomplete2 = new google.maps.places.Autocomplete(input2, options);