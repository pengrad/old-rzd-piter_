$(document).ready(function() {
    var ssDirection = $("<select>").attr("name", "direction").css("width", "200px");
    var ssSector = $("<select>").attr("name", "sector").css("width", "200px");
    var ssStation = $("<select>").attr("name", "station").css("width", "200px");
    $.ajax({
        url: '/rp/segmentSelect/getDirections.htm',
        dataType: 'json',
        async:false,
        success: function(data) {
            for (var i = 0; i < data.length; i++) {
                var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                $(ssDirection).append(ssOption);
            }
            $(".segmentSelect select[name=direction]").change();
        }
    });
    $(".segmentSelect").append(ssDirection).append(ssSector).append(ssStation);
    $(".segmentSelect select[name=direction]").live("change", function() {
        var dirId = $(this).find(":selected").val();
        $(ssSector).empty();
        $.ajax({
            url: '/rp/segmentSelect/getSectorByIdDirection.htm',
            dataType: 'json',
            data:{dirId:dirId},
            async:false,
            success: function(data) {
                for (var i = 0; i < data.length; i++) {
                    var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                    $(ssSector).append(ssOption);
                }
                $(".segmentSelect select[name=sector]").change();
            }
        });
    });
    $(".segmentSelect select[name=sector]").live("change", function() {
        var sectId = $(this).find(":selected").val();
        $(ssStation).empty();
        $.ajax({
            url: '/rp/segmentSelect/getStationByIdSector.htm',
            dataType: 'json',
            data:{sectId:sectId},
            async:false,
            success: function(data) {
                for (var i = 0; i < data.length; i++) {
                    var ssOption = $("<option>").attr("value", data[i].id).text(data[i].name);
                    $(ssStation).append(ssOption);
                }
            }
        });
    });
});

function selectStation(statId) {
    $.ajax({
        url: '/rp/segmentSelect/getDirectionByIdStation.htm',
        dataType: 'json',
        data:{statId:statId},
        async:false,
        success: function(data) {
            var dirId = data.id;
            $(".segmentSelect select[name=direction]").val(dirId);
            $(".segmentSelect select[name=direction]").change();
            $.ajax({
                url: '/rp/segmentSelect/getSectorByIdStation.htm',
                dataType: 'json',
                data:{statId:statId},
                async:false,
                success: function(data) {
                    var sectId = data.id;
                    $(".segmentSelect select[name=sector]").val(sectId);
                    $(".segmentSelect select[name=sector]").change();
                    $(".segmentSelect select[name=station]").val(statId);
                }
            });
        }
    });
}
