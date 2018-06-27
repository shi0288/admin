<!doctype html>
<html>
<head>
    <title>问答管理后台</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<#include "user/boot_css.ftl"/>
</head>
<body class="main_body">
<#include "user/boot_sub.ftl"/>
<div class="container-fluid main">
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-body" style="width: 100%;height:400px;" id="questionAndAnswer">
            </div>
        </div>
    </div>
</div>
<#include "user/boot_js.ftl"/>
<div class="mask_area">
    <div class="mask"></div>
</div>
<div id="loading">
    <div id="loading-center">
        <div id="loading-center-absolute">
            <div class="object" id="object_one"></div>
            <div class="object" id="object_two"></div>
            <div class="object" id="object_three"></div>
            <div class="object" id="object_four"></div>
            <div class="object" id="object_five"></div>
            <div class="object" id="object_six"></div>
            <div class="object" id="object_seven"></div>
            <div class="object" id="object_eight"></div>
        </div>
    </div>
</div>

<script src="${jsPath}/echarts.min.js"></script>
<script src="${jsPath}/shine.js"></script>
<script src="${jsPath}/charts/questionAndAnswer.js"></script>


</body>
</html>
