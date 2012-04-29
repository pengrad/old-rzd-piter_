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
        var chartData = [
            {
                segment: "Московское",
                people: 227,
                many: 408
            },
            {
                segment: "Финляндское",
                people: 371,
                many: 482
            },
            {
                segment: "Волховское",
                people: 433,
                many: 562
            },
            {
                segment: "Витебское",
                people: 345,
                many: 379
            },
            {
                segment: "Балтийское",
                people: 480,
                many: 501
            },
            {
                segment: "Псковское",
                people: 386,
                many: 443
            },
            {
                segment: "Петрозаводское",
                people: 348,
                many: 405
            }
        ];
        var chart;

        AmCharts.ready(function () {
            // SERIAL CHART
            chart = new AmCharts.AmSerialChart();
            chart.dataProvider = chartData;
            chart.categoryField = "segment";
            chart.marginTop = 0;


            // AXES
            // category axis
            var categoryAxis = chart.categoryAxis;
            categoryAxis.autoGridCount = false;
            categoryAxis.gridCount = 50;
            categoryAxis.gridAlpha = 0;
            categoryAxis.gridColor = "#000000";
            categoryAxis.axisColor = "#555555";
            categoryAxis.labelRotation = 45;

            var durationAxis = new AmCharts.ValueAxis();
            durationAxis.title = "Доход тыс.руб";
            durationAxis.gridAlpha = 0.05;
            durationAxis.axisAlpha = 0;
            durationAxis.inside = true;

            chart.addValueAxis(durationAxis);

            // Distance value axis
            var distanceAxis = new AmCharts.ValueAxis();
            distanceAxis.title = "Перевезено тыс.чел";
            distanceAxis.gridAlpha = 0;
            distanceAxis.position = "right";
            distanceAxis.inside = true;
            //                distanceAxis.unit = "mi";
            distanceAxis.axisAlpha = 0;
            chart.addValueAxis(distanceAxis);

            // GRAPHS
            // duration graph
            var durationGraph = new AmCharts.AmGraph();
            durationGraph.title = "Доход тыс.руб";
            durationGraph.valueField = "many";
            durationGraph.type = "line";
            durationGraph.valueAxis = durationAxis; // indicate which axis should be used
            durationGraph.lineColor = "#CC0000";
            durationGraph.balloonText = "[[value]] тыс.руб";
            durationGraph.lineThickness = 1;
            durationGraph.legendValueText = "[[value]] тыс.руб";
            durationGraph.bullet = "square";
            chart.addGraph(durationGraph);

            // distance graph
            var distanceGraph = new AmCharts.AmGraph();
            distanceGraph.valueField = "people";
            distanceGraph.title = "Перевезено тыс.чел";
            distanceGraph.type = "column";
            distanceGraph.fillAlphas = 0.1;
            distanceGraph.valueAxis = distanceAxis; // indicate which axis should be used
            distanceGraph.balloonText = "[[value]] тыс.чел";
            distanceGraph.legendValueText = "[[value]] тыс.чел";
            distanceGraph.lineColor = "#000000";
            distanceGraph.lineAlpha = 0;
            chart.addGraph(distanceGraph);

            // CURSOR
            var chartCursor = new AmCharts.ChartCursor();
            chartCursor.zoomable = false;
            //                chartCursor.categoryBalloonDateFormat = "4444";
            chartCursor.cursorAlpha = 0;
            chart.addChartCursor(chartCursor);

            // LEGEND
            var legend = new AmCharts.AmLegend();
            legend.bulletType = "round";
            legend.equalWidths = false;
            legend.valueWidth = 120;
            legend.color = "#000000";
            chart.addLegend(legend);

            // WRITE
            chart.write("chartdiv")
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
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Соотнесение пассажиропотока и дохода</a>
        </span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
        Соотнесение пассажиропотока и дохода
    </div>
    <div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>