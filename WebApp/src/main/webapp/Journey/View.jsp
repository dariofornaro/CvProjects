<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>



<h1>Your Journey information</h1> 
<h3>${Journey.getStatus()}
<button type="button" class="btn btn-secondary" onclick="myFunction();window.alert('The Journey URL was copyed and you can share it with other people.');">Get URL</button>
</h3>

<div style="display: flex; justify-content:flex-end ;">
  <c:if test="${SignedUser.IsLogisticUser()}">
    <c:if test="${Journey.getStatus().equals('Waiting for aproval')}">
      <a class="btn btn-success btn float-end" style="margin-right: 15px" submit="${Journey.setStatus('Active')}" onclick="window.confirm('Are you sure you want to confirm the journey?'); window.location.reload()">
      Confirm Shipment
      </a>
    </c:if>
    <a class="btn btn-warning btn float-end" href="/Journey/Measurements?ID=${ContainerID}" style="margin-right: 15px" >
      Set Measurements
    </a>
  </c:if>
  <a class="btn btn-info btn float-end" href="#" onclick="history.go(-1)" style="margin-right: 10%" >
    Back
  </a>
</div>

  

  <c:if test="${warning != null}">
    <div class="alert alert-danger" role="alert">
        ${warning}
    </div>
    <br>
  </c:if>
  
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
      var current_lat = "${Latitude}"
      var current_lng = "${Longitude}"
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
          zoom: 2,
          center: new google.maps.LatLng(current_lat,current_lng),
        });
        // The marker, positioned at the shipment location
        const marker =  new google.maps.Marker({
          opacity: 1,
          label: "Your shipment is here",
          animation: google.maps.Animation.DROP,
          dragable: true,
          position: new google.maps.LatLng(current_lat,current_lng),
          zIndex: 2,
          map: map,
          icon: "/packageicon.png",
        })
        marker.addListener("click", toggleBounce);
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
    
    <div class="panel-group" id="accordion">
          <div class="panel panel-default">
          <div class="panel-heading">
              <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
              History of the container's measurements</a>
              </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse out">
            <table class="table table-hover" >
              <thead class="thead-light" >
                  <tr>
                      <th> Latitude </th>
                      <th> Longitude </th>
                      <th> Temperature </th>
                      <th> Humidity </th>
                      <th> Pressure </th>
                      <th> Time </th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach items="${Container.getMeasurementsHistory()}" var="m">
                    <tr>
                    <c:if test="${m.get('JourneyID').equals(ID)}">
                        <td>${m.get('Latitude')}</td>
                        <td>${m.get('Longitude')}</td>
                        <td>${m.get('Temperature')}</td>
                        <td>${m.get('Humidity')}</td>
                        <td>${m.get('Pressure')}</td>
                        <td>${m.get('Time')}</td>
                    </c:if>
                    </tr>
                </c:forEach> 
              </tbody>
              </table>
        
          </div>
          </div>
  </div>
    
    
    <!--The div element for the map -->
    <div id="map"></div>
    
  
    <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAPxzk8oahETNTmTJPn39scuPoHIj_yZSY&callback=initMap&libraries=&v=weekly"
      async
    ></script>



<style>
    .panel-group {
        color:white;
        border-radius: 10px;
        width: 100%;
        margin: auto;
    }
    .panel-default {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        border-radius: 10px;
        width: 100%;
        margin: auto;
    }
    .panel-heading {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
        width: 100%;
        margin: auto;
    }
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
    h3 { text-align: center }
    .thead-light {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
    }
    .table-hover {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
    }
    .alert {width: 80%; margin:auto}
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>

