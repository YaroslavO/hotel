<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/23/15
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>${textHeader}</h1>

<table class="table">
    <tr>
        <th>id</th>
        <th>type</th>
        <th>class</th>
        <th>action</th>
    </tr>

    <c:forEach var="room" items="${rooms}">
        <tr>
            <td>${room.id}</td>
            <td>${room.type}</td>
            <td>${room.classRoom}</td>
            <td>
                <form action="/reserve/${room.id}" method="get" class="form-horizontal">
                    <input type="submit"
                           value="reservation"
                           class="btn btn-info pull-right"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
