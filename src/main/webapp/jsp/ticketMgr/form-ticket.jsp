<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.TicketMeta" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tbody>
    <c:if test="${not empty editing}">
        <tr>
            <td>ID</td>
            <td>
                <div>${detail.id}</div>
            </td>
        </tr>
    </c:if>
    <tr id="${TicketMeta.CUSTOMER_NAME.fieldName}-row" func="checkNull">
        <td class="required">Customer</td>
        <td>
            <input name="${TicketMeta.CUSTOMER_NAME.fieldName}" type="text" placeholder="Enter customer name"
                   maxlength="50" value="${detail.customerName}">
            <div id="error-${TicketMeta.CUSTOMER_NAME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TicketMeta.BOOKED_TIME.fieldName}-row" func="checkNull">
        <td class="required">Booked time</td>
        <td>
            <input name="${TicketMeta.BOOKED_TIME.fieldName}" type="time" placeholder="Enter time"
                   value="${detail.bookedTime}">
            <div id="error-${TicketMeta.BOOKED_TIME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TicketMeta.TRIP_ID.fieldName}-row" func="checkNull">
        <td class="required">Trip</td>
        <td>
            <select name="${TicketMeta.TRIP_ID.fieldName}" onchange="queryCar(this.value)">
                <option selected value="">Select trip</option>
                <c:forEach items="${tripListNotFull}" var="trip">
                    <option value="${trip.id}"
                            <c:if test="${trip.id eq detail.tripId}">selected</c:if>
                    >${trip.destination}</option>
                </c:forEach>
            </select>
            <div id="error-${TicketMeta.TRIP_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TicketMeta.LICENSE_PLATE.fieldName}-row" func="checkNull">
        <td class="required">License plate</td>
        <td>
            <select name="${TicketMeta.LICENSE_PLATE.fieldName}" id="licensePlateSelect">
                <option selected value="">Select trip to retrieve car list</option>
                <c:forEach items="${carListByTrip}" var="car">
                    <option value="${car.licensePlate}"
                            <c:if test="${car.licensePlate eq detail.licensePlate}">selected</c:if>
                    >${car.licensePlate}</option>
                </c:forEach>
            </select>
            <div id="error-${TicketMeta.LICENSE_PLATE.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    </tbody>
</table>