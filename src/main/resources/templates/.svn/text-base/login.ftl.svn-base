<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <link href="${cssPath}/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${cssPath}/base.css" rel="stylesheet" type="text/css">
</head>

<body class="login_body">

<div class="container">
    <div class="login_tag">
        <h2 class="login_header"> 问答管理后台 </h2>
        <form class="form_signin" id="filter" action="${base}/login" method="post">
            <div class="form-group">
                <label for="exampleInputEmail1">用户名</label>
                <input type="text" name="account" class="form-control" placeholder="用户名" value="">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">密码</label>
                <input type="password" name="password" class="form-control" placeholder="密码" value="">
            </div>
            <button data-loading-text="正在登录中,请稍等..." class="btn btn-lg btn-primary btn-block login_btn" type="button">
                登录
            </button>
        </form>
    </div>
</div>
<div class="modal fade" id="mymodal-data" tabindex="-1" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <p class="tip-content"></p>
            </div>
            <div class="modal-footer" id="alert-content"  style="display:none">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
            </div>
            <div class="modal-footer" id="conform-content" style="display:none">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="conform-but">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="mask_area"><div class="mask"></div></div>
<div id="loading"><div id="loading-center"><div id="loading-center-absolute"><div class="object" id="object_one"></div><div class="object" id="object_two"></div><div class="object" id="object_three"></div><div class="object" id="object_four"></div><div class="object" id="object_five"></div><div class="object" id="object_six"></div><div class="object" id="object_seven"></div><div class="object" id="object_eight"></div></div></div></div>

<script type="text/javascript" src="${jsPath}/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${jsPath}/bootstrap.min.js"></script>
<script type="text/javascript" src="${jsPath}/common_fun.js"></script>

<script>
    $(function () {
        $('.login_btn').on('click', function () {
            var self = $(this);
            self.button("loading");
            $.localFormAjax('filter', function (result) {
            var url=result.data;
                $.go('${base}'+url);
            }, function (result) {
                self.button('reset');
                alert(result.message);
            })
        })
    })
</script>
</body>
</html>