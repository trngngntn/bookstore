<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:38 AM
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
                       onchange="clearInput('${ResultFilter.TRIP.label}')"
                       name="${ResultFilter.TRIP.label}-check" ${resultFilters.contains(ResultFilter.TRIP)?"checked":""}>
                <span class="filter-name">${ResultFilter.TRIP.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.TRIP.label}"
                       id="${ResultFilter.TRIP.label}-input" placeholder="Enter trip"
                       value="${resultFilters.contains(ResultFilter.TRIP)?keywords[resultFilters.indexOf(ResultFilter.TRIP)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.PLACE.label}')"
                       name="${ResultFilter.PLACE.label}-check" ${resultFilters.contains(ResultFilter.PLACE)?"checked":""}>
                <span class="filter-name">${ResultFilter.PLACE.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.PLACE.label}"
                       id="${ResultFilter.PLACE.label}-input" placeholder="Enter place"
                       value="${resultFilters.contains(ResultFilter.PLACE)?keywords[resultFilters.indexOf(ResultFilter.PLACE)]:""}">
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
                    <th>ID</th>
                    <th>Name</th>
                    <th>Trip</th>
                    <th>Phone</th>
                    <th>Place</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultList}" var="item">
                    <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/bookingOffice/${item.id}')">
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>
                            <c:forEach items="${tripList}" var="trip">
                                <c:if test="${item.tripId eq trip.id}">${trip.destination}</c:if>
                            </c:forEach>
                        </td>
                        <td>${item.phone}</td>
                        <td>${item.place}</td>
                        <td>${item.price}</td>
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
