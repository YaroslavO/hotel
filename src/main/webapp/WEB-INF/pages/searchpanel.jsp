<%--
  Created by IntelliJ IDEA.
  User: employee
  Date: 6/12/15
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Search
    </button>

    <div class="collapse" id="collapseExample">
        <div class="well">
            <h1> Search room for reservation </h1>

            <form action="/rooms/search" method="POST" class="form-horizontal">
                <div class="form-group-lg">
                    <label for="SnapHost_Calendar2">Choose period</label>
                    <input type="date" name="startDate" id="SnapHost_Calendar2"/> -
                    <input type="date" name="endDate"/>
                </div>

                <div class="btn-group" data-toggle="buttons">
                    <label class="btn">
                        <input type="checkbox" autocomplete="off" name="SNG"> Single
                    </label>

                    <label class="btn">
                        <input type="checkbox" autocomplete="off" name="DBL"> Double
                    </label>

                </div>

                <div class="btn-group" data-toggle="buttons">
                    <label class="btn">
                        <input type="checkbox" autocomplete="off" name="LUX"> Lux
                    </label>

                    <label class="btn">
                        <input type="checkbox" autocomplete="off" name="ECONOM"> Econom
                    </label>

                    <label class="btn">
                        <input type="checkbox" autocomplete="off" name="STANDARD"> Standard
                    </label>
                </div>

                <input type="submit"
                       value="Search"
                       class="btn btn-info pull-right"/>
            </form>
        </div>
    </div>
</div>
