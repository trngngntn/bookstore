<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 7:52 AM
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
        <span class="bar-title">Add a new trip</span>
    </div>

    <form>
        <table>
            <tbody>
            <tr>
                <td class="required">Destination</td>
                <td><input type="text" placeholder="Enter destination"></td>
            </tr>
            <tr>
                <td class="required">Departure time</td>
                <td><input type="time" placeholder="Enter time"></td>
            </tr>
            <tr>
                <td class="required">Driver</td>
                <td><input type="text" placeholder="Enter driver"></td>
            </tr>
            <tr>
                <td class="required">Car type</td>
                <td><input type="text" placeholder="Enter car type"></td>
            </tr>
            <tr>
                <td class="required">Maximum online ticket</td>
                <td><input type="number" placeholder="Enter number"></td>
            </tr>
            <tr>
                <td class="required">Departure date</td>
                <td><input type="date" placeholder="Enter date"></td>
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