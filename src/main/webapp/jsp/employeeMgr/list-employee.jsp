<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
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
            <tr onclick="changePage('/CPMS/employee/1')">
                <th>ID</th>
                <th>Name</th>
                <th>Date of birth</th>
                <th>Address</th>
                <th>Phone number</th>
                <th>Department</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="item">
                <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/employee/${item.id}', document.title)">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.dobF}</td>
                    <td>${item.address}</td>
                    <td>${item.phone}</td>
                    <td>${item.departmentId}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../common/common--pagination.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="add-employee.jsp" />
