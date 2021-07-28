<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="common/common-head.jsp"/>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/css/login.css">
</head>
<body>
<form id="form-login" action="login" method="post">
    <input id="usr-input" type="text" name="username" placeholder="Username">
    <br><br>
    <input id="pwd-input" type="password" name="password" placeholder="Password">
    <br><br>
    <input type="checkbox" id="cbx-remember" name="rem">
    <label for="cbx-remember">Remember Me</label>
    <br><br>
    <button id="login-btn">Login</button>
    <br><br>
    <c:if test="${failed eq true}"><span class="error">Username or Password wrong!</span><br><br></c:if>
</form>
</body>
</html>
