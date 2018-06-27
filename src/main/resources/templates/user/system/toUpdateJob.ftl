<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">系统管理</a></li>
        <li class="active">任务管理</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/system/updateJob" method="post" id="filter">
            <input type="hidden" value="${task.id}" name="id"/>
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">任务名称:</label>
                <div class="col-md-3">
                       <input  name="jobDetailName" id="jobDetailName" type="text" value="${task.jobDetailName!''}"
                            style="height: 34px;"/>
                </div>
            </div>     
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">任务组名:</label>
                <div class="col-md-3">
                       <input  name="jobGroupName" id="jobGroupName" type="text" value="${task.jobGroupName!''}"
                            style="height: 34px;"/>
                </div>
            </div>     
            
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">执行时间:</label>
                <div class="col-md-3">
                       <input  name="cron" id="cron" type="text" value="${task.cron!''}"
                            style="height: 34px;"/>
                </div>
            </div> 
            
            <div class="form-group">
                <label for="isOpen" class="col-md-2 control-label">是否开启:</label>
                <div class="col-md-3">
                    <select id="isOpen" name="isOpen" class="form-control" disabled style="height: 34px;">
                        <option value="1" <#if task.isOpen==1>selected</#if> >是</option>
                        <option value="0" <#if task.isOpen==0>selected</#if> >否</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">任务类名:</label>
                <div class="col-md-3">
                       <input  name="targetObject" id="targetObject" type="text" disabled
                            style="height: 34px;"/>
                </div>
            </div> 
            
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">任务方法名称:</label>
                <div class="col-md-3">
                       <input  name="methodName" id="methodName" class="form-control" type="text" disabled/>
                </div>
            </div>              
            <div class="form-group">
                <label for="province_select" class="col-md-2 control-label">任务描述:</label>
                <div class="col-md-3">
                       <input  name="desc" id="desc" type="text" value="${task.desc!''}" 
                            class="form-control"/>
                </div>
            </div> 
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_update_jod">保存</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/system.js"></script>
</body>
</html>
