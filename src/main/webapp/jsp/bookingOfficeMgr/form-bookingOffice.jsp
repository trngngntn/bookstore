<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.BookingOfficeMeta" %>
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
        <td class="required">Name</td>
        <td><input name="${BookingOfficeMeta.NAME.fieldName}" type="text" placeholder="Enter office name" value="${detail.name}"></td>
    </tr>
    <tr>
        <td class="required">Trip</td>
        <td>
            <select name="${BookingOfficeMeta.TRIP_ID.fieldName}">
                <option disabled selected>Select trip</option>
                <c:forEach items="${tripList}" var="trip">
                    <option value="${trip.id}"
                            <c:if test="${trip.id eq detail.tripId}">selected</c:if>
                    >${trip.destination}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td class="required">Phone number</td>
        <td><input name="${BookingOfficeMeta.PHONE.fieldName}" type="text" placeholder="Enter phone number" value="${detail.phone}"></td>
    </tr>
    <tr>
        <td class="required">Place</td>
        <td><input name="${BookingOfficeMeta.PLACE.fieldName}" type="text" placeholder="Enter place" value="${detail.place}"></td>
    </tr>
    <tr>
        <td class="required">Price</td>
        <td><input name="${BookingOfficeMeta.PRICE.fieldName}" type="text" placeholder="Enter price" value="${detail.price}"></td>
    </tr>
    <tr>
        <td class="required">Begin contract</td>
        <td><input name="${BookingOfficeMeta.START_CONTRACT.fieldName}" type="date" value="${detail.startContract}"></td>
    </tr>
    <tr>
        <td class="required">End contract</td>
        <td><input name="${BookingOfficeMeta.END_CONTRACT.fieldName}" type="date" value="${detail.endContract}"></td>
    </tr>
    </tbody>
</table>