<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/31/21
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bar-action">
    <button class="icon-button align-left" onclick="toAdd()">
        <i class="material-icons-round">add</i>
        <span class="button-label">Add</span>
    </button>
    <div class="inline-block">
        <input id="search-area" type="text" placeholder="Search" value="${keyword}">
        <button id="search-button" class="icon-button" onclick="querySearch()">
            <i class="material-icons-round">search</i>
            <span class="button-label">Search</span>
        </button>
    </div>
    <select id="filter-select" class="align-right">
        <c:forEach items="${searchableMeta}" var="meta">
            <option value="${meta.fieldName}"
                <c:if test="${filter eq meta.fieldName}"> selected </c:if>
            >${StringUtils.getDisplayName(meta.fieldName)}</option>
        </c:forEach>
    </select>
</div>
<br>
<c:if test="${pageTitle eq 'Trip Manager'}">
    <div class="bar-action">
        <input id="date-filter" class="align-right" type="date" value="${date}">
    </div>
</c:if>