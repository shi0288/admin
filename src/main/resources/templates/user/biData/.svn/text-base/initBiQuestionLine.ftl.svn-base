<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">数据监控</a></li>
        <li class="active">运营曲线图</li>
    </ol>
    <div class="row">
                <div class="col-md-3">
                    <input value="${startDate!''}" name="startDate" id="startDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd ',realDateFmt:'yyyyMMdd '})"
                           placeholder="开始时间>="/>
                </div>
                <div class="col-md-3">
                    <input value="${endDate!''}" name="endDate" id="endDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyyMMdd ',realDateFmt:'yyyyMMdd '})"
                           placeholder="结束时间<="/>
                </div>
                <div class="col-md-3">
                    <select name="source" id="source" class="form-control">
                        <option value="1">问题</option>
                        <option value="2">回答</option>
                        <option value="3">百度</option>
                        <option value="5">平均时间</option>
                        <option value="6">活跃度</option>
                    </select>
                </div>
                
    </div>
    <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button waitingLoad type="button"    class="btn btn-selectBiCharts">筛选</button>
                </div>
    </div>            
    <p/>
    <div class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-body" style="height:600px;width:1000px;" id="charts_question">
                </div>
            </div>
    </div>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>
<script src="${jsPath}/echarts.min.js"></script>
<script src="${jsPath}/shine.js"></script>
<script src="${jsPath}/charts/questionBiLine.js"></script>
</body>
</html>
