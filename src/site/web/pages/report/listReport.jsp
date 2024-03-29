<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<SegmentInfo> monSegment = (Collection<SegmentInfo>) request.getAttribute("monSegment");
    String linkLevel = (request.getAttribute("linkLevel") != null ? (String) request.getAttribute("linkLevel") : null);
%>
<html>
<head>
    <title>Отчетность</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/amcharts/style.css" type="text/css">
    <script src="<%=request.getContextPath()%>/js/amcharts/amcharts.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/amcharts/raphael.js" type="text/javascript"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<div id="data" style="width:900px!important;">
    <div class="infoPath">
            <span>Отчетность&nbsp;
        </span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
        <table width="100%" border="0" style="border-spacing:20px!important;">
            <tr>
                <td>
                    <a style="font-size:14pt!important;" href="<%=request.getContextPath()%>/pages/report/graphics/gr1.jsp">
                        Доход по группам пассажиров
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a style="font-size:14pt!important;" href="<%=request.getContextPath()%>/pages/report/graphics/gr2.jsp">
                        Пассажиропоток по направлениям
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a style="font-size:14pt!important;" href="<%=request.getContextPath()%>/pages/report/graphics/gr3.jsp">
                        Доходах от перевозок
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a style="font-size:14pt!important;" href="<%=request.getContextPath()%>/pages/report/graphics/gr4.jsp">
                        Соотнесение пассажиропотока и дохода
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a style="font-size:14pt!important;" href="<%=request.getContextPath()%>/report/listReportCashiers.htm">
                        Отчетность по разъездным кассирам
                    </a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div>
    <div id="chartdiv" style="width: 700px; height: 600px;"></div>
    <div style="clear:both;"></div>
</div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>