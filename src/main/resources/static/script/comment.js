$(function () {
	$("#but_create_comment").on('click', function () {
        
        $.localFormAjax("filter", function (result) {
            alert('保存成功');
        })
    })

	$('.del_comment').on('click', function () {
        var self = $(this);
        var comment_id = self.attr('tagVal');
        if (comment_id == undefined || comment_id == null) {
            alert('ID不能为空');
            return;
        }
        myConfirm('确定要删除该条评论吗？', function () {
            var cond = {};
            cond.comment_id = comment_id;
            $.localAjax('/user/comment/del', cond, function () {
                alert('删除成功');
                var curContentObj = self.closest(".panel");
                curContentObj.remove();
            })
        })
    })
	
})


