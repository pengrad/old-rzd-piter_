<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="/js/jquery-1.7.js"></script>

    <script type="text/javascript" src="/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="/js/jquery.swfupload.js"></script>
    <script type="text/javascript">
        $(function() {
            $('.swfupload-control').swfupload({
                // Backend Settings
                upload_url: "fileupload.htm;jsessionid=<%=request.getSession().getId()%>",    // Relative to the SWF file (or you can use absolute paths)

                // Flash Settings
                flash_url : "/swfupload/swfupload.swf",

                //IMPORTANT: you need to set file_post_name otherwise flash sets it as Filedata, which does not conform to bean naming conventions.
                file_post_name: "kFile",

                // File Upload Settings
                file_size_limit : "102400", // 100MB
                file_types : "*.*",
                file_types_description : "All Files",
                file_upload_limit : "10",
                file_queue_limit : "0",

                // Button Settings
                button_image_url : "/swfupload/XPButtonUploadText_61x22.png", // Relative to the SWF file
                button_placeholder_id : "spanButtonPlaceholder",
                button_width: 61,
                button_height: 22



            });


            // assign our event handlers
            $('.swfupload-control')
                    .bind('fileQueued', function(event, file) {
                // start the upload once a file is queued
                $(this).swfupload('startUpload');
            })
                    .bind('uploadComplete', function(event, file) {
                alert('Upload completed - ' + file.name + '!');
                // start the upload (if more queued) once an upload is complete
                $(this).swfupload('startUpload');
            });

        });

    </script>
<head>
<body>
hello
<div id="file_upload"></div>
<div class="swfupload-control">
    <span id="spanButtonPlaceholder"></span>
</div>
</body>
</html>