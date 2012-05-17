<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="utils.Helper" %>
<%@ page import="objects.Link" %>
<%@ page import="objects.Cashiers" %>
<%@ page import="objects.PlanCashiers" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    //Collection<Link> links = (Collection<Link>) request.getAttribute("links");
    //Helper.action action=(Helper.action) request.getAttribute("action");
    Cashiers cashiers = (Cashiers) request.getAttribute("cashiers");
    int year = (Integer) request.getAttribute("year");
    int month = (Integer) request.getAttribute("month");
    SegmentInfo direction = (SegmentInfo) request.getAttribute("direction");
    SegmentInfo sector = (SegmentInfo) request.getAttribute("sector");
%>
<html>
<head>
<title>Разъездные кассиры</title>
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/js/treeview/treeview.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
<link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/segmentSelect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/validationForm.js"></script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/js/amcharts/style.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/amcharts/amcharts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/amcharts/raphael.js" type="text/javascript"></script>

<script type="text/javascript">
    var chartData = [];
    <%--alert(new Date('<%=new SimpleDateFormat("yyyy.MM.dd").format(new Date())%>'));--%>
    <%for(PlanCashiers pc:cashiers.getPlanCashiers()){%>
    chartData.push({
        date: new Date('<%=new SimpleDateFormat("yyyy.MM.dd").format(pc.getDate())%>'),
        visits: <%=pc.getPlanBase()%>,
        hits: <%=pc.getFactBase()%>
    });
    <%}%>
    var chartData2 = [];
    <%for(PlanCashiers pc:cashiers.getPlanCashiers()){%>
    chartData2.push({
        date: new Date('<%=new SimpleDateFormat("yyyy.MM.dd").format(pc.getDate())%>'),
        visits: <%=pc.getPlanRzd()%>,
        hits: <%=pc.getFactRzd()%>
    });
    <%}%>
    AmCharts.ready(function () {
        // SERIAL CHART
        var chart = new AmCharts.AmSerialChart();
        chart.pathToImages = "/lib/samples/javascript/images/";
        chart.panEventsEnabled = true;
        chart.dataProvider = chartData;
        chart.categoryField = "date";
        // AXES
        // category
        var categoryAxis = chart.categoryAxis;
        categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
        categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
        categoryAxis.dashLength = 1;
        categoryAxis.gridAlpha = 0.15;
        categoryAxis.axisColor = "#DADADA";
        // first value axis (on the left)
        var valueAxis1 = new AmCharts.ValueAxis();
        valueAxis1.axisColor = "#FF6600";
        valueAxis1.axisThickness = 2;
        valueAxis1.gridAlpha = 0;
        chart.addValueAxis(valueAxis1);
        // GRAPHS
        // first graph
        var graph1 = new AmCharts.AmGraph();
        graph1.valueAxis = valueAxis1; // we have to indicate which value axis should be used
        graph1.title = "red line";
        graph1.valueField = "visits";
        graph1.bullet = "round";
        graph1.hideBulletsCount = 31;
        chart.addGraph(graph1);

        // second graph
        var graph2 = new AmCharts.AmGraph();
        graph2.valueAxis = valueAxis1; // we have to indicate which value axis should be used
        graph2.title = "red line";
        graph2.valueField = "hits";
        graph2.bullet = "square";
        graph2.hideBulletsCount = 31;
        graph2.color = "#DAAADA";
        chart.addGraph(graph2);
        // CURSOR
        var chartCursor = new AmCharts.ChartCursor();
        chartCursor.cursorPosition = "mouse";
        chart.addChartCursor(chartCursor);
        chart.write("planBase");
    });
    AmCharts.ready(function () {
        // SERIAL CHART
        var chart = new AmCharts.AmSerialChart();
        chart.pathToImages = "/lib/samples/javascript/images/";
        chart.dataProvider = chartData2;
        chart.categoryField = "date";
        // AXES
        // category
        var categoryAxis = chart.categoryAxis;
        categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
        categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
        categoryAxis.dashLength = 1;
        categoryAxis.gridAlpha = 0.15;
        categoryAxis.axisColor = "#DADADA";
        // first value axis (on the left)
        var valueAxis1 = new AmCharts.ValueAxis();
        valueAxis1.axisColor = "#FF6600";
        valueAxis1.axisThickness = 2;
        valueAxis1.gridAlpha = 0;
        chart.addValueAxis(valueAxis1);
        // GRAPHS
        // first graph
        var graph1 = new AmCharts.AmGraph();
        graph1.valueAxis = valueAxis1; // we have to indicate which value axis should be used
        graph1.title = "red line";
        graph1.valueField = "visits";
        graph1.bullet = "round";
        graph1.hideBulletsCount = 31;
        chart.addGraph(graph1);

        // second graph
        var graph2 = new AmCharts.AmGraph();
        graph2.valueAxis = valueAxis1; // we have to indicate which value axis should be used
        graph2.title = "yellow line";
        graph2.valueField = "hits";
        graph2.bullet = "square";
        graph2.hideBulletsCount = 31;
        chart.addGraph(graph2);
        // CURSOR
        var chartCursor = new AmCharts.ChartCursor();
        chartCursor.cursorPosition = "mouse";
        chart.addChartCursor(chartCursor);
        chart.write("planRzd");
    });
