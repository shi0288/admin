<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">用户管理</a></li>
        <li class="active">封禁列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/manage/frozenUserList" method="post">
            <div class="row">
                <div class="col-md-3">
                    <input type="text" class="form-control" name="uid" value="${(user.uid)!''}" placeholder="用户ID" />
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="nickName" value="${(nickName)!''}" placeholder="昵称" />
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>

                
            </div>
        </form>
    </div>

<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"uid":(user.uid)!,"nickName":(nickName)!} />
    <@p.pager pager = pager baseUrl = "${base}/user/manage/frozenUserList" parameterMap = parameterMap/>
</#if>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>昵称</th>
                <th>身份</th>
                <th>封禁人</th>
                <th>封禁时间</th>
                <th>解禁时间</th>
                <th>联系方式</th>
                <th>封禁原因</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/manage/frozenUserListCom.ftl" />
            </tbody>
        </table>
    </div>

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/manage/frozenUserList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/manage.js"></script>
</body>
</html>
