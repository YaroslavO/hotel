<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/23/15
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.css"/>
<script src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.js"></script>
<style>
  .dp-highlight .ui-state-default {
    background: #ffe45c;
    color: #FFF;
  }
</style>
<script type="text/javascript">
  $(document).ready(function () {

    $.datepicker.setDefaults({
      firstDay: 1,
      dateFormat: $.datepicker.W3C
    });

    $("#datepicker").datepicker({
      beforeShowDay: function (date) {
        var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#startDate").val());
        var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#endDate").val());
        return [true, date1 && ((date.getTime() == date1.getTime()) || (date2 && date >= date1 && date <= date2)) ? "dp-highlight" : ""];
      },
      onSelect: function (dateText, inst) {
        var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#startDate").val());
        var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#endDate").val());
        if (!date1 || date2) {
          $("#startDate").val(dateText);
          $("#endDate").val("");
          $(this).datepicker("option", "minDate", dateText);
        } else {
          $("#endDate").val(dateText);
          $(this).datepicker("option", "minDate", null);
        }
      }
    });
  });
</script>

<div class="row">
  <h1 class="page-header"> Reserve room N=${idRoom} </h1>
  <div class="col-lg-6 col-lg-offset-3 well">
    <form method="post" action="<c:url value="/reserve/${idRoom}"/>" class="form">
      <div id="datepicker"></div>
      <div class="form-group">
        <label for="startDate">Start Date:</label>
        <input name="startDate"  type="text" id="startDate" required>
      </div>

      <div class="form-group">
        <label for="endDate">End Date</label>
        <input name="endDate" type="text" id="endDate" required>
      </div>

      <input type="submit" class="btn btn-primary" value="reserve">
    </form>
  </div>
</div>
