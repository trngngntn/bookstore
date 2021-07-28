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
    <div class="nav-entry" onclick="//changePage('<%= request.getContextPath()%>/car', 'Car Manager')">
        <i class="material-icons-round">people</i>
        <span class="nav-entry-label">Employee Manager</span>
    </div>
</nav>
<main id="main">
    <jsp:include page="${page}"/>
</main>
<footer></footer>
</body>
</html>
