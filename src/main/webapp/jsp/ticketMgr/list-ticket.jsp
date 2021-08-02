<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.DateTimeUtils" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <jsp:include page="../common/common-bar-action.jsp"/>
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
                <tr tabindex="0"  onclick="changePage('<%= request.getContextPath()%>/ticket/${item.id}')">
                    <td>${item.id}</td>
                    <td>
                        <c:forEach items="${tripList}" var="trip">
                            <c:if test="${item.tripId eq trip.id}">${trip.destination}</c:if>
                        </c:forEach>
                    </td>
                    <td>${item.licensePlate}</td>
                    <td>${item.customerName}</td>
                    <td>${DateTimeUtils.formatTime(item.bookedTime)}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common-pagination.jsp"/>
    </div>
</div>
<jsp:include page="../common/common-form.jsp" />