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

</script>
<div id="ticket" style="padding:20px">
    <form id="formTicket" action="<%=request.getContextPath()%>/edit/updateTicket.htm" method="post">
        <form:hidden path="ticket.ticketId"/>
        <form:hidden path="ticket.fileId"/>
        <table class="tableRow">
            <tr>
                <td>Kод перевозчика в АСОКУПЭ-Л</td>
                <td>
                    <form:input path="ticket.perevozGkey" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Категория поезда (скорый, пасс.)</td>
                <td>
                    <form:input path="ticket.trainCat" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Тарифный план (ID в БД АСОКУПЭ-Л)</td>
                <td>
                    <form:input path="ticket.tPlanID" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Станция отправления (Экспресс код)</td>
                <td>
                    <form:input path="ticket.fromStation" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Станция назначения (Экспресс код)</td>
                <td>
                    <form:input path="ticket.toStation" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Тип билета (разовый, абонемент)</td>
                <td>
                    <form:input path="ticket.ticketTypeID" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Льготная категория</td>
                <td>
                    <form:input path="ticket.ticketTypeL" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер билета / квитанции /операции</td>
                <td>
                    <form:input path="ticket.n" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Признак аннулирования</td>
                <td>
                    <form:input path="ticket.a" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Время операции</td>
                <td>
                    <form:input path="ticket.t" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Сумма оплаченная пассажиром</td>
                <td>
                    <form:input path="ticket.s" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Признак туда и обратно</td>
                <td>
                    <form:input path="ticket.r" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Дата предварительного проезда</td>
                <td>
                    <form:input path="ticket.p" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Сумма доплаты (сумма к выплате субъектом РФ, из федерального бюджета, из бюджета РЖД)</td>
                <td>
                    <form:input path="ticket.u" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Тип документа на льготу по КТО, ЭТТ, регионалы</td>
                <td>
                    <form:input path="ticket.v" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер льготного документа / ЭТТ с БСК</td>
                <td>
                    <form:input path="ticket.d" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Код билетного бюро</td>
                <td>
                    <form:input path="ticket.b" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Код организации</td>
                <td>
                    <form:input path="ticket.o" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Категория пассажира</td>
                <td>
                    <form:input path="ticket.h" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер ТК / СК / ЭТТ</td>
                <td>
                    <form:input path="ticket.c" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Кристалл ТК / СК / ЭТТ</td>
                <td>
                    <form:input path="ticket.k" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Фамилия льготника</td>
                <td>
                    <form:input path="ticket.f" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер аннулированного документа</td>
                <td>
                    <form:input path="ticket.z" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер ошибки</td>
                <td>
                    <form:input path="ticket.e" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Внутренний номер по ЭТТ с БСК</td>
                <td>
                    <form:input path="ticket.DK" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Стоимость услуги оформления ППД на ПКТК</td>
                <td>
                    <form:input path="ticket.col" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>НДС услуги оформления ППД на ПКТК</td>
                <td>
                    <form:input path="ticket.tax" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>НДС по ручной клади</td>
                <td>
                    <form:input path="ticket.NDS" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Номер бланка</td>
                <td>
                    <form:input path="ticket.Bl" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>СНИЛС</td>
                <td>
                    <form:input path="ticket.SN" readonly="${readonly}"/>
                </td>
            </tr>
            <tr>
                <td>Отчетный день, за который считать билет</td>
                <td>
                    <form:input path="ticket.timeCalcReport" readonly="${readonly}"/>
                </td>
            </tr>
        </table>
    </form>
</div>
