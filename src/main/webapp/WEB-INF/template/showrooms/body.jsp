<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/16/15
  Time: 4:20 PM
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
                <form action="/rooms/${room.id}" method="DELETE" class="form-horizontal">
                    <input type="submit"
                           value="delete room"
                           class="btn btn-danger"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

