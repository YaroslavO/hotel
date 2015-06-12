<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/12/15
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

  <h1> Search room for reservation </h1>

  <form action="/rooms/search" method="POST" class="form-horizontal">

    <label for="SnapHost_Calendar2">Choose period</label>
    <input type="date" name="startDate" id="SnapHost_Calendar2"/> -
    <input type="date" name="endDate" />

    <div class="form-group-lg">
      <label for="SNG">Single</label>
      <input type="checkbox" id="SNG" name="size">

      <label for="DBL">Gouble</label>
      <input type="checkbox" id="DBL" name="size">
    </div>

    <input type="submit"
           value="Search"
           class="btn btn-info pull-right"/>
  </form>
</div>
