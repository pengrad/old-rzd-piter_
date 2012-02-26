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
                month: "Декабрь",
                n1: 10,
                n2: 2.5,
                n3: 2.1,
                n4: 0.3,
                n5: 0.2,
                n6: 0.1,
                n7: 0.8

            },
            {
                month: "Январь",
                n1: 2.6,
                n2: 2.7,
                n3: 2.2,
                n4: 0.3,
                n5: 0.3,
                n6: 0.1,
                n7: 0.8
            },
            {
                month: "Феврль",
                n1: 2.8,
                n2: 2.9,
                n3: 2.4,
                n4: 0.3,
                n5: 0.3,
                n6: 0.1,
                n7: 2.2
            }
        ];

        AmCharts.ready(function () {

            // SERIAL CHART
            chart = new AmCharts.AmSerialChart();
            chart.dataProvider = chartData;
            chart.categoryField = "month";
            chart.plotAreaBorderAlpha = 0.2;


            chart.depth3D = 25;
            chart.angle = 30;

            // AXES
            // category
            var categoryAxis = chart.categoryAxis;
            categoryAxis.gridAlpha = 0.1;
            categoryAxis.axisAlpha = 0;
            categoryAxis.gridPosition = "start";

            // value
            var valueAxis = new AmCharts.ValueAxis();
            valueAxis.stackType = "regular";
            valueAxis.gridAlpha = 0.1;
            valueAxis.axisAlpha = 0;
            chart.addValueAxis(valueAxis);

            // GRAPHS
            // first graph
            var graph = new AmCharts.AmGraph();
            graph.title = "Московское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n1";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#C72C95";
            chart.addGraph(graph);

            // second graph
            graph = new AmCharts.AmGraph();
            graph.title = "Финляндское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n2";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#D8E0BD";
            chart.addGraph(graph);

            // third graph
            graph = new AmCharts.AmGraph();
            graph.title = "Волховское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n3";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#B3DBD4";
            chart.addGraph(graph);

            // fourth graph
            graph = new AmCharts.AmGraph();
            graph.title = "Витебское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n4";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#69A55C";
            chart.addGraph(graph);

            // fifth graph
            graph = new AmCharts.AmGraph();
            graph.title = "Балтийское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n5";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#B5B8D3";
            chart.addGraph(graph);

            // sixth graph
            graph = new AmCharts.AmGraph();
            graph.title = "Псковское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n6";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#F4E23B";
            chart.addGraph(graph);

              // sixth graph
            graph = new AmCharts.AmGraph();
            graph.title = "Петрозаводское направление";
            graph.labelText = "[[value]]";
            graph.valueField = "n7";
            graph.type = "column";
            graph.lineAlpha = 0;
            graph.fillAlphas = 1;
            graph.lineColor = "#F4E23C";
            chart.addGraph(graph);

            // LEGEND
            var legend = new AmCharts.AmLegend();
            legend.borderAlpha = 0.2;
            legend.horizontalGap = 10;
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
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Пассажиропоток по направлениям</a>
        </span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
      Объем перевозок пассажиров по направлениям<br>
        <span style="font-size:10pt">в тыс.шт</span>
    </div>
    <div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>