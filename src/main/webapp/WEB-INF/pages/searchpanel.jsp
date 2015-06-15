<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/12/15
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row well">

    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Search
    </button>

    <h1> Search room for reservation</h1>

    <div class="collapse" id="collapseExample">
            <h1> Search room for reservation </h1>

            <form:form action="/rooms/search" method="POST" class="form-horizontal" modelAttribute="searchForm" commandName="searchForm">
                <label for="group-calendar">Choose period</label>
                <div id="group-calendar" class="form-group-lg">
                    <label>
                        Start date: <form:input path="startDate" type="date" id="SnapHost_Calendar2"/>
                    </label>
                     -
                    <label>
                        edn date: <form:input path="endDate" type="date" name="endDate"/>
                    </label>
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
