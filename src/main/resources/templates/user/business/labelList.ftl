<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">运营管理</a></li>
        <li class="active">问题标签</li>
    </ol>
    <div class="well">
        <button type="button" class="btn btn-primary" id="but_label_question_submit" > 保存 </button>
    </div>
    <div class="well">
        <div class="row">
        <div class="col-md-8 pic_body">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">问题标签设置</h3>
        </div>
        <div class="panel-body">
            <div class="alert alert-warning">注意：填写标签,多个以英文半角逗号隔开,例:,</div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if (block)??><#list block as  name><#if !(name_has_next)>${name}<#else>${name},</#if></#list></#if>"
                       id="label_question_name"/>
            </div>
        </div>
    </div>
</div>
        </div>
    </div>
</div>

<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/business.js"></script>
</body>
</html>
