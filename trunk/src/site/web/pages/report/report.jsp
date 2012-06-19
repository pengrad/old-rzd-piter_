<%@ page import="utils.Helper" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="utils.Utils" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="controllers.CReport" %>
<%@ page import="objects.table.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Р§РµСЂРЅС‹С…Р•Рђ
  Date: 07.04.11
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Report report = (Report) request.getAttribute("report");
    Integer repKey = (Integer) request.getAttribute("repKey");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>
        <%--<%=report.getTables().get(0).getCaption().get(0).getName()%>--%>
    </title>
    <meta http-equiv="pragma" content="no-cache">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"
          media="screen, projection"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/report.css" type="text/css"
          media="screen, projection"/>
    <script type="text/javascript" lang="javascript"
            src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript">
        <%
        for(int f=0;f<report.getTables().size();f++){
        %>
        function v_<%=f%>() {
        <%
        for(int v=0;v<report.getTables().size();v++){
        %>
            var tab<%=v%> = $("#tab<%=v%>");
        <%if(f==v){%>
            $("#v<%=v%>_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
            $($("#v<%=v%>_l").parents()[0]).next().attr('class', 'selected');
            $("#v<%=v%>_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');
            tab<%=v%>.show();
        <%}else{%>
            $("#v<%=v%>_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
            $($("#v<%=v%>_l").parents()[0]).next().attr('class', 'unselected');
            $("#v<%=v%>_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');
            tab<%=v%>.hide();
        <%}%>
        <%}%>
        }
        <%}%>
    </script>
</head>

<body style="background: white!important;">
<div id="wrapper" style="min-width:<%=getWidth(report.getTables().get(0))+50%>px;">
    <div id="export">
        <%--<a href="<%=request.getContextPath()%>/controller/cReport.htm?action=<%=CReport.EXPORT_XML%>&repKey=<%=repKey%>">Экспорт--%>
        <%--в XML</a>--%>
        <%--<a href="<%=request.getContextPath()%>/controller/cReport.htm?action=<%=CReport.EXPORT_EXCEL%>&repKey=<%=repKey%>">Экспорт--%>
        <%--в Excel</a>--%>
    </div>
    <div class="tabs">
        <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
            <tr>
                <%for (int t = 0; t < report.getTables().size(); t++) {%>
                <%if (t == 0) {%>
                <td class="between"></td>
                <td><img id="v<%=t%>_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                <td class="selected" onclick="v_<%=t%>()">
                    <span><%=(report.getTables().get(t).getCaption().get(0).getName().length() > 30 ? report.getTables().get(t).getCaption().get(0).getName().substring(0, 30) + "..." : report.getTables().get(t).getCaption().get(0).getName())%></span>
                </td>
                <td><img id="v<%=t%>_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
                <%} else {%>
                <td class="between"></td>
                <td><img id="v<%=t%>_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                <td class="unselected" onclick="v_<%=t%>()">
                    <span><%=(report.getTables().get(t).getCaption().get(0).getName().length() > 30 ? report.getTables().get(t).getCaption().get(0).getName().substring(0, 30) + "..." : report.getTables().get(t).getCaption().get(0).getName())%></span>
                </td>
                <td style="margin-left:10px;">
                    <img id="v<%=t%>_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                </td>
                <%}%>
                <%}%>
            </tr>
        </table>
    </div>
    <div style="text-align:center;">
        <%for (int t = 0; t < report.getTables().size(); t++) {%>
        <div class="tTable" style="width:<%=getWidth(report.getTables().get(t))+15%>px;margin:0 auto">
            <div id="tab<%=t%>" style="<%=(t==0?"":"display:none")%>">
                <div class="header2">
                    <div>
                        <%for (Caption caption : report.getTables().get(t).getCaption()) {%>
                        <%if (caption.getStyle() == Caption.H1) {%>
                        <span class="repCaptionH1"><%=caption.getName().replace(" ", "&nbsp;")%></span>
                        <%}%>
                        <%if (caption.getStyle() == Caption.H2) {%>
                        <span class="repCaptionH2"><%=caption.getName().replace(" ", "&nbsp;")%></span>
                        <%}%>
                        <%if (caption.getStyle() == Caption.H3) {%>
                        <span class="repCaptionH3"><%=caption.getName().replace(" ", "&nbsp;")%></span>
                        <%}%>
                        <br>
                        <%}%>
                    </div>
                </div>
                <div style="width:100%;">
                    <div style="width:45%;text-align:left;font-size:10pt;float:left;">
                        &nbsp;&nbsp;&nbsp;<%=report.getTables().get(t).getTableName()%>
                    </div>
                    <div style="width:45%;text-align:right;font-size:10pt;float:right;"><%=(report.getTables().get(t).getPageName())%>
                        &nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <table width="<%=getWidth(report.getTables().get(t))%>px;">
                    <%
                        int r = 0;
                        int c = 0;
                    %>
                    <%
                        for (Row row : report.getTables().get(t).getHeader().getRows()) {
                            c = 0;
                    %>
                    <tr>
                        <%for (Cell cell : row.getCells()) {%>
                        <th width="<%=cell.getWidthCell()%>px" class="head" rowspan="<%=cell.getrSpan()%>"
                            colspan="<%=cell.getcSpan()%>"><%=cell.getValue()%>
                        </th>
                        <%
                                c++;
                            }
                        %>
                    </tr>
                    <%
                            r++;
                        }
                    %>
                    <%for (Row row : report.getTables().get(t).getData()) {%>
                    <tr>
                        <%for (int i = 0; i < row.getCells().size(); i++) {%>
                        <td class="<%=getClass(row.getCells().get(i))%>"
                            rowspan="<%=row.getCells().get(i).getrSpan()%>"
                            colspan="<%=row.getCells().get(i).getcSpan()%>">
                            <%if (row.getCells().get(i).getHref() != null) {%>
                            <%if (row.getCells().get(i).getHref().getWindowsOpen() == Href.WIN_OPEN_THIS) {%>
                            <a href="<%=request.getContextPath()%><%=row.getCells().get(i).getHref().getHref()%>"><%=Utils.getFormatValueForHtml(row.getCells().get(i))%>
                            </a>
                            <%} else {%>
                            <a href="#"
                               onclick="openReport('<%=request.getContextPath()%><%=row.getCells().get(i).getHref().getHref()%>')"><%=Utils.getFormatValueForHtml(row.getCells().get(i))%>
                            </a>
                            <%}%>
                            <%} else {%>
                            <%=Utils.getFormatValueForHtml(row.getCells().get(i))%>
                            <%}%>
                        </td>
                        <%}%>
                    </tr>
                    <%}%>
                </table>
                <br/><br/>
            </div>
        </div>
            <%
                }
            %>
    </div>
</div>
</body>
</html>

<%!
    private int getWidth(Table table) {
        int width = 0;
        for (int i = 0; i < table.getHeader().getRows().get(0).getCells().size(); i++) {
            width = width + table.getHeader().getRows().get(0).getCells().get(i).getWidthCell();
        }
        return width;
    }

    private String getClass(Cell cell) {
        if (cell.getStyleCell() == Cell.styleResFirstLear) {
            return "firsLevel";
        } else if (cell.getStyleCell() == Cell.styleResSecondLear) {
            return "secondLevel";
        } else if (cell.getStyleCell() == Cell.styleHeader) {
            return "head";
        } else if (cell.getStyleCell() == Cell.styleSubLear) {
            return "subLevel";
        } else return "defaultCell";
    }
%>