<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.MonitoringSegment" %>
<%@ page import="java.util.Collection" %>
<%@ page import="objects.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<MonitoringSegment> monSegment = (Collection<MonitoringSegment>) request.getAttribute("monSegment");
    String linkLevel = (request.getAttribute("linkLevel") != null ? (String) request.getAttribute("linkLevel") : null);
    Collection<File> files = (Collection<File>) request.getAttribute("files");
    Object dateReport = request.getAttribute("dateReport");
    Object idStation = request.getAttribute("idStation");

%>
<html>
<head>
    <title>Редактирование К - файлов</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/validationForm.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript">
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
            $(".editFile").live("click", function() {
                var fileId = $(this).attr("fileId");
                $("#modalFile").dialog({ buttons: [
                    {
                        text: "Сохранить",
                        click: function() {
                            valid('formFile');
                            //                            $(this).dialog("close");
                        }
                    },
                    {
                        text: "Закрыть",
                        click: function() {
                            $(this).dialog("close");
                        }
                    }

                ],
                    height:700,
                    width:1200,
                    modal:true,
                    open:function(event, ui) {
                        $.ajax({
                            url: '<%=request.getContextPath()%>/edit/editFile.htm',
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
                    }
                });
                return false;
            });
            $(".deleteFile").live("click", function() {
                var fileId = $(this).attr("fileId");
                var r = window.confirm("Вы действительно хотите произвести удаление записи?");
                if (r == 1) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/edit/deleteFile.htm',
                        data:{fileId:fileId},
                        dataType: 'json',
                        success: function(data) {
                            if (data) {
                                alert("Запись успешо удалена");
                                window.location.reload(true);
                            <%--window.location = <%=request.getContextPath()%>/edit/--%>
                            <%--searchForEditFile.htm ? dateReport =--%>
                            <%--<%=dateReport%>&--%>
                            <%--idStation =--%>
                            <%--<%=idStation%>--%>
                            } else {
                                alert("Во время удаления, возникла ошибка, попробуте еще раз");
                            }
                        },
                        error:function() {
                            alert("Во время удаления, возникла ошибка, попробуте еще раз");
                        }
                    });
                }
            });
        });
        function searchFiles(e) {


        }
    </script>


</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <span>Администрирование&nbsp;<img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;<span>Корректировка К - файлов</span></span>
    </div>
    <div>
        <div id="timeCalcReport" style="text-align:left;padding:20px">
            <form action="<%=request.getContextPath()%>/edit/searchForEditFile.htm" method="get">
                <div style="padding-left:10px;padding-top:10px;vertical-align: middle;">
                    <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Дата&nbsp;&nbsp;</span>
                    <input style="font-size:12pt;" class="datepicker" name="dateReport" readonly size="8" type="input"
                           value="<%=dateReport!=null?(String)dateReport:new SimpleDateFormat("dd.MM.yyyy").format(new Date())%>"/>
                    <span style="font-size:12pt;color:gray;">&nbsp;&nbsp;Код станции&nbsp;&nbsp;</span>

                    <input style="font-size:12pt;" type="text" size="8" name="idStation"
                           value="<%=(idStation!=null?(Integer)idStation:"")%>"/>
                    <input type="submit" value="Смотреть"/>
                </div>
            </form>
        </div>
        <%--<div id="cont"></div>--%>
        <%if (files != null) {%>
        <%if (files.size() > 0) {%>
        <div style="text-align:right;padding-right:25px;color:blue;">Найдено: <%=files.size()%>
        </div>
        <table class="tableRow" style="width:670px">
            <%for (File file : files) {%>
            <tr>
                <td>
                    <%=file.getFileName()%>
                </td>
                <td>
                    <%=file.getPlaceTerm()%>
                </td>
                <td>
                    <%=file.getTypeTerm()%>
                </td>
                <td>
                    <a class="viewFile" fileId="<%=file.getFileId()%>" href="#">
                        <img src="<%=request.getContextPath()%>/images/eye-icon.png"
                             title="Открыть для просмотра"/>
                    </a>
                    <a class="editFile" fileId="<%=file.getFileId()%>" href="#">
                        <img src="<%=request.getContextPath()%>/images/edit-icon.png"
                             title="Редактировать"/>
                    </a>
                    <a class="deleteFile" fileId="<%=file.getFileId()%>" href="#">
                        <img src="<%=request.getContextPath()%>/images/Delete-icon.png"
                             title="Удалить"/>
                    </a>
                </td>
            </tr>
            <%}%>
        </table>
        <%} else {%>
        <div class="infoMessage2">
            По вашему запросу ничего не найдено
        </div>
        <%}%>
        <%}%>
        <div style="clear:both;"></div>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalFile">

</div>