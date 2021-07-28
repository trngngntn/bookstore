<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div>
        <div id="page-title">${pageTitle}</div>
        <div id="site-title">Car Parking Management System</div>
    </div>
    <div class="align-right">
        <div class="header-button clickable">
            <i class="material-icons-round">account_circle</i>
            <span class="button-label">Welcome</span>
        </div>
        <div class="header-button clickable" onclick="window.location.href='<%=request.getContextPath()%>/logout'">
            <i class="material-icons-round">logout</i>
            <span class="button-label">Log Out</span>
        </div>
    </div>
</header>
