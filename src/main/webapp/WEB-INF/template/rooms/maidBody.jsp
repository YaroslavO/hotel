<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/24/15
  Time: 10:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="page-header">${textHeader}</h1>

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
          <input type="checkbox"
                 value="reservation"
                 class="checkbox" onclick=""/>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
