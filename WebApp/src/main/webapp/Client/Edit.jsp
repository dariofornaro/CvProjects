<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp">
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<div>
    <c:if test="${warning != null}">
        <div class="alert alert-danger" role="alert">
            ${warning}
        </div>
        <br>
    </c:if>
    

    <form action="/Client/Edit" method="post" class="card">
        <div class="card-header">
            <h5>Edit your Profile</h5>
        </div>
        <div class="card-body">
            <c:forEach items="${SignedUser.getProfile().getAllParameters()}" var="element">
                <label for="${element}">${element}:</label>
                <br>
                <input type="text" class="form-control" name="${element}" value="${SignedUser.getProfile().getParameter(element)}" >
                <br>
            </c:forEach>
            <label for="Password">Password:</label>
            <br>
            <input type="password" class="form-control" name="Password">
            <br>
            <label for="PasswordRetype">Retype Password:</label>
            <br>
            <input type="password" class="form-control" name="PasswordRetype">
            <br>
        </div>    
        <div style="display: flex; justify-content: space-between;" class="card-footer">
            <input type="submit" class="btn btn-success" value="Submit">
            <a class="btn btn-danger" href="/Client/View?ID=${SignedUser.getID()}">Cancel</a>
        </div>
    </form>
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
    .alert {width: 70%; margin:auto}
</style>


<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>