<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/23/21
  Time: 2:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div id="edit-view" class="view">
    <div class="bar-action">
        <span class="bar-title">Parking lot detail</span>
    </div>

    <form id="edit-form">
        <table>
            <tbody>
            <tr>
                <td class="required">ID</td>
                <td><div>${detail.id}</div></td>
            </tr>
            <tr>
                <td class="required">Name</td>
                <td><input type="text" value="${detail.name}"></td>
            </tr>
            <tr>
                <td class="required">Place</td>
                <td>
                    <select>
                        <option disabled selected>Select place</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="required">Area</td>
                <td><input type="text" value="${detail.area}"></td>
            </tr>
            <tr>
                <td class="required">Price</td>
                <td><input type="text" value="${detail.price}"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <button type="reset" id="reset-form-button" class="icon-button align-left">
                <i class="material-icons-round">restart_alt</i>
                <span class="button-label">Reset</span>
            </button>
            <button type="button" id="submit-form-button" class="icon-button align-right" onclick="submitForm()">
                <i class="material-icons-round">login</i>
                <span class="button-label">Submit</span>
            </button>
        </div>
    </form>
</div>