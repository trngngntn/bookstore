<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <div class="bar-action">
        <button class="icon-button align-left" onclick="toAdd()">
            <i class="material-icons-round">add</i>
            <span class="button-label">Add</span>
        </button>
        <div class="inline-block">
            <input id="search-area" type="text" placeholder="Search">
            <button id="search-button" class="icon-button">
                <i class="material-icons-round">search</i>
                <span class="button-label">Search</span>
            </button>
        </div>
        <select class="align-right">
            <option>Name</option>
        </select>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>License plate</th>
                <th>Type</th>
                <th>Color</th>
                <th>Company</th>
                <th>Parking Lot</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="item">
                <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/car/${item.licensePlate}')">
                    <td>${item.licensePlate}</td>
                    <td>${item.type}</td>
                    <td>${item.color}</td>
                    <td>
                        <c:forEach items="${officeList}" var="office">
                            <c:if test="${item.officeId eq office.id}">${office.name}</c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${parkingLotList}" var="parkingLot">
                            <c:if test="${item.parkingLotId eq parkingLot.id}">${parkingLot.name}</c:if>
                        </c:forEach>
                    </td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common-pagination.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="add-car.jsp"/>
