<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">数据监控</a></li>
        <li class="active">数据总览</li>
    </ol>

    <div class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-body" style="height:400px;" id="questionAndAnswer">
                </div>
            </div>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script src="${jsPath}/echarts.min.js"></script>
<script src="${jsPath}/shine.js"></script>
<script src="${jsPath}/charts/questionAndAnswer.js"></script>
</body>
</html>
