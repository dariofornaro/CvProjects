<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"></jsp:include>

<h1>
    List Of Registered Users:
</h1>

<div   class="card">
    <div class="card-header">
        <h5>User ${user.getFirstName()}</h5>
    </div>
    <div class="card-body">
        <p>
            <strong>Name:</strong> ${user.getFirstName()} ${user.getLastName()}
            <strong>ID:</strong> ${user.getID()}
        </p>
    </div>    
    <div style="display: flex; justify-content: space-between;" class="card-footer">
        <a href="/Users/Edit?ID=${user.getID()}" class="btn btn-success">Edit</a>
        <form action="/Users/Delete" method="post">
            <input value="${user.getID()}" name="ID" type="hidden">
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
