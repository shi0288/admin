<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">内容管理</a></li>
        <li class="active">过滤关键词</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/question/dictionaryList" method="post">
            <div class="row">
                
                <div class="col-md-3">
                    <input type="text" class="form-control" name="text" value="" placeholder="名称" />
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="submit"  class="btn btn-primary"> 查询</button>
                </div>
                <div class="col-md-1">
                    <button waitingLoad type="button"  onclick="addDictionary()"  class="btn btn-primary "> 添加</button>
                </div>
                

            </div>
        </form>
    </div>

<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"text":(text)!} />
    <@p.pager pager = pager baseUrl = "${base}/user/question/dictionaryList" parameterMap = parameterMap/>
</#if>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/question/dictionaryListCom.ftl" />
            </tbody>
        </table>
    </div>

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/dictionaryList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
</body>
</html>
