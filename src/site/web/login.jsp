<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 06.03.2012
  Time: 21:31:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Вход в систему</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>

</head>
<body style="background:white">
<div style="text-align:center;width:100%;">
<div style="margin:0 auto;height:150px;width:900px;background:url('<%=request.getContextPath()%>/images/header2Login.jpg')"></div>
    <div class="module" style="width:250px;margin:0 auto">
        <div>
            <div>
                <div style="height: 180px; ">
                    <div style="text-align:left;padding:5px">
                      <span style="font-size:14pt;color:red">Вход в систему</span>
                    </div>
                    <div style="text-align:center;">
                        <p>Логин</p>
                        <input type="text" size="20">

                        <p>Пароль</p>
                        <input type="password" size="20">
                        <div style="padding:10px">
                        <input type="button" value="Войти" size="15">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>