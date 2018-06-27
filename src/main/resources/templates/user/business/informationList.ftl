<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb" >
        <li><a href="##">运营管理</a></li>
        <li class="active">最热咨询</li>
    </ol>
    <div class="well">
        <button type="button" class="btn btn-primary" id="but_information_submit" > 保存 </button>
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>序号</th>
                <th>内容</th>
                <th>问题Key</th>
                <th>排序</th>
            </tr>
            </thead>
            <tbody>
            <#include  "/user/business/informationCom.ftl" />
            </tbody>
        </table>
    </div>


</div>

<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/business.js"></script>
</body>
</html>
