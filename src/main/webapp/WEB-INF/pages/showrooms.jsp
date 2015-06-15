<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/12/15
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all room</title>
    <jsp:include page="globalResource.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>

    <jsp:include page="searchpanel.jsp"/>

    <div class="row">
        <div class="col-lg-2">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/" class="btn btn-primary btn-default"> Main page</a></li>
            </ul>

            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="/rooms" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-book"></span> Viewing rooms</a></li>
                <li role="presentation"><a href="/rooms/new" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-plus"></span> Add room</a></li>
            </ul>
        </div>

        <div class="col-lg-9 col-lg-offset-1">
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
                            <form action="/reserve/${room.id}" method="POST" class="form-horizontal">

                                <label for="SnapHost_Calendar2">Choose period</label>
                                <input type="date" name="startDate" id="SnapHost_Calendar2"/> -
                                <input type="date" name="endDate"/>

                                <input type="submit"
                                       value="reservation"
                                       class="btn btn-info pull-right"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>

    <jsp:include page="footer.jsp"/>
</div>


</body>
</html>
