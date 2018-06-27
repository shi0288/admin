<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">回复管理</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/answer/answerList" method="post">
            <div class="row">
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>问答ID</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(question.questionId)!''}" name="questionId" placeholder="问答ID" />
                   </div>
                  </div> 
                </div>
                <div class="col-md-3">
                 <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>用户ID</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(answer.uid)!''}" name="uid" placeholder="用户ID" />
                   </div>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>用户名</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(nickName)!''}" name="nickName" placeholder="用户名" />
                   </div>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>关键词</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(keyWord)!''}" name="keyWord" id="keyWord" placeholder="关键词">
                   </div>
                  </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>用户身份</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="userType" class="form-control">
                        <option value="">全部</option>
                        <option value="0"
                        <#if userType??&& userType==0>
                                selected = "selected"
                        </#if>
                        >普通用户</option>
                        <option value="1"
                        <#if userType??&& userType==1>
                                selected = "selected"
                        </#if>
                        >标兵</option>
                        <option value="2"
                        <#if userType??&& userType==2>
                                selected = "selected"
                        </#if>
                        >专家</option>
                        <option value="3"
                        <#if userType??&& userType==3>
                                selected = "selected"
                        </#if>
                        >车顾问</option>
                        <option value="9"
                        <#if userType??&& userType==9>
                                selected = "selected"
                        </#if>
                        >官方</option>
                    </select>
                    </div>
                  </div> 
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>来源渠道</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="source" class="form-control">
                        <option value="">全部</option>
                        <#if (sourceMap)??>
                            <#list sourceMap.keySet() as key>
                                <option <#if source?? && source==key > selected="selected"</#if> value="${key}">${sourceMap.get(key)}</option>
                            </#list>
                        </#if>
                    </select>
                  </div>
                  </div>  
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>开始时间</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input value="${startDate!''}" name="startDate" id="startDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           placeholder="开始时间>="/>
                   </div>
                  </div>        
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>结束时间</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input value="${endDate!''}" name="endDate" id="endDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           placeholder="结束时间<="/>
                   </div>
                   </div>        
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-xs-6 col-md-1">
                   <label class="checkbox-inline" >
                        <input type="checkbox"  id="checked_all" name="checked_all" >全选
                   </label>
                </div>
                <div class="col-md-1">
                    <button waitingLoad type="submit" class="btn btn-primary">筛选</button>
                </div>
                <div class="col-md-1 hidden-xs">
                    <button type="button"  onclick="exportAnswerExcel()"  class="btn btn-primary"> 导出全部</button>
                </div>
                <div class="col-xs-6 col-md-1">
                    <button type="button"  onclick="deleteAns()"  class="btn btn-primary"> 批量删除</button>
                </div>
            </div>
        </form>
    </div>
<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"questionId":(question.questionId)!,
    "nickName":(nickName),"uid":(answer.uid)!,"userType":(userType)!,
    "source":(source),"keyWord":(keyWord)!,"startDate":(startDate)!,
    "endDate":(endDate)!
    }
    />
    <@p.pager pager = pager baseUrl = "${base}/user/answer/answerList" parameterMap = parameterMap/>
</#if>

<#include  "/user/answer/answerCom.ftl"  />

<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/answer/answerList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/answer.js"></script>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script src="${jsPath}/date/WdatePicker.js"></script>
</body>
</html>
