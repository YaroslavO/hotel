<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 21.06.2015
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <form:form action="/registration" method="POST" modelAttribute="user" id="form" commandName="user"
                   class="form">

            <div class="form-group">
                <label for="login">Login </label>
                <form:input type="text" class="form-control" id="login" path="login" name="login"
                            placeholder=""/>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <form:input type="password" class="form-control" id="password" path="password" name="password"
                            placeholder=""/>
            </div>

            <input type="submit" name="submit" id="submit" value="Створити"
                   class="btn-login btn btn-lg btn-primary btn-block">
        </form:form>
    </div>
</div>

