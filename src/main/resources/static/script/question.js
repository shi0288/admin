$(function() {
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
				self.attr('taskName', 'true');
				self.text("封禁用户");
				self.removeClass('btn-warning');
				self.addClass('btn-primary');
			});
		} else {
			window.location.href = "/user/question/toFrozenUser?uid=" + uid;
		}
	})

	$('.set_good').on('click', function() {
		var self = $(this);
		var questionId = self.attr('tagVal');
		if (questionId == undefined || questionId == null) {
			alert('问题ID不能为空');
			return;
		}
		var cond = {};
		cond.questionId = questionId;
		var taskName = self.attr('taskName');
		var con;
		if (taskName == 'true') {
			con = "确定要设置精华？";
		} else {
			con = "确定要取消精华？";
		}
		cond.is = taskName;
		myConfirm(con, function() {
			$.localAjax('/user/question/setGood', cond, function() {
				if (taskName == 'true') {
					alert('设置精华成功');
					self.attr('taskName', 'false');
					self.text("取消精华");
					self.removeClass('btn-primary');
					self.addClass('btn-warning');
				} else {
					alert('取消精华成功');
					self.attr('taskName', 'true');
					self.text("设为精华");
					self.removeClass('btn-warning');
					self.addClass('btn-primary');
				}
			})
		})
	})

	$('.set_top').on(
			'click',
			function() {
				var self = $(this);
				var questionId = self.attr('tagVal');
				if (questionId == undefined || questionId == null) {
					alert('问题ID不能为空');
					return;
				}
				var taskName = self.attr('taskName');
				var cond = {};
				cond.questionId = questionId;
				cond.is = taskName;
				if (taskName == 'false') {
					myConfirm("确定要取消置顶?", function() {
						cond.topSort = 0;
						$.localAjax('/user/question/setTop', cond, function() {
							alert('更新置顶状态成功');
							self.attr('taskName', 'true');
							self.text("设为置顶");
							self.removeClass('btn-warning');
							self.addClass('btn-primary');
						})
					})
				} else {
					before();
					window.location.href = "/user/question/topList?questionId="
							+ questionId;
				}
			})

	$('.del_question').on('click', function() {
		var self = $(this);
		var questionId = self.attr('tagVal');
		if (questionId == undefined || questionId == null) {
			alert('问题ID不能为空');
			return;
		}
		myConfirm("确定要删除该问题吗?", function() {
			var cond = {};
			cond.questionId = questionId;
			$.localAjax('/user/question/deleteQuestion', cond, function() {
				alert('删除成功');
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
			})
		})
	})

	$('.recovery_question').on('click', function() {
		var self = $(this);
		var questionId = self.attr('tagVal');
		if (questionId == undefined || questionId == null) {
			alert('问题ID不能为空');
			return;
		}
		myConfirm("确定要恢复该问题?", function() {
			var cond = {};
			cond.questionId = questionId;
			$.localAjax('/user/question/recoveryQuestion', cond, function() {
				alert('恢复成功');
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
			})
		})

	})

	$('.verify_question').on('click', function() {
		var self = $(this);
		var questionId = self.attr('tagVal');
		if (questionId == undefined || questionId == null) {
			alert('问题ID不能为空');
			return;
		}
		myConfirm("确定审核通过该问题?", function() {
			var cond = {};
			cond.questionId = questionId;
			$.localAjax('/user/question/verifyQuestion', cond, function() {
				alert('审核通过');
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
			})
		})
	})
	// 城市
	$('.province_sel').on(
			'change',
			function() {
				var provinceId = $(this).val();
				if (provinceId == undefined || provinceId == null
						|| provinceId == '' || provinceId == '选择') {
					$('.city_sel').empty();
					return;
				}
				var cond = {};
				cond.provinceId = provinceId;
				$.localAjax('/user/manage/getCitys', cond, function(result) {
					$('.city_sel').empty();
					$('.city_sel').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.city_sel').append(
								'<option  value="' + obj.id + '">' + obj.name
										+ '</option>');
					})
				})
			})
	// 品牌
	$('.brand_sel').on(
			'change',
			function() {
				var brand_id = $(this).val();
				if (brand_id == undefined || brand_id == null || brand_id == ''
						|| brand_id == '选择') {
					$('.car_sel').empty();
					return;
				}
				var cond = {};
				cond.brandId = brand_id;
				$.localAjax('/user/manage/getSeries', cond, function(result) {
					$('.car_sel').empty();
					$('.car_sel').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.car_sel').append(
								'<option  value="' + obj.seriesId + '">'
										+ obj.name + '</option>');
					})
				})
			})

	// 上传图
	var picArr = new Array();
	var urlPic = document.getElementById("attachs");
	if (urlPic != undefined) {
		var str = urlPic.value;
		if (str != null && str != '') {
			picArr = str.split(",")
		}
	}
	$("body")
			.on(
					"change",
					"#attachImg",
					function() {
						var self = $(this);
						var check = $("input[name='compression']:checked")
								.val();
						if (check == 0) {
							$
									.picsUpload(
											self.attr("id"),
											function(result) {
												$
														.each(
																result.data,
																function(n,
																		value) {
																	picArr
																			.addToArr(
																					value,
																					$("#attachs"));
																	$(
																			'.pic_after')
																			.append(
																					'<div class="col-xs-6 col-md-2"><button type="button" class="close img-close"><span>×</span></button><img src="'
																							+ value
																							+ '" class="img-responsive img-thumbnail"  alt="图片无法访问"></div>');
																});
											});
						} else {
							$
									.picMobileUpload(
											self.attr("id"),
											function(result) {
												$
														.each(
																result.data,
																function(n,
																		value) {
																	picArr
																			.addToArr(
																					value,
																					$("#attachs"));
																	$(
																			'.pic_after')
																			.append(
																					'<div class="col-xs-6 col-md-2"><button type="button" class="close img-close"><span>×</span></button><img src="'
																							+ value
																							+ '" class="img-responsive img-thumbnail"  alt="图片无法访问"></div>');
																});
											});
						}

					})

	// 删除图片
	$("body").on("click", ".img-close", function() {
		var self = $(this);
		var srcVal = self.next().attr('src');
		picArr.removeByValue(srcVal, $("#attachs"));
		self.parent().remove();
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

	// 品牌
	$("body").on(
			"change",
			'.brand_select',
			function() {
				var brand_id = $(this).val();
				if (brand_id == undefined || brand_id == null || brand_id == ''
						|| brand_id == '选择') {
					$('.car_select').empty();
					return;
				}
				var cond = {};
				cond.brandId = brand_id;
				$.localAjax('/user/manage/getSeries', cond, function(result) {
					$('.car_select').empty();
					$('.car_select').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.car_select').append(
								'<option  value="' + obj.seriesId + '">'
										+ obj.name + '</option>');
					})
				})
			})

	$("body")
			.on(
					"change",
					'.car_select',
					function() {
						var ids = $(this).val();
						if (ids == '') {
							return;
						}
						var checkText = $(this).find("option:selected").text();
						var isHad = false;
						$("input[name='seriesIds']").each(function() {
							var temp = $(this).val();
							if (temp == ids) {
								isHad = true;
								return false;
							}
						});
						if (isHad) {
							alert('已经选择了这个车系');
							return;
						}
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

	$('.set_tp').on('change', function() {
		var self = $(this);
		var attachId = self.attr('tagVal');
		var id = self.attr("name");
		var con = "确定要改变活动类型";
		myConfirm(con, function() {
			var cond = {};
			cond.attachId = attachId;
			$.localAjax('/user/question/updateAttach', cond, function() {
				alert('修改活动类型成功');
			})
		})
	})

	$("#checked_all").click(function() {
		// $("input[name='check_questionId']").attr('checked',this.checked);
		$("input[name='check_questionId']").prop('checked', this.checked);
	});

	$("#but_create_question").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('保存成功');
		})
	})

	$("#but_frozen_user").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('封禁成功');
			window.location.href = "/user/manage/frozenUserList";
		})
	})

	// 封禁原因
	$("body").on("change", '.reason_select', function() {
		var reasonCode = $(this).val();
		if (reasonCode != undefined && reasonCode != null && reasonCode == 5) {
			$('.other_reason').show();
		} else {
			$('.other_reason').hide();
		}
	})

	$("body").on("change", '.question_type_select', function() {
		var questionType = $(this).val();
		if (questionType == 4) {
			$('.active_select').show();
		} else {
			$('.active_select').hide();
			$('.active_time').hide();
			$('.active_content_select').hide();
			$('.take_part_time_content').hide();
			$("input[name='activeId']:checked").prop('checked', false);
			$("input[name='partakeTimeId']:checked").prop('checked', false);
			$("input[name='partakeTime']").val('');
		}
		var userType = $("input[name='userType']:checked").val();
		if(userType==1&&questionType==5){
			$('.content_select').show();
		}else{					
			$(".content_select input[name='contentType']").each(function(){  
			    if($(this).val() == 0){  
			        $(this).prop( "checked", true );  
			    }  
			});  
			$('.content_select').hide();
			$('.pic_content').hide();
			$('.pub_content').show();
			editor.setContent('');
		}
		
	})
	// 活动选择
	$("body")
			.on(
					"change",
					'.active_select',
					function() {
						var activeId = $("input[name='activeId']:checked")
								.val();
						if (activeId != undefined && activeId != null
								&& activeId != '') {
							$('.active_content_select').hide();
							$('.take_part_time_content').hide();
							$('.subject_active_select').hide();
							$("input[name='partakeTimeId']:checked").prop(
									'checked', false);
							$("input[name='partakeTime']").val('');
							$("input[name='subjet_content']").val('');
							$('.active_time').show();
							if (activeId == 2) {
								$('.active_content_select').show();
							}else if(activeId == 4){
								$('.subject_active_select').show();
							} 

						} else {
							$('.active_time').hide();
							$('.active_content_select').hide();
							$('.take_part_time_content').hide();
							$("input[name='applyStartTime']").val('');
							$("input[name='applyEndTime']").val('');
							$("input[name='partakeTimeId']:checked").prop(
									'checked', false);
							$("input[name='partakeTime']").val('');
						}
					})

	$("body").on("change", '.take_part_time_select', function() {
		var activeId = $("input[name='partakeTimeId']:checked").val();
		if (activeId != undefined && activeId != null) {
			$('.take_part_time_content').show();

		} else {
			$('.take_part_time_content').hide();
			$("input[name='partakeTime']").val('');
		}
	})

	$("#add_partakeTime")
			.on(
					'click',
					function() {
						$('.take_part_time_content')
								.append(
										'<div class="row col-xs-12 col-md-12">'
												+ '<div class="col-xs-10 col-md-10"><input style="z-index:0"'
												+ ' class="form-control col-xs-10 col-md-10" name="partakeTime" '
												+ 'value=""/></div><div class="col-xs-1 col-md-1"><button type="button"'
												+ ' class="btn btn-danger del_partakeTime">删除</button></div> </div>');
					})

	$(".take_part_time_content").on("click", '.del_partakeTime', function() {
		var self = $(this);
		console.log(self.parent());
		self.parent().parent().remove();
	})

	$('.del_keyWord').on('click', function() {
		var id = $(this).attr("tagVal");
		if (id != undefined && id != null) {
			var cond = {};
			cond.id = id;
			var self = $(this);
			$.localAjax('/user/question/deleteDictionary', cond, function() {
				alert('删除成功');
				var curContentObj = self.closest(".tr_body");
				curContentObj.remove();
			})
		}
	})

	$("#but_add_dictionary").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('添加成功');
			window.location.href = "/user/question/dictionaryList";
		})
	})

	$("#save_question_speed").on('click', function() {
		$.localFormAjax("filter", function(result) {
			alert('保存成功');
		})
	})

	$("img").click(function() {
		var _this = $(this);
		var path = _this.attr("src")
		window.open(path);
	})

	$('.push_question').on('click', function() {
		var self = $(this);
		var questionId = self.attr('tagVal');
		var uid = prompt("请输入专家id:");
		if (uid != null) {
			var cond = {}
			cond.uid = uid;
			cond.questionId = questionId;
			$.localAjax('/user/question/pushQuestion', cond, function() {
				alert("消息推送成功");
			});
		} else {
			alert("请输入回答的专家id");
		}
	})
	
	$("body").on("change", '.content_select', function() {
		var contentType = $("input[name='contentType']:checked")
		.val();
		
		var editor = UE.getEditor('container');
		if (contentType!=null&&contentType==1) {
			$('.pub_content').hide();
			$('.pic_content').show();
			//$('.title_select').show();
			$("textarea[name='content']").val('');			
		}else{
			//$('.title_select').hide();
			$('.pic_content').hide();
			$('.pub_content').show();
			//$("input[name='title']").val('');
			editor.setContent('');
		}
	})
	
	
		// 用户身份
	$("body").on(
			"change",
			'.user_type_select',
			function() {
				var userType = $("input[name='userType']:checked").val();
				if (userType == undefined || userType == null || userType == ''						) {
					$('.uid_select').empty();
					return;
				}
				var editor = UE.getEditor('container');
				var open_pic=false;
				if(userType==1){
					var questionType = $("#questionType").val();
					if (questionType==5) {
						open_pic=true;						
					}
					$('.title_select').show();
				}else{
					$('.title_select').hide();
					$("input[name='title']").val('');
				}
				if (open_pic==true) {
					$('.content_select').show();
				}else{					
					$(".content_select input[name='contentType']").each(function(){  
					    if($(this).val() == 0){  
					        $(this).prop( "checked", true );  
					    }  
					});  
					$('.content_select').hide();
					$('.pic_content').hide();
					$('.pub_content').show();
					editor.setContent('');
				}
				var cond = {};
				cond.userType = userType;
				$.localAjax('/user/question/getOperateUser', cond, function(result) {
					$('.uid_select').empty();
					$('.uid_select').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.uid_select').append(
								'<option  value="' + obj.user_id + '">'
										+ obj.nick_name + '</option>');
					})
				})
			})
			
			
			// 分类选择
	$("body").on(
			"change",
			'.category_select',
			function() {
				var category_id = $(this).val();
				if (category_id == undefined || category_id == null
						|| category_id == '' || category_id == '选择') {
					$('.sub_category_select').empty();
					return;
				}
				var cond = {};
				cond.category_id = category_id;
				$.localAjax('/user/question/getSubCategory', cond, function(result) {
					$('.sub_category_select').empty();
					$('.sub_category_select').append('<option value="">请选择</option>');
					$.each(result.data, function(index, obj) {
						$('.sub_category_select').append(
								'<option  value="' + obj.categoryId + '">' + obj.name
										+ '</option>');
					})
				})
			})
			
    $('.del_spider').on('click', function() {
    	var self = $(this);
		var id = self.attr('tagVal');
		if (id == undefined || id == null) {
			alert('问题ID不能为空');
			return;
		}
		myConfirm("确定要删除该问题吗?", function() {
			var cond = {};
			cond.id = id;
			$.localAjax('/user/question/delSpider', cond, function() {
				alert('删除成功');
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
			})
		})		
	})
	$('.look_spider').on('click', function() {
		var self = $(this);
		var url = self.attr('tagVal');
		window.open(url);
		
	})
	
	$(".save_spider").on('click', function() {
		var obj = $(this).closest(".form-filter")
		var id=$(this).closest(".form-filter").attr("id")
		var self = $(this);
		/*ajaxupload.ajaxFrom({id:id},function(res){
			 var curContentObj = self.closest(".panel");
				curContentObj.remove();
				alert('保存成功');
			 
		 })*/
		
		
		$.localFormAjax({id:id}, function(result) {
			var curContentObj = self.closest(".panel");
			curContentObj.remove();
			alert('保存成功');
		})
	})
			
			
			

})
function addDictionary() {
	window.location.href = "/user/question/toAddDictionary";
}

