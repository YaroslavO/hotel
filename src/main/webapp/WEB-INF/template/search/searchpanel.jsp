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
//    var cb = function (start, end, label) {
//      console.log(start.toISOString(), end.toISOString(), label);
//      $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
//    };
//
//    $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
//
//    $('#reportrange').daterangepicker(optionSet1, cb);
//
//    $('#reportrange').on('show.daterangepicker', function () {
//      console.log("show event fired");
//    });
//
//    $('#reportrange').on('hide.daterangepicker', function () {
//      console.log("hide event fired");
//    });
//
//    $('#reportrange').on('apply.daterangepicker', function (ev, picker) {
//      $.ajax({
//        url: "/foodtable/" + picker.startDate + "/" + picker.endDate,
//        type: "POST",
//        beforeSend: function (xhr) {
//          xhr.setRequestHeader("Accept", "application/json");
//          xhr.setRequestHeader("Content-Type", "application/json");
//        },
//        success: function (suc) {
//          $('#foods').html(suc);
//        },
//        error: function (jqXHR, textStatus, errorThrown) {
//          alert(jqXHR.status + ' ' + jqXHR.responseText);
//        }
//      });
//      console.log("apply event fired, start/end dates are "
//              + picker.startDate.format('MMMM D, YYYY')
//              + " to "
//              + picker.endDate.format('MMMM D, YYYY')
//      );
//    });
//
//    $('#reportrange').on('cancel.daterangepicker', function (ev, picker) {
//      console.log("cancel event fired");
//    });
//
//    var optionSet1 = {
//      startDate: moment(),
//      endDate: moment(),
//      minDate: '01/01/2012',
//      maxDate: '12/31/2026',
//      dateLimit: {days: 60},
//      showDropdowns: true,
//      showWeekNumbers: true,
//      timePicker: false,
//      timePickerIncrement: 1,
//      timePicker12Hour: true,
//      ranges: {
//        'now': [moment(), moment()],
//        'yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
//        'week': [moment().subtract(6, 'days'), moment()],
//        'last 30 days': [moment().subtract(29, 'days'), moment()],
//        'this month': [moment().startOf('month'), moment().endOf('month')],
//        'last month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
//      },
//      opens: 'left',
//      buttonClasses: ['btn btn-default'],
//      applyClass: 'btn-sm btn-primary',
//      cancelClass: 'btn-sm',
//      format: 'YY-MM-DD',
//      separator: ' to ',
//      locale: {
//        applyLabel: 'Submit',
//        cancelLabel: 'Clear',
//        fromLabel: 'From',
//        toLabel: 'To',
//        customRangeLabel: 'select date',
//        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
//        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
//        firstDay: 1
//      }
//    };
//
//    $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);

    var optionSet = {
      dateFormat: $.datepicker.W3C,
      firstDay: 1,
      selectOtherMonths: true,
      showOtherMonths: true,
      showWeek: true,
      rangeSelect: true,
      monthsToShow: 2
    };

    $.datepicker.setDefaults(optionSet);

    $( "#datepicker" ).datepicker();
  });
</script>

<div class="row well">

  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
          aria-expanded="false" aria-controls="collapseExample">
    Search
  </button>

  <h1> Search room for reservation</h1>

  <div class="collapse" id="collapseExample">
    <form:form action="/rooms/search" method="POST" class="form-horizontal" modelAttribute="searchForm" commandName="searchForm">
      <label for="group-calendar">Choose period</label>
      <div id="group-calendar" class="form-group-lg">
          <label>
              Date: <input type="text" id="datepicker">
          </label>
        <form:hidden path="startDate" id="SnapHost_Calendar2"/>
        <form:hidden path="endDate" name="endDate"/>
      </div>

      <label for="size-group">Size hotel room: </label>
      <div id="size-group" class="btn-group" data-toggle="buttons">
        <label class="btn">
          <form:checkbox value="false" path="sizeSGL" autocomplete="off" cssClass="checkbox" /> Single
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
          <form:checkbox path="budgetTypeStandard" value="false" autocomplete="off" cssClass="checkbox"/> Standard
        </label>
      </div>

      <input type="submit"
             value="Search"
             class="btn btn-info pull-right"/>
    </form:form>
  </div>
</div>
