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
        <h3>Отчетность</h3>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td><a href="<%=request.getContextPath()%>/pages/report/graphics/gr1.jsp">Доход по группам пассажиров</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/pages/report/graphics/gr2.jsp">Пассажиропоток по направлениям</a></td>
            </tr>
            <tr>
                <td><a href="<%=request.getContextPath()%>/pages/report/graphics/gr3.jsp">Доходах от перевозок</a></td>
            </tr>
              <tr>
                <td><a href="<%=request.getContextPath()%>/pages/report/graphics/gr4.jsp">Соотнесение пассажиропотока и дохода</a></td>
            </tr>
            <%--<tr>--%>
                <%--<td><a href="<%=request.getContextPath()%>/addFile/uploadFile.htm">Загрузка</a></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><a href="<%=request.getContextPath()%>/edit/editFile.htm">Корректировка</a></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><a href="<%=request.getContextPath()%>/3932/direction.htm">Формирование 3932</a></td>--%>
            <%--</tr>--%>
        </table>
    </div>
    <div class="bottom"></div>
</div>