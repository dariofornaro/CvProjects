<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Shared/MainLayoutTop.jsp"> 
    <jsp:param name="SignedUser" value="${SignedUser}"/>
</jsp:include>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<h1 class="display-1">Team 6</h1>

<div style="display: flex; justify-content: center; flex-wrap: wrap">
  
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Dario.jpeg" alt="Dario" style="width:100%" class="center"   >
    <h2>Dario</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
  </div>
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Diana.jpeg" alt="Diana" style="width:100%" class="center"   >
    <h2>Diana</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
  </div>
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Dusana.jpeg" alt="Dusana" style="width:100%" class="center"   >
    <h2>Dusana</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
  </div>
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Emma.jpeg" alt="Emma" style="width:100%" class="center"   >
    <h2>Emma</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
  </div>
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Paulo.jpeg" alt="Paulo" style="width:100%" class="center"   >
    <h2>Paulo</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
  </div>
  <div class="card" style="width: 300px; margin: 20px">
    <img src="/Renjue.jpeg" alt="Renjue" style="width:100%" class="center"   >
    <h2>Renjue</h2>
    <p class="title">CEO & Founder</p>
    <p>Technical University of Denmark</p>
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
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        max-width: 300px;
        text-align: center;
    }
    h1 { color:white; text-align: center }
</style>

<jsp:include page="../Shared/MainLayoutBottom.jsp"></jsp:include>
