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
    <title>Миниторин загрузки К - файлов в систему</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript">


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

            var base = $("#base");
            var upload = $("#upload");
            var notUpload = $("#notUpload");
            base.show();
            upload.hide();
            notUpload.hide()
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

            var base = $("#base");
            var upload = $("#upload");
            var notUpload = $("#notUpload");
            base.hide();
            upload.show();
            notUpload.hide();
        }

        function v_3() {
            $("#v3_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
            $($("#v3_l").parents()[0]).next().attr('class', 'selected');
            $("#v3_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');
            $("#v1_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
            $($("#v1_l").parents()[0]).next().attr('class', 'unselected');
            $("#v1_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');
            $("#v2_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
            $($("#v2_l").parents()[0]).next().attr('class', 'unselected');
            $("#v2_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');

            var base = $("#base");
            var upload = $("#upload");
            var notUpload = $("#notUpload");
            base.hide();
            upload.hide();
            notUpload.show();
        }

        function uploadFileByStation(e) {
            var dateReport = $(e).parent().find("input[name=dateReport]").val();
            var idStation = $(e).parent().find("input[name=idStation]").val();
            var cont = $("#uploadKFile");
            if ($.trim(idStation).length > 0 && !isNaN($.trim(idStation))) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/mon/uploadFileByStation.htm',
                    data:{dateReport:dateReport,idStation:idStation},
                    dataType: 'json',
                    beforeSend: function(xhr) {
                        $(cont).empty();
                        $(cont).append("<div class='infoMessage2'>Подождите...</div>");
                    },
                    success: function(file) {
                        $(cont).empty();
                        if (file.length > 0) {
                            var s = "<table class='tableRow' style='width:670px'>";
                            for (var i = 0; i < file.length; i++) {
                                s = s + "<tr>";
                                s = s + "<td>";
                                s = s + file[i].fileName;
                                s = s + "</td>";
                                s = s + "<td>";
                                s = s + file[i].placeTerm;
                                s = s + "</td>";
                                s = s + "<td>";
                                s = s + file[i].typeTerm;
                                s = s + "</td>";
                                s = s + "<td>";
                                s = s + "<a class='viewFile' fileId='" + file[i].fileId + "' href='#'>";
                                s = s + "<img src='<%=request.getContextPath()%>/images/eye-icon.png'";
                                s = s + "title='Открыть для просмотра'/>";
                                s = s + "</a>";
                                s = s + "</td>";
                                s = s + "</tr>";
                            }
                            s = s + "</table>";
                            $(cont).append(s);
                        } else {
                            $(cont).append("<div class='infoMessage2'>По вашему запросу ничего не найдено</div>");
                        }
                    }
                });
            } else {
                alert("Введите код станции");
            }
            return false;
        }

        $(document).ready(function() {
            $(".viewFile").live("click", function() {
                var fileId = $(this).attr("fileId");
                $("#modalFile").dialog({ buttons: [
                    {
                        text: "Закрыть",
                        click: function() {
                            $(this).dialog("close");
                        }
                    }
                ],
                    height: 700,
                    width:1200,
                    modal: true,
                    open: function(event, ui) {
                        $.ajax({
                            url: '<%=request.getContextPath()%>/edit/viewFile.htm',
                            data:{fileId:fileId},
                            dataType: 'html',
                            beforeSend: function(xhr) {
                                $("#modalFile").empty();
                                $("#modalFile").append("<div class='infoMessage2'>Подождите...</div>");
                            },
                            success: function(data) {
                                $("#modalFile").empty();
                                $("#modalFile").append(data);
                            },
                            error:function() {
                                $("#modalFile").empty();
                                $("#modalFile").append("<div class='infoMessage2'>Во время получения данных, возникла ошибка, попробуте еще раз</div>");

                            }
                        });
                    }});
              return false;
            });
        });

    </script>

</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Администрирование&nbsp;
            <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Мониторинг загрузки К - файлов</a>
        </span>
    </div>

    <div>
        <div style="text-align:right;padding-right:20px"><a href="#" onclick="alert('Функция еще не реализована')">Задать
            станции для мониторинга</a></div>
        <div class="tabs">
            <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
                <tr>
                    <td class="between"></td>
                    <td><img id="v1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                    <td class="selected" onclick="v_1()">
                        <span>Текущее состояние</span>
                    </td>
                    <td><img id="v1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
                    <td class="between"></td>
                    <td><img id="v2_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                    <td class="unselected" onclick="v_2()">
                        <span>Загружено К - файлов</span>
                    </td>
                    <td style="margin-left:10px;">
                        <img id="v2_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                    </td>
                    <td class="between"></td>
                    <td><img id="v3_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
                    <td class="unselected" onclick="v_3()">
                        <span>Не загружено К - файлов</span>
                    </td>
                    <td style="margin-left:10px;">
                        <img id="v3_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
                    </td>
                </tr>
            </table>
        </div>
        <div id="base">
            <div id="timeCalcReport" style="text-align:left;padding:20px">
                <form action="<%=request.getContextPath()%>/monDirection.htm" method="get">
                    <div style="padding-left:10px;padding-top:10px;vertical-align: middle;">
                        <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Дата&nbsp;&nbsp;</span>
                        <input style="font-size:12pt;" class="datepicker" name="dateMon" readonly size="8" type="input"
                               value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                        <input type="button" value="Смотреть" onclick="alert('Не загружены коды станций')"/>
                    </div>
                </form>
            </div>
            <%if (monSegment.size() > 0) {%>
            <div style="text-align:right;margin:10px">
                <img src="<%=request.getContextPath()%>/images/ok.png" alt="Данные загружены"/>
                <span style="color:gray;">-данные загрудены</span>
                <img src="<%=request.getContextPath()%>/images/error.png" alt="Данные не загружены"/>
                <span style="color:gray;">-данные не загружены</span>
                <img src="<%=request.getContextPath()%>/images/noActive.png" alt="Не ведется мониторинг"/>
                <span style="color:gray;">-не ведется мониторинг</span>
            </div>
            <table class="tableRow">
                <%for (MonitoringSegment mSegment : monSegment) {%>
                <tr>
                    <td style="width:500px;">
                        <%if (linkLevel != null) {%>
                        <a href="<%=linkLevel+mSegment.getId()%>"><%=mSegment.getName()%>
                        </a>
                        <%} else {%>
                        <span><%=mSegment.getName()%></span>
                        <%}%>
                    </td>
                    <td style="width:50px;">
                        <img src="<%=request.getContextPath()%>/images/ok.png" alt="Данные загружены"/>
                    </td>
                    <td style="width:70px;">200/200</td>
                </tr>
                <%}%>
            </table>
            <%} else {%>
            <div class="infoMessage" style="text-align:center;font-size:20px;color:gray;padding:30px">Данные временно
                отсутсвуют
            </div>
            <%}%>
        </div>
        <div id="upload" style="display:none;">
            <div style="text-align:left;padding:20px;vertical-align: middle;">
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Дата&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" class="datepicker" name="dateReport" readonly size="8" type="input"
                       value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Код станции&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" type="text" size="8" name="idStation"/>
                <input type="button" value="Смотреть" onclick="uploadFileByStation(this)"/>
            </div>
            <div id="uploadKFile">

            </div>

        </div>
        <div id="notUpload" style="display:none;">
            <div style="text-align:left;padding:20px;vertical-align: middle;">
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Дата&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" class="datepicker" name="dateReport" readonly size="8" type="input"
                       value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Код станции&nbsp;&nbsp;</span>
                <input style="font-size:12pt;" type="text" size="8" name="idStation"/>
                <input type="button" value="Смотреть" onclick="alert('Данный раздел находится в разработке')"/>

                <div id="notUploadKFile">
                    <p class="infoMessage">
                        Данный раздел находится в процесс разработки
                    </p>
                </div>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalFile">

</div>