<%@ page import="objects.File" %>
<%--
  Created by IntelliJ IDEA.
  User: Evgeniy
  Date: 16.02.2012
  Time: 23:30:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function vf_1() {
        $("#vf1_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
        $($("#vf1_l").parents()[0]).next().attr('class', 'selected');
        $("#vf1_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');

        $("#vf2_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#vf2_l").parents()[0]).next().attr('class', 'unselected');
        $("#vf2_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');
        var file = $("#file");
        var ticket = $("#tickets");
        ticket.hide();
        file.show();
    }
    function vf_2() {
        $("#vf2_l").attr('src', '<%=request.getContextPath()%>/images/active_left.gif');
        $($("#vf2_l").parents()[0]).next().attr('class', 'selected');
        $("#vf2_r").attr('src', '<%=request.getContextPath()%>/images/active_right.gif');
        $("#vf1_l").attr('src', '<%=request.getContextPath()%>/images/inactive_left.gif');
        $($("#vf1_l").parents()[0]).next().attr('class', 'unselected');
        $("#vf1_r").attr('src', '<%=request.getContextPath()%>/images/inactive_right.gif');
        var file = $("#file");
        var ticket = $("#tickets");
        file.hide();
        ticket.show();
    }

    $(document).ready(function() {
        $(".viewTicket").live("click", function() {
            var ticketId = $(this).attr("ticketId");
            $("#modalTicket").dialog({ buttons: [
                {
                    text: "Закрыть",
                    click: function() {
                        $(this).dialog("close");
                    }
                }
            ],
                height: 700,
                width:900,
                modal: true,
                open: function(event, ui) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/edit/viewTicket.htm',
                        data:{ticketId:ticketId},
                        dataType: 'html',
                        beforeSend: function(xhr) {
                            $("#modalTicket").empty();
                            $("#modalTicket").append("<div class='infoMessage2'>Подождите...</div>");
                        },
                        success: function(data) {
                            $("#modalTicket").empty();
                            $("#modalTicket").append(data);
                        },
                        error:function() {
                            $("#modalTicket").empty();
                            $("#modalTicket").append("<div class='infoMessage2'>Во время получения данных, возникла ошибка, попробуте еще раз</div>");

                        }
                    });
                }});
            return false;
        });
        $(".editTicket").live("click", function() {
            var ticketId = $(this).attr("ticketId");
            $("#modalTicket").dialog({ buttons: [
                {
                    text: "Сохранить",
                    click: function() {
                        valid('formTicket');
                    }
                },
                {
                    text: "Закрыть",
                    click: function() {
                        $(this).dialog("close");
                    }
                }
            ],
                height: 700,
                width:900,
                modal: true,
                open: function(event, ui) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/edit/editTicket.htm',
                        data:{ticketId:ticketId},
                        dataType: 'html',
                        beforeSend: function(xhr) {
                            $("#modalTicket").empty();
                            $("#modalTicket").append("<div class='infoMessage2'>Подождите...</div>");
                        },
                        success: function(data) {
                            $("#modalTicket").empty();
                            $("#modalTicket").append(data);
                        },
                        error:function() {
                            $("#modalTicket").empty();
                            $("#modalTicket").append("<div class='infoMessage2'>Во время получения данных, возникла ошибка, попробуте еще раз</div>");

                        }
                    });
                }});
            return false;
        });
        $(".deleteTicket").live("click", function() {
            var tr = $(this).parent().parent().parent();
            var ticketId = $(this).attr("ticketId");
            var r = window.confirm("Вы действительно хотите произвести удаление записи?");
            if (r == 1) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/edit/deleteTicket.htm',
                    data:{ticketId:ticketId},
                    dataType: 'json',
                    success: function(data) {
                        if (data) {
                            alert("Запись успешо удалена");
                            $(tr).remove();
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
</script>
<div class="tabs">
    <table cellpadding="0" cellspacing="0" style="position:relative;bottom:-3px">
        <tr>
            <td class="between"></td>
            <td><img id="vf1_l" src="<%=request.getContextPath()%>/images/active_left.gif"/></td>
            <td class="selected" onclick="vf_1()">
                <span>Общие сведения</span>
            </td>
            <td><img id="vf1_r" src="<%=request.getContextPath()%>/images/active_right.gif"/></td>
            <td class="between"></td>
            <td><img id="vf2_l" src="<%=request.getContextPath()%>/images/inactive_left.gif"/></td>
            <td class="unselected" onclick="vf_2()">
                <span>Билеты</span>
            </td>
            <td style="margin-left:10px;">
                <img id="vf2_r" src="<%=request.getContextPath()%>/images/inactive_right.gif"/>
            </td>
        </tr>
    </table>
</div>

<div id="file" style="padding:20px">
<form id="formFile" action="<%=request.getContextPath()%>/edit/updateFile.htm" method="post">
<form:hidden path="file.fileId"/>
<table class="tableRow">
<tr>
    <td>Имя XML документа</td>
    <td>
        <form:input path="file.fileName" readonly="${readonly}"/>
        <%--<form:errors path="file.FileName"/>--%>
    </td>
</tr>
<tr>
    <td>Номер терминала</td>
    <td>
        <form:input path="file.numTerm" readonly="${readonly}"/>
        <%--<form:errors path="file.NumTerm"/>--%>
    </td>
</tr>
<tr>
    <td>Номер смены</td>
    <td>
        <form:input path="file.smenaNum" readonly="${readonly}"/>
        <%--<form:errors path="file.SmenaNum"/>--%>
    </td>
</tr>
<tr>
    <td>Станция приписки</td>
    <td>
        <form:input path="file.placeTerm" readonly="${readonly}"/>
    </td>
</tr>
<tr>
    <td>Номер месяца</td>
    <td>
        <form:input path="file.month" readonly="${readonly}"/>
        <%--<form:errors path="file.Month"/>--%>
    </td>
</tr>
<tr>
    <td>Дата и время открытия смены</td>
    <td>
        <form:input path="file.timeOpen" readonly="${readonly}"/>
        <%--<form:errors path="file.TimeOpen"/>--%>
    </td>
</tr>
<tr>
    <td>Порядковый номер пробного билета</td>
    <td>
        <form:input path="file.firstTicket" readonly="${readonly}"/>
        <%--<form:errors path="file.FirstTicket"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма смены (денежная выручка за рабочую смену)</td>
    <td>
        <form:input path="file.sum" readonly="${readonly}"/>
        <%--<form:errors path="file.Sum"/>--%>
    </td>
</tr>
<tr>
    <td>Дата и время закрытия смены</td>
    <td>
        <form:input path="file.timeClose" readonly="${readonly}"/>
        <%--<form:errors path="file.TimeClose"/>--%>
    </td>
</tr>
<tr>
    <td>Количество оформленных документов</td>
    <td>
        <form:input path="file.numTickets" readonly="${readonly}"/>
        <%--<form:errors path="file.NumTickets"/>--%>
    </td>
</tr>
<tr>
    <td>Длина ленты(метраж)</td>
    <td>
        <form:input path="file.lenTape" readonly="${readonly}"/>
        <%--<form:errors path="file.LenTape"/>--%>
    </td>
</tr>
<tr>
    <td>Тип терминала</td>
    <td>
        <form:input path="file.typeTerm" readonly="${readonly}"/>
        <%--<form:errors path="file.TypeTerm"/>--%>
    </td>
</tr>
<tr>
    <td>Версия ПО</td>
    <td>
        <form:input path="file.softVersion" readonly="${readonly}"/>
        <%--<form:errors path="file.SoftVersion"/>--%>
    </td>
</tr>
<tr>
    <td>ИНН владельца терминала (организации при постановке на налоговый учет)</td>
    <td>
        <form:input path="file.INN" readonly="${readonly}"/>
        <%--<form:errors path="file.INN"/>--%>
    </td>
</tr>
<tr>
    <td>ФИО кассира</td>
    <td>
        <form:input path="file.FIO" readonly="${readonly}"/>
        <%--<form:errors path="file.FIO"/>--%>
    </td>
</tr>
<tr>
    <td>Возвращено карт (Сумма принятого залога)</td>
    <td>
        <form:input path="file.cardOut" readonly="${readonly}"/>
        <%--<form:errors path="file.CardOut"/>--%>
    </td>
</tr>
<tr>
    <td>Выдано карт (Сумма возвращенного залога)</td>
    <td>
        <form:input path="file.cardIn" readonly="${readonly}"/>
        <%--<form:errors path="file.CardIn"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма доплаты льготных билетов (выпадающий доход за рабочую смену)</td>
    <td>
        <form:input path="file.sup" readonly="${readonly}"/>
        <%--<form:errors path="file.Sup"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма анулированных билетов</td>
    <td>
        <form:input path="file.cancel" readonly="${readonly}"/>
        <%--<form:errors path="file.Cancel"/>--%>
    </td>
</tr>
<tr>
    <td>Количество билетов (для p97)</td>
    <td>
        <form:input path="file.numProc" readonly="${readonly}"/>
        <%--<form:errors path="file.NumProc"/>--%>
    </td>
</tr>
<tr>
    <td>Выручка (сумма билетов для p97)</td>
    <td>
        <form:input path="file.sumProc" readonly="${readonly}"/>
        <%--<form:errors path="file.SumProc"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма в ЭКЛЗ</td>
    <td>
        <form:input path="file.sumEKLZ" readonly="${readonly}"/>
        <%--<form:errors path="file.SumEKLZ"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма сборов за оформление ППД</td>
    <td>
        <form:input path="file.sCol" readonly="${readonly}"/>
        <%--<form:errors path="file.SCol"/>--%>
    </td>
</tr>
<tr>
    <td>НДС за оформление ППД</td>
    <td>
        <form:input path="file.sTax" readonly="${readonly}"/>
        <%--<form:errors path="file.STax"/>--%>
    </td>
</tr>
<tr>
    <td>Расход бланков</td>
    <td>
        <form:input path="file.blank" readonly="${readonly}"/>
        <%--<form:errors path="file.Blank"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма возврата по аварийным квитанциям</td>
    <td>
        <form:input path="file.sumRet" readonly="${readonly}"/>
        <%--<form:errors path="file.SumRet"/>--%>
    </td>
</tr>
<tr>
    <td>Сумма услуги</td>
    <td>
        <form:input path="file.sumServ" readonly="${readonly}"/>
        <%--<form:errors path="file.SumServ"/>--%>
    </td>
</tr>
<tr>
    <td>НДС услуги</td>
    <td>
        <form:input path="file.NDSServ" readonly="${readonly}"/>
        <%--<form:errors path="file.NDSServ"/>--%>
    </td>
</tr>
<tr>
    <td>Дата записи файла</td>
    <td>
        <form:input path="file.timeCreate" readonly="${readonly}"/>
        <%--<form:errors path="file.TimeCreate"/>--%>
    </td>
</tr>
</table>
</form>
</div>
<div id="tickets" style="padding:20px;display:none;">
    <table class="tableRow">
        <tr>
            <th>Код в АСОКУПЭ-Л</th>
            <th>Категория поезда</th>
            <th>ID в БД АСОКУПЭ-Л</th>
            <th>Станция отправления</th>
            <th>Станция назначения</th>
            <th>ID типа билета</th>
            <th>Код льготы</th>
            <th>Номер билета</th>
            <th>Признак аннулирования</th>
            <th>Сумма оплаты</th>
            <th>Признак туда и обратно</th>
            <th>Сумма доплаты</th>
            <th></th>
        </tr>
        <c:forEach var="ticket" items="${file.tickets}">
            <tr>
                <td>${ticket.perevozGkey}</td>
                <td>${ticket.trainCat}</td>
                <td>${ticket.TPlanID}</td>
                <td>${ticket.fromStation}</td>
                <td>${ticket.toStation}</td>
                <td>${ticket.ticketTypeID}</td>
                <td>${ticket.ticketTypeL}</td>
                <td>${ticket.n}</td>
                <td>${ticket.a}</td>
                <td>${ticket.s}</td>
                <td>${ticket.r}</td>
                <td>${ticket.u}</td>
                <td>
                    <c:if test="${readonly!=true}">
                        <nobr>
                            <a href="#" class="viewTicket" ticketId="${ticket.ticketId}">
                                <img src="<%=request.getContextPath()%>/images/eye-icon.png"
                                     title="Смотреть"/>
                            </a>
                            <a href="#" class="editTicket" ticketId="${ticket.ticketId}">
                                <img src="<%=request.getContextPath()%>/images/edit-icon.png"
                                     title="Редактировать"/>
                            </a>
                            <a href="#" class="deleteTicket" ticketId="${ticket.ticketId}">
                                <img src="<%=request.getContextPath()%>/images/Delete-icon.png"
                                     title="Удалить"/>
                            </a>
                        </nobr>
                    </c:if>
                    <c:if test="${readonly}">
                        <a href="#" class="viewTicket" ticketId="${ticket.ticketId}">
                            <img src="<%=request.getContextPath()%>/images/eye-icon.png"
                                 title="Смотреть"/>
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="modalTicket"></div>