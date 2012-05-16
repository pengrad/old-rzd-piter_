<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="objects.SegmentInfo" %>
<%@ page import="utils.Helper" %>
<%@ page import="objects.Link" %>
<%@ page import="objects.Cashiers" %>
<%@ page import="java.util.*" %>
<%@ page import="objects.PlanCashiers" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    //Collection<Link> links = (Collection<Link>) request.getAttribute("links");
    Cashiers cashiers = (Cashiers) request.getAttribute("cashiers");
    int year = (Integer) request.getAttribute("year");
    int month = (Integer) request.getAttribute("month");
    Collection<SegmentInfo> directions = (Collection<SegmentInfo>) request.getAttribute("directions");
    Collection<SegmentInfo> sectors = (Collection<SegmentInfo>) request.getAttribute("sectors");
    SegmentInfo direction = (SegmentInfo) request.getAttribute("direction");
    SegmentInfo sector = (SegmentInfo) request.getAttribute("sector");
    Helper.action action = (Helper.action) request.getAttribute("action");

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
        function view() {
            window.location.href = '<%=request.getContextPath()%>/cashiers/details/view.htm?idCashiers=<%=cashiers.getIdCashiers()%>&year=' + $("select[name=year]").val() + '&month=' + $("select[name=month]").val();
        }
        function save() {
            $(".errorMessage").remove();
            $.ajax({
                <%if(action.equals(Helper.action.add)){%>
                url: '/rp/cashiers/details/save.htm',
                <%}%>
                <%if(action.equals(Helper.action.edit)){%>
                url: '/rp/cashiers/details/update.htm',
                <%}%>
                dataType: 'json',
                data:$("#formCashiers").serialize(),
                type :'post',
                success: function(data) {
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            $("input[name=" + data[i].fieldName + "]").before("<span class='errorMessage'>" + data[i].errorMessage + "</span>");
                            $("textarea[name=" + data[i].fieldName + "]").before("<span class='errorMessage'>" + data[i].errorMessage + "</span>");
                        }
                    } else {
                        window.location.href = '<%=request.getContextPath()%>/cashiers/view.htm';
                    }
                }
            });
        }

        function updateSector(dirId) {
            var sector = $("select[name=sector]");
            $(sector).empty();
            $.ajax({
                url: '/rp/segmentSelect/getSectorByIdDirection.htm',
                dataType: 'json',
                data:{dirId:dirId},
                async:false,
                success: function(data) {
                    for (var i = 0; i < data.length; i++) {
                        var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                        $(sector).append(ssOption);
                    }
                }
            });
        }
        $(document).ready(function() {
            $("select[name=direction]").live("change", function() {
                var dirId = $(this).find(":selected").val();
                updateSector(dirId);
            });
        });
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


            var cashiersData = $("#cashiersData");
            var cashiersResult = $("#cashiersResult");
            cashiersResult.hide();
            cashiersData.show();
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

            var cashiersData = $("#cashiersData");
            var cashiersResult = $("#cashiersResult");
            cashiersData.hide();
            cashiersResult.show();
        }

    </script>
</head>
<body>
<jsp:include page="/pages/share/head.jsp"/>
<jsp:include page="/pages/share/menuAdministration.jsp"/>
<div id="data">
    <div class="infoPath">
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
        <a href="<%=request.getContextPath()%>/cashiers/view.htm">Корректировка разъедных кассиров</a>
        <img src="<%=request.getContextPath()%>/images/h3-bg.gif" alt="">&nbsp;
       <span>
             <%=cashiers.getFioCashiers()%>
       </span>
    </div>
    <div class="tabs">
        <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
            <tr>
                <td class="between"></td>
                <td><img id="v1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
                <td class="selected" onclick="v_1()">
                    <span>Персональные данные</span>
                </td>
                <td><img id="v1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
            </tr>
        </table>
    </div>
    <form id="formCashiers" action="<%=request.getContextPath()%>/cashiers/details/save.htm" method="post">
        <div id="cashiersData">
            <div style="text-align:right;padding-right:10px">
                <button onclick="save()" type="button">Сохранить</button>
                <a href="<%=request.getContextPath()%>/cashiers/view.htm">
                    <button>Отмена</button>
                </a>
            </div>
            <div style="margin:10px;">
                <div style="text-align:left;">Введите уникальный код кассира</div>
                <div style="text-align:left;">
                    <input  <%=action.equals(Helper.action.edit) ? "readonly" : ""%> name="idCashiers" value="<%=cashiers.getIdCashiers()%>" type="text" style="width:670px"/>
                </div>
                <div style="text-align:left;">Введите ФИО кассира</div>
                <div style="text-align:left;">
                    <input name="fioCashiers" value="<%=cashiers.getFioCashiers()%>" type="text" style="width:670px"/>
                </div>
                <div style="text-align:left;">Станция приписки</div>
                <div style="text-align:left;">
                    <select name="direction">
                        <%for (SegmentInfo si : directions) {%>
                        <option value="<%=si.getId()%>" <%=(si.getId() == direction.getId() ? "selected" : "")%>>
                            <%=si.getName()%>
                        </option>
                        <%}%>
                    </select>
                    <select name="sector">
                        <%for (SegmentInfo si : sectors) {%>
                        <option value="<%=si.getId()%>" <%=(si.getId() == sector.getId() ? "selected" : "")%>>
                            <%=si.getName()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <div style="text-align:left;">Прочие сведения</div>
                <div style="text-align:left;">
                    <textarea name="comment" type="text" style="width:670px"></textarea>
                </div>
            </div>
            <div>
                <div style="border-top:1px solid gray;padding-left:10px;padding-top:10px;text-align:left;">
                    <span>Год</span>
                    <select name="year">
                        <%
                            GregorianCalendar gc = new GregorianCalendar();
                            for (int i = gc.get(Calendar.YEAR) - 5; i <= gc.get(Calendar.YEAR); i++) {
                        %>
                        <option <%=(i == year ? "selected" : "")%>>
                            <%=i%>
                        </option>
                        <%}%>
                    </select>
                    <span>Месяц</span>
                    <select name="month">
                        <%for (int i = 1; i <= 12; i++) {%>
                        <option <%=(i == month ? "selected" : "")%>>
                            <%=i%>
                        </option>
                        <%}%>
                    </select>
                    <button type="button" onclick="view()" onblur="">Смотреть</button>
                </div>
                <table class="tableRow" style="width:670px">
                    <tr>
                        <th style="width:80px">Число</th>
                        <th style="width:190px">№ маршрута</th>
                        <th style="width:200px">План по выручке (общий)</th>
                        <th style="width:200px">План по выручке (ОАО "РЖДf")</th>
                    </tr>
                    <%
                        int i = 1;
                        for (PlanCashiers pc : cashiers.getPlanCashiers()) {
                    %>
                    <tr>
                        <td>
                            <input name="date<%=i%>" type="text" style="border:none;width:70px" readonly value="<%=new SimpleDateFormat("dd.MM.yyyy").format(pc.getDate())%>"/>
                        </td>
                        <td>
                            <input name="routeNumber<%=i%>" type="text" style="width:170px" value="<%=pc.getRouteNumber()%>"/>
                        </td>
                        <td>
                            <input name="planBase<%=i%>" type="text" style="width:170px" value="<%=pc.getPlanBase()%>"/>
                        </td>
                        <td>
                            <input name="planRzd<%=i%>" type="text" style="width:170px" value="<%=pc.getPlanRzd()%>"/>
                        </td>
                    </tr>
                    <%
                            i++;
                        }
                    %>
                </table>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/pages/share/footer.jsp"/>
</body>
</html>
