<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">问题管理</li>
    </ol>
<#if questionList??>
    <#list questionList as item>
        <div class="panel <#if item_index==0>panel-info<#else>panel-default</#if>">
            <div class="panel-heading">
                <div class="container-fluid content-block">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">置顶序号</div>
                                    <input style="z-index:0" class="form-control" value="${(item.topSort)!''}"  id="topSort_${(item.questionId)!''}"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button type="button" id="save_top" onclick="saveTop(${(item.questionId)!''})"
                                    class="btn btn-warning">
                                保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <p>【话题编号】${(item.questionId)!''}</p>
                <p>【内容】${(item.content)!''}</p>
            </div>
        </div>
    </#list>
</#if>


</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script src="${jsPath}/date/WdatePicker.js"></script>
</body>
</html>

