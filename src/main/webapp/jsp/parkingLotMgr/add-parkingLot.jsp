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
        <div id="back-button" class="clickable icon-button align-right"  onclick="backToList()">
            <i class="material-icons-round">close</i>
            <span class="button-label">Close</span>
        </div>
        <span class="bar-title">Add a new parking lot</span>
    </div>

    <form>
        <table>
            <tbody>
            <tr>
                <td class="required">Name</td>
                <td><input type="text" placeholder="Enter parking lot name"></td>
            </tr>
            <tr>
                <td class="required">Place</td>
                <td>
                    <select>
                        <option disabled selected>Select place</option>
                        <c:forEach items="${placeList}" var="place">
                            <option value="${placeId}">${place.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="required">Area</td>
                <td><input type="text" placeholder="Enter area"></td>
            </tr>
            <tr>
                <td class="required">Price</td>
                <td><input type="text" placeholder="Enter price"></td>
            </tr>
            </tbody>
        </table>
        <div>
            <button type="reset" id="reset-form-button" class="icon-button align-left">
                <i class="material-icons-round">restart_alt</i>
                <span class="button-label">Reset</span>
            </button>
            <button id="submit-form-button" class="icon-button align-right">
                <i class="material-icons-round">login</i>
                <span class="button-label">Submit</span>
            </button>
        </div>
    </form>
</div>