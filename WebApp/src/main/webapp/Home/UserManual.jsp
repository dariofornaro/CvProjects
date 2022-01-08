<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<h1 class="display-1">User Manual</h1>
<br>
<br>

<div style="display: flex; justify-content: center; flex-wrap: wrap">
    <div class="card" style="width: 21.4rem;">
        <img class="card-img-top" class="rounded" src="https://images.unsplash.com/photo-1494961104209-3c223057bd26?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=992&q=80" alt="Card image cap">
        <div class="card-body">
        <h5 class="card-title">Logistics User</h5>
        <p class="card-text">You have the ability to sign-up new clients and manage Journeys and Containers.</p>
        </div>
    </div>
    <div class="card" style="width: 23rem;">
        <img class="card-img-top" src="https://images.unsplash.com/photo-1521790797524-b2497295b8a0?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1049&q=80" alt="Card image cap">
        <div class="card-body">
        <h5 class="card-title">Client</h5>
        <p class="card-text">You have the ability to request new journeys and share it with your own customers</p>
        </div>
    </div>
    <div class="card" style="width: 23rem;">
        <img class="card-img-top" src="https://images.unsplash.com/photo-1586769852836-bc069f19e1b6?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80" alt="Card image cap">
        <div class="card-body">
        <h5 class="card-title">Search bar</h5>
        <p class="card-text">Anyone can check the status of an order, provided that they know the order's ID. </p>
    </div>
    </div>
    <div class="card" style="width: 23rem;">
        <img class="card-img-top" src="https://images.unsplash.com/photo-1501868984184-76121ed6a6e2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80" alt="Card image cap">
        <div class="card-body">
        <h5 class="card-title">World Map</h5>
        <p class="card-text">Anyone can check the world map where all the journeys are being shown.</p>
    </div>
    </div>
</div>

<style>
    .body {
        background-image: url("https://images.pexels.com/photos/3989748/pexels-photo-3989748.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
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
        margin-bottom: 10px;
    }
    .main-card {
        background-color: rgba(0, 0, 0, 0.2);
        color:white;
        backdrop-filter: blur(10px);
        border-radius: 10px;
    }
    h1 { color:white; text-align: center }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