function saveTop(id) {
	var questionId = id;
	var topSort = document.getElementById("topSort_" + questionId).value;
	if (questionId == undefined || questionId == null) {
		alert('问题ID不能为空');
		return;
	}
	var con;
	var cond = {};
	if (topSort == undefined || topSort == null || topSort == 0) {
		con = "确定要将问题：" + questionId + "取消置顶吗?";
		cond.is = false;
		topSort = 0;
	} else {
		con = "确定要将问题：" + questionId + "置顶吗?";
		cond.is = true;
	}
	cond.questionId = questionId;
	cond.topSort = topSort;
	myConfirm(con, function() {
		$.localAjax('./setTop', cond, function() {
			alert('更新置顶状态成功');
		})
	})
}
function exportQuestionExcel() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var categoryId = document.getElementById("categoryId").value;
	var keyWord = document.getElementById("keyWord").value;
	window.location.href = "/user/question/exportQuestion?startDate="
			+ startDate + "&endDate=" + endDate + "&categoryId=" + categoryId
			+ "&keyWord=" + keyWord;
}

function setGood() {
	var obj = $("input[name='check_questionId']:checked");
	var questionId = '';
	var con = "确定要批量设置精华";
	myConfirm(con, function() {
		obj.each(function() {
			var self = $(this);
			questionId = $(this).attr("value");
			var cond = {};
			cond.questionId = questionId;
			cond.is = true;
			$.localAjax('/user/question/setGood', cond, function() {
				$('#' + self.attr('data')).attr('taskName', 'false');
				$('#' + self.attr('data')).text("取消精华");
				self.attr("checked", false);
			})
		});
		alert('更新置顶状态成功');
	})
}
function deleteQue() {
	var obj = $("input[name='check_questionId']:checked");
	var questionId = '';
	var con = "确定要批量删除问题";
	myConfirm(con, function() {
		obj.each(function() {
			questionId = $(this).attr("value");
			var self = $(this);
			var cond = {};
			cond.questionId = questionId;
			$.localAjax('/user/question/deleteQuestion', cond, function() {
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
				self.attr("checked", false);
			})
		});
		alert('删除问题成功');
	})
}
function verifyQue() {
	var obj = $("input[name='check_questionId']:checked");
	var questionId = '';
	var con = "确定要批量审核通过问题";
	myConfirm(con, function() {
		obj.each(function() {
			var self = $(this);
			questionId = $(this).attr("value");
			var cond = {};
			cond.questionId = questionId;
			cond.is = true;
			$.localAjax('/user/question/verifyQuestion', cond, function() {
				var curContentObj = self.closest(".panel");
				curContentObj.remove();
			})
		});
		alert('批量审核问题成功');
	})
}

function exportRubbish() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var keyWord = document.getElementById("keyWord").value;
	var source = document.getElementById("source").value;
	window.location.href = "/user/question/exportRubbish?startDate="
			+ startDate + "&endDate=" + endDate + "&keyWord=" + keyWord
			+ "&source=" + source;
}

function delcache() {
	var con = "确定要清除缓存吗？";
	var cond = {};
	myConfirm(con, function() {
		$.localAjax('./delcache',cond, function() {
			alert('清除缓存成功');
		});
	})
}
