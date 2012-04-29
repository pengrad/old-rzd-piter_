<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="utils.Helper" %>
<%@ page import="objects.Link" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    Collection<SegmentInfo> segment = (Collection<SegmentInfo>) request.getAttribute("segment");
    Collection<Link> links = (Collection<Link>) request.getAttribute("links");
%>
<html>
<head>
    <title>Корректировка НСИ по направлениям, участкам производства и станциям</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/js/treeview/treeview.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/treeview/treeview.js"></script>

    <script type="text/javascript">

        $(document).ready(function() {

        });

        function saveSegment(e) {
            var tr = $(e).parent().parent();
            var idSegment = tr.find("input[name = segmentName]").val();
            var nameSegment = tr.find("input[name = segmentName]").val();
            $.ajax({
                type:'POST',
                url: tr.attr("action"),
                data:
                {
                    typeSegment:'<%=request.getAttribute("typeSegment")%>',
                    <%if(request.getAttribute("typeSegment").equals(Helper.segment.station)){%>
                    idSegment:idSegment,
                    <%}%>
                    segmentName:nameSegment
                },
                dataType: 'json',
                success: function(data) {
                    if (data.res == 'ok') {
                        tr.css("background", "white");
                        tr.find("input").remove();
                        tr.find("td:eq(0)").html(idSegment + '&nbsp' + nameSegment);
                        tr.find("td:eq(1)").empty().append("<a>").children(":eq(0)").attr({onclick:'deleteSegmente(this,10)',href:'#'}).html("<img src='<%=request.getContextPath()%>/images/edit-icon.png' title='Изменить'/>");
                        tr.find("td:eq(2)").empty().append("<a>").children(":eq(0)").attr({onclick:'deleteSegmente(this,10)',href:'#'}).html("<img src='<%=request.getContextPath()%>/images/Delete-icon.png' title = 'Удалить'/>");
                        alert("Данные успешно сохранены")
                    } else {
                        alert("Во время выполнения произошла ошибка")
                    }
                },
                error:function() {
                    alert("Во время выполнения произошла ошибка!!")
                }
            });
        }

        function cancelSegment(e) {
            $(e).parent().parent().remove();
        }

        function addSegment(e) {
            var table = $("#segments");
            var tr = $("<tr>").attr("action", "<%=request.getContextPath()%>/nsiSegment/add.htm").css("background", "lightpink");
        <%if(request.getAttribute("typeSegment").equals(Helper.segment.station)){%>
            $("<td>").css("width", "600px").html("<input  name='segmentId' type='text' size='10'/>&nbsp;<input name='segmentName' type='text' size='50'/>").appendTo(tr);
        <%}else{%>
            $("<td>").css("width", "600px").html("<input  name='segmentName' type='text' size='50'/>").appendTo(tr);
        <%}%>
            $("<td>").css("width", "20px").html("<img src='<%=request.getContextPath()%>/images/Ok-icon.png'/>").children(":eq(0)").css("cursor", "pointer").attr("onclick", "saveSegment(this)").parent().appendTo(tr);
            $("<td>").css("width", "20px").html("<img src='<%=request.getContextPath()%>/images/Delete-icon.png'/>").children(":eq(0)").css("cursor", "pointer").attr("onclick", "cancelSegment(this)").parent().appendTo(tr);
            var firstTR = table.find("tr:eq(0)");
            // alert(form.html());
            if (firstTR.length > 0) {
                firstTR.before(tr);
            } else {
                table.append(tr);
            }
        }


        function deleteSegmente(e, idSegment) {
            if (confirm('Вы действительно хотите удалить?')) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/nsiSegment/delete.htm',
                    data:{typeSegment:'<%=request.getAttribute("typeSegment")%>',idSegment:idSegment},
                    dataType: 'json',
                    beforeSend: function(xhr) {
                    },
                    success: function(data) {
                        if (data.res == 'ok') {
                            $(e).parent().parent().remove();
                            $("<div>").addClass("infoMessage").html("Данные успешно удалены").css({position:'absolute',top:'10px'}).appendTo("body").show(1).delay(1500).slideUp(400);
                        } else {
                            alert("Во время удаления произошла ошибка")
                        }
                    },
                    error:function() {
                        alert("Во время удаления произошла ошибка")
                    }
                });
            }
        }

    </script>

</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <a href="<%=request.getContextPath()%>/nsiSegment/direction.htm">Корректировка НСИ по станциям</a>
        <% if (links != null) {%>
        <%for (Link link : links) {%>
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <%if (link.getLink() != null) {%>
        <a href="<%=request.getContextPath()%>/<%=link.getLink()%>">
            <%=link.getName()%>
        </a>
        <%} else {%>
        <span>
              <%=link.getName()%>
        </span>
        <%}%>
        <%}%>
        <%}%>
    </div>
    <div>
        <div style="text-align:right;padding-right:10px">
            <input onclick="addSegment(this)" type="button" value="&nbsp;Добавить&nbsp;">
        </div>
        <%if (segment.size() > 0) {%>
        <table id="segments" class="tableRow" style="width:670px">
            <%for (SegmentInfo si : segment) {%>
            <tr>
                <td style="width:600px;">
                    <%if (!request.getAttribute("typeSegment").equals(Helper.segment.station)) {%>
                    <a href="<%=request.getContextPath()%>/nsiSegment/<%=request.getAttribute("typeSegmentForLink")%>.htm?idSegment=<%=si.getId()%>">
                        <%=si.getName()%>
                    </a>
                    <%} else {%>
                    <span><%=si.getId()%>&nbsp;<%=si.getName()%></span>
                    <%}%>
                </td>
                <td style="width:20px;">
                    <a onclick="editSegment(this,<%=si.getId()%>)" href="#">
                        <img src="<%=request.getContextPath()%>/images/edit-icon.png" alt="Изменить"/>
                    </a>
                </td>
                <td style="width:20px;">
                    <a onclick="deleteSegmente(this,<%=si.getId()%>)" href="#">
                        <img src="<%=request.getContextPath()%>/images/Delete-icon.png" alt="Удалить"/>
                    </a>
                </td>
            </tr>
            <%}%>
        </table>
        <%} else {%>
        <div class="infoMessage" style="text-align:center;font-size:20px;color:gray;padding:30px">
            Данные отсутствуют
        </div>
        <%}%>

    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalFile">

</div>