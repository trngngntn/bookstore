<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 5:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
        <c:if test="${not empty editing}"> id="edit-view" class="view" </c:if>
        <c:if test="${empty editing}"> id="add-view" class="view hide-top"</c:if>
>
    <div class="bar-action">
        <c:if test="${empty editing}">
            <div id="back-button" class="clickable icon-button align-right" onclick="backToList()">
                <i class="material-icons-round">close</i>
                <span class="button-label">Close</span>
            </div>
        </c:if>
        <span class="bar-title">${barTitle}</span>
    </div>

    <form
            <c:if test="${not empty editing}"> id="edit-form" action="${actionPath}/${detail.id}"
            </c:if>
            <c:if test="${empty editing}"> id="add-form" action="${actionPath}"
            </c:if>
    >
        <jsp:include page="${formPage}"/>

        <div>
            <button type="reset" id="reset-form-button" class="icon-button align-left">
                <i class="material-icons-round">restart_alt</i>
                <span class="button-label">Reset</span>
            </button>
            <button type="button" id="submit-form-button" class="icon-button align-right"
                    <c:if test="${not empty editing}"> onclick="submitEditForm()" </c:if>
                    <c:if test="${empty editing}"> onclick="submitAddForm()"</c:if>
            >
                <i class="material-icons-round">login</i>
                <span class="button-label">Submit</span>
            </button>
        </div>
    </form>
</div>