<%--
  Created by IntelliJ IDEA.
  User: wallius
  Date: 7/28/21
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page-nav">
    <c:if test="${currentPage gt 1}">
        <span>
            <button id="first-button" class="icon-button" onclick="changePage(`?${prm}index=1`, true)">
                <i class="material-icons-round">first_page</i>
            </button>
            <button id="prev-button" class="icon-button" onclick="changePage(`?${prm}index=${currentPage - 1}`, true)">
                <i class="material-icons-round">navigate_before</i>
            </button>
        </span>
    </c:if>

    <span id="page-nav-entry">
        <c:if test="${currentPage - 4 > 1 and maxPage > 9}">
            <div>. . .</div>
        </c:if>

        <c:if test="${currentPage + 4 > maxPage and maxPage >= 9}">
            <c:forEach begin="${1 > (maxPage - 8)?1:maxPage-8}" end="${currentPage - 5}" var="page">
                <c:if test="${page ne currentPage}">
                    <div class="clickable" onclick="changePage(`?${prm}index=${page}`, true)">${page}</div>
                </c:if>
            </c:forEach>
        </c:if>

        <c:forEach begin="${(currentPage - 4)>1?currentPage - 4:1}" end="${(currentPage + 4)<maxPage?currentPage + 4:maxPage}" var="page">
            <c:if test="${page eq currentPage}">
                <div class="current">${page}</div>
            </c:if>
            <c:if test="${page ne currentPage}">
                <div class="clickable" onclick="changePage(`?${prm}index=${page}`, true)">${page}</div>
            </c:if>
        </c:forEach>

        <c:if test="${currentPage - 4 < 1}">
            <c:forEach begin="${currentPage + 5}" end="${9<maxPage?9:maxPage}" var="page">
                <c:if test="${page ne currentPage}">
                    <div class="clickable" onclick="changePage(`?${prm}index=${page}`, true)">${page}</div>
                </c:if>
            </c:forEach>
        </c:if>


        <c:if test="${currentPage + 4 < maxPage and maxPage > 9}">
            <div>. . .</div>
        </c:if>
    </span>

    <c:if test="${currentPage lt maxPage}">
        <span>
            <button id="next-button" class="icon-button" onclick="changePage(`?${prm}index=${currentPage + 1}`, true)">
                <i class="material-icons-round">navigate_next</i>
            </button>
            <button id="last-button" class="icon-button" onclick="changePage(`?${prm}index=${maxPage}`, true)">
                <i class="material-icons-round">last_page</i>
            </button>
        </span>
    </c:if>

</div>
