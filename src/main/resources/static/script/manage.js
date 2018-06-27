$(function() {
	$('.frozen_user').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		var cond = {};
		cond.uid = uid;
		if (self.attr('frozenType') == 'frozen') {
			myConfirm('确定要隐藏这个用户身份吗?', function() {
				$.localAjax('/user/manage/frozenUser', cond, function() {
					alert('修改成功');
					self.attr('frozenType', 'thaw');
					self.text('恢复身份');
					self.removeClass('btn-primary');
					self.addClass('btn-warning');
				})
			})
		} else if (self.attr('frozenType') == 'thaw') {
			myConfirm('确定要显示这个用户身份吗?', function() {
				$.localAjax('/user/manage/thawUser', cond, function() {
					alert('修改成功');
					self.attr('frozenType', 'frozen');
					self.text('隐藏身份');
					self.removeClass('btn-warning');
					self.addClass('btn-primary');
				})
			})
		} else {
			alert('操作类型错误');
		}
	})

	$('.set_model').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		myConfirm("确定要设置为标兵吗？", function() {
			var cond = {};
			cond.uid = uid;
			$.localAjax('/user/manage/setModel', cond, function() {
				alert('设置成功');
				var curContentObj = self.closest(".tr_body");
				curContentObj.remove();
			})
		})
	})

	$('.set_expert').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		myConfirm("确定要设置为专家吗？", function() {
			var cond = {};
			cond.uid = uid;
			$.localAjax('/user/manage/setExpert', cond, function() {
				alert('设置成功');
				var curContentObj = self.closest(".tr_body");
				curContentObj.remove();
			})
		})
	})

	// 替换
	$(".right_list").delegate("button[name=topMove]", "click", function() {
		var curContentObj = $(this).closest(".user_body");
		var preContentObj = curContentObj.prev();
		if (preContentObj.length == 0 || !preContentObj.hasClass('user_body')) {
			return;
		}
		var curNum = curContentObj.find('.user_index').text();
		var preNum = preContentObj.find('.user_index').text();
		curContentObj.find('.user_index').text(preNum);
		preContentObj.before(curContentObj);
		preContentObj.find('.user_index').text(curNum);
	})

	$(".right_list").delegate(
			"button[name=bottomMove]",
			"click",
			function() {
				var curContentObj = $(this).closest(".user_body");
				var nextContentObj = curContentObj.next();
				if (nextContentObj.length == 0
						|| !nextContentObj.hasClass('user_body')) {
					return;
				}
				var curNum = curContentObj.find('.user_index').text();
				var nextNum = nextContentObj.find('.user_index').text();
				curContentObj.find('.user_index').text(nextNum);
				nextContentObj.after(curContentObj);
				nextContentObj.find('.user_index').text(curNum);
			})

	$("#but_save_special").on(
			'click',
			function() {
				var target = $(this).attr('tagVal');
				if (target != 'special' && target != 'specialExpert'
						&& target != 'specialModel') {
					alert('类型错误');
					return;
				}
				var arrBody = new Array();
				$('.user_body').each(function() {
					var uid = $(this).find('.user_id').attr('tagVal');
					arrBody.push(uid);
				})
				if (target == 'special' && arrBody.length != 6) {
					alert("数量必须为6个");
					return;
				}
				if (target == 'specialExpert' && arrBody.length != 5) {
					alert("数量必须为5个");
					return;
				}
				if (target == 'specialModel' && arrBody.length != 5) {
					alert("数量必须为5个");
					return;
				}
				var cond = {};
				cond.target = target;
				cond.content = arrBody.toRString();
				$.localAjax('/user/manage/saveSpecial', cond, function(result) {
					alert('保存成功');
				})
			})

	$(".right_list").delegate(
			".user_delete",
			"click",
			function() {
				var curContentObj = $(this).closest(".user_body");
				var curNum = curContentObj.find('.user_index').text();
				var nextContentObj = curContentObj.next();
				while (nextContentObj.length > 0
						&& nextContentObj.hasClass('user_body')) {
					nextContentObj.find('.user_index').text(curNum);
					curNum++;
					nextContentObj = nextContentObj.next();
				}
				curContentObj.remove();
			})

	$(".user_pool")
			.on(
					'click',
					'.add_list',
					function() {
						var self = $(this);
						var uid = self.attr('tagVal');
						// 判断是否已经添加
						var arrBody = new Array();
						$('.user_body').each(function() {
							var uid = $(this).find('.user_id').attr('tagVal');
							arrBody.push(uid);
						})

						if (arrBody.contains(uid)) {
							alert("已经存在这个用户了");
							return;
						}
						var name = self.attr('tagName');
						var trNode = $('<tr class="user_body"></tr>');
						// 索引
						var tdIndexNode = $('<td class="user_index">'
								+ ($('.user_body').length + 1) + '</td>');
						trNode.append(tdIndexNode);
						// 用户名
						var tdNameNode = $('<td class="user_id" tagVal="' + uid
								+ '">' + name + '</td>');
						trNode.append(tdNameNode);
						// 操作
						trNode
								.append('<td><button class="btn btn-primary btn-xs" name="topMove" ><span class="glyphicon glyphicon-arrow-up"></span></button> <button class="btn btn-primary btn-xs" name="bottomMove" ><span class="glyphicon glyphicon-arrow-down"></span></button></td>');
						trNode
								.append('<td><button class="btn btn-danger btn-xs user_delete" ><span class="glyphicon glyphicon-minus"></span></button></td>');
						$(".list_pool").append(trNode);
					})

	$('#findExpertById').textRunner({
		delay : 1000,
		run : function() {
			$.localAjax("./specialListAssist", {
				uid : this.ele.val()
			}, function(result) {
				$('.user_pool').empty();
				$('.user_pool').append(result.data);
			})
		}
	});

	$('#findExpertByName').textRunner({
		delay : 1000,
		run : function() {
			$.localAjax("./specialListAssist", {
				expert_name : this.ele.val()
			}, function(result) {
				$('.user_pool').empty();
				$('.user_pool').append(result.data);
			})
		}
	});

	$('#findModelById').textRunner({
		delay : 1000,
		run : function() {
			$.localAjax("./modelListAssist", {
				uid : this.ele.val()
			}, function(result) {
				$('.user_pool').empty();
				$('.user_pool').append(result.data);
			})
		}
	});

	$('#findModelByName').textRunner({
		delay : 1000,
		run : function() {
			$.localAjax("./modelListAssist", {
				nickName : this.ele.val()
			}, function(result) {
				$('.user_pool').empty();
				$('.user_pool').append(result.data);
			})
		}
	});

	$("body").on("change", '#userAvatar', function() {
		var id = $(this).attr("id");
		$.picUpload(id, function(result) {
			$("#userAvatarImg").attr('src', result.data);
			$("input[name='expert_avatar']").val(result.data);
		});
	})

	$("body").on("click", '.span_series_name', function() {
		var id = $(this).attr("tagVal");
		$('input[name="seriesIds"]').each(function() {
			var tempId = $(this).val();
			if (id == tempId) {
				$(this).remove();
				return false;
			}
		})
		$(this).parent().remove();
	})

	$("body")
			.on(
					"change",
					'.brand_select',
					function() {
						var brand_id = $(this).val();
						if (brand_id == undefined || brand_id == null
								|| brand_id == '' || brand_id == '选择') {
							$('.car_select').empty();
							return;
						}
						var cond = {};
						cond.brandId = brand_id;
						var checkText = $(this).find("option:selected").text();
						$('.car_show')
								.append(
										'<div class="col-xs-6 col-md-2"><span  class="btn btn-warning btn-xs span_series_name" tagVal="0_'
												+ brand_id
												+ '" >'
												+ checkText
												+ '</span></div>');
						$('.car_show').append(
								'<input type="hidden"  name="seriesIds"  value="0_'
										+ brand_id + '"  />');
						$.localAjax('./getSeries', cond, function(result) {
							$('.car_select').empty();
							$('.car_select').append(
									'<option value="">请选择</option>');
							$.each(result.data, function(index, obj) {
								$('.car_select').append(
										'<option  value="' + obj.seriesId + '_'
												+ obj.brandId + '">' + obj.name
												+ '</option>');
							})
						})
					})

	$("body")
			.on(
					"change",
					'.car_select',
					function() {
						var ids = $(this).val();
						if (ids == undefined || ids == '') {
							return;
						}
						var checkText = $(this).find("option:selected").text();
						$('.car_show')
								.append(
										'<div class="col-xs-6 col-md-2"><span  class="btn btn-warning btn-xs span_series_name" tagVal="'
												+ ids
												+ '" >'
												+ checkText
												+ '</span></div>');
						$('.car_show').append(
								'<input type="hidden"  name="seriesIds"  value="'
										+ ids + '"  />');
					})

	$("#but_update_user").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('保存成功');
		})
	})

	$("#reset_user_info").on('click', function() {
		var nickName = $('input[name="nickName"]').val();
		if (nickName == undefined || nickName == null) {
			alert('昵称不能为空');
			return;
		}
		var cond = {};
		cond.nickName = nickName;
		$.localAjax('/user/manage/resetUserInfo', cond, function(result) {
			window.location.href = './expertEdit?uid=' + result.data;
		})
	})

	$('.set_frozen').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		var taskName = self.attr('taskName');
		if (taskName == 'false') {
			var cond = {};
			cond.is = taskName;
			cond.uid = uid;
			// 解禁用户
			$.localAjax('/user/question/frozenUserAndExpert', cond, function() {
				alert('解禁用户成功');
				window.location.href = "/user/manage/frozenUserList";
			});
		}
	})

	$('.enable_distribute').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		var cond = {};
		cond.uid = uid;
		if (self.attr('type') == 'join') {
			cond.enableDistribute = 1;
			// myConfirm('确定要隐藏这个用户身份吗?',function(){
			$.localAjax('/user/manage/setEnableDistribute', cond, function() {
				alert('修改成功');
				self.attr('type', 'quit');
				self.text('退出自动推送');
				self.removeClass('btn-primary');
				self.addClass('btn-warning');
			})
			// })
		} else if (self.attr('type') == 'quit') {
			cond.enableDistribute = 0;
			// myConfirm('确定要显示这个用户身份吗?',function(){
			$.localAjax('/user/manage/setEnableDistribute', cond, function() {
				alert('修改成功');
				self.attr('type', 'join');
				self.text('加入自动推送');
				self.removeClass('btn-warning');
				self.addClass('btn-primary');
			})
			// })
		}
	})

	// 城市
	$("body").on(
			"change",
			'.province_select',
			function() {
				var provinceId = $(this).val();
				if (provinceId == undefined || provinceId == null
						|| provinceId == '' || provinceId == '选择') {
					$('.city_select').empty();
					return;
				}
				var cond = {};
				cond.provinceId = provinceId;
				$.localAjax('/user/manage/getCitys', cond, function(result) {
					$('.city_select').empty();
					$('.city_select').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.city_select').append(
								'<option  value="' + obj.id + '">' + obj.name
										+ '</option>');
					})
				})
			})

	$('.set_adviser').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		myConfirm("确定要设置为车顾问吗？", function() {
			var cond = {};
			cond.uid = uid;
			$.localAjax('/user/manage/updateAdviser', cond, function() {
				alert('设置成功');
				var curContentObj = self.closest(".tr_body");
				curContentObj.remove();
			})
		})
	})

	$('.update_adviser').on('click', function() {
		var self = $(this);
		var uid = self.attr('tagVal');
		if (uid == undefined || uid == null) {
			alert('用户ID不能为空');
			return;
		}
		var cond = {};
		cond.uid = uid;
		$.localAjax('/user/manage/updateAdviser', cond, function() {
			alert('更新成功');
		})

	})
	
	$("#but_update_active_user").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('保存成功');
		})
	})
	
	$("img").click(function() {
		var _this = $(this);
		var path = _this.attr("src")
		window.open(path);
	})

})