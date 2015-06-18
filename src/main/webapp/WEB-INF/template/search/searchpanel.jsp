<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/16/15
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.css"/>
<script src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

    //    maybe use

    //    var optionSet = {
    //      dateFormat: $.datepicker.W3C,
    //      firstDay: 1,
    //      selectOtherMonths: true,
    //      showOtherMonths: true,
    //      showWeek: true,
    //      rangeSelect: true,
    //      monthsToShow: 2
    //    };
    //
    //    $.datepicker.setDefaults(optionSet);
    //
    //    $( "#datepicker" ).datepicker();
    //  });
</script>

<script>
    /*
     * jQuery UI Datepicker: Using Datepicker to Select Date Range
     * http://salman-w.blogspot.com/2013/01/jquery-ui-datepicker-examples.html
     */
    $(function () {
        $("#datepicker").datepicker({
            monthsToShow: 2,
            selectOtherMonths: true,
            firstDay: 1,
            dateFormat: $.datepicker.W3C,
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

<div class="row well">

    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Search
    </button>

    <h1> Search room for reservation</h1>

    <div class="collapse" id="collapseExample">
        <form:form action="/rooms/search" method="POST" class="form-horizontal" modelAttribute="searchForm"
                   commandName="searchForm">
            <label for="group-calendar">Choose period</label>

            <div id="group-calendar" class="form-group-lg">
                <div id="datepicker"></div>
                <label>Select date:
                    <form:input type="text" path="startDate" id="startDate"/>
                    <form:input type="text" path="endDate" name="endDate" id="endDate"/>
                </label>
            </div>

            <label for="size-group">Size hotel room: </label>

            <div id="size-group" class="btn-group" data-toggle="buttons">
                <label class="btn">
                    <form:checkbox value="false" path="sizeSGL" autocomplete="off" cssClass="checkbox"/> Single
                </label>

                <label class="btn">
                    <form:checkbox value="false" path="sizeDBL" autocomplete="off" cssClass="checkbox"/> Double
                </label>
            </div>

            <br>
            <label for="type-group">Type hotel room: </label>

            <div id="type-group" class="btn-group" data-toggle="buttons">
                <label class="btn">
                    <form:checkbox value="false" path="budgetTypeLux" autocomplete="off" cssClass="checkbox"/> Lux
                </label>

                <label class="btn">
                    <form:checkbox value="false" path="budgetTypeEconom" autocomplete="off" cssClass="checkbox"/> Econom
                </label>

                <label class="btn">
                    <form:checkbox path="budgetTypeStandard" value="false" autocomplete="off" cssClass="checkbox"/>
                    Standard
                </label>
            </div>

            <input type="submit"
                   value="Search"
                   class="btn btn-info pull-right"/>
        </form:form>
    </div>
</div>
