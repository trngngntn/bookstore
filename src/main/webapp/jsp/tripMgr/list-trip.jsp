<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div>
        <table>
            <thead>
            <tr>
                <th>No</th>
                <th>Destination</th>
                <th>Departure time</th>
                <th>Driver</th>
                <th>Car type</th>
                <th>Booked ticket</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="item">
                <tr tabindex="0"  onclick="changePage('<%= request.getContextPath()%>/trip/${item.id}')">
                    <td>${item.id}</td>
                    <td>${item.destination}</td>
                    <td>${item.departureTime}</td>
                    <td>${item.driver}</td>
                    <td>${item.carType}</td>
                    <td>${item.bookedTicket}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common-pagination.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="../common/common-form.jsp" />