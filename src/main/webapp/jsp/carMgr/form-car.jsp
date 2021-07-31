<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 3:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.CarMeta" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tbody>
    <tr>
        <td class="required">License plate</td>
        <td>
            <c:if test="${empty editing}">
                <input name="${CarMeta.LICENSE_PLATE.fieldName}" type="text" placeholder="Enter license plate">
            </c:if>
            <c:if test="${not empty editing}">
                <div>${detail.licensePlate}</div>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>Type</td>
        <td><input name="${CarMeta.TYPE.fieldName}" type="text" placeholder="Enter car's type" value="${detail.type}"></td>
    </tr>
    <tr>
        <td>Color</td>
        <td><input name="${CarMeta.COLOR.fieldName}" type="text" placeholder="Enter color" value="${detail.color}"></td>
    </tr>
    <tr>
        <td class="required">Company</td>
        <td>
            <select name="${CarMeta.OFFICE_ID.fieldName}">
                <option disabled selected>Select company</option>
                <c:forEach items="${officeList}" var="office">
                    <option value="${office.id}"
                            <c:if test="${office.id eq detail.officeId}">selected</c:if>
                    >${office.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td class="required">Parking lot</td>
        <td>
            <select name="${CarMeta.PARKING_LOT_ID.fieldName}">
                <option disabled selected>Select parking lot</option>
                <c:forEach items="${parkingLotListBlank}" var="parkingLot">
                    <option value="${parkingLot.id}"
                            <c:if test="${parkingLot.id eq detail.parkingLotId}">selected</c:if>
                    >${parkingLot.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    </tbody>
</table>