
function initCompleteUser() {
    $("[completeBrand]").each(function () {
        $(this).autocomplete({
            source: function (request, response) {
                $.ajax({
                    type: "POST",
                    url: "/user/manage/findBrand?timestamp=" + new Date().getTime(),
                    dataType: "json",
                    cache: false,
                    data: {
                        name: request.term
                    },
                    success: function (data) {
                        var arr = new Array();
                        $.each(data, function (key, val) {
                            arr[key] = {label: val.name, id: val.brandId};
                        });
                        response(arr);
                    }
                });
            },
            select:function(event, ui){
                var id=ui.item.id;
                var name=ui.item.label;
                $('.car_show').append('<div class="col-xs-6 col-md-2"><span  class="btn btn-warning btn-xs span_series_name" tagVal="0_' + id + '" >' + name + '</span></div>');
                $('.car_show').append('<input type="hidden"  name="seriesIds"  value="0_' + id + '"  />');
                var cond = {};
                cond.brandId = id;
                $.localAjax('./getSeries', cond, function (result) {
                    $('.car_select').empty();
                    $('.car_select').append('<option value="">请选择</option>');
                    $.each(result.data, function (index, obj) {
                        $('.car_select').append('<option  value="' + obj.seriesId + '_' + obj.brandId + '">' + obj.name + '</option>');
                    })
                })
            },
            change:function( event, ui ){
                if(ui.item==null||ui.item==undefined||ui.item==''){
                    $(this).val('');
                    $(this).change();
                }
            },
            minLength: 1
        });
    });


    $("[completeSeries]").each(function () {
        $(this).autocomplete({
            source: function (request, response) {
                $.ajax({
                    type: "POST",
                    url: "/user/manage/findBrand?timestamp=" + new Date().getTime(),
                    dataType: "json",
                    cache: false,
                    data: {
                        name: request.term
                    },
                    success: function (data) {
                        var arr = new Array();
                        $.each(data, function (key, val) {
                            arr[key] = {label: val.name, id: val.brandId};
                        });
                        response(arr);
                    }
                });
            },
            select:function(event, ui){
                console.log(ui);
                var id=ui.item.id;
                var cond = {};
                cond.brandId = id;
                $.localAjax('/user/manage/getSeries', cond, function (result) {
                    $('.car_select').empty();
                    $('.car_select').append('<option value="">请选择</option>');
                    $.each(result.data, function (index, obj) {
                        $('.car_select').append('<option  value="'+obj.seriesId +'">' + obj.name + '</option>');
                    })
                })
            },
            change:function( event, ui ){
                if(ui.item==null||ui.item==undefined||ui.item==''){
                    $(this).val('');
                    $(this).change();
                }
            },
            minLength: 1
        });
    });


}

$(function(){
    initCompleteUser();
});

