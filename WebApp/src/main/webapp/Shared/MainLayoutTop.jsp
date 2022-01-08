<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <title>ForYouShipment</title>
  </head>

  <body class = "body">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 shadow-sm"
      style="justify-content: space-between; background-color: #273745!important;
      border-bottom: 1px solid #000000!important">
      <h5 class="my-0 mr-md-auto font-weight-normal">
        <a href="/"><strong>ForYouShipment</strong></a>
      </h5>
      <div class="d-flex flex-column flex-md-row align-items-center">
        <nav class="my-2 my-md-0 mr-md-3">
          <c:if test="${SignedUser != null && !SignedUser.IsLogisticUser()}">
            <a class="btn btn-outline-secondary" href="/">Home</a>
            <a class="btn btn-outline-secondary" href="/WorldMap">World Map</a>
            <a class="btn btn-outline-secondary" href="/Journey">Journey Page</a>
            <a class="btn btn-outline-secondary" href="/Client">Client Page</a>
            <a class="btn btn-outline-secondary" href="/Client/View?ID=${SignedUser.getID()}">${SignedUser.getUsername()}</a>
            <a class="btn btn-outline-primary" href="/Login/Logout">Log out</a>
          </c:if>
          <c:if test="${SignedUser != null && SignedUser.IsLogisticUser()}">
            <a class="btn btn-outline-secondary" href="/">Home</a>
            <a class="btn btn-outline-secondary" href="/WorldMap">World Map</a>
            <a class="btn btn-outline-secondary" href="/Journey/Search">Journeys</a>
            <a class="btn btn-outline-secondary" href="/Logistics">Logistics Page</a>
            <a class="btn btn-outline-secondary" href="/Client/Search">Search Clients</a>
            <a class="btn btn-outline-primary" href="/Login/Logout">Log out</a>
          </c:if>
          <c:if test="${SignedUser == null}">
            <a class="btn btn-outline-secondary" href="/">Home</a>
            <a class="btn btn-outline-secondary" href="/WorldMap">World Map</a>
            <a class="btn btn-outline-primary" href="/Login">Log in</a>
          </c:if>
        </nav>
      </div>
    </div>
    <div>
      <main style="padding-left: 10%; padding-right: 10%;">
