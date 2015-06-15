<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/12/15
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new hotel room</title>
    <jsp:include page="globalResource.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>

    <div class="row">
        <div class="col-lg-2">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/" class="btn btn-primary btn-default"> Main page</a></li>
            </ul>

            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="/rooms" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-book"></span> Viewing rooms</a></li>
                <li role="presentation" class="active"><a href="/rooms/new" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-plus"></span> Add room</a></li>
            </ul>
        </div>

        <div class="col-lg-9 col-lg-offset-1">
            <h1>${textHeader}</h1>

            <form:form action="/rooms" method="POST" id="form" modelAttribute="room" commandName="room" class="form-horizontal">

                <div class="form-group-lg">
                    <label for="type">Select room type </label>

                    <div class="input-group-lg">
                        <form:select path="type" id="type">
                            <form:option value="SGL"/>
                            <form:option value="DBL"/>
                        </form:select>
                    </div>
                </div>

                <div class="form-group-lg">
                    <label for="class">Select room type </label>

                    <div class="input-group-lg">
                        <form:select path="classRoom" id="class">
                            <form:option value="LUX"/>
                            <form:option value="STANDARD"/>
                            <form:option value="ECONOM"/>
                        </form:select>
                    </div>
                </div>

                <input type="submit" value="Add" class="btn btn-info pull-right">
            </form:form>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>
