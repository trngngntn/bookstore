<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 8:05 AM
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
        <span class="bar-title">Add a new ticket</span>
    </div>

    <form>
        <table>
            <tbody>
            <tr>
                <td class="required">Customer</td>
                <td><input type="text" placeholder="Enter customer name"></td>
            </tr>
            <tr>
                <td class="required">Booking time</td>
                <td><input type="time" placeholder="Enter time"></td>
            </tr>
            <tr>
                <td class="required">Trip</td>
                <td>
                    <select>
                        <option disabled selected>Select trip</option>

                    </select>
                </td>
            </tr>
            <tr>
                <td class="required">License plate</td>
                <td>
                    <select>
                        <option disabled selected>Select license plate</option>

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
            <button id="submit-form-button" class="icon-button align-right">
                <i class="material-icons-round">login</i>
                <span class="button-label">Submit</span>
            </button>
        </div>
    </form>
</div>