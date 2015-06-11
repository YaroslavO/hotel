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
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/hotel-style.css"/>
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">
      Main page Hotel
    </h1>
  </div>
</div>

<div class="container">
  <div class="row well">
    <div class="col-lg-4 center-block">
      <a href="#" class="btn btn-primary btn-default"><span class="glyphicon glyphicon-book"></span> Viewing rooms</a>
    </div>

    <div class="col-lg-4 center-block">
      <a href="#" class="btn btn-primary btn-default"><span class="glyphicon glyphicon-plus"></span> Add room</a>
    </div>
  </div>
</div>

<footer>
  <div class="container">
    <p class="text-muted">Copyright &copy; Best Hotel</p>
  </div>
</footer>

</body>
</html>
