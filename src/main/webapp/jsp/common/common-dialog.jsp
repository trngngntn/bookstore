<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 8/6/21
  Time: 3:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="request" var="alertMessage" value="Are you sure to delete this ticket?"/>
<c:set scope="request" var="function" value="submitDelete()"/>
<div class="blur hidden" id="blur">
    <div class="windows hidden" id="confirm-dialog">
        <span>${alertMessage}</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="${function}">
                <i class="material-icons-round">check</i>
                <span class="button-label">Yes</span>
            </button>
            <button class="icon-button" onclick="hideWindow('confirm-dialog')">
                <i class="material-icons-round">close</i>
                <span class="button-label">No</span>
            </button>
        </div>
    </div>

    <div class="windows hidden" id="add-dialog">
        <span class="success">Added to database</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="hideWindow('add-dialog')">
                <i class="material-icons-round">done</i>
                <span class="button-label">Ok</span>
            </button>
        </div>
    </div>

    <div class="windows hidden" id="update-dialog">
        <span class="success">Update successfully</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="hideWindow('update-dialog')">
                <i class="material-icons-round">done</i>
                <span class="button-label">Ok</span>
            </button>
        </div>
    </div>

    <div class="windows hidden" id="delete-dialog">
        <span class="success">Delete successfully</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="hideWindow('delete-dialog')">
                <i class="material-icons-round">done</i>
                <span class="button-label">Ok</span>
            </button>
        </div>
    </div>

    <div class="windows hidden" id="error-dialog">
        <span class="error">An error has occurred</span>
        <div class="windows-bottom">
            <button class="icon-button" onclick="hideWindow('error-dialog')">
                <i class="material-icons-round">done</i>
                <span class="button-label">Ok</span>
            </button>
        </div>
    </div>
</div>
