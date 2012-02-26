<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.MonitoringSegment" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<MonitoringSegment> monSegment = (Collection<MonitoringSegment>) request.getAttribute("monSegment");
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
        var chart;

        var chartData = [
            {
                group: "Платные",
                many: 4252
            },
            {
                group: "Федеральные",
                many: 1882
            },
            {
                group: "Региональные",
                many: 1809
            },
            {
                group: "Учащиеся",
                many: 1322
            },
            {
                group: "Военнослужащие",
                many: 110
            },
            {
                group: "Железнодорожники",
                many: 1114
            }
        ];


        AmCharts.ready(function () {
            // PIE CHART
            chart = new AmCharts.AmPieChart();
            chart.dataProvider = chartData;
            chart.titleField = "group";
            chart.valueField = "many";
            chart
            chart.sequencedAnimation = true;
            chart.startEffect = "elastic";
            chart.innerRadius = "30%";
            chart.startDuration = 2;
            chart.labelRadius = 15;
            // the following two lines makes the chart 3D
            chart.depth3D = 10;
            chart.angle = 15;

            // WRITE
            chart.write("chartdiv");
        });
    </script>

</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuReport.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Отчетность&nbsp;
            <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Доход по группам пассажиров</a>
        </span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
       Доход по группам пассажиров за январь 2012 года
        <%--<span style="font-size:10pt">в тыс.шт</span>--%>
    </div>
    <div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>