<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/16/15
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-2">
  <nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Best Hotel</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/">Main</a></li>
        <li><a href="/rooms">Viewing rooms</a></li>
        <li><a href="/rooms/new">Add room</a></li>
        <li><a href="/rooms/search">Search</a></li>
        <li><a href="/login">Login</a></li>
        <li><a href="/registration">Registration</a></li>
        <sec:authorize access="isAuthenticated()">
          <li><a href="/j_spring_security_logout">Logout</a></li>
        </sec:authorize>
      </ul>
    </div><!-- /.navbar-collapse -->
  </nav>
</div>
