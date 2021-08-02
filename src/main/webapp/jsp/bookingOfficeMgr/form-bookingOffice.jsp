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
            <td>ID</td>
            <td>
                <div>${detail.id}</div>
            </td>
        </tr>
    </c:if>
    <tr id="${BookingOfficeMeta.NAME.fieldName}-row" func="checkNull">
        <td class="required">Name</td>
        <td>
            <input name="${BookingOfficeMeta.NAME.fieldName}" type="text" placeholder="Enter office name" value="${detail.name}" maxlength="50">
            <div id="error-${BookingOfficeMeta.NAME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${BookingOfficeMeta.TRIP_ID.fieldName}-row" func="checkNull">
        <td class="required">Trip</td>
        <td>
            <select name="${BookingOfficeMeta.TRIP_ID.fieldName}">
                <option selected value="">Select trip</option>
                <c:forEach items="${tripList}" var="trip">
                    <option value="${trip.id}"
                            <c:if test="${trip.id eq detail.tripId}">selected</c:if>
                    >${trip.destination}</option>
                </c:forEach>
            </select>
            <div id="error-${BookingOfficeMeta.TRIP_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${BookingOfficeMeta.PHONE.fieldName}-row" func="checkPhone">
        <td class="required">Phone number</td>
        <td>
            <input name="${BookingOfficeMeta.PHONE.fieldName}" type="text" placeholder="Enter phone number" value="${detail.phone}" maxlength="20">
            <div id="error-${BookingOfficeMeta.PHONE.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${BookingOfficeMeta.PHONE.fieldName}-2" class="error hidden">Incorrect format</div>
        </td>
    </tr>
    <tr id="${BookingOfficeMeta.PLACE.fieldName}-row" func="checkNull">
        <td class="required">Place</td>
        <td>
            <input name="${BookingOfficeMeta.PLACE.fieldName}" type="text" placeholder="Enter place" value="${detail.place}" maxlength="50">
            <div id="error-${BookingOfficeMeta.PLACE.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${BookingOfficeMeta.PRICE.fieldName}-row" func="checkNum">
        <td class="required">Price</td>
        <td>
            <input name="${BookingOfficeMeta.PRICE.fieldName}" type="number" placeholder="Enter price" value="${detail.price}" min="0">
            <div id="error-${BookingOfficeMeta.PRICE.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${BookingOfficeMeta.PRICE.fieldName}-2" class="error hidden">Price >= 0</div>

        </td>
    </tr>
    <tr id="${BookingOfficeMeta.START_CONTRACT.fieldName}-row" func="checkNull">
        <td class="required">Begin contract</td>
        <td>
            <input name="${BookingOfficeMeta.START_CONTRACT.fieldName}" type="date" value="${detail.startContract}">
            <div id="error-${BookingOfficeMeta.START_CONTRACT.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${BookingOfficeMeta.END_CONTRACT.fieldName}-row" func="checkNull">
        <td class="required">End contract</td>
        <td>
            <input name="${BookingOfficeMeta.END_CONTRACT.fieldName}" type="date" value="${detail.endContract}">
            <div id="error-${BookingOfficeMeta.END_CONTRACT.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${BookingOfficeMeta.END_CONTRACT.fieldName}-2" class="error hidden">End contract date must be after begin contract date.</div>
        </td>
    </tr>
    </tbody>
</table>