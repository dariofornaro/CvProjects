<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>


<h1 class="display-1">ForYouShipment</h1>
<br>
<br>

<div class="card text-center">
    <div class="card-body">
        <p class="card-text"> Logistics Company in charge of your company orders
            <br>
        </p>
    </div>
</div>

<br>
<div class="row" style="display: flex; flex-wrap: wrap">
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Our partner companies</h5>
        <p class="card-text">As a company client you have access to a personalised account, 
                where you can manage your orders. In order to log in on this website, you must 
                use the unique password and username provided by our team. </p>
        <a href="/UserManual" class="btn btn-primary">Short User Manual</a>
      </div>
    </div>
  </div>
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Our Team</h5>
        <p class="card-text">This project is designed for course nr. 02160 Agile Object-Oriented Software
                  Development. We are Group no. 6 and we present you the best logistics website that has
                  ever been created on the market. Enjoy your time! 
        </p>
        <a href="/AboutUs" class="btn btn-primary">About us</a>
      </div>
    </div>
  </div>
</div>

<br>
<br>
<br>
<br>
<br>
<br>
<div style="display: flex;
            flex-wrap: nowrap;
            padding: 10px;"
        class="main-card">
    <div class="input-group" style="display: flex; flex-wrap: nowrap;">
        <span class="input-group-text" id="addon-wrapping">#</span>
        <input type="text" id="shipmentidinput" class="form-control"
                placeholder="Shipment URL" aria-label="Shipment URL"
                aria-describedby="addon-wrapping">
    </div>
    <button class="btn btn-success" onclick="GoToShimpentPage()"
            style="margin-left: 10px;">Search</button>
</div>

<script>
    function GoToShimpentPage() {
        var button_content = document.getElementById('shipmentidinput').value
        window.location.href = button_content;
    }
</script>


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
        border-radius: 10px;
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
