<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
