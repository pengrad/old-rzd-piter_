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
                segment: "Московское",
                prevYear: 30962.205,
                plan: 33579.674,
                thisYear: 3357
            },
            {
                segment: "Финляндское",
                prevYear: 36037.897,
                plan: 39059.097,
                thisYear:3905
            },
            {
                segment: "Волховское",
                prevYear: 4866.602,
                plan: 5338.692,
                thisYear:533
            },
            {
                segment: "Витебское",
                prevYear: 23704.598,
                plan: 24818.463,
                thisYear:2481
            },
            {
                segment: "Балтийское",
                prevYear: 35881.398,
                plan: 36369.385,
                thisYear:3636
            },
            {
                segment: "Псковское",
                prevYear: 3127.229,
                plan: 3212.707,
                thisYear:20
            },
            {
                segment: "Петрозаводское",
                prevYear: 813.624,
                plan: 933.045,
                thisYear:93
            }
        ];


        AmCharts.ready(function () {
            // SERIAL CHART
            chart = new AmCharts.AmSerialChart();
            chart.dataProvider = chartData;
            chart.categoryField = "segment";
            chart.startDuration = 1;
            chart.plotAreaBorderColor = "#DADADA";
            chart.plotAreaBorderAlpha = 1;
            // this single line makes the chart a bar chart
            //            chart.rotate = true;
            chart.depth3D = 25;
            chart.angle = 30;

            // AXES
            // Category
            var categoryAxis = chart.categoryAxis;
            categoryAxis.labelRotation = 45;
            categoryAxis.gridPosition = "start";
            categoryAxis.gridAlpha = 0.1;
            categoryAxis.axisAlpha = 0;

            // Value
            var valueAxis = new AmCharts.ValueAxis();
            valueAxis.axisAlpha = 0;
            valueAxis.gridAlpha = 0.1;
            valueAxis.position = "top";
            chart.addValueAxis(valueAxis);

            // GRAPHS
            // first graph
            var graph1 = new AmCharts.AmGraph();
            graph1.type = "column";
            graph1.title = "2011 год";
            graph1.valueField = "prevYear";
            graph1.balloonText = "2011 год:[[value]]";
            graph1.lineAlpha = 0;
            graph1.fillColors = "#B3DBD4";
            graph1.fillAlphas = 1;
            chart.addGraph(graph1);

            // second graph
            var graph2 = new AmCharts.AmGraph();
            graph2.type = "column";
            graph2.title = "План на 2012 год";
            graph2.valueField = "plan";
            graph2.balloonText = "План на 2012 год:[[value]]";
            graph2.lineAlpha = 0;
            graph2.fillColors = "#B5B8D3";
            graph2.fillAlphas = 1;
            chart.addGraph(graph2);

            // second graph
            var graph3 = new AmCharts.AmGraph();
            graph3.type = "column";
            graph3.title = "Факт 2012 год";
            graph3.valueField = "thisYear";
            graph3.balloonText = "Факт 2012 год:[[value]]";
            graph3.lineAlpha = 0;
            graph3.fillColors = "#F4E23B";
            graph3.fillAlphas = 1;
            chart.addGraph(graph3);

            // LEGEND
            var legend = new AmCharts.AmLegend();
            chart.addLegend(legend);

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
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Доходах от перевозок</a>
        </span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
        Доходах от перевозок ВСЕГО<br>
        <span style="font-size:10pt">в тыс.руб</span>
    </div>
    <div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>