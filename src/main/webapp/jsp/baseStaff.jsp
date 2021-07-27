<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/common-head.jsp"/>
</head>
<body>
<header></header>
<nav>
    <div class="nav-category">
        <div class="nav-category-label">Car Manager</div>
        <div class="nav-entry clickable">
            <i class="material-icons-round">directions_car</i>
            <span class="nav-entry-label">Car list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('addCar')">
            <i class="material-icons-round">add</i>
            <span class="nav-entry-label">Add car</span>
        </div>
    </div>

    <div class="nav-category">
        <div class="nav-category-label">Booking Office Manager</div>
        <div class="nav-entry clickable" onclick="changePage('listBookingOffice')">
            <i class="material-icons-round">apartment</i>
            <span class="nav-entry-label">Booking office list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('addBookingOffice')">
            <i class="material-icons-round">add</i>
            <span class="nav-entry-label">Add booking office</span>
        </div>
    </div>

    <div class="nav-category">
        <div class="nav-category-label">Parking Lot Manager</div>
        <div class="nav-entry clickable" onclick="changePage('addParkingLot')">
            <i class="material-icons-round">local_parking</i>
            <span class="nav-entry-label">Parking lot list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('addParkingLot')">
            <i class="material-icons-round">add</i>
            <span class="nav-entry-label">Add parking lot</span>
        </div>
    </div>

    <div class="nav-category">
        <div class="nav-category-label">Trip Manager</div>
        <div class="nav-entry clickable" onclick="changePage('listTrip')">
            <i class="material-icons-round">commute</i>
            <span class="nav-entry-label">Trip list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('addTrip')">
            <i class="material-icons-round">add</i>
            <span class="nav-entry-label">Add trip</span>
        </div>
    </div>

    <div class="nav-category">
        <div class="nav-category-label">Ticket Manager</div>
        <div class="nav-entry clickable">
            <i class="material-icons-round">confirmation_number</i>
            <span class="nav-entry-label">Ticket list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('addTicker')">
            <i class="material-icons-round">add</i>
            <span class="nav-entry-label">Add ticket</span>
        </div>
    </div>
</nav>
<main>
    <jsp:include page="${page}"/>
</main>
<footer></footer>
</body>
</html>
