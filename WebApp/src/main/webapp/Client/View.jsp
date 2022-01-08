<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>





<div class="card">
    <div class="card-body">
      <h2 class="card-title">${ProfileUser.getUsername()}'s Profile</h2>
      <ul class="list-group"  >
        <c:forEach items="${ProfileUser.getProfile().getAllParameters()}" var="element">
            <li class="list-group-item">
                ${element}:     ${ProfileUser.getProfile().getParameter(element)}
            </li>   
        </c:forEach>
    </ul>
        <c:if test="${SignedUser == ProfileUser}">
            <div style="display: flex; justify-content: space-between; flex-wrap: nowrap">
                <a href="/Client/Edit" class="btn btn-success">Edit Profile</a>
                <a class="btn btn-info" href="#" onclick="history.go(-1)">Back</a>
            </div>
        </c:if>
        <c:if test="${SignedUser != ProfileUser}">
            <div style="display: flex; justify-content: space-between; flex-wrap: nowrap">
                <a href="/Client/Delete?ID=${ProfileUser.getID()}" class="btn btn-danger">Delete User</a>
                <a class="btn btn-info" href="#" onclick="history.go(-1)">Back</a>
            </div>
            
        </c:if>
    </div>
  </div>


<style>
    .body {
        background-image: url("https://images.pexels.com/photos/7634552/pexels-photo-7634552.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
        
    } 
    .card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        margin: auto;
        width: 70%;
        backdrop-filter: blur(10px);
        border-radius: 10px;
    }
    .list-group-item {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
    }
</style>


<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>

