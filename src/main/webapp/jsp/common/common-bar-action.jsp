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
        <input id="search-area" type="text" placeholder="Search" name="${filters[0].label}" value="${keyword}">
        <button id="search-button" class="icon-button" onclick="querySearch()">
            <i class="material-icons-round">search</i>
            <span class="button-label">Search</span>
        </button>
    </div>
    <button class="icon-button align-right" onclick="toggleFilter()">
        <i class="material-icons-round">filter_list</i>
        <span class="button-label">Filter</span>
    </button>
</div>