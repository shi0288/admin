<#include "/user/boot_top.ftl"/>
 <script type="text/javascript" rel="stylesheet" src="${jsPath}/biData.js"></script>

<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">数据监控</a></li>
        <li class="active">top排行</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/biData/topBi" method="post">
            <div class="row">
                <div class="col-xs-6 col-md-3">
                    <input value="${startTime!''}" id="startTime" name="startTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd',realDateFmt:'yyyyMMdd '})"
                           placeholder="开始时间>="/>
                </div>
                <div class="col-xs-6 col-md-3">
                    <input value="${endTime!''}" id="endTime" name="endTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd',realDateFmt:'yyyyMMdd '})"
                           placeholder="结束时间<="/>
                </div>
                <div class="col-xs-6 col-md-3">
                    <select name="selectType" id="selectType" class="form-control">
                        <option value="0">请选择</option>
                        <option value="1"<#if selectType??&& selectType==1>selected = "selected"</#if>>问题关联最多前50车型</option>
                        <option value="2"<#if selectType??&& selectType==2>selected = "selected"</#if>>回复数最多前20问题</option>
                        <option value="3"<#if selectType??&& selectType==3>selected = "selected"</#if>>点赞数最多前20回复</option>
                        <option value="4"<#if selectType??&& selectType==4>selected = "selected"</#if>>提问最多前50用户</option>
                        <option value="5"<#if selectType??&& selectType==5>selected = "selected"</#if>>回答最多前50用户</option>
                        <option value="6"<#if selectType??&& selectType==6>selected = "selected"</#if>>问题关联最多前50城市</option>
                        <option value="7"<#if selectType??&& selectType==7>selected = "selected"</#if>>回复关联最多前50城市 </option>

                    </select>
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-xs-6 col-md-1">
                    <button waitingLoad type="submit" class="btn btn-primary">筛选</button>
                </div>
                <div class="col-xs-6 col-md-2 hidden-xs">                
                    <button  type="button" onclick="exportTopExcel()"  class="btn btn-primary">导出表格</button>
                </div> 
            </div>
        </form>
    </div>
<#if selectType??&& selectType==1>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>车系id</th>
                <th>品牌车系名</th>
                <th>对应问题量</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topCarsCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==2>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>回答数量</th>
                <th>问题ID</th>
                <th>提问者昵称</th>
                <th>提问者ID</th>
                <th>问题内容</th>
                <th>跳转链接</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topQuestionCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==3>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>点赞数</th>
                <th>回复ID</th>                
                <th>回复人ID</th>
                <th>回复人昵称</th>
                <th>问题ID</th>
                <th>跳转</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topAnswerCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==4>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>提问数量</th>
                <th>提问者ID</th>
                <th>提问者昵称</th>
                <th>个人主页</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topUserQueCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==5>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>回复数量</th>
                <th>回复人ID</th>
                <th>回复人昵称</th>
                <th>个人主页</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topUserAnsCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==6>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>城市</th>
                <th>省份</th>
                <th>关联问题次数</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topCityQueCom.ftl"/>
            </tbody>
        </table>
    </div>
<#elseif selectType??&& selectType==7>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>排名</th>
                <th>城市</th>
                <th>省份</th>
                <th>关联回复次数</th>
            </tr>
            </thead>
            <tbody>
            <#include "/user/biData/topCityAnsCom.ftl"/>
            </tbody>
        </table>
    </div>
<#else>
</#if>
  
  
</div>


<#include "/user/boot_bottom.ftl"/>
<script src="${jsPath}/date/WdatePicker.js"></script>
</body>
</html>
