<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.ResultFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div class="hidden" id="filter">
        <form id="filter-form">
            <br>
            <label id="filter-form-label"> Advanced filters: </label>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.STATUS.label}')"
                       name="${ResultFilter.STATUS.label}-check" ${resultFilters.contains(ResultFilter.STATUS)?"checked":""}>
                <span class="filter-name">${ResultFilter.STATUS.display}</span>
                <select id="${ResultFilter.STATUS.label}-input" name="${ResultFilter.STATUS.label}"
                        class="filter-value">
                    <option disabled selected></option>
                    <option value="1"
                    ${resultFilters.contains(ResultFilter.STATUS) && keywords[resultFilters.indexOf(ResultFilter.STATUS)].equals("1")?"selected":""}>
                        Occupied
                    </option>
                    <option value="0"
                    ${resultFilters.contains(ResultFilter.STATUS) && keywords[resultFilters.indexOf(ResultFilter.STATUS)].equals("0")?"selected":""}>
                        Blank
                    </option>
                </select>
            </label>
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Place</th>
                <th>Area</th>
                <th>Price</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="item">
                <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/parkingLot/${item.id}')">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>
                        <c:forEach items="${placeList}" var="place">
                            <c:if test="${item.placeId eq place.id}">${place.name}</c:if>
                        </c:forEach>
                    </td>
                    <td>${item.area}</td>
                    <td>${item.price}</td>
                    <td>
                        <c:if test="${item.status eq true}">Occupied</c:if>
                        <c:if test="${item.status eq false}">Blank</c:if>
                    </td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common-pagination.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="../common/common-form.jsp"/>
