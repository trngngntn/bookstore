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
<body>
<jsp:include page="common/common-header.jsp" />
<nav>
    <div class="nav-category">
        <div class="nav-category-label">Employee Manager</div>
        <div class="nav-entry clickable">
            <i class="material-icons-round">list</i>
            <span class="nav-entry-label">Employee list</span>
        </div>
        <div class="nav-entry clickable" onclick="changePage('createEmployee')">
            <i class="material-icons-round">person_add_alt</i>
            <span class="nav-entry-label">Create employee</span>
        </div>
    </div>
</nav>
<main id="main">
    <jsp:include page="${page}"/>
</main>
<footer></footer>
</body>
</html>
