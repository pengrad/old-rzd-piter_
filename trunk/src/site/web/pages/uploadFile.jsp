<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 07.02.2012
  Time: 19:31:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Загрузка К - файлов</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet"--%>
    <%--href="<%=request.getContextPath()%>/js/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css"--%>
    <%--type="text/css" media="screen"/>--%>

    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <%--<script type="text/javascript" src="http://bp.yahooapis.com/2.4.21/browserplus-min.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plupload/js/plupload.full.js"></script>
    <%--<script type="text/javascript"--%>
    <%--src="<%=request.getContextPath()%>/js/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>--%>
    <script type="text/javascript">
        $(function() {
            var uploader = new plupload.Uploader({
                //                gears,html5,
                runtimes : 'gears,html5,flash,silverlight,browserplus',
                browse_button : 'pickfiles',
                container : 'container',
                max_file_size : '100mb',
                urlstream_upload: true,
                url: '<%=request.getContextPath()%>/upload/upload.htm',
                flash_swf_url : '<%=request.getContextPath()%>/js/plupload/js/plupload.flash.swf',
                silverlight_xap_url : '<%=request.getContextPath()%>/js/plupload/js/plupload.silverlight.xap',
                filters : [

                    {
                        title : "K_FILES files",
                        extensions : "xml"
                    }
                ]
            });

            uploader.bind('UploadFile', function(up, params) {
                up.settings.multipart_params = {
                    typeTimeCalcReport :$("input[name=typeTimeCalcReport]").val(),
                    timeCalcReport:$("input[name=timeCalcReport]").val()
                };
            });

            uploader.bind('Init', function(up, params) {
                $('#v1').append("<div>Current runtime: " + params.runtime + "</div>");
            });

            $('#uploadfiles').click(function(e) {
                uploader.start();
                e.preventDefault();
            });

            $(".deleteFile").live("click", function(e) {
                var id = $(this).attr("idfile");
                $.each(uploader.files, function(i, file) {
                    if (id == file.id) {
                        uploader.removeFile(file);
                    }
                });
            });

            $("#deleteFilesAll").live("click", function(e) {
                uploader.splice();
            });

            uploader.init();

            uploader.bind('FilesAdded', function(up, files) {
                $.each(files, function(i, file) {
                    $(".tableRow").append(
                            "<tr id='" + file.id + "'>" +
                            "<td style='width:500px'>" + file.name + "</td>" +
                            "<td style='width:100px'>" + plupload.formatSize(file.size) + "</td>" +
                            "<td style='width:60px;'><div class='status' style='height:20px;width:40px;'><img title='Удалить файл' style='cursor:pointer' idfile='" + file.id + "' class='deleteFile' src='<%=request.getContextPath()%>/images/Delete-icon.png'/></div></td>" +
                            "</tr>");
                });
                up.refresh(); // Reposition Flash/Silverlight
            });

            uploader.bind('FilesRemoved', function(up, files) {
                plupload.each(files, function(file) {
                    $("#" + file.id).remove();
                });
                up.refresh(); // Reposition Flash/Silverlight
            });

            uploader.bind('UploadProgress', function(up, file) {
                $('#' + file.id + " .status").html(file.percent + "%");
            });

            uploader.bind('Error', function(up, err) {
                $('#' + err.file.id + " .status").html("<img title='Error: " + err.code + ", Message: " + err.message + (err.file ? ", File: " + err.file.name : '') + "' src='<%=request.getContextPath()%>/images/error.png'/>");
                up.refresh(); // Reposition Flash/Silverlight
            });

            uploader.bind('FileUploaded', function(up, file, r) {
                var response = jQuery.parseJSON(r.response);
                if (response.code != 0) {
                    uploader.trigger('Error', {
                        code : response.code,
                        message : response.message,
                        details : response.details,
                        file : file
                    });
                    return false;
                }
                //                alert( $('#' + file.id + " .status").html());
                $('#' + file.id + " .status").html("<img src='<%=request.getContextPath()%>/images/Ok-icon.png'/>");
                //                alert( $('#' + file.id + " .status").html());
            });
        });
        <%--function v_1() {--%>
        <%--$("#v1_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');--%>
        <%--$($("#v1_l").parents()[0]).next().attr('class', 'selected');--%>
        <%--$("#v1_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');--%>

        <%--$("#v2_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');--%>
        <%--$($("#v2_l").parents()[0]).next().attr('class', 'unselected');--%>
        <%--$("#v2_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');--%>

        <%--var v1 = $("#v1");--%>
        <%--var v2 = $("#v2");--%>
        <%--v2.hide();--%>
        <%--v1.show();--%>
        <%--}--%>
        <%--function v_2() {--%>
        <%--$("#v2_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');--%>
        <%--$($("#v2_l").parents()[0]).next().attr('class', 'selected');--%>
        <%--$("#v2_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');--%>
        <%--$("#v1_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');--%>
        <%--$($("#v1_l").parents()[0]).next().attr('class', 'unselected');--%>
        <%--$("#v1_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');--%>

        <%--var v1 = $("#v1");--%>
        <%--var v2 = $("#v2");--%>
        <%--v1.hide();--%>
        <%--v2.show();--%>
        <%--var cont = ("#v2");--%>
        <%--$.ajax({--%>
        <%--url: '<%=request.getContextPath()%>/upload/uploadFileToday.htm',--%>
        <%--//                data:{dateReport:dateReport,idStation:idStation},--%>
        <%--dataType: 'json',--%>
        <%--beforeSend: function(xhr) {--%>
        <%--$(cont).html("<div class='infoMessage2'>Подождите...</div>");--%>
        <%--},--%>
        <%--success: function(file) {--%>
        <%--$(cont).empty();--%>
        <%--if (file.length > 0) {--%>
        <%--var s = "<table class='tableRow' style='width:670px'>";--%>
        <%--for (var i = 0; i < file.length; i++) {--%>
        <%--s = s + "<tr>";--%>
        <%--s = s + "<td>";--%>
        <%--s = s + file[i].fileName;--%>
        <%--s = s + "</td>";--%>
        <%--s = s + "<td>";--%>
        <%--s = s + file[i].placeTerm;--%>
        <%--s = s + "</td>";--%>
        <%--s = s + "<td>";--%>
        <%--s = s + file[i].typeTerm;--%>
        <%--s = s + "</td>";--%>
        <%--s = s + "<td>";--%>
        <%--&lt;%&ndash;s = s + "<a class='viewFile' fileId='" + file[i].fileId + "' href='#'>";&ndash;%&gt;--%>
        <%--&lt;%&ndash;s = s + "<img src='<%=request.getContextPath()%>/images/eye-icon.png'";&ndash;%&gt;--%>
        <%--&lt;%&ndash;s = s + "title='Открыть для просмотра'/>";&ndash;%&gt;--%>
        <%--&lt;%&ndash;s = s + "</a>";&ndash;%&gt;--%>
        <%--s = s + "</td>";--%>
        <%--s = s + "</tr>";--%>
        <%--}--%>
        <%--s = s + "</table>";--%>
        <%--$(cont).html(s);--%>
        <%--} else {--%>
        <%--$(cont).html("<div class='infoMessage2'>По вашему запросу ничего не найдено</div>");--%>
        <%--}--%>
        <%--}--%>
        <%--});--%>
        <%--}--%>
    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Администрирование&nbsp;<img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;<span>Загрузка</span></span>
    </div>
    <%--<div class="tabs">--%>
    <%--<table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">--%>
    <%--<tr>--%>
    <%--<td class="between"></td>--%>
    <%--<td><img id="v1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>--%>
    <%--<td class="selected">--%>
    <%--<a href="#upload" >Загрузка К-файлов</a>--%>
    <%--</td>--%>
    <%--<td><img id="v1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>--%>
    <%--<td class="between"></td>--%>
    <%--&lt;%&ndash;<td><img id="v2_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td class="unselected" onclick="v_2()">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<a href="#uploadToday" onclick="v_2()">Загружено за сегодня</a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;&lt;%&ndash;<span>Загружено за сегодня</span>&ndash;%&gt;&ndash;%&gt;--%>
    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<td style="margin-left:10px;">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<img id="v2_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</div>--%>
    <div id="v1">
        <%--<form id="form1" action="upload/upload.htm" method="post"--%>
        <%--enctype="multipart/form-data">--%>
        <div style="text-align:right;padding-right:20px">
            <a href="<%=request.getContextPath()%>/mon/direction.htm?date=<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>">Загружено
                сегодня</a>
        </div>
        <p class="infoMessage">
            Для загрузки файлов выберите тип отчетной даты затем нажмите "Выбрать файлы", после чего
            в открывшемся окне выберите нужные файлы (для выбора нескольких файлов используйте Ctrl и Shift) и нажмите
            "Загрузить файлы"
        </p>

        <div id="timeCalcReport" style="text-align:left;padding:10px">
            <span class="titleTimeCalcReport">Отчетная дата:</span>
            <table>
                <tr>
                    <td style="height:30px;vertical-align:middle;">
                        <input name="typeTimeCalcReport" type="radio" checked value="0"/>
                        <span>дата в файле</span>
                        <input name="typeTimeCalcReport" type="radio" value="1"/>
                        <span>дата загрузки</span>
                        <input name="typeTimeCalcReport" type="radio" value="2"/>
                        <span>задать дату</span>
                    </td>
                    <td style="height:30px;vertical-align:middle;">
                    <span id="cTimeCalcReport" style="display:none;">
                    <input class="datepicker" name="timeCalcReport" readonly size="8" type="input"
                           value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                    <%--<img class="datepicker" src="<%=request.getContextPath()%>/images/calendar-icon.png" alt="">--%>
                    </span>
                    </td>
                </tr>
            </table>
        </div>
        <%--url: '<%=request.getContextPath()%>/upload/upload.htm',--%>
        <div id="container">
            <div style="text-align:left;padding:10px">
                <input type="button" id="pickfiles" value="Выбрать файлы"/>
                <input type="button" id="uploadfiles" value="Загрузить файлы"/>
                <input type="button" id="deleteFilesAll" value="Очистить область загрузки"/>
            </div>
            <table style="width:670px" class="tableRow"></table>
        </div>


    </div>
    <div id="v2" style="display:none;"></div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>