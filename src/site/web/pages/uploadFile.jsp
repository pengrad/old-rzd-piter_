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

    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/swfupload/swfupload.queue.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/handlers.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/fileprogress.js"></script>
    <script type="text/javascript">
        var upload1;
        window.onload = function() {
            upload1 = new SWFUpload({

                // Backend Settings
                upload_url: "<%=request.getContextPath()%>/upload/upload.htm?JSESSIONID=<%=request.getSession().getId()%>",    // Relative to the SWF file (or you can use absolute paths)

                // File Upload Settings
                file_size_limit : "10240000",    // 100MB
                file_types : "*.*",
                file_types_description : "XML",
                file_upload_limit : "100000",
                file_queue_limit : "50",

                // Event Handler Settings (all my handlers are in the Handler.js file)
                file_dialog_start_handler : fileDialogStart,
                file_queued_handler : fileQueued,
                file_queue_error_handler : fileQueueError,
                file_dialog_complete_handler : fileDialogComplete,
                upload_start_handler : uploadStart,
                upload_progress_handler : uploadProgress,
                upload_error_handler : uploadError,
                upload_success_handler : uploadSuccess,
                upload_complete_handler : uploadComplete,
                prevent_swf_caching:false,
                //                button_action:SWFUpload.START_UPLOAD,

                //                postData:{www:'rrrr'},
                // Button Settings
                button_image_url : "<%=request.getContextPath()%>/swfupload/XPButtonUploadText.png", // Relative to the SWF file
                button_placeholder_id : "spanButtonPlaceholder1",
                button_width: 61,
                button_height: 22,

                // Flash Settings
                flash_url : "<%=request.getContextPath()%>/swfupload/swfupload.swf?t=<%=new Date().getTime()%>",

                //IMPORTANT: you need to set file_post_name otherwise flash sets it as Filedata, which does not conform to bean naming conventions.
                file_post_name: "kFile",

                custom_settings : {
                    progressTarget : "fsUploadProgress1",
                    cancelButtonId : "btnCancel1"
                },

                // Debug Settings
                debug: false
            });
        };


    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Администрирование&nbsp;<img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;<span>Загрузка</span></span>
    </div>
    <div>
        <form id="form1" action="upload/upload.htm" method="post"
              enctype="multipart/form-data">
            <p class="infoMessage">
                Для загрузки файлов выберите тип отчетной даты затем нажмите "Открыть", после чего
                в открывшемся окне выберите нужные файлы и нажмите "Загрузить"
            </p>
            <div id="timeCalcReport" style="text-align:left;padding:20px">
                <span class="titleTimeCalcReport">Отчетная дата:</span>

                <div style="padding-left:10px;padding-top:10px;vertical-align: middle;">
                    <input name="typeTimeCalcReport" type="radio" checked value="0"/>
                    <span>дата в файле</span>
                    <input name="typeTimeCalcReport" type="radio" value="1"/>
                    <span>дата загрузки</span>
                    <input name="typeTimeCalcReport" type="radio" value="2"/>
                    <span>задать дату</span>
                    <span id="cTimeCalcReport" style="display:none;">
                    <input class="datepicker" name="timeCalcReport" readonly size="8" type="input"
                           value="<%=new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                    <%--<img class="datepicker" src="<%=request.getContextPath()%>/images/calendar-icon.png" alt="">--%>
                    </span>
                </div>
            </div>
            <table>
                <tr valign="top">
                    <td>
                        <div>
                            <div class="fieldset flash" id="fsUploadProgress1">
                                <span class="legend">Загружено файлов</span>
                            </div>
                            <div style="padding-left: 5px;">
                                <span id="spanButtonPlaceholder1"></span>
                                &nbsp;
                                <input type="button" value="Загрузить" onclick="upload1.startUpload()"/>
                                <input id="btnCancel1" type="button" value="Отмена"
                                       onclick="cancelQueue(upload1);"
                                       disabled="disabled"
                                       style="margin-left: 2px; height: 22px; font-size: 8pt;"/>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>