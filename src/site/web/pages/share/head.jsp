<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 11.02.2012
  Time: 20:21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<SCRIPT type="text/javascript">
    $(document).ready(function() {
        if ($.browser.msie && $.browser.version == 6) {
            $("body").empty();
            $("body").append("<div style='padding: 20px;text-align: center;font-size: 20pt;color: gray;'>Уважаемый пользователь, Вы пользуетесь устаревшей версией интернет обозравателя (Internet Explorer 6.0) для корректной работы АРМа обновите версию браузера, или используйте алтернативные интернет обозреватели</div>")
        }
    });
</SCRIPT>

<div id="ja-wrapper1">
    <div id="ja-wrapper2">
        <div id="ja-wrapper3">
            <div id="ja-wrapper4" class="clearfix">
                <div id="content">
                    <div id="header">
                        <div style="height:12px;text-align:right;padding-right:10px;position:relative;top:0;z-index:3;">
                            <a style="font-size:8pt;" href="<%=request.getContextPath()%>/logout.htm">Сменить
                                пользователя</a>
                        </div>
                        <div class="hMenu">
                            <div>
                                <ul>
                                    <li class="first"><a href="<%=request.getContextPath()%>/3932/direction.htm">Администрирование</a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/pages/report/graphics/gr1.jsp">Отчетность</a>
                                    </li>
                                    <li><a href="#">Рабочая область</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div id="tBody">
                        <div id="body">
