<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div id="add-view" class="view hide-top">
    <div class="bar-action">
        <div id="back-button" class="clickable icon-button align-left"  onclick="backToList()">
            <i class="material-icons-round">arrow_back</i>
            <span class="button-label">Back</span>
        </div>
        <span class="bar-title">Add a new employee</span>
    </div>

    <form id="add-form" action="<%= request.getContextPath()%>/employee">
        <table>
            <tbody>
            <tr>
                <td class="required">Full name</td>
                <td><input name="name" type="text" placeholder="Enter full name"></td>
            </tr>
            <tr>
                <td class="required">Phone number</td>
                <td><input name="phone" type="text" placeholder="Enter phone number"></td>
            </tr>
            <tr>
                <td class="required">Date of birth</td>
                <td><input name="dob" type="date" placeholder="Enter date of birth"></td>
            </tr>
            <tr>
                <td class="required">Sex</td>
                <td>
                    <label class="radio-btn-cont">
                        <input type="radio" name="sex" value="0">
                        <div class="radio-btn">
                            <div class="radio-btn-dot"></div>
                        </div>
                        <span class="radio-btn-label">Male</span>
                    </label>
                    <label class="radio-btn-cont">
                        <input type="radio" name="sex" value="1">
                        <div class="radio-btn">
                            <div class="radio-btn-dot"></div>
                        </div>
                        <span class="radio-btn-label">Female</span>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input name="address" type="text" placeholder="Enter address"></td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td><input name="email" type="text" placeholder="Enter e-mail"></td>
            </tr>
            <tr>
                <td class="required">Account</td>
                <td><input name="account" type="text" placeholder="Enter account"></td>
            </tr>
            <tr>
                <td class="required">Password</td>
                <td><input name="password" type="password" placeholder="Enter password"></td>
            </tr>
            <tr>
                <td class="required">Department</td>
                <td>
                    <select name="depId">
                        <option disabled selected>Select department</option>
                        <c:forEach items="${departmentList}" var="department">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <div>
            <button type="reset" id="reset-form-button" class="icon-button align-left">
                <i class="material-icons-round">restart_alt</i>
                <span class="button-label">Reset</span>
            </button>
            <button type="button" id="submit-form-button" class="icon-button align-right" onclick="submitAddForm()">
                <i class="material-icons-round">login</i>
                <span class="button-label">Submit</span>
            </button>
        </div>
    </form>
</div>