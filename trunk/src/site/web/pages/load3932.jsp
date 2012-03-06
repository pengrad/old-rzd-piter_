<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.MonitoringSegment" %>
<%@ page import="java.util.Collection" %>
<%@ page import="objects.ReportSegment" %>
<%@ page import="utils.Helper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<ReportSegment> repSegment = (Collection<ReportSegment>) request.getAttribute("repSegment");
    String linkLevel = (request.getAttribute("linkLevel") != null ? (String) request.getAttribute("linkLevel") : null);
    String linkDownloadXML = (request.getAttribute("linkDownloadXML") != null ? (String) request.getAttribute("linkDownloadXML") : null);
    String linkDownloadTXT = (request.getAttribute("linkDownloadTXT") != null ? (String) request.getAttribute("linkDownloadTXT") : null);
    String linkDownloadXLS = (request.getAttribute("linkDownloadXLS") != null ? (String) request.getAttribute("linkDownloadXLS") : null);
    String linkDownloadXMLByStation = (request.getAttribute("linkDownloadXMLByStation") != null ? (String) request.getAttribute("linkDownloadXMLByStation") : null);
    String linkDownloadTXTByStation = (request.getAttribute("linkDownloadTXTByStation") != null ? (String) request.getAttribute("linkDownloadTXTByStation") : null);
    String linkDownloadXLSByStation = (request.getAttribute("linkDownloadXLSByStation") != null ? (String) request.getAttribute("linkDownloadXLSByStation") : null);
