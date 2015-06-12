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

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row well">
        <div class="col-lg-2">
            <a href="#" class="btn btn-primary btn-default"><span class="glyphicon glyphicon-book"></span> Viewing rooms</a>
        </div>

        <div class="right col-lg-2">
            <a href="#" class="btn btn-primary btn-default"><span class="glyphicon glyphicon-plus"></span> Add room</a>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>
