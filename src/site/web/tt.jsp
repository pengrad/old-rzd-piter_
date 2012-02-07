<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 05.02.2012
  Time: 2:16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>SWFUpload v2.0 Multi-Instance Demo</title>

    <link href="/css/default.css" rel="stylesheet" type="text/css"/>
    <%--<script type="text/javascript" src="/js/jquery-1.7.js"></script>--%>
    <script type="text/javascript" src="/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="/swfupload/swfupload.queue.js"></script>
    <script type="text/javascript" src="/js/handlers.js"></script>
    <script type="text/javascript" src="/js/fileprogress.js"></script>
    <script type="text/javascript">
        var upload1;

        window.onload = function() {
            upload1 = new SWFUpload({
                // Backend Settings
                upload_url: "fileupload.htm; jsessionid=<%=request.getSession().getId()%>",    // Relative to the SWF file (or you can use absolute paths)

                // File Upload Settings
                file_size_limit : "102400",    // 100MB
                file_types : "*.*",
                file_types_description : "All Files",
                file_upload_limit : "1000",
                file_queue_limit : "0",

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

                // Button Settings
                button_image_url : "/swfupload/XPButtonUploadText_61x22.png", // Relative to the SWF file
                button_placeholder_id : "spanButtonPlaceholder1",
                button_width: 61,
                button_height: 22,

                // Flash Settings
                flash_url : "/swfupload/swfupload.swf",

                //IMPORTANT: you need to set file_post_name otherwise flash sets it as Filedata, which does not conform to bean naming conventions.
                file_post_name: "kFile",



                custom_settings : {
                    progressTarget : "fsUploadProgress1",
                    cancelButtonId : "btnCancel1"
                },

                // Debug Settings
                debug: false
            });
        }

    </script>
</head>
<body>
<div id="header">
    <h1 id="logo"><a href="../">SWFUpload</a></h1>

    <div id="version">v2.2.0</div>
</div>
<div id="content">
    <h2>Multi-Instance Demo</h2>

    <form id="form1" action="fileupload.htm" method="post" enctype="multipart/form-data">
        <p>This page demonstrates how multiple instances of SWFUpload can be loaded on the same page.
            It also demonstrates the use of the graceful degradation plugin and the queue plugin.</p>
        <table>
            <tr valign="top">
                <td>
                    <div>
                        <div class="fieldset flash" id="fsUploadProgress1">
                            <span class="legend">Large File Upload Site</span>
                        </div>
                        <div style="padding-left: 5px;">
                            <span id="spanButtonPlaceholder1"></span>
                            <input id="btnCancel1" type="button" value="Cancel Uploads" onclick="cancelQueue(upload1);"
                                   disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;"/>
                            <br/>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>