%>
<html>
<head>
    <title>Формирование справки 3932</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript">
        function load3932(e) {
            var linkDownload = $(e).attr("link");
            var dateReport = $(e).parent().parent().find("input[name=dateReport]").val();
            var typeTerm = $(e).parent().parent().find("select[name=typeTerm]").val();
            var img = $(e).html();
            <%--$(e).html("<img src='<%=request.getContextPath()%>/images/ajax_wait_9.gif' title='Экспорт в формате XML'/>");--%>
//            alert("**");
//            window.location = linkDownload + "&dateReport=" + dateReport + "&typeTerm=" + typeTerm;
            window.open(linkDownload + "&dateReport=" + dateReport + "&typeTerm=" + typeTerm, '', 'height=600,width=850,left=50,top=150,directories=no,scrollbars=yes,resizable=yes');

//            alert("*****");
//            $(e).html(img);
            return false;
        }
        function load3932ByIDStation(e) {
            var linkDownload = $(e).attr("link");
            var dateReport = $(e).parent().find("input[name=dateReport]").val();
            var idStation = $(e).parent().find("input[name=idStation]").val();
            var typeTerm = $(e).parent().find("select[name=typeTerm]").val();
            if ($.trim(idStation).length > 0 && !isNaN($.trim(idStation))) {
                window.location = linkDownload + $.trim(idStation) + "&dateReport=" + dateReport + "&typeTerm=" + typeTerm;
            } else {
                alert("Введите код станции");
            }
            return false;
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


            var segment = $("#segment");
            var search = $("#search");
            var shedule = $("#shedule");
            search.hide();
            shedule.hide();
            segment.show();
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

            var segment = $("#segment");
            var search = $("#search");
            var shedule = $("#shedule");
            segment.hide();
            shedule.hide();
            search.show();
        }
        function v_3() {
            $("#v3_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
            $($("#v3_l").parents()[0]).next().attr('class', 'selected');
            $("#v3_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');

            $("#v2_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
            $($("#v2_l").parents()[0]).next().attr('class', 'unselected');
            $("#v2_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');

            $("#v1_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
            $($("#v1_l").parents()[0]).next().attr('class', 'unselected');
            $("#v1_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');
            var segment = $("#segment");
            var search = $("#search");
            var shedule = $("#shedule");
            segment.hide();
            search.hide();
            shedule.show();
        }


    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Администрирование&nbsp;<img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;<a
                href="<%=request.getContextPath()%>/3932/direction.htm">Формирование справки 3932</a></span>
    </div>
    <a href="<%=request.getContextPath()%>/upload/uploadAll.htm">dowload all</a>

    <div class="tabs">
        <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
            <tr>
                <td class="between"></td>
                <td><img id="v1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                <td class="selected" onclick="v_1()">
                    <span>По группам</span>
                </td>
                <td><img id="v1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
                <td class="between"></td>
                <td><img id="v2_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                <td class="unselected" onclick="v_2()">
                    <span>По коду станции</span>
                </td>
                <td style="margin-left:10px;">
                    <img id="v2_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                </td>
                <td class="between"></td>
                <td><img id="v3_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                <td class="unselected" onclick="v_3()">
                    <span>Настроить расписание</span>
                </td>
                <td style="margin-left:10px;">
                    <img id="v3_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                </td>
            </tr>
        </table>
    </div>
    <div id="segment">
        <%if (repSegment.size() > 0) {%>
        <div style="text-align:right;margin:10px">
            <img src="<%=request.getContextPath()%>/images/xml.png" alt="Экспорт в формате XML"/>
            <span style="color:gray;">-экспорт в формате XML</span>
            <img src="<%=request.getContextPath()%>/images/excel.png" alt="Экспорт в формате EXCEL"/>
            <span style="color:gray;">-экспорт в формате EXCEL</span>
            <img src="<%=request.getContextPath()%>/images/text.png" alt="Экспорт в формате EXCEL"/>
            <span style="color:gray;">-экспорт в формате TXT</span>
        </div>
        <table class="tableRow" style="width:670px">
            <%for (ReportSegment rSegment : repSegment) {%>
            <tr>
                <td style="width:330px;">
                    <%if (linkLevel != null) {%>
                    <a href="<%=linkLevel+rSegment.getIdSegment()%>">
                        <%=rSegment.getNameSegment()%>
                    </a>
                    <%} else {%>
                    <span><%=rSegment.getNameSegment()%></span>
                    <%}%>
                </td>
                <td style="width:130px;vertical-align: middle;">
                    <input class="datepicker" name="dateReport" readonly size="8" type="input"
                           value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                </td>
                <td style="width:70px;vertical-align: middle;">
                    <select name="typeTerm">
                        <option value="<%=Helper.TYPE_TERM_ALL%>">ВСЕ</option>
                        <option value="<%=Helper.TYPE_TERM_MKTK%>">МКТК</option>
                        <option value="<%=Helper.TYPE_TERM_PKTK%>">ПКТК</option>
                    </select>
                </td>
                <td style="width:100px;">
                    <a href="#" link="<%=linkDownloadXML+rSegment.getIdSegment()%>" onclick="load3932(this)">
                        <img src="<%=request.getContextPath()%>/images/xml.png" title="Экспорт в формате XML"/>
                    </a>
                    <a href="#" link="<%=linkDownloadXLS+rSegment.getIdSegment()%>" onclick="load3932(this)">
                        <img src="<%=request.getContextPath()%>/images/excel.png" title="Экспорт в формате EXCEL"/>
                    </a>
                    <a href="#" link="<%=linkDownloadTXT+rSegment.getIdSegment()%>" onclick="load3932(this)">
                        <img src="<%=request.getContextPath()%>/images/text.png" title="Экспорт в формате TXT"/>
                    </a>
                </td>
            </tr>
            <%}%>
        </table>
        <%} else {%>
        <div class="infoMessage" style="text-align:center;font-size:20px;color:gray;padding:30px">Данные временно
            отсутсвуют
        </div>
        <%}%>
        <div style="clear:both;"></div>
    </div>
    <div id="search" style="display:none;">
        <p class="infoMessage">
            Для формирования справки 3932 укажите отчетную дату и код станции по которой будет формироваться справка,
            затем нажмите по иконке с сответствующим форматом
        </p>

        <form action="" method="get">
            <div style="padding:5px;padding-top:40px;text-align:center;vertical-align: middle;">
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Дата&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" class="datepicker" name="dateReport" readonly size="8" type="input"
                       value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Тип терменала&nbsp;&nbsp;</span>
                <select name="typeTerm">
                    <option value="<%=Helper.TYPE_TERM_ALL%>">ВСЕ</option>
                    <option value="<%=Helper.TYPE_TERM_MKTK%>">МКТК</option>
                    <option value="<%=Helper.TYPE_TERM_PKTK%>">ПКТК</option>
                </select>
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Код станции&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" type="text" size="8" name="idStation"/>
                <a href="#"
                   link="<%=linkDownloadXMLByStation%>" onclick="load3932ByIDStation(this)">
                    <img src="<%=request.getContextPath()%>/images/xml.png" title="Экспорт в формате XML"/>
                </a>
                <a href="#"
                   link="<%=linkDownloadXLSByStation%>" onclick="load3932ByIDStation(this)">
                    <img src="<%=request.getContextPath()%>/images/excel.png" title="Экспорт в формате EXCEL"/>
                </a>
                <a href="#"
                   link="<%=linkDownloadTXTByStation%>" onclick="load3932ByIDStation(this)">
                    <img src="<%=request.getContextPath()%>/images/text.png" title="Экспорт в формате TXT"/>
                </a>

                <div id="info" style="padding:20px;text-align:center;font-size:12pt;color:red"></div>
            </div>
        </form>
    </div>
    <div id="shedule" style="display:none;">
        <p class="infoMessage">
            В дальнейшем здесь будет настройка планировщика, для автоматической передаче на FTP
        </p>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>