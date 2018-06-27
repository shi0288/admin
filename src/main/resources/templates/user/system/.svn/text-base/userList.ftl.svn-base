<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">系统管理</a></li>
        <li class="active">用户列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/system/userList" method="post">
            <div class="row">
                <div class="col-md-3">
                    <input type="text" class="form-control" name="uid" value="${(user.uid)!''}" placeholder="用户ID" />
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="userName" value="${(user.userName)!''}" placeholder="用户名" />
                </div>                
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
                <div class="col-md-1">
                 <button waitingLoad type="button"  onclick="addUser()"  class="btn btn-primary">添加用户</button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>用户名</th>
                <th>用户来源</th>
                <!--<th>专家姓名</th>
                <th>擅长问题</th>
                <th>联系方式</th>-->
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/system/userListCom.ftl" />
            </tbody>
        </table>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
