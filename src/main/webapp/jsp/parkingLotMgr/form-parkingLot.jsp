<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.ParkingLotMeta" %>
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
    <tr id="${ParkingLotMeta.NAME.fieldName}-row" func="checkNull">
        <td class="required">Name</td>
        <td>
            <input name="${ParkingLotMeta.NAME.fieldName}" type="text" value="${detail.name}" maxlength="50"
                   placeholder="Enter parking lot name">
            <div id="error-${ParkingLotMeta.NAME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${ParkingLotMeta.PLACE_ID.fieldName}-row" func="checkNull">
        <td class="required">Place</td>
        <td>
            <select name="${ParkingLotMeta.PLACE_ID.fieldName}">
                <option selected value="">Select place</option>
                <c:forEach items="${placeList}" var="place">
                    <option value="${place.id}"
                            <c:if test="${place.id eq detail.placeId}">selected</c:if>
                    >${place.name}</option>
                </c:forEach>
            </select>
            <div id="error-${ParkingLotMeta.PLACE_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${ParkingLotMeta.AREA.fieldName}-row" func="checkNum">
        <td class="required">Area</td>
        <td>
            <input name="${ParkingLotMeta.AREA.fieldName}" type="text" placeholder="Enter area" min="0"
                   value="${detail.area}">
            <div id="error-${ParkingLotMeta.AREA.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${ParkingLotMeta.AREA.fieldName}-2" class="error hidden">Area >= 0</div>
        </td>
    </tr>
    <tr id="${ParkingLotMeta.PRICE.fieldName}-row" func="checkNum">
        <td class="required">Price</td>
        <td>
            <input name="${ParkingLotMeta.PRICE.fieldName}" type="text" placeholder="Enter price" min="0"
                   value="${detail.price}">
            <div id="error-${ParkingLotMeta.PRICE.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${ParkingLotMeta.PRICE.fieldName}-2" class="error hidden">Price >= 0</div>
        </td>
    </tr>
    </tbody>
</table>
