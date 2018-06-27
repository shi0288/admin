$(function() {
	$("#but_add_user").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('添加用户成功');
		})
	})

	$("#but_add_Operate").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('添加功能成功');
		})
	})
	$("#but_add_menu").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('添加菜单成功');
		})
	})
	$("#but_update_menu").on('click', function () {
        $.localFormAjax("filter", function (result) {
            alert('修改菜单成功');
        })
    })
    $("#but_update_operate").on('click', function () {
        $.localFormAjax("filter", function (result) {
            alert('修改功能成功');
        })
    })
	 $("body").on("change", '.menu_select', function () {
        var mid = $(this).val();
        if (mid == undefined || mid == null || mid == '' || mid == '选择') {
            $('.operate_select').empty();
            return;
        }
        var cond = {};
        cond.mid = mid;
        $.localAjax('/user/system/selectOperte', cond, function (result) {
            $('.operate_select').empty();
            $('.operate_select').append('<option value="0">请选择</option>');
            $.each(result.data, function (index, obj) {
                $('.operate_select').append('<option  value="' + obj.operateId + '">' + obj.operateName + '</option>');
            })
        })
    })
    
    $("#checked_all").click(function(){   
    	$("input[name='check_powerId']").prop('checked',this.checked);
    }); 
	
	$("#but_add_jod").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('添加任务成功');
		})
	})
	
	$("#but_update_jod").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('修改任务成功');
		})
	})
	
	
	 $('.set_open').on('click', function () {
        var self = $(this);
        var id = self.attr('tagVal');
        if (id == undefined || id == null) {
            alert('任务ID不能为空');
            return;
        }
        var cond = {};
        cond.id = id;
        var taskName = self.attr('taskName');
        var con;
        if (taskName == 'true') {
            con = "确定要打开任务吗？";
            cond.isOpen = 1;
        } else {
            con = "确定要关闭任务吗？";
            cond.isOpen = 0;
        }       
        myConfirm(con, function () {
            $.localAjax('/user/system/openJob', cond, function () {
                if (taskName == 'true') {
                    alert('打开任务成功');
                    self.attr('taskName', 'false');
                    self.text("关闭");
                    self.removeClass('btn-primary');
                    self.addClass('btn-warning');
                } else {
                    alert('关闭任务成功');
                    self.attr('taskName', 'true');
                    self.text("打开");
                    self.removeClass('btn-warning');
                    self.addClass('btn-primary');
                }
            })
        })
    })
    
    
    $('.delete_job').on('click', function () {
        var self = $(this);
        var id = self.attr('tagVal');
        if (id == undefined || id == null) {
            alert('任务ID不能为空');
            return;
        }
        var cond = {};
        cond.id = id;
        var con="确定要删除任务吗？";
              
        myConfirm(con, function () {
            $.localAjax('/user/system/delete', cond, function () {
                alert("删除任务成功");
                window.location.href = "/user/system/jobList?";
            })
        })
    })
    
    $('.start_job').on('click', function () {
        var self = $(this);
        var id = self.attr('tagVal');
        if (id == undefined || id == null) {
            alert('任务ID不能为空');
            return;
        }
        var cond = {};
        cond.id = id;
        var con="确定要立刻执行任务吗？";             
        myConfirm(con, function () {
            $.localAjax('/user/system/startJob', cond, function () {
                alert("执行成功");
            })
        })
    })
	
})

function addUser() {
	window.location.href = "/user/system/toAddUser";
}
function addMenu() {
	window.location.href = "/user/system/toAddMenu";
}
function addOperate() {
	window.location.href = "/user/system/toAddOperate";
}

function addJob() {
	window.location.href = "/user/system/toAddJob";
}

function saveUserPower(){
	var uid=document.getElementById("uid").value;
	var obj= $("input[name='check_powerId']:checked");	
	var powerIds="";
	obj.each(function(){
		var self=$(this);
		var powerId=$(this).attr("value");		
		powerIds=powerIds+powerId+",";
	});
	powerIds=powerIds.substr(0, powerIds.length - 1);
	var cond={};
	cond.powerIds = powerIds;
	cond.uid = uid;
	$.localAjax('/user/system/saveUserPower', cond, function (result) {	        
    })
	alert('保存权限成功');
	
}
