<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"></jsp:include>

<h1>
    List Of Registered Users:
</h1>

<style>
    .demo-list-icon {
      width: 300px;
    }
</style>

<ul>
    <c:forEach items="${users}" var="user">
        <li>
            <span>
                <a href="/Users/View?ID=${user.getID()}">
                    <i>person</i>
                    ${user.getFirstName()} ${user.getLastName()}
                </a>
            </span>
        </li>   
    </c:forEach>
</ul>

<br>



<form action="/Users/New" method="post"   class="card">
    <div class="card-header">
        <h5>Create New User</h5>
    </div>
    <div class="card-body">
        <label for="FirstName">First Name:</label>
        <br>
        <input type="text" class="form-control" name="FirstName">
        </br>
        <label for="LastName">Last Name:</label>
        <br>
        <input type="text" class="form-control" name="LastName">
        <br>
    </div>    
    <div style="display: flex; justify-content: space-between;" class="card-footer">
        <input type="submit" class="btn btn-success" value="Submit">
        <a class="btn btn-danger" href="/Users/Index">Cancel</a>
    </div>
</form>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
