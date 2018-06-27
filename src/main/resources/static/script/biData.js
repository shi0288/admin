$(function() {
	$('.to_question').on('click', function() {
		alert(111);
		var self = $(this);
		var questionId = self.attr('tagVal');
		if (questionId == undefined || questionId == null) {
			alert('问题ID不能为空');
			return;
		}
		var cond = {};
		cond.questionId = questionId;
		$.localAjax('/user/question/selectQuestionKey', cond, function(result) {
			alert(result)
			
		})
	})

})
function exportQueBi() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	window.location.href = "/user/biData/exportQueBi?startTime=" + startTime
			+ "&endTime=" + endTime;
}
function exportQueTypeBi() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	window.location.href = "/user/biData/exportQueTypeBi?startTime="
			+ startTime + "&endTime=" + endTime;
}
function exportQueUserBi() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	window.location.href = "/user/biData/exportQueUserBi?startTime="
			+ startTime + "&endTime=" + endTime;
}

function exportTopExcel() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	var selectType = document.getElementById("selectType").value;
	window.location.href = "/user/biData/exportTopBi?startTime="
			+ startTime + "&endTime=" + endTime+"&selectType="+selectType;
}

function exportUserExpert() {
	var startTime = document.getElementById("startTime").value;
	var endTime = document.getElementById("endTime").value;
	window.location.href = "/user/biData/exportUserExpert?startTime="
			+ startTime + "&endTime=" + endTime;
}
