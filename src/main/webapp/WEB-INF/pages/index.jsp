<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.06.2015
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel</title>
    <jsp:include page="globalResource.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>

    <div class="row well">

        <div class="col-lg-2">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="/" class="btn btn-primary btn-default"> Main page</a></li>
                <li class="divider"></li>
                <li role="presentation"><a href="/rooms" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-book"></span> Viewing rooms</a></li>
                <li role="presentation"><a href="/rooms/new" class="btn btn-primary btn-default"><span
                        class="glyphicon glyphicon-plus"></span> Add room</a></li>
            </ul>
        </div>

        <div class="col-lg-9 col-lg-offset-1">
            <h1>main page best hotel</h1>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>
