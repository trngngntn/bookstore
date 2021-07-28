<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 8:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
                <th>No</th>
                <th>Trip</th>
                <th>License plate</th>
                <th>Customer</th>
                <th>Booking time</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%--c:forEach items="${resultList}" var="item">
                <tr tabindex="0"  onclick="changePage('<%= request.getContextPath()%>/office/${item.id}', document.title)">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>
                        <c:forEach items="${placeList}" var="place">
                            <c:if test="${item.placeId eq place.id}">${place.name}</c:if>
                        </c:forEach>
                    </td>
                    <td>${item.area}</td>
                    <td>${item.price}</td>
                    <td>
                        <c:if test="${item.status eq true}">Occupied</c:if>
                        <c:if test="${item.status eq false}">Blank</c:if>
                    </td>
                    <td></td>
                </tr>
            </c:forEach--%>
            <tr>
                <td>1</td>
                <td>Trip 1</td>
                <td>00A - 000.00</td>
                <td>Customer name</td>
                <td>00:00</td>
                <td></td>
            </tr>
            <tr>
                <td>1</td>
                <td>Trip 1</td>
                <td>00A - 000.00</td>
                <td>Customer name</td>
                <td>00:00</td>
                <td></td>
            </tr>
            <tr>
                <td>1</td>
                <td>Trip 1</td>
                <td>00A - 000.00</td>
                <td>Customer name</td>
                <td>00:00</td>
                <td></td>
            </tr>
            <tr>
                <td>1</td>
                <td>Trip 1</td>
                <td>00A - 000.00</td>
                <td>Customer name</td>
                <td>00:00</td>
                <td></td>
            </tr>
            <tr>
                <td>1</td>
                <td>Trip 1</td>
                <td>00A - 000.00</td>
                <td>Customer name</td>
                <td>00:00</td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <jsp:include page="../common/common--pagination.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="add-ticket.jsp" />