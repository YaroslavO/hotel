<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 21.06.2015
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <form class="form-signin" id="form" action="/j_spring_security_check" method="POST">
            <label for="us_name">Логін</label>
            <input type="text" id="us_name" class="form-control" placeholder="" name="username" required
                   autofocus></br>
            <label for="us_pass">Пароль</label>
            <input type="password" id="us_pass" name="password" class="form-control" placeholder="" required>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
        </form>
        </br>
        <span style="color:red">${err}</span>
    </div>
</div>
