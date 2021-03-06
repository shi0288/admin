/**
 * Created by shiqm on 2017/3/24.
 */


$(function () {
    //上传图
    $("body").on("change", ".createPic", function () {
        var id = $(this).attr("id");
        var imgId = id.replace('createPic', 'pic');
        $.picUpload($(this).attr("id"), function (result) {
            $("#" + imgId).attr('src', result.data);
        });
    })
    
    $("body").on("change", ".createNewPic", function () {
        var id = $(this).attr("id");
        var imgId = id.replace('createNewPic', 'picNew');
        $.picUpload($(this).attr("id"), function (result) {
            $("#" + imgId).attr('src', result.data);
        });
    })
    $("body").on("change", ".createbackground", function () {
        var id = $(this).attr("id");
        var imgId = id.replace('createbackground', 'background');
        $.picUpload($(this).attr("id"), function (result) {
            $("#" + imgId).attr('src', result.data);
        });
    })

    $("#my_submit").on('click', function () {
        var target = $(this).attr('tagVal');
        if (target != 'PC' && target != 'MOBILE') {
            alert('类型错误');
            return;
        }
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.title = $(this).find('.pic_title').val();
            body.img = $(this).find('.pic_img').attr('src');
            body.url = $(this).find('.pic_url').val();
            var appUrl = $(this).find('.pic_app_url').val();
            if (appUrl != undefined && appUrl != null && appUrl.length > 0) {
                body.appUrl = appUrl;
            }
            arrBody.push(body);
        });
        var cond = {};
        cond.target = target;
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/saveBanner', cond, function (result) {
            alert('保存成功');
        })
    })


    $("#but_notice_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.text = $(this).find('.pic_title').val();
//            if (body.text.length > 14) {
//                alert('不能大于14个字');
//                return;
//            }
            body.url = $(this).find('.pic_url').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/saveNotice', cond, function (result) {
            alert('保存成功');
        })
    })


    $("#but_expert_submit").on('click', function () {
        var jsonObj = {};
        jsonObj.img = $('#pic_expert').attr('src');
        jsonObj.url = $('.pic_url').val();
        var cond = {};
        cond.content = JSON.stringify(jsonObj);
        $.localAjax('/user/business/saveExpertPic', cond, function (result) {
            alert('保存成功');
        })
    })


    $("#but_business_user_submit").on('click', function () {
        var businessUserArr = $('#business_user_name').val();
        if (businessUserArr == undefined || businessUserArr == '') {
            alert('不能运营ID为空');
            return;
        }
        businessUserArr = businessUserArr.split(',');
        var cond = {};
        cond.content = businessUserArr.toRString();
        $.localAjax('/user/business/saveBusinessUser', cond, function (result) {
            alert('保存成功');
        })
    })

    $(".pic_body").delegate("a[name=moveTop]", "click", function () {
        var curContentObj = $(this).closest(".pic_body");
        var preContentObj = curContentObj.prev();
        if (preContentObj.length == 0 || !preContentObj.hasClass('pic_body')) {
            return;
        }
        var curNum = curContentObj.find('.pic_number').text();
        var preNum = preContentObj.find('.pic_number').text();
        curContentObj.find('.pic_number').text(preNum);
        preContentObj.before(curContentObj);
        preContentObj.find('.pic_number').text(curNum);
    })
    
    $(".body_array").delegate("a[name=delete]", "click", function () {
        var curContentObj = $(this).closest(".pic_body");
        curContentObj.remove();
    })


    //add
    $(".table").delegate("a[name=moveTop]", "click", function () {
        var curContentObj = $(this).closest("tr");
        var preContentObj = curContentObj.prev();
        if (preContentObj.length == 0 || preContentObj.get(0).tagName != 'TR') {
            return;
        }
        var curNum = curContentObj.find('.index_number').text();
        var preNum = preContentObj.find('.index_number').text();
        curContentObj.find('.index_number').text(preNum);
        preContentObj.before(curContentObj);
        preContentObj.find('.index_number').text(curNum);
    })


    //add
    $(".pic_body").delegate("a[name=moveBottom]", "click", function () {
        var curContentObj = $(this).closest(".pic_body");
        var nextContentObj = curContentObj.next();
        if (nextContentObj.length == 0 || !nextContentObj.hasClass('pic_body')) {
            return;
        }
        var curNum = curContentObj.find('.pic_number').html();
        var nextNum = nextContentObj.find('.pic_number').html();
        curContentObj.find('.pic_number').html(nextNum);
        nextContentObj.after(curContentObj);
        nextContentObj.find('.pic_number').html(curNum);
    })


    //add
    $(".table").delegate("a[name=moveBottom]", "click", function () {
        var curContentObj = $(this).closest("tr");
        var nextContentObj = curContentObj.next();
        if (nextContentObj.length == 0 || nextContentObj.get(0).tagName != 'TR') {
            return;
        }
        var curNum = curContentObj.find('.index_number').html();
        var nextNum = nextContentObj.find('.index_number').html();
        curContentObj.find('.index_number').html(nextNum);
        nextContentObj.after(curContentObj);
        nextContentObj.find('.index_number').html(curNum);
    })


    $("#but_topic_submit").on('click', function () {
        var body = {};
        body.title = $('.pic_title').val();
        body.content = $('.pic_content').val();
        body.url = $('.pic_url').val();
        var cond = {};
        cond.content = JSON.stringify(body);
        $.localAjax('/user/business/saveTopic', cond, function (result) {
            alert('保存成功');
        })
    })


    //tr_body
    $("#but_information_submit").on('click', function () {
        var arrBody = new Array();
        $('.tr_body').each(function () {
            var body = {};
            body.content = $(this).find('.pic_content').val();
            body.id = $(this).find('.pic_id').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/savenInformation', cond, function (result) {
            alert('保存成功');
        })
    })

    $("#but_adver1_submit").on('click', function () {
        var jsonObj = {};
        jsonObj.picture = $('#pic_expert').attr('src');
        jsonObj.url = $('.pic_url').val();
        var cond = {};
        cond.content = JSON.stringify(jsonObj);
        $.localAjax('/user/business/savenAdver1', cond, function (result) {
            alert('保存成功');
        })
    })

    $("#but_adver2_submit").on('click', function () {
        var jsonObj = {};
        jsonObj.picture = $('#pic_expert').attr('src');
        jsonObj.url = $('.pic_url').val();
        var cond = {};
        cond.content = JSON.stringify(jsonObj);
        $.localAjax('/user/business/savenAdver2', cond, function (result) {
            alert('保存成功');
        })
    })

    $("#but_userExpert_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.picture = $(this).find('.pic_img').attr('src');
            body.title = $(this).find('.pic_title').val();
            body.name = $(this).find('.pic_name').val();
            body.id = $(this).find('.pic_id').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/savenuserExpert', cond, function (result) {
            alert('保存成功');
        })
    })

    $("#but_popular_topic_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.title = $(this).find('.pic_title').val();
            body.img = $(this).find('.pic_img').attr('src');
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/savenPopularTopic', cond, function (result) {
            alert('保存成功');
        })
    })
    
    $("#but_one_informationList_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.title = $(this).find('.pic_title').val();
            body.img = $(this).find('.pic_img').attr('src');
            body.key = $(this).find('.pic_key').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/saveOneInformation', cond, function (result) {
            alert('保存成功');
        })
    })
    
    $("#recommend_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.title = $(this).find('.pic_title').val();
            body.subject = $(this).find('.pic_subject').val();
            body.img = $(this).find('.pic_img').attr('src');
            body.newimg = $(this).find('.picNew_img').attr('src');
            body.background = $(this).find('.background_img').attr('src');
            body.url = $(this).find('.pic_url').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/saveRecommendTopic', cond, function (result) {
            alert('保存成功');
        })
    })
    
    $("#adver_home_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.id = $(this).find('.pic_id').val();
            body.title = $(this).find('.pic_title').val();
            body.img = $(this).find('.pic_img').attr('src');
            body.url = $(this).find('.pic_url').val();
            body.hurl = $(this).find('.pic_h5_url').val();
            body.murl = $(this).find('.pic_m_url').val();
            body.purl = $(this).find('.pic_pc_url').val();
            body.wxurl = $(this).find('.pic_wx_url').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/saveAdverHome', cond, function (result) {
            alert('保存成功');
        })
    })
    
     $("#adver_home_add").on('click', function () {       
        var number=0;
        /*$('.pic_body').each(function () {
        	var body = {};
            number = $(this).find('.pic_number').text();  
            
        });*/
        number=$('.pic_body').length;
        number=parseInt(number)+1;
        var corn={};
        corn.number=number;
        $.localAjax('/user/business/addAdverHome',corn,  function (result) {       	
        	$('.body_array').append(result.data);
        })
    })
    
    $("#but_label_question_submit").on('click', function () {
        var businessUserArr = $('#label_question_name').val();
        if (businessUserArr == undefined || businessUserArr == '') {
            alert('不能运营ID为空');
            return;
        }
        var cond = {};
        cond.content = businessUserArr;
        $.localAjax('/user/business/savelabel', cond, function (result) {
            alert('保存成功');
        })
    })
    
    $("#advisor_home_submit").on('click', function () {
        var arrBody = new Array();
        $('.pic_body').each(function () {
            var body = {};
            body.uid = $(this).find('.pic_uid').val();
            body.intro = $(this).find('.pic_intro').val();
            body.img = $(this).find('.pic_img').attr('src');
            body.name = $(this).find('.pic_name').val();
            arrBody.push(body);
        });
        var cond = {};
        cond.content = arrBody.toJSONString();
        $.localAjax('/user/business/savenAdvisorHome', cond, function (result) {
            alert('保存成功');
        })
    })
    $("#advisor_home_add").on('click', function () {       
        var number=0;
        number=$('.pic_body').length;
        number=parseInt(number)+1;
        var corn={};
        corn.number=number;
        $.localAjax('/user/business/addAdvisorHome',corn,  function (result) {       	
        	$('.body_array').append(result.data);
        })
    })
    
    

})