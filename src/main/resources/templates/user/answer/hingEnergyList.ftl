<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">内容管理</a></li>
        <li class="active">推送列表</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/answer/hingEnergyList" method="post">
            <div class="row">
                <div class="col-md-3">
                    <input type="text" class="form-control" name="uid" value="${(user.uid)!''}" placeholder="用户ID" />
                </div>
                <div class="col-md-3">
                    <input value="${time!''}" name="time" id="time" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMM',realDateFmt:'yyyyMM'})"
                           placeholder="时间>="/>
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
            </div>
        </form>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>用户ID</th>
                <th>数量</th>              
                <th>金额</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/answer/hingEnergyListCom.ftl" />
            </tbody>
        </table>
    </div>

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/manage/normalList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/answer.js"></script>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>

</body>
</html>
