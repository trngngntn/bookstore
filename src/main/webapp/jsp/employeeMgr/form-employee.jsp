<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fa.training.meta.EmployeeMeta" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="request" var="submitFunc" value="submit${EmployeeMeta.getEntityClass().getSimpleName()}"/>
<table>
    <tbody>
    <c:if test="${not empty editing}">
        <tr>
            <td>ID</td>
            <td>
                <div>${detail.id}</div>
            </td>
        </tr>
    </c:if>
    <tr id="${EmployeeMeta.NAME.fieldName}-row" func="checkNull">
        <td class="required">Full name</td>
        <td>
            <input name="${EmployeeMeta.NAME.fieldName}" type="text" placeholder="Enter full name" maxlength="50"
                   oninput="" value="${detail.name}">
            <div id="error-${EmployeeMeta.NAME.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.PHONE.fieldName}-row" func="checkPhone">
        <td class="required">Phone number</td>
        <td>
            <input name="${EmployeeMeta.PHONE.fieldName}" type="text" placeholder="Enter phone number" maxlength="20"
                   oninput="" value="${detail.phone}">
            <div id="error-${EmployeeMeta.PHONE.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.PHONE.fieldName}-2" class="error hidden">Incorrect format</div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.DOB.fieldName}-row" func="checkDOB">
        <td class=" required">Date of birth
        </td>
        <td>
            <input name="${EmployeeMeta.DOB.fieldName}" type="date" placeholder="Enter date of birth"
                   value="${detail.dob}">
            <div id="error-${EmployeeMeta.DOB.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.DOB.fieldName}-2" class="error hidden">Date of birth must smaller than today
            </div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.SEX.fieldName}-row" func="checkNull">
        <td class="required">Gender</td>
        <td>
            <input class="hidden" type="radio" name="${EmployeeMeta.SEX.fieldName}" value=""
                   <c:if test="${empty editing}">checked</c:if>>
            <label class="radio-btn-cont">
                <input type="radio" name="${EmployeeMeta.SEX.fieldName}" value="0"
                       <c:if test="${detail.sex eq false}">checked</c:if>>
                <div class="radio-btn" tabindex="0">
                    <div class="radio-btn-dot"></div>
                </div>
                <span class="radio-btn-label">Male</span>
            </label>
            <label class="radio-btn-cont">
                <input type="radio" name="${EmployeeMeta.SEX.fieldName}" value="1"
                       <c:if test="${detail.sex eq true}">checked</c:if>>
                <div class="radio-btn" tabindex="0">
                    <div class="radio-btn-dot"></div>
                </div>
                <span class="radio-btn-label">Female</span>
            </label>
            <div class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.SEX.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.ADDRESS.fieldName}-row" func="">
        <td>Address</td>
        <td>
            <input name="${EmployeeMeta.ADDRESS.fieldName}" type="text" placeholder="Enter address" maxlength="100"
                   value="${detail.address}">
        </td>
    </tr>
    <tr id="${EmployeeMeta.EMAIL.fieldName}-row" func="checkEmail">
        <td>E-mail</td>
        <td>
            <input name="${EmployeeMeta.EMAIL.fieldName}" type="text" placeholder="Enter e-mail" maxlength="128"
                   value="${detail.email}">
            <div id="error-${EmployeeMeta.EMAIL.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.EMAIL.fieldName}-2" class="error hidden">Incorrect format</div>
            <div id="error-${EmployeeMeta.EMAIL.fieldName}-3" class="error hidden">E-mail is already in use</div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.ACCOUNT.fieldName}-row" func="checkAccount">
        <td class="required">Account</td>
        <td>
            <input name="${EmployeeMeta.ACCOUNT.fieldName}" type="text" placeholder="Enter account" maxlength="50"
                   value="${detail.account}">
            <div id="error-${EmployeeMeta.ACCOUNT.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.ACCOUNT.fieldName}-2" class="error hidden">Account is already existed</div>
            <div id="error-${EmployeeMeta.ACCOUNT.fieldName}-3" class="error hidden">Forbidden value</div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.PASSWORD.fieldName}-row" func="checkPassword">
        <td class="required">Password</td>
        <td>
            <input name="password" type="password"
                   <c:if test="${not empty editing}">placeholder="Unchanged"</c:if>
                   <c:if test="${empty editing}">placeholder="Enter password"</c:if>
            >
            <div id="error-${EmployeeMeta.PASSWORD.fieldName}-1" class="error hidden">This field is required</div>
            <div id="error-${EmployeeMeta.PASSWORD.fieldName}-2" class="error hidden">Password must contain at least 6
                characters, including at least one number and includes both lower and uppercase letters
            </div>
        </td>
    </tr>
    <tr id="${EmployeeMeta.DEPARTMENT_ID.fieldName}-row" func="checkNull">
        <td class="required">Department</td>
        <td>
            <select name="${EmployeeMeta.DEPARTMENT_ID.fieldName}">
                <option selected value="">Select department</option>
                <c:forEach items="${departmentList}" var="department">
                    <option value="${department.id}"
                            <c:if test="${department.id eq detail.departmentId}">selected</c:if>
                    >${department.name}</option>
                </c:forEach>
            </select>
            <div id="error-${EmployeeMeta.DEPARTMENT_ID.fieldName}-1" class="error hidden">This field is required</div>
        </td>
    </tr>
    </tbody>
</table>