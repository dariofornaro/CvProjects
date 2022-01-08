<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>

<br>

<h1 class="center">All Journeys</h1>

<br>

<div class="card">
  <div class="card-body">
    <style type="text/css">
      /* Set the size of the div element that contains the map */
      #map {
        height: 400px;
        /* The height is 400 pixels */
        width: 100%;
        /* The width is the width of the web page */
      }
    </style>
    <script>
      var containers = [
        <c:forEach var="container" items="${Containers}">
            ['<c:out value="Journey" />',
              <c:out value="${Double.parseDouble(container.getParameter('Latitude'))}" />,
              <c:out value="${Double.parseDouble(container.getParameter('Longitude'))}" />,
            ],
        </c:forEach>   ]; 

      var markers = [
        <c:forEach var="marker" items="${ports}">
            ['<c:out value="${marker.toString()}" />',
              <c:out value="${marker.getLatitude()}" />,
              <c:out value="${marker.getLongitude()}" />,
            ],
        </c:forEach>   ]; 
      // Initialize and add the map
      function initMap() {
        // Initialize the map and center it in the container location
        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 1,
          center: new google.maps.LatLng(10,10),
        });
        
        // Markers for all the ports
        for (i = 0; i < markers.length; i++){
          const marker = new google.maps.Marker({
            icon: "/anchor-32.png",
            label: markers[i][0],
            position: new google.maps.LatLng(markers[i][1],markers[i][2]),
            zIndex: 1,
            map: map,
          });
        }
        
        for (i = 0; i < containers.length; i++){
          console.log("im here");
          console.log(containers[i][1])
          const marker = new google.maps.Marker({
            icon: "/packageicon.png",
            label: containers[i][0],
            position: new google.maps.LatLng(containers[i][1],containers[i][2]),
            zIndex: 2,
            map: map,
          });
        }

      }
      function toggleBounce() {
        if (marker.getAnimation() !== null) {
         marker.setAnimation(null);
        } 
        else {
          marker.setAnimation(google.maps.Animation.BOUNCE);
        }
      }
      function myFunction() {
        var dummy = document.createElement('input'),
        text = window.location.href;

        document.body.appendChild(dummy);
        dummy.value = text;
        dummy.select();
        document.execCommand('copy');
        document.body.removeChild(dummy);
      }

      
    </script>
   </head>
   <body>

    <!--The div element for the map -->
    <div id="map"></div>
    
  
    <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPxzk8oahETNTmTJPn39scuPoHIj_yZSY&callback=initMap&libraries=&v=weekly"
      async
    ></script>

  </div>

</div>


<style>
  .card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        margin: auto;
        width: 80%;
        backdrop-filter: blur(10px);
        margin-bottom: 10px;
    }
  .body {
        background-image: url("https://images.pexels.com/photos/234272/pexels-photo-234272.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
    }
    h1 { text-align: center }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>