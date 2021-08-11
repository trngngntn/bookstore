<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.ResultFilter" %>
<%@ page import="fa.training.utils.DateTimeUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div class="hidden" id="filter">
        <form id="filter-form">
            <br>
            <label id="filter-form-label"> Advanced filters: </label>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.TRIP.label}')"
                       name="${ResultFilter.TRIP.label}-check" ${resultFilters.contains(ResultFilter.TRIP)?"checked":""}>
                <span class="filter-name">${ResultFilter.TRIP.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.TRIP.label}"
                       id="${ResultFilter.TRIP.label}-input" placeholder="Enter driver name"
                       value="${resultFilters.contains(ResultFilter.TRIP)?keywords[resultFilters.indexOf(ResultFilter.TRIP)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.LICENSE_PLATE.label}')"
                       name="${ResultFilter.LICENSE_PLATE.label}-check" ${resultFilters.contains(ResultFilter.LICENSE_PLATE)?"checked":""}>
                <span class="filter-name">${ResultFilter.LICENSE_PLATE.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.LICENSE_PLATE.label}"
                       id="${ResultFilter.LICENSE_PLATE.label}-input" placeholder="Enter place"
                       value="${resultFilters.contains(ResultFilter.LICENSE_PLATE)?keywords[resultFilters.indexOf(ResultFilter.LICENSE_PLATE)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.CUSTOMER.label}')"
                       name="${ResultFilter.CUSTOMER.label}-check" ${resultFilters.contains(ResultFilter.CUSTOMER)?"checked":""}>
                <span class="filter-name">${ResultFilter.CUSTOMER.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.CUSTOMER.label}"
                       id="${ResultFilter.CUSTOMER.label}-input" placeholder="Enter place"
                       value="${resultFilters.contains(ResultFilter.CUSTOMER)?keywords[resultFilters.indexOf(ResultFilter.CUSTOMER)]:""}">
            </label>
            <br>
        </form>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>No</th>
                <th>Trip</th>
                <th>License plate</th>
                <th>Customer</th>
                <th>Booked time</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="item">
                <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/ticket/${item.id}')">
                    <td>${item.id}</td>
                    <td>
                        <c:forEach items="${tripList}" var="trip">
                            <c:if test="${item.tripId eq trip.id}">${trip.destination}</c:if>
                        </c:forEach>
                    </td>
                    <td>${item.licensePlate}</td>
                    <td>${item.customerName}</td>
                    <td>${DateTimeUtils.formatTime(item.bookedTime)}</td>
                    <td class="clickable" style="width: 2rem" onclick="doDelete(${item.id});">
                        <span class="material-icons-round">
                        delete
                        </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common-pagination.jsp"/>
    </div>
</div>
<jsp:include page="../common/common-form.jsp"/>
<c:set scope="request" var="alertMessage" value="Are you sure to delete this ticket?"/>
<c:set scope="request" var="function" value="submitDelete()"/>