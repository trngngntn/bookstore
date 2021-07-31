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
            <td class="required">ID</td>
            <td>
                <div>${detail.id}</div>
            </td>
        </tr>
    </c:if>
    <tr>
        <td class="required">Name</td>
        <td><input name="${ParkingLotMeta.NAME.fieldName}" type="text" value="${detail.name}"
                   placeholder="Enter parking lot name"></td>
    </tr>
    <tr>
        <td class="required">Place</td>
        <td>
            <select name="${ParkingLotMeta.PLACE_ID.fieldName}">
                <option disabled selected>Select place</option>
                <c:forEach items="${placeList}" var="place">
                    <option value="${place.id}"
                            <c:if test="${place.id eq detail.placeId}">selected</c:if>
                    >${place.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td class="required">Area</td>
        <td>
            <input name="${ParkingLotMeta.AREA.fieldName}" type="text" placeholder="Enter area"
                   value="${detail.area}">
        </td>
    </tr>
    <tr>
        <td class="required">Price</td>
        <td>
            <input name="${ParkingLotMeta.PRICE.fieldName}" type="text" placeholder="Enter price"
                   value="${detail.price}">
        </td>
    </tr>
    </tbody>
</table>
