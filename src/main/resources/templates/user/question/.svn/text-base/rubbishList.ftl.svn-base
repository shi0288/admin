<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">问答回收站</li>
    </ol>

    <div class="well">
        <form class="table-filter" action="${base}/user/question/rubbishList" method="post">
            <div class="row">
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-xs-3  col-md-3 group-label">  <label>问答ID</label>  </div>
                        <div class="col-xs-9  col-md-9 group-control">  <input type="text" class="form-control" value="${(answeQuestion.questionId)!''}" name="questionId" placeholder="问答ID" />  </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-xs-3  col-md-3 group-label">   <label class="">用户ID</label> </div>
                        <div class="col-xs-9  col-md-9 group-control">  <input type="text" class="form-control" value="${(answeQuestion.uid)!''}" name="uid" placeholder="用户ID" /> </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-xs-3  col-md-3 group-label">   <label class="">用户名</label></div>
                        <div class="col-xs-9  col-md-9 group-control">   <input type="text" class="form-control" value="${nickName!''}" name="nickName" placeholder="用户名" /> </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-xs-3  col-md-3 group-label">     <label class="">关键词</label></div>
                        <div class="col-xs-9  col-md-9 group-control">   <input class="form-control" type="text" value="${(keyWord)!''}" id="keyWord" name="keyWord" placeholder="关键词" /> </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-4  col-md-4 group-label">  <label>问答类型</label>  </div>
                        <div class="col-xs-8  col-md-8 group-control">
                    <select name="answerType" class="form-control">
                        <option
                        <#if answerType==1>
                                selected = "selected"
                        </#if>
                                value="1">问题</option>
                        <option
                        <#if answerType==2>
                                selected = "selected"
                        </#if>
                                value="2">回答</option>
                    </select>
                    </div>
                   </div> 
                </div>
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>问答分类</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select name="categoryId" class="form-control">
                        <option value="">全部</option>
                        <option value="0">其他</option>
                    <#if categoryList??>
                        <#list categoryList as category>
                            <option value="${category.categoryId}"
                                <#if answeQuestion.categoryId==category.categoryId>
                                    selected = "selected"
                                </#if>
                            >${category.name}</option>
                        </#list>
                    </#if>
                    </select>
                   </div>
                  </div>  
                </div>

                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>开始时间</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <input value="${startDate!''}" id="startDate" name="startDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           placeholder="开始时间>="/>
                   </div>
                   </div>
                </div>
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>结束时间</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <input value="${endDate!''}" id="endDate" name="endDate" type="text"
                           class="form-control Wdate" style="height: 34px;"
                           onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss'})"
                           placeholder="结束时间<="/>
                  </div>
                  </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3">
                 <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>发布省份</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select class="form-control province_sel">
                        <option value="">全部</option>
                    <#if (provinceList)??>
                        <#list  provinceList as province>
                            <option value="${(province.id)!''}">${(province.name)!''}</option>
                        </#list>
                    </#if>
                    </select>
                  </div>
                 </div>   
                </div>
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>发布城市</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select name="cityId" class="form-control city_sel" >
                        <option value="">全部</option>
                    </select>
                  </div>
                  </div>  
                </div>
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>来源渠道</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select name="source" id="source" class="form-control">
                        <option value="">全部</option>
                    <#if (sourceMap)??>
                        <#list sourceMap.keySet() as key>
                            <option <#if answeQuestion.source?? && answeQuestion.source==key > selected="selected"</#if> value="${key}">${sourceMap.get(key)}</option>
                        </#list>
                    </#if>
                    </select>
                  </div>
                  </div>  
                </div>
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>自己删除</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select name="deletedSelf" id="deletedSelf" class="form-control">
                        <option value="">全部</option>
                        <option
                        <#if answeQuestion.deletedSelf==1>
                                selected = "selected"
                        </#if>
                                value="1">是</option>
                        <option
                        <#if answeQuestion.deletedSelf==0>
                                selected = "selected"
                        </#if>
                                value="0">否</option>
                        <option
                        <#if answeQuestion.deletedSelf==-1>
                                selected = "selected"
                        </#if>
                                value="-1">系统拦截</option>        
                    </select>
                    </div>
                    </div>
                </div>
            </div>
               <div class="row" >
                <div class="col-md-3">
                  <div class="row">
                        <div class="col-xs-3  col-md-4 group-label">  <label>用户身份</label>  </div>
                        <div class="col-xs-9  col-md-8 group-control">
                    <select name="userType" id="userType" class="form-control">
                        <option value="">全部</option>
                    <#if (userTypeMap)??>
                        <#list userTypeMap.keySet() as key>
                            <option <#if userType?? && userType==key > selected="selected"</#if> value="${key}">${userTypeMap.get(key)}</option>
                        </#list>
                    </#if>
                    </select>
                  </div>
                </div>
            </div>
            <div class="row" style="padding-top: 10px;">
                <div class="col-md-1">
                    <button type="submit" class="btn btn-primary"> 筛选</button>
                </div>
                <div class="col-md-1 hidden-xs">
                    <button type="button"  onclick="exportRubbish()"  class="btn btn-primary"> 导出全部</button>
                </div>
            </div>
        </form>
    </div>
<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"questionId":(answeQuestion.questionId)!,
    "nickName":(nickName),"uid":(answeQuestion.uid)!,"answerType":(answerType)!,
    "categoryId":(answeQuestion.categoryId)!,"userType":(userType)!,
    "keyWord":(keyWord)!,"startDate":(startDate)!,"endDate":(endDate)!,"source":(answeQuestion.source)!
    }
    />
    <@p.pager pager = pager baseUrl = "${base}/user/question/rubbishList" parameterMap = parameterMap/>
</#if>


<#if answerType==1>
    <#include  "/user/question/rubbishCom.ftl" />
<#else>
    <#assign curName="rubbishList" />
    <#include  "/user/answer/answerCom.ftl"  />
</#if>



<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/rubbishList" parameterMap = parameterMap/>
</#if>

</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/answer.js"></script>
<script src="${jsPath}/date/WdatePicker.js"></script>
</body>
</html>
