<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.utils.ResultFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="list-view" class="view">
    <jsp:include page="../common/common-bar-action.jsp"/>
    <div class="hidden" id="filter">
        <form id="filter-form">
            <br>
            <label id="filter-form-label"> Advanced filters: </label>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.TYPE.label}')"
                       name="${ResultFilter.TYPE.label}-check" ${resultFilters.contains(ResultFilter.TYPE)?"checked":""}>
                <span class="filter-name">${ResultFilter.TYPE.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.TYPE.label}"
                       id="${ResultFilter.TYPE.label}-input" placeholder="Enter type"
                       value="${resultFilters.contains(ResultFilter.TYPE)?keywords[resultFilters.indexOf(ResultFilter.TYPE)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.COLOR.label}')"
                       name="${ResultFilter.COLOR.label}-check" ${resultFilters.contains(ResultFilter.COLOR)?"checked":""}>
                <span class="filter-name">${ResultFilter.COLOR.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.COLOR.label}"
                       id="${ResultFilter.COLOR.label}-input" placeholder="Enter color"
                       value="${resultFilters.contains(ResultFilter.COLOR)?keywords[resultFilters.indexOf(ResultFilter.COLOR)]:""}">
            </label>
            <br>
            <label class="filter-toggle">
                <input class="filter-toggle-box" type="checkbox"
                       onchange="clearInput('${ResultFilter.OFFICE.label}')"
                       name="${ResultFilter.OFFICE.label}-check" ${resultFilters.contains(ResultFilter.OFFICE)?"checked":""}>
                <span class="filter-name">${ResultFilter.OFFICE.display}</span>
                <input class="filter-value" type="text" name="${ResultFilter.OFFICE.label}"
                       id="${ResultFilter.OFFICE.label}-input" placeholder="Enter office name"
                       value="${resultFilters.contains(ResultFilter.OFFICE)?keywords[resultFilters.indexOf(ResultFilter.OFFICE)]:""}">
            </label>
        </form>
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
<jsp:include page="../common/common-form.jsp"/>
