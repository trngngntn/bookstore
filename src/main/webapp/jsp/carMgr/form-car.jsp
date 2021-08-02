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
    <c:if test="${empty editing}">
        <tr  id="${CarMeta.LICENSE_PLATE.fieldName}-row" func="checkNull">
            <td class="required">License plate</td>
            <td>
                <input name="${CarMeta.LICENSE_PLATE.fieldName}" type="text" placeholder="Enter license plate"
                       maxlength="20">
                <div id="error-${CarMeta.LICENSE_PLATE.fieldName}-1" class="error hidden">This field is required</div>
            </td>
        </tr>
    </c:if>
    <c:if test="${not empty editing}">
        <tr>
            <td>License plate</td>
            <td>
                <div>${detail.licensePlate}</div>
            </td>
        </tr>
    </c:if>
    <tr id="${CarMeta.TYPE.fieldName}-row" func="">
        <td>Type</td>
        <td><input name="${CarMeta.TYPE.fieldName}" type="text" placeholder="Enter car's type" value="${detail.type}"
                   maxlength="20"></td>
    </tr>
    <tr id="${CarMeta.COLOR.fieldName}-row" func="">
        <td>Color</td>
        <td><input name="${CarMeta.COLOR.fieldName}" type="text" placeholder="Enter color" value="${detail.color}"
                   maxlength="20"></td>
    </tr>
    <tr id="${CarMeta.OFFICE_ID.fieldName}-row" func="checkNull">
        <td class="required">Company</td>
        <td>
            <select name="${CarMeta.OFFICE_ID.fieldName}">
                <option selected value="">Select company</option>
                <c:forEach items="${officeList}" var="office">
                    <option value="${office.id}"
                            <c:if test="${office.id eq detail.officeId}">selected</c:if>
                    >${office.name}</option>
                </c:forEach>
            </select>
            <div id="error-${CarMeta.OFFICE_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${CarMeta.PARKING_LOT_ID.fieldName}-row" func="checkNull">
        <td class="required">Parking lot</td>
        <td>
            <select name="${CarMeta.PARKING_LOT_ID.fieldName}">
                <option selected value="">Select parking lot</option>
                <c:forEach items="${parkingLotListBlank}" var="parkingLot">
                    <option value="${parkingLot.id}"
                            <c:if test="${parkingLot.id eq detail.parkingLotId}">selected</c:if>
                    >${parkingLot.name}</option>
                </c:forEach>
            </select>
            <div id="error-${CarMeta.PARKING_LOT_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    </tbody>
</table>