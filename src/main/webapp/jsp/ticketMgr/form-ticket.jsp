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
    <tr>
        <td class="required">Customer</td>
        <td><input name="${TicketMeta.CUSTOMER_NAME.fieldName}" type="text" placeholder="Enter customer name" value="${detail.customerName}"></td>
    </tr>
    <tr>
        <td class="required">Booked time</td>
        <td><input name="${TicketMeta.BOOKED_TIME.fieldName}" type="time" placeholder="Enter time" value="${detail.bookedTime}"></td>
    </tr>
    <tr>
        <td class="required">Trip</td>
        <td>
            <select name="${TicketMeta.TRIP_ID.fieldName}">
                <option disabled selected>Select trip</option>
                <c:forEach items="${tripListNotFull}" var="trip">
                    <option value="${trip.id}"
                            <c:if test="${trip.id eq detail.tripId}">selected</c:if>
                    >${trip.destination}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td class="required">License plate</td>
        <td>
            <select name="${TicketMeta.LICENSE_PLATE.fieldName}">
                <option disabled selected>Select license plate</option>
                <c:forEach items="${carList}" var="car">
                    <option value="${car.licensePlate}"
                            <c:if test="${car.licensePlate eq detail.licensePlate}">selected</c:if>
                    >${car.licensePlate}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    </tbody>
</table>