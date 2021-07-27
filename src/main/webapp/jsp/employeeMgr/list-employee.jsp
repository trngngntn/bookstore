<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/22/21
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--h1>List Employee</h1>
<hr-->
<div id="list-view" class="view">
    <div class="bar-action">
        <button class="icon-button align-left" onclick="toAdd()">
            <i class="material-icons-round">add</i>
            <span class="button-label">Add</span>
        </button>
        <div class="inline-block">
            <input id="search-area" type="text" placeholder="Search">
            <button id="search-button" class="icon-button">
                <i class="material-icons-round">search</i>
                <span class="button-label">Search</span>
            </button>
        </div>
        <select class="align-right">
            <option>Name</option>
        </select>
    </div>
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
            </tr>
            </thead>
            <tbody>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>1</td>
                <td>Nguyen Van A</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            <tr tabindex="0">
                <td>2</td>
                <td>Nguyen Van B</td>
                <td>0000-00-00</td>
                <td>-------------,----------------------</td>
                <td>0123456789</td>
                <td>employee</td>
            </tr>
            </tbody>
        </table>
        <div id="page-nav">
        <span class="align-left">
            <button id="first-button" class="icon-button">
                <i class="material-icons-round">first_page</i>
                <span class="button-label">First</span>
            </button>
            <button id="prev-button" class="icon-button">
                <i class="material-icons-round">navigate_before</i>
                <span class="button-label">Prev</span>
            </button>
        </span>

            <span id="page-nav-entry">
            <div>. . .</div>
            <div class="clickable">1</div>
            <div class="clickable">2</div>
            <div class="clickable">3</div>
            <div class="current">4</div>
            <div class="clickable">5</div>
            <div class="clickable">6</div>
            <div class="clickable">7</div>
            <div class="clickable">8</div>
            <div>. . .</div>
        </span>

            <span class="align-right">
            <button id="next-button" class="icon-button">
                <i class="material-icons-round">navigate_next</i>
                <span class="button-label">Next</span>
            </button>
            <button id="last-button" class="icon-button">
                <i class="material-icons-round">last_page</i>
                <span class="button-label">Last</span>
            </button>
        </span>

        </div>
    </div>
</div>
<jsp:include page="add-employee.jsp" />
