<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">运营管理</a></li>
        <li class="active">运营号设置</li>
    </ol>
    <div class="well">
        <button type="button" class="btn btn-primary" id="but_business_user_submit" > 保存 </button>
    </div>
    <div class="well">
        <div class="row">
        <#include  "/user/business/businessUserCom.ftl" />
        </div>
    </div>
</div>

<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/business.js"></script>
</body>
</html>
