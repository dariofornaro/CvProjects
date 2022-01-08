<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>



<h2>Search all your clients</h2> 

<form action="/Client/Search" method="get" style="display:flex;">
    <input type="text" class="form-control" name="Query" value="${Query}"
        placeholder="Criteria" aria-label="Criteria">
    <input type="submit" class="btn btn-success" value="Search" style="margin-left: 5px"> 
</form>

<br>
<br>


<table class="table table-hover" >
    <thead class="thead-light" >
        <tr>
            <th> Username </th>
            <th> First Name </th>
            <th> Last Name </th>
            <th> Email </th>
            <th> Comapny Name </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${answer}" var="element">
            <tr style='cursor: pointer; cursor: hand;' onclick="window.location='/Client/View?ID=${element.getID()}';">
                <td>${element.getUsername()} </td>
                <td>${element.getProfile().getParameter("First Name")} </td>
                <td>${element.getProfile().getParameter("Last Name")}</td>
                <td>${element.getProfile().getParameter("Email")} </td>
                <td>${element.getProfile().getParameter("Company Name")}</td>
            </tr>   
        </c:forEach>
    </tbody>
    </table>
    

<style>
    .body {
        background-image: url("https://images.unsplash.com/photo-1573030889348-c6b0f8b15e40?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1613&q=80");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
    } 
    .card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
        border-radius: 10px;
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

