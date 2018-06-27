<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">数据监控</a></li>
        <li class="active">基础数据</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/biData/basicBi" method="post" id="filter">
            <div class="row">
                <div class="col-xs-6 col-md-3">
                    <input value="${startTime!''}" name="startTime" id="startTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyy-MM-dd '})"
                           placeholder="开始时间>="/>
                </div>
                <div class="col-xs-6 col-md-3">
                    <input value="${endTime!''}" name="endTime" id="endTime" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyy-MM-dd '})"
                           placeholder="结束时间<="/>
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-xs-6 col-md-2">
                    <button waitingLoad type="submit" class="btn btn-primary">筛选</button>
                </div>
                <div class="col-xs-6 col-md-2 hidden-xs">                
                    <button  type="button" onclick="exportQueBi()"  class="btn btn-primary">导出基本数据</button>
                </div>
                <div class="col-xs-6 col-md-2 hidden-xs">                
                    <button  type="button" onclick="exportQueTypeBi()"  class="btn btn-primary">按类型导出数据</button>
                </div>
                <div class="col-xs-6 col-md-2 hidden-xs">                
                    <button  type="button" onclick="exportQueUserBi()"  class="btn btn-primary">导出用户数据</button>
                </div>
                <div class="col-xs-6 col-md-2 hidden-xs">                
                    <button  type="button" onclick="exportUserExpert()"  class="btn btn-primary">导出专家考核数据</button>
                </div>                                 
            </div>
        </form>
    </div>
  
</div>
<#include "/user/biData/basicBiCom.ftl"/>
<#include "/user/boot_bottom.ftl"/>
<script src="${jsPath}/date/WdatePicker.js"></script>
 <script type="text/javascript" rel="stylesheet" src="${jsPath}/biData.js"></script>
</body>
</html>