</script>


<script type="text/javascript">
    function view() {
        window.location.href = '<%=request.getContextPath()%>/cashiers/details/view.htm?idCashiers=<%=cashiers.getIdCashiers()%>&year=' + $("select[name=year]").val() + '&month=' + $("select[name=month]").val();
    }
    function updateSector(dirId) {
        var sector = $("select[name=sector]");
        $(sector).empty();
        $.ajax({
            url: '/rp/segmentSelect/getSectorByIdDirection.htm',
            dataType: 'json',
            data:{dirId:dirId},
            async:false,
            success: function(data) {
                for (var i = 0; i < data.length; i++) {
                    var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                    $(sector).append(ssOption);
                }
            }
        });
    }
    $(document).ready(function() {
        $("select[name=direction]").live("change", function() {
            var dirId = $(this).find(":selected").val();
            updateSector(dirId);
        });
    });


    function del() {
        if (confirm("Вы действительно хотите удалить запись?")) {
            $.ajax({
                url: '/rp/cashiers/details/delete.htm',
                dataType: 'json',
                data:{idCashiers:<%=cashiers.getIdCashiers()%>},
                async:false,
                success: function(data) {
                    window.location.href = '<%=request.getContextPath()%>/cashiers/view.htm';
                }
            });
        }
    }

    function v_1() {
        $("#v1_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
        $($("#v1_l").parents()[0]).next().attr('class', 'selected');
        $("#v1_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');

        $("#v2_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#v2_l").parents()[0]).next().attr('class', 'unselected');
        $("#v2_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');

        $("#v3_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#v3_l").parents()[0]).next().attr('class', 'unselected');
        $("#v3_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');


        var graphicData = $("#graphicData");
        var tableData = $("#tableData");
        tableData.hide();
        graphicData.show();
    }
    function v_2() {
        $("#v2_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
        $($("#v2_l").parents()[0]).next().attr('class', 'selected');
        $("#v2_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');

        $("#v1_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#v1_l").parents()[0]).next().attr('class', 'unselected');
        $("#v1_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');

        $("#v3_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#v3_l").parents()[0]).next().attr('class', 'unselected');
        $("#v3_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');

        var graphicData = $("#graphicData");
        var tableData = $("#tableData");
        graphicData.hide();
        tableData.show();
    }

</script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <a href="<%=request.getContextPath()%>/cashiers/view.htm">Корректировка разъедных кассиров</a>
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
       <span>
             <%=cashiers.getFioCashiers()%>
       </span>
    </div>
    <div class="tabs">
        <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
            <tr>
                <td class="between"></td>
                <td><img id="v1_l1" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                <td class="selected" onclick="">
                    <span>Персональные данные</span>
                </td>
                <td><img id="v1_r1" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>

            </tr>
        </table>
    </div>
    <div id="cashiersData">
        <div style="text-align:right;padding-right:10px">
            <button name="bEdit" onclick="window.location.href='<%=request.getContextPath()%>/cashiers/details/edit.htm?idCashiers=<%=cashiers.getIdCashiers()%>&year=<%=year%>&month=<%=month%>'">
                Редактировать
            </button>
            <button onclick="del()" name="bDelete">Удалить</button>
        </div>
        <form id="formCashiers">
            <div style="margin:10px;">
                <div style="text-align:left;padding:5px">
                    <span style="color:gray;">Уникальный код кассира - </span>
                    <span style="color:black;font-weight:bold;"><%=cashiers.getIdCashiers()%></span>
                </div>
                <div style="text-align:left;padding:5px">
                    <span style="color:gray;">ФИО кассира - </span>
                    <span style="color:black;font-weight:bold;"><%=cashiers.getFioCashiers()%></span>
                </div>
                <div style="text-align:left;padding:5px">
                    <span style="color:gray;">Станция приписки - </span>
                    <span style="color:black;font-weight:bold;"><%=direction.getName()%></span>
                    <span style="color:gray;">>></span>
                    <span style="color:black;font-weight:bold;"><%=sector.getName()%></span>
                </div>
                <div style="text-align:left;padding:5px">
                    <span style="color:gray;">Прочие сведения - </span>
                    <span style="color:black;"><%=cashiers.getComments()%></span>
                </div>
            </div>
            <div>
                <div style="border-top:1px solid gray;padding-left:10px;padding-top:10px;text-align:left;">
                    <span>Год</span>
                    <select name="year">
                        <%
                            GregorianCalendar gc = new GregorianCalendar();
                            for (int i = gc.get(Calendar.YEAR) - 5; i <= gc.get(Calendar.YEAR); i++) {
                        %>
                        <option <%=(i == year ? "selected" : "")%>>
                            <%=i%>
                        </option>
                        <%}%>
                    </select>
                    <span>Месяц</span>
                    <select name="month">
                        <%for (int i = 1; i <= 12; i++) {%>
                        <option <%=(i == month ? "selected" : "")%>>
                            <%=i%>
                        </option>
                        <%}%>
                    </select>
                    <button type="button" onclick="view()" onblur="">Смотреть</button>
                </div>
                <div class="tabs">
                    <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
                        <tr>
                            <td class="between"></td>
                            <td><img id="v1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                            <td class="selected" onclick="v_1()">
                                <span>В графическом виде</span>
                            </td>
                            <td><img id="v1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
                            <td class="between"></td>
                            <td><img id="v2_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                            <td class="unselected" onclick="v_2()">
                                <span>В табличном виде</span>
                            </td>
                            <td style="margin-left:10px;">
                                <img id="v2_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="graphicData">
                    <div style="padding:10px;text-align:left;font-size:12pt;color:gray;">Общая выручка</div>
                    <div id="planBase" style="width:100%; height:400px;"></div>
                    <div style="text-align:center;">
                        <div style="margin-left:500px;float:left;height:20px;width:20px;background:#FF6600"></div>
                        <div style="float:left;">-план</div>
                        <div style="margin-left:20px;float:left;height:20px;width:20px;background:#FCD202"></div>
                        <div style="float:left;">-факт</div>
                        <div style="clear:both;"></div>
                    </div>
                    <div style="padding:10px;text-align:left;font-size:12pt;color:gray;">Выручка по ОАО "РЖД"</div>
                    <div id="planRzd" style="width:100%; height:400px;"></div>
                    <div style="text-align:center;">
                        <div style="margin-left:500px;float:left;height:20px;width:20px;background:#FF6600"></div>
                        <div style="float:left;">-план</div>
                        <div style="margin-left:20px;float:left;height:20px;width:20px;background:#FCD202"></div>
                        <div style="float:left;">-факт</div>
                        <div style="clear:both;"></div>
                    </div>
                    <%--<div id="chartdiv" style="width:100%; height:400px;"></div>--%>
                </div>
                <div id="tableData" style="display:none;">
                    <table class="tableRow" style="width:680px">
                        <tr>
                            <th rowspan="2" style="width:70px">Число</th>
                            <th rowspan="2" style="width:200px">№ маршрута</th>
                            <th colspan="2">Выручка общая</th>
                            <th colspan="2">Выручка по ОАО "РЖД"</th>
                        </tr>
                        <tr>
                            <th style="width:100px">План</th>
                            <th style="width:100px">Факт</th>
                            <th style="width:100px">План</th>
                            <th style="width:100px">Факт</th>
                        </tr>
                        <%for (PlanCashiers pc : cashiers.getPlanCashiers()) {%>
                        <tr>
                            <td style="text-align:center;">
                                <span><%=new SimpleDateFormat("dd.MM.yyyy").format(pc.getDate())%></span></td>
                            <td style="text-align:center;"><span><%=pc.getRouteNumber()%></span></td>
                            <td style="text-align:center;"><span><%=pc.getPlanBase()%></span></td>
                            <td style="text-align:center;"><span><%=pc.getFactBase()%></span></td>
                            <td style="text-align:center;"><span><%=pc.getPlanRzd()%></span></td>
                            <td style="text-align:center;"><span><%=pc.getFactRzd()%></span></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </form>
    </div>
    <div id="cashiersResult" style="display:none;"></div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
