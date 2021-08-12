<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 8/6/21
  Time: 3:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="request" var="alertMessage" value="Are you sure to delete this ticket?"/>
<c:set scope="request" var="function" value="submitDelete()"/>
<div class="blur hidden" id="blur">
    <div class="windows">
        <span>${alertMessage}</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="${function}">
                <i class="material-icons-round">check</i>
                <span class="button-label">Yes</span>
            </button>
            <button class="icon-button" onclick="hideWindow()">
                <i class="material-icons-round">close</i>
                <span class="button-label">No</span>
            </button>
        </div>
    </div>
</div>
