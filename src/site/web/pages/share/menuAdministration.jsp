<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 12.02.2012
  Time: 4:25:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="lMenu">
    <div class="top"></div>
    <div class="boxMenu">
        <h3>Администрирование</h3>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td><a href="<%=request.getContextPath()%>/mon/direction.htm">Мониторинг</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/upload/uploadFile.htm#upload">Загрузка</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/edit/searchForEditFile.htm">Корректировка</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/3932/direction.htm">Формирование 3932</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/nsiSegment/direction.htm">НСИ по станциям</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/cashiers/view.htm">Разъездные кассиры</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/pages/testVaadin.jsp">Тест вадин</a></td>
            </tr>
        </table>
    </div>
    <div class="bottom"></div>
</div>