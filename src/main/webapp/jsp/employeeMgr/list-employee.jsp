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
    <jsp:include page="../common/common-bar-action.jsp"/>
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
                <tr tabindex="0" onclick="changePage('<%= request.getContextPath()%>/employee/${item.id}')">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.dobF}</td>
                    <td>${item.address}</td>
                    <td>${item.phone}</td>
                    <td>
                        <c:forEach items="${departmentList}" var="department">
                            <c:if test="${department.id eq item.departmentId}">${department.name}</c:if>
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
<jsp:include page="../common/common-form.jsp" />
