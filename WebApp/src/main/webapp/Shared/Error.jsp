<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>

<h1 class="display-1 center">Error 404: Not found :(</h1>


<style>
    .body {
        background-image: url("https://images.unsplash.com/photo-1559771675-6f43f7e37a77?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1818&q=80");
        background-repeat: no-repeat;
    } 
    .center {
        margin: auto;
        width: 50%;
        height: 50%;
        padding-top: 5%;
    }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>