<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <form action="<c:url value="/j_spring_security_check"/>" method="POST">
                            <p>Логин</p>
                            <input style="background:lightgoldenrodyellow" name="j_username"
                                   value="<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>"
                                   type="text" size="20">

                            <p>Пароль</p>
                            <input style="background:lightgoldenrodyellow" name="j_password" type="password" size="20">

                            <div style="color:red;font-size:9pt">
                                <%=(request.getParameter("login_error") != null ? (request.getParameter("login_error").toString().equals("1") ? "Неверный логин или пароль" : "") : "")%>
                            </div>
                            <div style="padding:10px">
                                <input type="submit" value="Войти" size="15">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>