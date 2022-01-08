<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<div class=main-card>

</div>
<h2>Search all your Journeys</h2> 

<form action="/Journey/Search" method="get" style="display:flex;">
    <input type="text" class="form-control" name="Query" value="${Query}"
        placeholder="Criteria" aria-label="Criteria">
    <input type="submit" class="btn btn-success" value="Search" style="margin-left: 5px"> 
</form> 
<br>
<br>

<c:if test="${!SignedUser.IsLogisticUser()}">
<a class="btn btn-success btn-lg float-end" href="/Journey/New" > New Journey</a>
</c:if>

<h2>Journey List</h2> 

<br>

<table class="table table-hover" >
<thead class="thead-light" >
    <tr>
        <c:if test="${SignedUser.IsLogisticUser()}"><th> User </th></c:if>
        <th> Origin </th>
        <th> Destination </th>
        <th> Content type </th>
        <th> Cargo </th>
        <th> Status </th>
    </tr>
</thead>
<tbody>
    <c:forEach items="${answer}" var="element">
        <c:if test="${!element.getStatus().equals('Completed')}"> 
            <tr style='cursor: pointer; cursor: hand;' onclick="window.location='/Journey/View?ID=${element.getId()}';">
                <c:if test="${SignedUser.IsLogisticUser()}"><td> ${element.getParameter("Username")} </td></c:if>
                <td>${element.getOrigin().toString()} </td>
                <td>${element.getDestination().toString()} </td>
                <td>${element.getContent_type()}</td>
                <td>${element.getCargo()} </td>
                <td>${element.getStatus()}</td>
            </tr>
        </c:if>
        <c:if test="${element.getStatus().equals('Completed')}"> 
            <tr>
                <c:if test="${SignedUser.IsLogisticUser()}"><td> ${element.getParameter("Username")} </td></c:if>
                <td>${element.getOrigin().toString()} </td>
                <td>${element.getDestination().toString()} </td>
                <td>${element.getContent_type()}</td>
                <td>${element.getCargo()} </td>
                <td>${element.getStatus()}</td>
            </tr>
        </c:if>    
    </c:forEach>
</tbody>
</table>


<style>
    .body {
        background-image: url("https://images.pexels.com/photos/1655166/pexels-photo-1655166.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
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
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
