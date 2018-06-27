<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">内容管理</a></li>
        <li class="active">导入数据审核</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/question/spiderList" method="post">
           

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
            </div>
        </form>
    </div>

<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
  
    <@p.pager pager = pager baseUrl = "${base}/user/question/spiderList" />
</#if>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>id</th>
                <th>车系</th>
                <th>分类</th>
                <th>子分类</th>
                <th>内容</th>
                
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/question/spiderCom.ftl" />
            </tbody>
        </table>
    </div>

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/spiderList" />
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
</body>
</html>
