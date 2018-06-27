$(function () {

    $("#but_create_answer").on('click', function () {
        var imgStr = $("#attachs").val();
        if (imgStr != '') {
            if (imgStr.split(',').length > 1) {
                alert("回复只能上传一张图片");
                return;
            }
        }
        $.localFormAjax("filter", function (result) {
            alert('保存成功');
        })
    })

    $('.del_answer').on('click', function () {
        var self = $(this);
        var answerId = self.attr('tagVal');
        if (answerId == undefined || answerId == null) {
            alert('ID不能为空');
            return;
        }
        myConfirm('确定要删除该回复？', function () {
            var cond = {};
            cond.answerId = answerId;
            $.localAjax('/user/answer/deleteAnswer', cond, function () {
                alert('删除成功');
                var curContentObj = self.closest(".panel");
                curContentObj.remove();
            })
        })
    })

    // 审核回复
    $('.verify_answer').on('click', function () {
        var self = $(this);
        var answerId = self.attr('tagVal');
        if (answerId == undefined || answerId == null) {
            alert('ID不能为空');
            return;
        }
        myConfirm('确定审核通过该回复？', function () {
            var cond = {};
            cond.answerId = answerId;
            $.localAjax('/user/answer/verifyAnswer', cond, function () {
                alert('审核成功');
                var curContentObj = self.closest(".panel");
                curContentObj.remove();
            })
        })

    })

    // 恢复回复
    $('.recovery_answer').on('click', function () {
        var self = $(this);
        var answerId = self.attr('tagVal');
        if (answerId == undefined || answerId == null) {
            alert('ID不能为空');
            return;
        }
        myConfirm('确定恢复该回复？', function () {
            var cond = {};
            cond.answerId = answerId;
            $.localAjax('/user/answer/recoverAnswer', cond, function () {
                alert('恢复成功');
                var curContentObj = self.closest(".panel");
                curContentObj.remove();
            })
        })
    })
    $('.set_hingEnerg').on('click', function (){
    	alert("设置高能");
    	var self = $(this);
    	var answerId = self.attr('tagVal');
    	var taskName = self.attr('taskName');
    	var confirm;
    	if (taskName=='true') {
    		confirm="确定要设置高能";
		}else{
			confirm="确定要取消高能";
		}
    	var cond = {};
    	cond.answerId=answerId;
    	cond.isHingEnergy=taskName;
    	myConfirm(confirm, function () {
    		$.localAjax('/user/answer/setHingEnergy', cond, function () {
    			if (taskName == 'true') {
    				alert('设置高能成功');
    				self.attr('taskName', 'false');
    				self.text("取消高能");
    				self.removeClass('btn-primary');
    				self.addClass('btn-warning');
    			} else {
    				alert('取消高能成功');
    				self.attr('taskName', 'true');
    				self.text("设置高能");
    				self.removeClass('btn-warning');
    				self.addClass('btn-primary');
    			}
    		})
    		
    	})
    })
    
     $('.write_comment').on('click', function (){
    	showPopup(600,200);
    	var popUp = document.getElementById("statusbar"); 
    	var baseText = popUp.innerHTML; 
        popUp.innerHTML = baseText + '<button onclick="savePopup()">保存</button>';          
    })
    
     $('.see_comment').on('click', function (){
    	 showPopup(600,200);
    })
    $("#checked_all").click(function(){   
    	$("input[name='check_answerId']").prop('checked',this.checked);
    }); 
    
    $("img").click(function() {
		var path = $("img").attr("src");
		window.open(path);
	})
	
	$('.push_hing_energy').on('click', function (){
		 var self = $(this);
	     var uid = self.attr('tagVal');
	     var count=self.attr('tagCount');
	     var money=document.getElementById("money_"+uid).value;
	     var cond={}
	     cond.uid=uid;
	     cond.time=document.getElementById("time").value;
	     cond.money=money;
	     $.localAjax('/user/answer/pushAnswer', cond, function () {
	    	 alert('推送成功');
	     });
	})
	
	$('.set_first_answer').on('click', function () {
        var self = $(this);
        var answerId = self.attr('tagVal');
        if (answerId == undefined || answerId == null) {
            alert('ID不能为空');
            return;
        }
        myConfirm('确定要将该回复,设置成第一条回答吗?', function () {
            var cond = {};
            cond.answerId = answerId;
            $.localAjax('/user/answer/firstAnswer', cond, function (data) {
            	alert("123");
                alert(data.data);
            })
        })
    })
	
})


function showPopup(w,h){ 
	var baseText = null; 
    var popUp = document.getElementById("popupcontent"); 
    popUp.style.top = "200px"; 
    popUp.style.left = "200px"; 
    popUp.style.width = w + "px"; 
    popUp.style.height = h + "px"; 
    if (baseText == null) baseText = popUp.innerHTML; 
    popUp.innerHTML = baseText + '<div id="statusbar" style="text-align:center; width:100%;height:100%;margin:0px;"><button onclick="hidePopup()">关闭</button></div>'; 
    var sbar = document.getElementById("statusbar"); 
   // sbar.style.marginTop = (parseInt(h)-60) + "px"; 
    popUp.style.visibility = "visible"; 
} 

function hidePopup(){ 
	var popUp = document.getElementById("popupcontent"); 
	popUp.style.visibility = "hidden"; 
	var bar = document.getElementById("statusbar"); 
	bar.remove();
	} 

function savePopup(){ 
	var popUp = document.getElementById("popupcontent"); 
	var comment = document.getElementById("comment").value; 
	var answerId = document.getElementById("answerId").value; 
	var cond={};
	cond.comment=comment;
	cond.answerId=answerId;
	 $.localAjax('/user/answer/writeComment', cond, function () {
         alert('保存成功');
         var curContentObj = self.closest(".panel");
         curContentObj.remove();
     })
     popUp.style.visibility = "hidden"; 
	
	} 

function exportAnswerExcel(){
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var keyWord = document.getElementById("keyWord").value;
	window.location.href = "/user/answer/exportAnswer?startDate="
			+ startDate + "&endDate=" + endDate+"&keyWord="+keyWord;
}

function deleteAns(){
	var obj= $("input[name='check_answerId']:checked");
	var answerId='';		
	var con="确定要批量删除回复";
    myConfirm(con,function(){
    	obj.each(function(){
    		answerId=$(this).attr("value");
    		var self=$(this);
    		var cond={};
    		cond.answerId = answerId;
    		$.localAjax('/user/answer/deleteAnswer', cond, function () {                              
                var curContentObj = self.closest(".panel");
                curContentObj.remove();
                self.attr("checked",false);
            })
    	});    
    	 alert('删除回复成功');
    })	
}
