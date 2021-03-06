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
<body onload="initPage()">
<jsp:include page="common/common-header.jsp"/>
<nav>
    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/car', false, 'Car Manager')">
        <i class="material-icons-round">directions_car</i>
        <span class="nav-entry-label">Car Manager</span>
    </div>

    <div class="nav-entry clickable"
         onclick="changePage('<%= request.getContextPath()%>/bookingOffice', false, 'Booking Office Manager')">
        <i class="material-icons-round">apartment</i>
        <span class="nav-entry-label">Booking Office Manager</span>
    </div>

    <div class="nav-entry clickable"
         onclick="changePage('<%= request.getContextPath()%>/parkingLot', false, 'Parking Lot Manager')">
        <i class="material-icons-round">local_parking</i>
        <span class="nav-entry-label">Parking Lot Manager</span>
    </div>

    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/trip', false, 'Trip Manager')">
        <i class="material-icons-round">commute</i>
        <span class="nav-entry-label">Trip Manager</span>
    </div>

    <div class="nav-entry clickable"
         onclick="changePage('<%= request.getContextPath()%>/ticket', false, 'Ticket Manager')">
        <i class="material-icons-round">confirmation_number</i>
        <span class="nav-entry-label">Ticket Manager</span>
    </div>
</nav>
<main id="main">
    <jsp:include page="${page}"/>
</main>
<jsp:include page="common/common-dialog.jsp"/>
<div id="status-bar">
    <div id="added-status" class="status-message success hidden">
        <span class="material-icons-round success">check_circle</span>
        Added to database
    </div>
    <div id="updated-status" class="status-message success hidden">
        <span class="material-icons-round success">check_circle</span>
        Update successfully
    </div>
    <div id="deleted-status" class="status-message success hidden">
        <span class="material-icons-round success">check_circle</span>
        Delete successfully
    </div>
    <div id="db-error-status"  class="status-message error hidden">
        <span class="material-icons-round error">error</span>
        Database connection error
    </div>
</div>
<footer></footer>
</body>
</html>
