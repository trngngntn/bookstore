<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/common-head.jsp"/>
</head>
<body>
<form id="form-login" action="login" method="post">
    <input id="usr-input" type="text" name="username" placeholder="Username">
    <br><br>
    <input id="pwd-input" type="password" name="password" placeholder="Password">
    <br><br>
    <input id="" type="button" value="Login" onclick="login()">
</form>
</body>
</html>
