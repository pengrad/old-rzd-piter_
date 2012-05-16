function valid(idForm, action) {
    var form = $("#" + idForm);
    //    var text = $(e).val();
    //    $(e).attr("disabled", "disabled");
    //    $(e).val("Подождите...");
    var aa;
    if (action == null) {
        aa = $(form).attr("action")
    } else {
        aa = action;
    }
    $.ajax({
        url: aa,
        dataType: "json",
        type: "POST",
        data:$(form).serialize(),
        cache: false,
        beforeSend : function(xhr) {
            $(form).find("div.error").remove();
            xhr.setRequestHeader("X-AjaxRequest", "1");
        },
        success: function(response) {
            if (response.length == 0) {
                alert("Данные успешно изменены");
                backToPage();
            } else {
                for (var i = 0; i < response.length; i++) {
                    $(form).find("textarea[name=" + response[i].fieldName + "]").before("<div class='error' style='color:red'>" + response[i].errorMessage + "</div>");
                    $(form).find("input[name=" + response[i].fieldName + "]").before("<div class='error' style='color:red'>" + response[i].errorMessage + "</div>");
                }
            }
        },
        error: function(response) {
            if (response.status == 401) {
                alert("Превышено время простоя системы, приложение будет перезапущено");
            } else if (response.status == 403) {
                alert("У вас нат доступа к этой странице");
            } else {
                alert("Во время выполнения произошла ошибка. код ошибки - " + response.status);
            }
        },
        complete: function() {
            //            $(e).removeAttr("disabled");
            //            $(e).val(text);
        },
        statusCode: {}
    });
}



