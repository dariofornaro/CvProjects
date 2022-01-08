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
    

    <form action="/Login/Login" method="post" class="card">
        <div class="card-header">
            <h5>Login</h5>
        </div>
        <div class="card-body">
            <label for="Username">Username:</label>
            <br>
            <input type="text" class="form-control" name="Username" required>
            </br>
            <label for="Password">Password:</label>
            <br>
            <input type="password" class="form-control" name="Password" required>
            <br>
        </div>    
        <div style="display: flex; justify-content: space-between;" class="card-footer">
            <input type="submit" class="btn btn-success" value="Login">
            <a class="btn btn-danger" href="/">Cancel</a>
        </div>
    </form>
</div>


<style>
    .body {
        background-image: url("https://images.unsplash.com/photo-1511578194003-00c80e42dc9b?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80");
        width: 100;
        background-repeat: no-repeat;
        background-size: cover;
    } 
    .card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
        border-radius: 10px;
        width:70%;
        margin:auto
    }
    .warning {
        width:70%;
    }
    .alert {width: 70%; margin:auto}
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>