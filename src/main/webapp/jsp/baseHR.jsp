<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/common-head.jsp"/>
</head>
<body onload="initPage()">
<jsp:include page="common/common-header.jsp" />
<nav>
    <div class="nav-entry clickable" onclick="changePage('<%= request.getContextPath()%>/employee')">
        <i class="material-icons-round">people</i>
        <span class="nav-entry-label">Employee Manager</span>
    </div>
</nav>
<main id="main">
    <jsp:include page="${page}"/>
</main>
<div id="status-bar">
    <div id="added-status" class="status-message success hidden">
        <span class="material-icons-round success">check_circle</span>
        Added to database
    </div>
    <div id="updated-status" class="status-message success hidden">
        <span class="material-icons-round success">check_circle</span>
        Updated
    </div>
    <div id="db-error-status"  class="status-message error hidden">
        <span class="material-icons-round error">error</span>
        Database connection error
    </div>
</div>
<footer></footer>
</body>
</html>
