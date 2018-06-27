<div class="col-md-8 pic_body">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">运营管理帐号设置</h3>
        </div>
        <div class="panel-body">
            <div class="alert alert-warning">注意：填写运营人员账户的UID,多个以英文半角逗号隔开,例:,</div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if (block)??><#list block as  name><#if !(name_has_next)>${name}<#else>${name},</#if></#list></#if>"
                       id="business_user_name"/>
            </div>
        </div>
    </div>
</div>

