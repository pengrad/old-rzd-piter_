<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<SegmentInfo> directions = (Collection<SegmentInfo>) request.getAttribute("directions");
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
        function updateSector(dirId) {
            var sector = $("select[name=sector]");
            $(sector).empty();
            $.ajax({
                url:'/rp/segmentSelect/getSectorByIdDirection.htm',
                dataType:'json',
                data:{dirId:dirId},
                async:false,
                success:function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                        $(sector).append(ssOption);
                    }
                }
            });
        }
        $(document).ready(function () {
            $("select[name=direction]").live("change", function () {
                var dirId = $(this).find(":selected").val();
//                alert(dirId);
                updateSector(dirId);
            });
            $("select[name=direction]").change();
        });
    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuReport.jsp"/>
<div id="data">
    <div class="infoPath">
        <a href="<%=request.getContextPath()%>/report/listReport.htm">Отчетность</a>&nbsp;
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">
        <span>Отчетность по разъездным кассирам</span>
    </div>
    <div style="color:gray;font-size:14pt;text-align:center;">
        <table width="100%" border="0" style="border-spacing:20px!important;">
            <tr>
                <td>
                    <form action="<%=request.getContextPath()%>/report/reportCashiers1.htm" method="get">
                    <span style="font-size:12pt!important;"
                          href="<%=request.getContextPath()%>/report/listReportCashiers.htm">
                        План-график на месяц по ВЫРУЧКЕ и РЖД
                    </span>

                        <div>
                            <span>Год</span>
                            <select name="year">
                                <%
                                    GregorianCalendar gc = new GregorianCalendar();
                                    for (int i = gc.get(Calendar.YEAR) - 5; i <= gc.get(Calendar.YEAR); i++) {
                                %>
                                <option <%=((i == gc.get(Calendar.YEAR)) ? "selected" : "")%>>
                                    <%=i%>
                                </option>
                                <%}%>
                            </select>
                            <span>Месяц</span>
                            <select name="month">
                                <%for (int i = 1; i <= 12; i++) {%>
                                <option <%=((i == (gc.get(Calendar.MONTH) + 1)) ? "selected" : "")%>>
                                    <%=i%>
                                </option>
                                <%}%>
                            </select>
                            <select style="width: 180px" name="direction">
                                <%for (SegmentInfo si : directions) {%>
                                <option value="<%=si.getId()%>">
                                    <%=si.getName()%>
                                </option>
                                <%}%>
                            </select>
                            <select style="width: 180px" name="sector"></select>
                            <button type="submit" onblur="">Смотреть</button>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="<%=request.getContextPath()%>/report/reportCashiers1.htm" method="get">
                    <span style="font-size:12pt!important;"
                          href="<%=request.getContextPath()%>/report/listReportCashiers.htm">
                          Приложение №3 (5-тидневки по-фам.отчет ККБР и пл-графики)
                    </span>

                        <div>
                            <span>Год</span>
                            <select name="year">
                                <%
                                     gc = new GregorianCalendar();
                                    for (int i = gc.get(Calendar.YEAR) - 5; i <= gc.get(Calendar.YEAR); i++) {
                                %>
                                <option <%=((i == gc.get(Calendar.YEAR)) ? "selected" : "")%>>
                                    <%=i%>
                                </option>
                                <%}%>
                            </select>
                            <span>Месяц</span>
                            <select name="month">
                                <%for (int i = 1; i <= 12; i++) {%>
                                <option <%=((i == (gc.get(Calendar.MONTH) + 1)) ? "selected" : "")%>>
                                    <%=i%>
                                </option>
                                <%}%>
                            </select>
                            <select style="width: 180px" name="direction">
                                <%for (SegmentInfo si : directions) {%>
                                <option value="<%=si.getId()%>">
                                    <%=si.getName()%>
                                </option>
                                <%}%>
                            </select>
                            <select style="width: 180px" name="sector"></select>
                            <button type="submit" onblur="">Смотреть</button>
                        </div>
                    </form>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>