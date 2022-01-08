<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp">
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<div   >
    <c:if test="${warning != null}">
        <div class="alert alert-danger" role="alert">
            ${warning}
        </div>
        <br>
    </c:if>

    <form action="/Signup/${SubmitLink}" method="post" class="card">
        <div class="card-header">
            <h5>Create New User</h5>
        </div>
        <div class="card-body">
            <label for="Username">Username:</label>
            <br>
            <input type="text" value="${SignUpUser.getUsername()}" class="form-control" name="Username" required>
            <br>
            <c:forEach items="${SignUpUser.getProfile().getAllParameters()}" var="element">
                <label for="${element}">${element}:</label>
                <br>
                <input type="text" class="form-control" name="${element}" value="${SignUpUser.getProfile().getParameter(element)}" required>
                <br>
            </c:forEach>
            <label for="Password">Password:</label>
            <br>
            <input type="password" class="form-control" name="Password" required>
            <br>
            <label for="PasswordRetype">Retype Password:</label>
            <br>
            <input type="password" class="form-control" name="PasswordRetype" required>
            <br>
        </div>    
        <div style="display: flex; justify-content: space-between;" class="card-footer">
            <input type="submit" class="btn btn-success" value="Submit">
            <a class="btn btn-danger" href="/Logistics">Cancel</a>
        </div>
    </form>
</div>


<style>
    .body {
        background-image: url("https://images.unsplash.com/photo-1573030889348-c6b0f8b15e40?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1613&q=80");
        width: 100%;
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