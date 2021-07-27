<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="add-view" class="view hide-right">
    <div class="bar-action">
        <div id="back-button" class="clickable icon-button align-left"  onclick="backToList()">
            <i class="material-icons-round">arrow_back</i>
            <span class="button-label">Back</span>
        </div>
        <span class="bar-title">Add a new employee</span>
    </div>

    <form>
        <table>
            <tbody>
            <tr>
                <td class="required">Full name</td>
                <td><input type="text" placeholder="Enter full name"></td>
            </tr>
            <tr>
                <td class="required">Phone number</td>
                <td><input type="text" placeholder="Enter phone number"></td>
            </tr>
            <tr>
                <td class="required">Date of birth</td>
                <td><input type="text" placeholder="Enter date of birth"></td>
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
                <td><input type="text" placeholder="Enter address"></td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td><input type="text" placeholder="Enter e-mail"></td>
            </tr>
            <tr>
                <td class="required">Account</td>
                <td><input type="text" placeholder="Enter account"></td>
            </tr>
            <tr>
                <td class="required">Password</td>
                <td><input type="password" placeholder="Enter password"></td>
            </tr>
            <tr>
                <td class="required">Department</td>
                <td>
                    <select>
                        <option disabled selected>Select department</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <div>
            <button id="reset-form-button" class="icon-button align-left">
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