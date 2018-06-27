<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">封禁用户</li>
    </ol>
    <div class="well">
        <form class="form-horizontal" action="${base}/user/question/frozenUserAndExpert" method="post" id="filter">
            <div class="form-group">
                <label for="uid" class="col-md-2 control-label">用户id:</label>
                <div class="col-md-3">
                    <input readonly  class="form-control" id="uid" name="uid"  type="text" value="${uid}"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="time" class="col-md-2 control-label">禁言时间(天,0表示永久):</label>
                <div class="col-md-3">
                    <input class="form-control" id="time" name="time"  type="text" value=""/>
                </div>
            </div>
            <div class="form-group">
                <label for="delQue" class="col-md-2 control-label">删除问题:</label>
                <input type="checkbox" name="delQue" value="1"> 
            </div>
            <div class="form-group">
                <label for="delAns" class="col-md-2 control-label">删除回复:</label>
                <input type="checkbox" name="delAns" value="1"> 
            </div>

            <div class="form-group">
                <label for="reason" class="col-md-2 control-label">禁言原因:</label>
                <div class="col-md-3">
                    <select id="reasonCode" name="reasonCode" class="form-control reason_select">
                        <option value="0">选择</option>             
                        <option value="1">政治敏感</option> 
                        <option value="2">人身攻击</option>
                        <option value="3">淫秽色情</option>
                        <option value="4">广告</option>
                        <option value="5">其他</option>   
                        <option value="6">水贴</option>           
                    </select>
                </div>
            </div>                       

            <div class="form-group other_reason"  style="display:none">
                <label for="content" class="col-md-2 control-label">其他原因:</label>
                <div class="col-md-8">
                    <textarea  class="form-control" id="content" name="content" rows="5"></textarea>
                </div>
            </div>
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="but_frozen_user">封禁</button>
                </div>
            </div>

        </form>
    </div>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>
<#include  "/user/autocomplete.ftl" />
</body>
</html>
