jQuery(function($) {
    $.datepicker.regional['ru'] = {
        closeText: 'Закрыть',
        prevText: '&#x3c;Пред',
        nextText: 'След&#x3e;',
        currentText: 'Сегодня',
        monthNames: ['Январь','Февраль','Март','Апрель','Май','Июнь',
            'Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'],
        monthNamesShort: ['Янв','Фев','Мар','Апр','Май','Июн',
            'Июл','Авг','Сен','Окт','Ноя','Дек'],
        dayNames: ['воскресенье','понедельник','вторник','среда','четверг','пятница','суббота'],
        dayNamesShort: ['вск','пнд','втр','срд','чтв','птн','сбт'],
        dayNamesMin: ['Вс','Пн','Вт','Ср','Чт','Пт','Сб'],
        weekHeader: 'Нед',
        dateFormat: 'dd.mm.yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''};
    $.datepicker.setDefaults($.datepicker.regional['ru']);
});
$(document).ready(function() {
    $("#timeCalcReport input[name=typeTimeCalcReport]").bind("click", function() {
        showTimeCalcReport(this);
    });
    /* Russian (UTF-8) initialisation for the jQuery UI date picker plugin. */
    /* Written by Andrew Stromnov (stromnov@gmail.com). */


    $(".datepicker").datepicker({
        showOn: 'both',
        buttonImageOnly: true,
        buttonImage: '/rp/images/calendar-icon.png'
    });

});

//function showTimeCalcReport(e) {
//    if ($(e).val() == 2) {
//        $("#cTimeCalcReport").show();
//    } else {
//        $("#cTimeCalcReport").hide();
//    }
//}












