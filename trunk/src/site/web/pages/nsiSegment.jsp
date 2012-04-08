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
            $("#browser").treeview();
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
            <a href="<%=request.getContextPath()%>/mon/direction.htm">Корректировка НСИ по станциям</a>
        </span>
    </div>
    <div>
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
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalFile">

</div>