<#include "/user/boot_top.ftl"/>
<div class="panel-body">
    <ol class="breadcrumb">
        <li><a href="##">内容管理</a></li>
        <li class="active">待审核问题</li>
    </ol>
    <div class="well">
        <form class="table-filter" action="${base}/user/question/verifyList" method="post">
            <div class="row">
                
                <div class="col-md-3">
                 <div class="row"> 
                  <div class="col-xs-4  col-md-4 group-label"><label>问答ID</label></div>
                  <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(answeQuestion.questionId)!''}" name="questionId" placeholder="问答ID" />
                  </div>
                 </div>
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>用户ID</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${(answeQuestion.uid)!''}" name="uid" placeholder="用户ID" />
                   </div>
                 </div>
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>用户名</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input type="text" class="form-control" value="${nickName!''}" name="nickName" placeholder="用户名" />
                   </div>
                  </div>               
                </div>
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>关键词</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input class="form-control" type="text" value="${(keyWord)!''}" name="keyWord" placeholder="关键词" />
                   </div>
                  </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>问答类型</label></div>
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
                   <div class="col-xs-4  col-md-4 group-label"><label>问题分类</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
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
                   <div class="col-xs-4  col-md-4 group-label"><label>开始时间</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <input value="${startDate!''}" name="startDate" type="text"
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
                    <input value="${endDate!''}" name="endDate" type="text"
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
                   <div class="col-xs-4  col-md-4 group-label"><label>问答状态</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="status" class="form-control" >
                        <option
                        <#if answeQuestion.status==-1>
                                selected = "selected"
                        </#if>
                                value="-1">待审核</option>
                        <option
                        <#if answeQuestion.status==-2>
                                selected = "selected"
                        </#if>
                                value="-2">黑名单
                        </option>
                        <option
                        <#if answeQuestion.status==-98>
                                selected = "selected"
                        </#if>
                                value="-98">待发布
                        </option>
                    </select>
                   </div>
                   </div>  
                </div>

                <div class="col-md-3">
                  <div class="row"> 
                   <div class="col-xs-4  col-md-4 group-label"><label>发布省份</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
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
                   <div class="col-xs-4  col-md-4 group-label"><label>发布城市</label></div>
                   <div class="col-xs-8  col-md-8 group-control">
                    <select name="cityId" class="form-control city_sel" >
                        <option value="">发布城市</option>
                    </select>
                   </div>
                 </div>  
                </div>
            </div>

            <div class="row" style="padding-top: 10px;">
                <div class="col-xs-6 col-md-1 ">
                   <label class="checkbox-inline " >
                        <input type="checkbox"  id="checked_all" name="checked_all" >全选
                   </label>
                </div>
                <div class="col-xs-6  col-md-1">
                    <button type="submit" class="btn btn-primary"> 筛选</button>
                </div>
                <div class="col-xs-6 col-md-1">
                    <button type="button"  onclick="deleteQue()"  class="btn btn-primary"> 批量删除</button>
                </div>
                <#if (answeQuestion.status==-1) || (answeQuestion.status==-2)>
                    <div class="col-xs-2 col-md-1">
                       <button type="button"  onclick="verifyQue()"  class="btn btn-primary"> 批量审核</button>
                    </div>
                </#if>
                
            </div>
        </form>
    </div>
<#if  (pager.list)??>
    <#import "/user/boot_pager.ftl" as p>
    <#assign parameterMap = {"questionId":(answeQuestion.questionId)!,
    "nickName":(nickName),"uid":(answeQuestion.uid)!,"answerType":(answerType)!,
    "categoryId":(answeQuestion.categoryId)!,"status":(answeQuestion.status),
    "keyWord":(keyWord)!,"startDate":(startDate)!,"endDate":(endDate)!
    }
    />
    <@p.pager pager = pager baseUrl = "${base}/user/question/verifyList" parameterMap = parameterMap/>
</#if>

<#if answerType==1>
    <#include  "/user/question/verifyCom.ftl"  />
<#else>
    <#assign curName="verifyList" />
    <#include  "/user/answer/answerCom.ftl"  />
</#if>


<#if  (pager.list)??>
    <@p.pager pager = pager baseUrl = "${base}/user/question/verifyList" parameterMap = parameterMap/>
</#if>
       <div class="col-xs-6 col-md-1">
            <button type="button"  onclick="deleteQue()"  class="btn btn-primary"> 批量删除</button>
       </div>
       <#if (answeQuestion.status==-1) || (answeQuestion.status==-2)>
            <div class="col-xs-2 col-md-1">
                 <button type="button"  onclick="verifyQue()"  class="btn btn-primary"> 批量审核</button>
            </div>
       </#if>
</div>
<#include "/user/boot_bottom.ftl"/>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/question.js"></script>
<script type="text/javascript" rel="stylesheet" src="${jsPath}/answer.js"></script>
<script type="text/javascript" src="${jsPath}/date/WdatePicker.js"></script>

</body>
</html>
