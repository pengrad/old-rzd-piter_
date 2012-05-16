<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="utils.Helper" %>
<%@ page import="objects.Link" %>
<%@ page import="objects.Cashiers" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    //    Collection<Link> links = (Collection<Link>) request.getAttribute("links");
    Collection<Cashiers> cashiers = (Collection<Cashiers>) request.getAttribute("cashiers");
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/segmentSelect.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/validationForm.js"></script>

    <script type="text/javascript">
//        $(document).ready(function() {
//            $(".addCashiers").live("click", function() {
//                $("#formCashiers input[name=idCashiers]").removeAttr("readonly");
//                $("#modalCashiers").dialog({ buttons: [
//                    {
//                        text: "Сохранить",
//                        click: function() {
//                            valid('formCashiers', '/rp/cashiers/add.htm');
//                        }
//                    },
//                    {
//                        text: "Отмена",
//                        click: function() {
//                            $(this).dialog("close");
//                        }
//                    }
//                ],
//                    height: 300,
//                    width:450,
//                    modal: true,
//                    open: function(event, ui) {
//                        $("#formCashiers input").val("");
//                    }});
//                return false;
//            });
//
//            $(".updateCashiers").live("click", function() {
//                var idCashiers = $(this).attr("idCashiers");
//                $("#formCashiers input[name=idCashiers]").attr("readonly", true);
//                $.ajax({
//                    url: '/rp/cashiers/get.htm',
//                    dataType: 'json',
//                    data:{idCashiers:idCashiers},
//                    async:false,
//                    success: function(data) {
//                        $("#formCashiers input[name=idCashiers]").val(data.idCashiers);
//                        $("#formCashiers input[name=fioCashiers]").val(data.fioCashiers);
//                        selectStation(data.station);
//                    }
//                });
//                $("#modalCashiers").dialog({ buttons: [
//                    {
//                        text: "Сохранить",
//                        click: function() {
//                            valid('formCashiers', '/rp/cashiers/update.htm');
//                        }
//                    },
//                    {
//                        text: "Отмена",
//                        click: function() {
//                            $(this).dialog("close");
//                        }
//                    }
//                ],
//                    height: 300,
//                    width:450,
//                    modal: true,
//                    open: function(event, ui) {
//                    }});
//                return false;
//            });
//        });
//
//        $(".deleteCashiers").live("click", function() {
//            if (confirm("Вы действительно хотите удалить запись?")) {
//                var idCashiers = $(this).attr("idCashiers");
//                $.ajax({
//                    url: '/rp/cashiers/delete.htm',
//                    dataType: 'json',
//                    data:{idCashiers:idCashiers},
//                    async:false,
//                    success: function(data) {
//                        backToPage();
//                    }
//                });
//            }
//        });

//        function backToPage() {
//            window.location.reload();
//        }


    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <a href="<%=request.getContextPath()%>/cashiers/view.htm">Корректировка разъедных кассиров</a>
    </div>
       <div>
        <div style="text-align:right;padding-right:10px">
            <input class="addCashiers" type="button" value="&nbsp;Добавить&nbsp;" onclick="window.location.href='<%=request.getContextPath()%>/cashiers/details/add.htm'"/>
        </div>
        <table class="tableRow" style="width:670px">
            <tr>
                <th>Код</th>
                <th>ФИО</th>
                <th>Участок</th>
            </tr>
            <%for (Cashiers cash : cashiers) {%>
            <tr>
                <td style="width:70px;">
                    <%=cash.getIdCashiers()%>
                </td>
                <td style="width:300px;">
                    <a href="<%=request.getContextPath()%>/cashiers/details/view.htm?idCashiers=<%=cash.getIdCashiers()%>">
                        <%=cash.getFioCashiers()%>
                    </a>
                </td>
                <td style="width:300px;">
                    <%=cash.getNameSector()%>
                </td>
                <%--<td style="width:20px;">--%>
                <%--<img style="cursor:pointer" class="updateCashiers" idCashiers="<%=cash.getIdCashiers()%>" src="<%=request.getContextPath()%>/images/edit-icon.png" alt="Изменить"/>--%>
                <%--</td>--%>
                <%--<td style="width:20px;">--%>
                <%--<img style="cursor:pointer" class="deleteCashiers" idCashiers="<%=cash.getIdCashiers()%>" src="<%=request.getContextPath()%>/images/Delete-icon.png" alt="Удалить"/>--%>
                <%--</td>--%>
            </tr>
            <%}%>
        </table>
    </div>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
<div id="modalCashiers" style="display:none;">
    <form id="formCashiers">
        <div style="text-align:left;">Введите уникальный код кассира</div>
        <div><input name="idCashiers" type="text" style="width:400px"/></div>
        <div style="text-align:left;">Введите ФИО кассира</div>
        <div><input name="fioCashiers" type="text" style="width:400px"/></div>
        <div style="text-align:left;">Станция приписки</div>
        <div class="segmentSelect"></div>
    </form>
</div>