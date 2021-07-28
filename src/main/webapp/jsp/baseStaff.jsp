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
    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/car', 'Car Manager')">
        <i class="material-icons-round">directions_car</i>
        <span class="nav-entry-label">Car Manager</span>
    </div>

    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/bookingOffice', 'Booking Office Manager')">
        <i class="material-icons-round">apartment</i>
        <span class="nav-entry-label">Booking Office Manager</span>
    </div>

    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/parkingLot', 'Parking Lot Manager')">
        <i class="material-icons-round">local_parking</i>
        <span class="nav-entry-label">Parking Lot Manager</span>
    </div>

    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/trip', 'Trip Manager')">
        <i class="material-icons-round">commute</i>
        <span class="nav-entry-label">Trip Manager</span>
    </div>

    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/ticket', 'Ticket Manager')">
        <i class="material-icons-round">confirmation_number</i>
        <span class="nav-entry-label">Ticket Manager</span>
    </div>
</nav>
<main id="main">
    <jsp:include page="${page}"/>
</main>
<footer></footer>
</body>
</html>
