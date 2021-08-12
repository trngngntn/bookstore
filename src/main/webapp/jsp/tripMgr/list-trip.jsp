<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.ResultFilter" %>
<%@ page import="fa.training.utils.DateTimeUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <c:set scope="request" var="placeholder" value="Search by destination"/>
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div class="hidden" id="filter">
        <form id="filter-form">
            <br>
            <label id="filter-form-label"> Advanced filters: </label>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.DRIVER.label}')"
                       name="${ResultFilter.DRIVER.label}-check" ${resultFilters.contains(ResultFilter.DRIVER)?"checked":""}>
                <span class="filter-name">${ResultFilter.DRIVER.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.DRIVER.label}"
                       id="${ResultFilter.DRIVER.label}-input" placeholder="Enter driver name"
                       value="${resultFilters.contains(ResultFilter.DRIVER)?keywords[resultFilters.indexOf(ResultFilter.DRIVER)]:""}">
            </label>
            <br>
            <label class="filter-group">
                <input class="filter-group-toggle" type="radio" name="date-check"
                       onchange="clearInput('${ResultFilter.DATE.label}')"
                ${resultFilters.contains(ResultFilter.TO_DATE) || resultFilters.contains(ResultFilter.FROM_DATE) ?"checked":""}>
                <div class="filter-group-label">Filter by date range</div>
                <div class="filter-group-content">
                    <label class="filter-toggle">
                        <input class="filter-toggle-box" type="checkbox" name="${ResultFilter.FROM_DATE.label}-check"
                               onchange="clearInput('${ResultFilter.FROM_DATE.label}')"
                        ${resultFilters.contains(ResultFilter.FROM_DATE)?"checked":""}>
                        <span class="filter-name">${ResultFilter.FROM_DATE.display}</span>
                        <input class="filter-value" type="date" name="${ResultFilter.FROM_DATE.label}"
                               id="${ResultFilter.FROM_DATE.label}-input"
                               value="${resultFilters.contains(ResultFilter.FROM_DATE)?keywords[resultFilters.indexOf(ResultFilter.FROM_DATE)]:""}">
                    </label>
                    <label class="filter-toggle">
                        <input class="filter-toggle-box" type="checkbox" name="${ResultFilter.TO_DATE.label}-check"
                               onchange="clearInput('${ResultFilter.TO_DATE.label}')"
                        ${resultFilters.contains(ResultFilter.TO_DATE)?"checked":""}>
                        <span class="filter-name">${ResultFilter.TO_DATE.display}</span>
                        <input class="filter-value" type="date" name="${ResultFilter.TO_DATE.label}"
                               id="${ResultFilter.TO_DATE.label}-input"
                               value="${resultFilters.contains(ResultFilter.TO_DATE)?keywords[resultFilters.indexOf(ResultFilter.TO_DATE)]:""}">
                    </label>
                </div>
            </label>
            <br>
            <label class="filter-group">
                <input class="filter-group-toggle" type="radio" name="date-check"
                       onchange="clearInput('${ResultFilter.FROM_DATE.label}');clearInput('${ResultFilter.TO_DATE.label}')"
                ${resultFilters.contains(ResultFilter.DATE)?"checked":""}>
                <div class="filter-group-label">Filter by exact date</div>
                <div class="filter-group-content">
                    <label class="filter-toggle">
                        <input class="filter-toggle-box" type="checkbox" name="${ResultFilter.DATE.label}-check"
                               onchange="clearInput('${ResultFilter.DATE.label}')"
                        ${resultFilters.contains(ResultFilter.DATE)?"checked":""}>
                        <span class="filter-name">${ResultFilter.DATE.display}</span>
                        <input class="filter-value" type="date" name="${ResultFilter.DATE.label}"
                               id="${ResultFilter.DATE.label}-input"
                               value="${resultFilters.contains(ResultFilter.DATE)?keywords[resultFilters.indexOf(ResultFilter.DATE)]:""}">
                    </label>
                </div>
            </label>
        </form>
    </div>
    <div>
        <c:if test="${empty resultList.size()}">
            <span class="no-match">No matches</span>
        </c:if>
        <c:if test="${resultList.size() > 0}">
            <table>
                <thead>
                <tr>
                    <th>No</th>
                    <th>Destination</th>
                    <th>Departure</th>
                    <th>Driver</th>
                    <th>Car type</th>
                    <th>Booked ticket</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultList}" var="item">
                    <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/trip/${item.id}')">
                        <td>${item.id}</td>
                        <td>${item.destination}</td>
                        <td>${DateTimeUtils.formatTime(item.departureTime)} ${DateTimeUtils.formatDateUI(item.departureDate)}</td>
                        <td>${item.driver}</td>
                        <td>${item.carType}</td>
                        <td>${item.bookedTicket}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <jsp:include page="../common/common-pagination.jsp"></jsp:include>
        </c:if>
    </div>
</div>
<jsp:include page="../common/common-form.jsp"/>