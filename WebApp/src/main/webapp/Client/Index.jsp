<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>

<h2 class="display-1">Welcome to the Client Board!</h2> 
<br>

<div class="main-card">
    <div class="card-body">
        <!-- <h2 class="card-title">How to Use</h3> -->
        <ul class="nav nav-tabs">
            <li class="active"><a class="white-option" data-toggle="tab" href="#home">General</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu1">Destinations</a></li>
            <li><a class="white-option" data-toggle="tab" href="#menu2">Journeys</a></li>
          </ul>
          
          <div class="tab-content">
            <div id="home" class="tab-pane fade in active">
              <h3>General Information</h3>
              <p>Having an account on this website gives you the possibility to request orders and the logsitcs company will
                    take care of them, keeping you up-to date with the latest changes to your order.
                <br>
                <br>
                As a client user, you have the ability to view your profile, aswell as update your personal details.
              </p>
              <div style="display: flex; justify-content:flex-start ;">
                <a class="btn btn-success" href="/Client/View?ID=${SignedUser.getID()}" style="margin-right: 30px"> View your profile</a>
              </div>
            </div>
            <div id="menu1" class="tab-pane fade" color="white">
              <h3>Destinations</h3>
              <p>As our client, you can send your packages all over the world!
                <br>
                <br>
                Below you can find a list of all available destinations for your packages.
                <br>
                <br>
                Just remember that you can only start a Journey from the available destinations found in the tab "Journeys".
                <ul>
                  <c:forEach items="${Ports}" var="element">
                      <c:if test="${!(element.toString().equals('In Transit'))}">
                        <li>${element.toString()}</li>
                      </c:if>
                  </c:forEach>
                </ul>
              </p>
            </div>
            <div id="menu2" class="tab-pane fade" color="white">
              <h3>Your Journeys</h3>
              <p>
                Available containers for starting a new Journeys from the following ports:
                <ul>
                    <c:if test="${portMap.size() == 0}">
                      <li>There are no free containers at the moment. We apologise for the inconvenience, please try again later!</li>
                    </c:if>
                    <c:if test="${portMap.size() > 0}">
                      <c:forEach items="${portMap}" var="element">
                          <li>${element.getKey()}</li>
                      </c:forEach>
                    </c:if>
                </ul>
                <br>
                All you need to do is to go to the Journey Page, order the available container for your journey, and wait for our approval. 
                  Once we approve it, your package is on its way to its destination.
                <br>
                <br>
                Follow the internal status and position of your package whenever you want! Share the ID of the journey with your customers, so they can follow the package as easily as you. 
                  You can see the package's location on World Map as well.
                <br>
                <br>
                To find your current or previous journeys, it is enough to search it by simple criteria, by anything you can remember about that journey. 
                Maintaining your personal page is also very easy, go to your profile's page and update your information if necessary.                
              </p>
              <div style="display: flex; justify-content:flex-start ;">
                <a class="btn btn-success" href="/Journey" style="margin-right: 30px"> Your Journeys</a>
              </div>
            </div>
    </div>
</div>


<style>
    .body {
        background-image: url("https://images.pexels.com/photos/7634552/pexels-photo-7634552.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
        
    } 
    .main-card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        margin: auto;
        width: 70%;
        backdrop-filter: blur(10px);
        border-radius: 10px;
    }
    .center {
        color: white;
        margin: auto;
        width: 50%;
        height: 50%;
    }
    .white-option {
        color: #e9ecef;
        text-decoration: none;
    }
    .white-option {
        background-color: rgb(255 255 255 / 15%);
    }
    h2 { text-align: center; color:white }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>

