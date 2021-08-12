<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.ResultFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <c:set scope="request" var="placeholder" value="Search by name"/>
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div class="hidden" id="filter">
        <form id="filter-form">
            <br>
            <label id="filter-form-label"> Advanced filters: </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.ADDRESS.label}')"
                       name="${ResultFilter.ADDRESS.label}-check" ${resultFilters.contains(ResultFilter.ADDRESS)?"checked":""}>
                <span class="filter-name">${ResultFilter.ADDRESS.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.ADDRESS.label}"
                       id="${ResultFilter.ADDRESS.label}-input" placeholder="Enter address"
                       value="${resultFilters.contains(ResultFilter.ADDRESS)?keywords[resultFilters.indexOf(ResultFilter.ADDRESS)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.PHONE.label}')"
                       name="${ResultFilter.PHONE.label}-check" ${resultFilters.contains(ResultFilter.PHONE)?"checked":""}>
                <span class="filter-name">${ResultFilter.PHONE.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.PHONE.label}"
                       id="${ResultFilter.PHONE.label}-input" placeholder="Enter phone"
                       value="${resultFilters.contains(ResultFilter.PHONE)?keywords[resultFilters.indexOf(ResultFilter.PHONE)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.DEPARTMENT.label}')"
                       name="${ResultFilter.DEPARTMENT.label}-check" ${resultFilters.contains(ResultFilter.DEPARTMENT)?"checked":""}>
                <span class="filter-name">${ResultFilter.DEPARTMENT.display}</span>
                <select id="${ResultFilter.DEPARTMENT.label}-input" name="${ResultFilter.DEPARTMENT.label}"
                        class="filter-value">
                    <option disabled selected></option>
                    <option value="1"
                    ${resultFilters.contains(ResultFilter.DEPARTMENT) && keywords[resultFilters.indexOf(ResultFilter.DEPARTMENT)].equals("1")?"selected":""}>
                        Employee
                    </option>
                    <option value="2"
                    ${resultFilters.contains(ResultFilter.DEPARTMENT) && keywords[resultFilters.indexOf(ResultFilter.DEPARTMENT)].equals("2")?"selected":""}>
                        Parking
                    </option>
                    <option value="3"
                    ${resultFilters.contains(ResultFilter.DEPARTMENT) && keywords[resultFilters.indexOf(ResultFilter.DEPARTMENT)].equals("3")?"selected":""}>
                        Service
                    </option>
                </select>
            </label>
        </form>
    </div>
    <div>
        <c:if test="${empty resultList.size()}">
            <span class="no-match">No matches</span>
        </c:if>
        <c:if test="${resultList.size() > 0}">
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
        </c:if>
    </div>
</div>
<jsp:include page="../common/common-form.jsp"/>
