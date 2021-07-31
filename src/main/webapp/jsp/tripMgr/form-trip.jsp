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
    <tr>
        <td class="required">Destination</td>
        <td><input name="${TripMeta.DESTINATION.fieldName}" type="text" placeholder="Enter destination" value="${detail.destination}"></td>
    </tr>
    <tr>
        <td class="required">Departure time</td>
        <td><input name="${TripMeta.DEPARTURE_TIME.fieldName}" type="time" placeholder="Enter time" value="${detail.departureTime}"></td>
    </tr>
    <tr>
        <td class="required">Driver</td>
        <td><input name="${TripMeta.DRIVER.fieldName}" type="text" placeholder="Enter driver" value="${detail.driver}"></td>
    </tr>
    <tr>
        <td class="required">Car type</td>
        <td><input name="${TripMeta.CAR_TYPE.fieldName}" type="text" placeholder="Enter car type" value="${detail.carType}"></td>
    </tr>
    <tr>
        <td class="required">Maximum online ticket</td>
        <td><input  name="${TripMeta.MAX_ONL_TICKET.fieldName}" type="number" placeholder="Enter number" value="${detail.maxOnlTicket}"></td>
    </tr>
    <tr>
        <td class="required">Departure date</td>
        <td><input name="${TripMeta.DEPARTURE_DATE.fieldName}" type="date" placeholder="Enter date" value="${detail.departureDate}"></td>
    </tr>
    </tbody>
</table>