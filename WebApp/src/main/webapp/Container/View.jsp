<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<div class=main-card>

</div>
<h2>Container #${Container.getId()}</h2> 

<form action="/Container/Delete?ID=${Container.getId()}" method="post" style="display:flex; justify-content: flex-end;">
    <c:if test="${Container.getJourney() == null}">
        <input type="submit" class="btn btn-danger btn-lg float-end" href="#" onclick="history.go(-1)" value="Delete Container" style="margin-right: 5px"> 
    </c:if>
    <a class="btn btn-info btn-lg float-end" href="#" onclick="history.go(-1)"> Back </a>   
</form>

<br>
<br>

<c:if test="${Container.getJourneyHistory().size() == 0}">
    <div class="card text-center">
        <div class="card-body">
            <h3 class="card-text"> There is no history of any Journey involved with this container.
                <br>
            </h3>
        </div>
    </div>
</c:if>

<div class="panel-group" id="accordion"></div>
    
    <c:forEach items="${Container.getJourneyHistory()}" var="journey" >
       
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#${journey.getId()}">
                    From ${journey.getOrigin().toString()} to ${journey.getDestination().toString()} ordered by ${journey.getParameter("Username")}: Journey ID #${journey.getId()}</a>
                </h4>
            </div>
            <div id="${journey.getId()}" class="panel-collapse collapse out">
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
                        <c:if test="${m.get('JourneyID').equals(journey.getId())}">
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
       
    </c:forEach>
</div>


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
        backdrop-filter: blur(10px);
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
    .body {
        background-image: url("https://images.pexels.com/photos/2850287/pexels-photo-2850287.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
    } 
    .thead-light {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(20px);
    }
    .table-hover {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
    }
    h2 { color:white; text-align: center }
    .card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        margin: auto;
        width: 70%;
        backdrop-filter: blur(10px);
        border-radius: 10px;
    }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
