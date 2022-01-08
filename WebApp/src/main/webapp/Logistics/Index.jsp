<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<h2 class="display-1">Welcome to the Logistics Board!</h2> 
<br>

<div class="main-card">
    <div class="card-body">
        <!-- <h2 class="card-title">Important Statistics</h3> -->
        <ul class="nav nav-tabs">
            <li class="active"><a class="white-option" data-toggle="tab" href="#home">Clients</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu1">Journeys</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu2">Containers</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu3">Ports</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu4">Coordinates</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu5">Profile</a></li>
          </ul>
          
          <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
              <h3>Client Management</h3>
              <p>As a logistic user, you have the ability to sign-up clients, by creating a username and password for them.
                <br>
                <br>
                Once they log in on the system, they can update their profile information even further.
                <br>
                <br>
                You have the ability to view any client profile, as well as delete any client, but you are not allowed
                    to change their personal information, nor modify their username or password.
                <br>
                <br>
                The search engine allows you to find any client by any criteria, eg. username.
                <br>
                <br>
                Number of clients: ${numberClients}
              </p>
              <div style="display: flex; justify-content:flex-start ;">
                <a class="btn btn-success" href="/Signup/Client" style="margin-right: 30px"> Add new Client</a>
                <a class="btn btn-success" href="/Signup/Logistics" style="margin-right: 30px"> Add new Manager</a>
                <a class="btn btn-info" href="/Client/Search"> Search Clients</a>
              </div>
            </div>
            <div id="menu1" class="tab-pane fade" color="white">
              <h3>Journey Management</h3>
              <p>As a logistic user, you have the ability to accept any Journey request made by the clients.
                <br>
                <br>
                Once the Journey is accepted, both the logistics user and the client user can track its history 
                    on the World Map. 
                <br>
                <br>
                The search engine allows you to find any journeys by any criteria, eg. destination port.
                <br>
                <br>
                Everytime a client creates a new Journey, you will get a little notification on the button bellow
                <br>
                <br>
                Number of active journeys: ${numberAprovedJourneys}
              </p>
              <div style="display: flex; justify-content:flex-start ;">
                <a type="button" class="btn btn-info" href="/Journey/Search">
                  Journeys <span class="badge bg-secondary">${numberJourneysToApprove}</span>
                </a>
              </div>
            </div>
            <div id="menu2" class="tab-pane fade">
              <h3>Container Management</h3>
              <p>Every time a client request a Journey, an available container will be assigned to it.
              <br>
              <br>
              Each Journey can be tracked in terms of the measurements for a container, for eg. Longitute and Latitute.
                As a logistic user, you need to insert this measures manually.
              <br>
              <br>
              In order for a Journey to be started, it is necessary that you, as a logistic user, confirm the order.
              <br>
              <br>
              Likewise, in order to end it, you must choose the option "Yes" in the measurements form for the Journey to end.
              <br>
              <br>
              Finally, the used container will become free to use from the destination port.
            </p>
            </div>
            <div id="menu3" class="tab-pane fade">
              <h3>Ports Management</h3>
              <p>
                By clicking on the specific ports bellow, you can add or delete the containers located there.
                <br>
                <br>
                Moreover, you can check the history of each container on all the Journeys it went on. 
                  For every Journey there is a history of measurements included too.
                <br>
                <br>
                To be noted that in order for a client to request a Journey from a specific port, it is manditory
                  that in that location there exists free containers.
                  <br>
              <c:forEach items="${portMap}" var="element">
                  <c:if test="${element.getKey().equals('In Transit')}">
                    Containers 
                    <a class="btn btn-primary" style="margin: 10px;" 
                      href='/Port/View?Port=${element.getKey()}'>
                      ${element.getKey()} <span class="badge bg-info text-dark">${element.getValue()}</span>
                    </a>
                  </c:if>
              </c:forEach>
              <br>
              All ports with the number of containers located in each one of them:
              <br>
              <div style="display: flex; justify-content: flex-start; flex-wrap: wrap">
                <c:forEach items="${portMap}" var="element">
                  <c:if test="${!element.getKey().equals('In Transit')}">
                    <a class="btn btn-primary" style="margin: 10px;" 
                      href='/Port/View?Port=${element.getKey()}'>
                      ${element.getKey()} <span class="badge bg-info text-dark">${element.getValue()}</span>
                    </a>
                  </c:if>
                </c:forEach>
              </div> 
              </p>
          </div>
          <div id="menu4" class="tab-pane fade">
            <h3>Coordinates of all ports</h3>
            <p>
              You can check all the coordinates of all ports here:
              <br>
            <c:forEach items="${Ports}" var="element">
              <ul>
                <c:if test="${!(element.toString().equals('In Transit'))}"> 
                  <li>${element.toString()}(${element.getLongitude()}, ${element.getLatitude()})</li>
                </c:if>
              </ul>
                
            </c:forEach>
            </p>
        </div>
            <div id="menu5" class="tab-pane fade">
              <h3>Your Profile</h3>
              <p> 
                <ul class="list-group"  >
                  <c:forEach items="${SignedUser.getProfile().getAllParameters()}" var="element">
                      <li class="list-group-item">
                          ${element}:     ${SignedUser.getProfile().getParameter(element)}
                      </li>   
                  </c:forEach>
              </ul>
              </p>
            </div>
    </div>
</div>


<style>
    .body {
        background-image: url("https://images.unsplash.com/photo-1573030889348-c6b0f8b15e40?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1613&q=80");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
    } 
    .main-card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
        border-radius: 10px;
        width: 70%;
        margin: auto;
    }
    .list-group-item {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
    }
    .white-option {
        color: #e9ecef;
        text-decoration: none;
    }
    .white-option {
        background-color: rgb(255 255 255 / 15%);
    }
    h2 { text-align: center }
</style>





<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>

