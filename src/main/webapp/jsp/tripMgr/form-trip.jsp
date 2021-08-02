<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.TripMeta" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tbody>
    <c:if test="${not empty editing}">
        <tr>
            <td class="required">ID</td>
            <td>
                <div>${detail.id}</div>
            </td>
        </tr>
    </c:if>
    <tr id="${TripMeta.DESTINATION.fieldName}-row" func="checkNull">
        <td class="required">Destination</td>
        <td>
            <input name="${TripMeta.DESTINATION.fieldName}" type="text" placeholder="Enter destination" maxlength="50"
                   value="${detail.destination}">
            <div id="error-${TripMeta.DESTINATION.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TripMeta.DEPARTURE_TIME.fieldName}-row" func="checkNull">
        <td class="required">Departure time</td>
        <td>
            <input name="${TripMeta.DEPARTURE_TIME.fieldName}" type="time" placeholder="Enter time"
                   value="${detail.departureTime}">
            <div id="error-${TripMeta.DEPARTURE_TIME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TripMeta.DRIVER.fieldName}-row" func="checkNull">
        <td class="required">Driver</td>
        <td>
            <input name="${TripMeta.DRIVER.fieldName}" type="text" placeholder="Enter driver" maxlength="50"
                   value="${detail.driver}">
            <div id="error-${TripMeta.DRIVER.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TripMeta.CAR_TYPE.fieldName}-row" func="checkNull">
        <td class="required">Car type</td>
        <td>
            <input name="${TripMeta.CAR_TYPE.fieldName}" type="text" placeholder="Enter car type" maxlength="20"
                   value="${detail.carType}">
            <div id="error-${TripMeta.CAR_TYPE.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TripMeta.MAX_ONL_TICKET.fieldName}-row" func="checkNull">
        <td class="required">Maximum online ticket</td>
        <td>
            <input name="${TripMeta.MAX_ONL_TICKET.fieldName}" type="number" placeholder="Enter number" min="0"
                   value="${detail.maxOnlTicket}">
            <div id="error-${TripMeta.MAX_ONL_TICKET.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${TripMeta.DEPARTURE_DATE.fieldName}-row" func="checkNull">
        <td class="required">Departure date</td>
        <td>
            <input name="${TripMeta.DEPARTURE_DATE.fieldName}" type="date" placeholder="Enter date"
                   value="${detail.departureDate}">
            <div id="error-${TripMeta.DEPARTURE_DATE.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    </tbody>
</table>