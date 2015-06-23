<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/16/15
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/hotel-style.css"/>
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('ul.nav.navbar-nav').find('a[href="' + location.pathname + '"]')
                    .closest('li').addClass('active');
        });
    </script>
</head>
<body>

<div class="container">

    <tiles:insertAttribute name="header"/>

    <div class="row well">
        <sec:authorize access="isAnonymous()">
            <tiles:insertAttribute name="menu"/>
        </sec:authorize>

        <sec:authorize access="isAuthenticated() and hasRole('Admin')">
            <tiles:insertAttribute name="adminMenu"/>
        </sec:authorize>

        <div class="col-lg-9 col-lg-offset-1">
            <tiles:insertAttribute name="search"/>
            <tiles:insertAttribute name="body"/>
        </div>
    </div>

    <tiles:insertAttribute name="footer"/>
</div>

</body>
</html>
