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
    //    Collection<Link> links = (Collection<Link>) request.getAttribute("links");
%>
<html>
<head>
    <title>Разъездные кассиры</title>
    <link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/js/treeview/treeview.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/js.js"></script>
    <link href="<%=request.getContextPath()%>/css/upload.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/css/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript">

    </script>


</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <a href="<%=request.getContextPath()%>/cashiers/view.htm">Корректировка разъедных кассиров</a>
        <%--<% if (links != null) {%>--%>
        <%--<%for (Link link : links) {%>--%>
        <%--<img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;--%>
        <%--<%if (link.getLink() != null) {%>--%>
        <%--<a href="<%=request.getContextPath()%>/<%=link.getLink()%>">--%>
        <%--<%=link.getName()%>--%>
        <%--</a>--%>
        <%--<%} else {%>--%>
        <%--<span>--%>
        <%--<%=link.getName()%>--%>
        <%--</span>--%>
        <%--<%}%>--%>
        <%--<%}%>--%>
        <%--<%}%>--%>
    </div>
    <div>
        <div style="text-align:right;padding-right:10px">
            <input type="button" value="&nbsp;Добавить&nbsp;">
        </div>
        <table class="tableRow" style="width:670px">
            <%--<%for (SegmentInfo si : segment) {%>--%>
            <%--<tr>--%>
            <%--<td style="width:600px;">--%>
            <%--<%if (!request.getAttribute("typeSegment").equals(Helper.segment.station)) {%>--%>
            <%--<a href="<%=request.getContextPath()%>/nsiSegment/<%=request.getAttribute("typeSegmentForLink")%>.htm?idSegment=<%=si.getId()%>">--%>
            <%--<%=si.getName()%>--%>
            <%--</a>--%>
            <%--<%} else {%>--%>
            <%--<span><%=si.getId()%>&nbsp;<%=si.getName()%></span>--%>
            <%--<%}%>--%>
            <%--</td>--%>
            <%--<td style="width:20px;">--%>
            <%--<a href="<%=request.getContextPath()%>/nsiSegment/edit.htm?typeSegment=<%=request.getAttribute("typeSegment")%>&idSegment=<%=si.getId()%>">--%>
            <%--<img src="<%=request.getContextPath()%>/images/edit-icon.png" alt="Изменить"/>--%>
            <%--</a>--%>
            <%--</td>--%>
            <%--<td style="width:20px;">--%>
            <%--<a onclick="deleteSegmente(this,<%=si.getId()%>)" href="#">--%>
            <%--<img src="<%=request.getContextPath()%>/images/Delete-icon.png" alt="Удалить"/>--%>
            <%--</a>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<%}%>--%>
        </table>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalFile">

</div>