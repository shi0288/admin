<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">放题速度</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/question/saveQuestionSpeed" method="post" id="filter">
            
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">速度(0.0):</label>
                <div class="col-md-3">
                       <input  name="speed" id="speed" type="text" value="<#if (speed)??>${(speed)}<#else>0.0</#if>"
                            style="height: 34px;"/>
                </div>
                
                <label for="province_select" class="col-md-2 control-label">时间(分钟):</label>
                <div class="col-md-3">
                       <input  name="time" id="time" type="text" value="<#if (time)??>${time}<#else>0</#if>"
                            style="height: 34px;"/>
                </div>
            </div> 

            <div class="form-group">
                <div class="col-sm-offset-2 col-xs-6 col-md-2">
                    <button type="button" class="btn btn-primary" id="save_question_speed">保存</button>
                </div>
                
                <div class="col-xs-6 col-md-2">
                    <button type="button" onclick="delcache()"  class="btn btn-primary" >清除缓存</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
</body>
</html>
