<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
    <jsp:param name="Ownjourneys" value="${Ownjourneys}"/>

</jsp:include>



<h1>Journey Management</h1> 

<div style="display: flex; justify-content:flex-end ;">
    <a class="btn btn-success btn-lg float-end" style="margin-right: 15px" href="/Journey/New" > New Journey</a>
    <a class="btn btn-info btn-lg float-end" href="/Journey/Search" > Search </a>
</div>

<br>

<table class="table table-hover" >
<thead class="thead-light" >
    <tr>
        <th> Origin </th>
        <th> Destination </th>
        <th> Content type </th>
        <th> Cargo </th>
        <th> Status </th>
    </tr>
</thead>
<tbody>
    <c:forEach items="${Ownjourneys}" var="element">
        <c:if test="${!element.getStatus().equals('Completed')}"> 
            <tr style='cursor: pointer; cursor: hand;' onclick="window.location='/Journey/View?ID=${element.getId()}';">
                <td>${element.getOrigin().toString()} </td>
                <td>${element.getDestination().toString()} </td>
                <td>${element.getContent_type()}</td>
                <td>${element.getCargo()} </td>
                <td>${element.getStatus()}</td>
            </tr>
        </c:if>
        <c:if test="${element.getStatus().equals('Completed')}"> 
            <tr>
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
    h1 { color:white;text-align: center }
    
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>

